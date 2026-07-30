module com.ocp.greetings {
    exports com.ocp.greetings;
    provides com.ocp.greetings.I
            with com.ocp.greetings.AI;
}
