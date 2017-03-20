package com.epam.java.se.unit07.rearchitecturing;

/**
 * Created by Yegor on 20.03.2017.
 */
public enum OperationType {
    FROM("from"),
    TO("to");

    private String operationType;

    OperationType(String operationType){
        this.operationType = operationType;
    }

    public String getOperationType() {
        return operationType;
    }
}
