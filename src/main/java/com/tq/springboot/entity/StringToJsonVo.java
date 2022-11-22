package com.tq.springboot.entity;

/**
 * @Auther: tq
 * @Date: 2022/6/6
 * @Description
 * @Version: 1.0
 */
public class StringToJsonVo {
    private String id;
    private String text;

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
