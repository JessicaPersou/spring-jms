package com.persou.springjms.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private BigDecimal value;

    public Order() {
    }

    public Order(String id, String name, BigDecimal value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
