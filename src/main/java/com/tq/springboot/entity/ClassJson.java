package com.tq.springboot.entity;

import java.io.Serializable;


public class ClassJson implements Serializable {

    private String code;
    private String text;

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ClassJson{" +
                "code='" + code + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
