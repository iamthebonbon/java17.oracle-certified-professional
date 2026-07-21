package com.example.ocp.records;

public record RecordClass(int id, String name) implements RecordClassInterface {
    static int instanceCount = 0;

    public RecordClass {
        System.out.println(instanceCount);
    }

    @Override
    public String name() {
        return this.name + " is overridden";
    }

}
