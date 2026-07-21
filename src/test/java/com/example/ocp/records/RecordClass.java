package com.example.ocp.records;

public record RecordClass(int id, String name) implements RecordClassInterface {

    @Override
    public String name() {
        return this.name + " is overridden";
    }

}
