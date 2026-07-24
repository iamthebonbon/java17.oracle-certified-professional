# JPMS practice: two modules

This folder is a standalone Java Platform Module System (JPMS) sandbox,
isolated from the Maven project at the repo root. It's plain `javac`/`java`
on the command line — no Maven, no IDE run configs.

Two modules:

- `com.ocp.greetings` — declares `exports com.ocp.greetings;`, making its
  `GreetingService` class visible to any module that `requires` it.
- `com.ocp.app` — declares `requires com.ocp.greetings;` and calls
  `GreetingService` from its `Main` class.

Without `exports` on the greetings side, or `requires` on the app side,
compilation fails — that's the whole point of the module system: strong
encapsulation between modules instead of everything being on one big classpath.

## Compile

Run from the repo root:

```bash
javac -d modules/mods --module-source-path modules $(find modules -name "*.java")
```

This compiles both modules in one pass (`--module-source-path` tells javac
where to look for module directories by name) and writes each module's
classes to `modules/mods/<module-name>/`.

## Run

```bash
java --module-path modules/mods -m com.ocp.app/com.ocp.app.Main
```

`--module-path mods` is the module-system equivalent of `-cp`, and
`-m com.ocp.app/com.ocp.app.Main` says "launch this module's `Main` class".

Expected output:

```
Hello, OCP candidate! This greeting came from the com.ocp.greetings module.
```

## Things to try next

- Remove `exports com.ocp.greetings;` from `com.ocp.greetings/module-info.java`,
  recompile, and see the `package is not visible` error.
- Remove `requires com.ocp.greetings;` from `com.ocp.app/module-info.java`
  and see the `package ... is not visible` / cannot find symbol error instead.
- Try `exports com.ocp.greetings to com.ocp.app;` (qualified export) so only
  `com.ocp.app` can see the package, not any arbitrary module.

`mods/` is gitignored (see `modules/.gitignore`) since it's just compiled output.
