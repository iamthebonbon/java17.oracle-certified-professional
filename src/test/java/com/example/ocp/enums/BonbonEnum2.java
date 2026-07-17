package com.example.ocp.enums;

public enum BonbonEnum2 {

    ONE(1),
    TWO(2),
    THREE(3);

    private final int state;

    BonbonEnum2(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

}
