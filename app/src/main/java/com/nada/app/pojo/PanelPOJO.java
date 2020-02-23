package com.nada.app.pojo;

public class PanelPOJO {
    String datetime;
    String name;
    String orders;
    String cases;

    public PanelPOJO(String datetime, String name, String orders, String cases) {
        this.datetime = datetime;
        this.name = name;
        this.orders = orders;
        this.cases = cases;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }
}
