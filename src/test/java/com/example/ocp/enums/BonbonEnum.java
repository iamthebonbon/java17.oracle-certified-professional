package com.example.ocp.enums;

public enum BonbonEnum {

    ONE(1),
    TWO(2),
    THREE(3);

    private final int state;

    BonbonEnum(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

}
