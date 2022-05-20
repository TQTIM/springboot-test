package com.tq.springboot.entity;

import java.io.Serializable;
import java.util.List;

public class Chirldren implements Serializable {

    private List<Chirldren> chirldren;
    private String code;
    private String name;


    public void setCode(String code) {
        this.code = code;
    }

    public void setChirldrenList(List<Chirldren> chirldren) {
        this.chirldren = chirldren;
    }

    public List<Chirldren> getChirldren() {
        return chirldren;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
