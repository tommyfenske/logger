package com.tommy.loggerapp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Log {
    private final LocalDate date;
    private List<Field> fields;

    public Log() {
        this.date = LocalDate.now();
        this.fields =  new ArrayList<>();
        fields.add(new Field());
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public void addField(Field field) {
        fields.add(field);
    }

    @Override
    public String toString() {
        return "Log [date=" + date + ", fields=\n" + fields.toString() + "\n]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Log) {
            return ((Log) obj).date.equals(this.date);
        }
        return false;
    }
}
