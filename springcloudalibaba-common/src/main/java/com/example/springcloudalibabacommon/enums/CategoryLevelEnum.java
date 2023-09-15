package com.example.springcloudalibabacommon.enums;

/**
 * @program: SpringCloudAlibabaDemo
 * @description: category level
 * @author: Mr.markWang 2764
 * @create: 2023-09-11 18:52
 **/
public enum CategoryLevelEnum {
    DEFAULT(0, "ERROR"),
    LEVEL_ONE(1, "一级分类"),
    LEVEL_TWO(2, "二级分类"),
    LEVEL_THREE(3, "三级分类");

    private int level;
    private String name;

    CategoryLevelEnum(int level, String name){
        this.level = level;
        this.name = name;
    }

    public static CategoryLevelEnum getCategoryLevel(int level){
        for (CategoryLevelEnum categoryLevel :
                CategoryLevelEnum.values()) {
            if (categoryLevel.getLevel() == level) {
                return categoryLevel;
            }
        }
        return DEFAULT;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
