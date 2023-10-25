package com.lodgereservation.model.domain;

import java.sql.Date;
import java.util.ArrayList;

public class ResComposite {

    private ArrayList<Date> updates;

    public ResComposite() {
        updates = new ArrayList<Date>();
    }

    public void addUpdate(Date date) {
        System.out.println("update added from ResComposite.addUpdate(Date)");
        updates.add(date);
    }
}
