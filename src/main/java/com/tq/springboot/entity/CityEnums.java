package com.tq.springboot.entity;

/**
 * @Auther: tq
 * @Date: 2022/4/19
 * @Description
 * @Version: 1.0
 */
public enum CityEnums {
    HANGZHOU("571","360"),
    HUZHOU("572","362");

    private String source;
    private String target;

    CityEnums(String source, String target){
        this.source=source;
        this.target=target;
    }

    /**
     * 根据源地市编码获取目标地市编码
     * @param source
     * @return
     */
    public static String getOrgId(String source){
        CityEnums[] values = values();
        for (CityEnums value : values) {
            if(value.getSource().equals(source)){
                return value.getTarget();
            }
        }
        return null;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
}
