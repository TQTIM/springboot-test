package com.tq.springboot;

import com.tq.springboot.juc.Student;

public class Undergraduate extends Student {
    private String major;

    public Undergraduate(String id, String name, String major) {
        super(id, name);
        this.major = major;
    }

    public String getMajor() { return major; }

    @Override
    public String toString() {
        return "Undergraduate[" + super.toString() + ", major=" + major + "]";
    }
}
