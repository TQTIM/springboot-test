package com.tq.springboot.juc;

public class Student {
    private String id;
    private String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter方法
    public String getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Student[id=" + id + ", name=" + name + "]";
    }
}
