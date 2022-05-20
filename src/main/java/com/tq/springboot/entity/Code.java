package com.tq.springboot.entity;

import java.io.Serializable;
import java.util.List;

public class Code implements Serializable {

    private String code;
    private String name;
    private List<Chirldren> chirldren;

    public  Code(){

    }
    public Code(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public List<Chirldren> getChirldren() {
        return chirldren;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChirldrens(List<Chirldren> chirldren) {
        this.chirldren = chirldren;
    }

    @Override
    public String toString() {
        return "Code{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
