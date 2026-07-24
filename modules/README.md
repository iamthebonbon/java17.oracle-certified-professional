# JPMS practice: named modules + an unnamed-module (classpath) mix

This folder is a standalone Java Platform Module System (JPMS) sandbox,
isolated from the Maven project at the repo root. It's plain `javac`/`java`
on the command line — no Maven, no IDE run configs.

Two **named** modules:

- `com.ocp.greetings` — declares `exports com.ocp.greetings;`, making its
  `GreetingService` class visible to any module that `requires` it.
- `com.ocp.app` — declares `requires com.ocp.greetings;` and calls
  `GreetingService` from its `Main` class.

Without `exports` on the greetings side, or `requires` on the app side,
compilation fails — that's the whole point of the module system: strong
encapsulation between modules instead of everything being on one big classpath.

Plus one **unnamed-module** class, `legacy/com/ocp/legacy/LegacyReportPrinter.java`
— deliberately has no `module-info.java`, representing legacy classpath code
sitting alongside a modularized app. See "Mixed run" below.

## Compile the named modules

Run from the repo root. The command lists the two module directories explicitly
(not a blanket `find modules -name "*.java"`) because `legacy/` below has no
`module-info.java` and would break a `--module-source-path` compile if included:

```bash
javac -d modules/mods --module-source-path modules \
  modules/com.ocp.greetings/module-info.java modules/com.ocp.greetings/com/ocp/greetings/*.java \
  modules/com.ocp.app/module-info.java modules/com.ocp.app/com/ocp/app/*.java
```

This compiles both modules in one pass (`--module-source-path` tells javac
where to look for module directories by name) and writes each module's
classes to `modules/mods/<module-name>/`.

## Run (named modules only)

```bash
java --module-path modules/mods -m com.ocp.app/com.ocp.app.Main
```

`--module-path modules/mods` is the module-system equivalent of `-cp`, and
`-m com.ocp.app/com.ocp.app.Main` says "launch this module's `Main` class".

Expected output:

```
Hello, OCP candidate! This greeting came from the com.ocp.greetings module.
```

## Mixed run: unnamed module (classpath) calling into a named module

First compile the legacy class against the already-compiled `com.ocp.greetings`
module, using an ordinary `-cp` (not `--module-path`) — so it compiles as
classpath code with no module of its own:

```bash
javac -d modules/legacy-classes -cp modules/mods/com.ocp.greetings \
  modules/legacy/com/ocp/legacy/LegacyReportPrinter.java
```

Now run it, combining `--module-path` (for the named module) with `-cp`
(for the unnamed-module class) in the same launch:

```bash
java --module-path modules/mods --add-modules com.ocp.greetings \
  -cp modules/legacy-classes com.ocp.legacy.LegacyReportPrinter
```

Expected output:

```
[legacy, unnamed module] Hello, legacy caller! This greeting came from the com.ocp.greetings module.
LegacyReportPrinter's module: unnamed module @...
GreetingService's module:     module com.ocp.greetings
```

Two things this proves, both real OCP points:

1. **`--add-modules com.ocp.greetings` is required.** Drop it and you get a
   `NoClassDefFoundError` at runtime. When the launch's initial module is the
   *unnamed* module (a classpath `Main`, no `-m`), named modules on
   `--module-path` are **not** auto-resolved — the unnamed module reads
   everything only among modules that actually got resolved into the module
   graph, and by default that's just `java.se` and friends. `--add-modules`
   is what pulls `com.ocp.greetings` into the graph so the unnamed module can
   read it.
2. **It only works one direction.** The unnamed module can freely read any
   named module once it's resolved — but a *named* module can never read the
   unnamed module back, because you can't `requires` something that has no
   name. Try making `com.ocp.app`'s `Main` import
   `com.ocp.legacy.LegacyReportPrinter` and recompiling with both
   `--module-path modules/mods` and `-cp modules/legacy-classes` — it fails
   with `package com.ocp.legacy is not visible ... module com.ocp.app does
   not read it`. Modularizing is a one-way ratchet: named code can't casually
   reach back into the classpath.

## Things to try next

- Remove `exports com.ocp.greetings;` from `com.ocp.greetings/module-info.java`,
  recompile, and see the `package is not visible` error.
- Remove `requires com.ocp.greetings;` from `com.ocp.app/module-info.java`
  and see the `package ... is not visible` / cannot find symbol error instead.
- Try `exports com.ocp.greetings to com.ocp.app;` (qualified export) so only
  `com.ocp.app` can see the package, not any arbitrary module.
- Try the reverse mixed-run experiment described above (named module trying
  to read the unnamed module) and see it fail to compile.
- Automatic modules: jar up `com.ocp.greetings`'s classes with **no**
  `module-info.class` (`jar --create --file greetings.jar -C modules/mods/com.ocp.greetings .`)
  and put the jar on `--module-path` instead of the exploded directory. It
  becomes an *automatic module* named `com.ocp.greetings` (derived from the
  jar name), readable by name from `com.ocp.app` just like before, and — unlike
  a real named module — it can read every other module *and* the classpath.

`modules/mods/` and `modules/legacy-classes/` are gitignored (see
`modules/.gitignore`) since they're just compiled output.
