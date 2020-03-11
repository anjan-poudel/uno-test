package com.uno.homeloans.web.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;


@DynamoDBTable(tableName = "uno.datetDiff")
public class CalculationResult {

    private String id;

    private long value;

    @DynamoDBHashKey(attributeName = "key")
    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    @DynamoDBAttribute
    public long getValue() {
        return value;
    }

    public void setValue(final long value) {
        this.value = value;
    }
}
