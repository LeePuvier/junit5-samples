package com.example.project;

/**
 * @Author : wecashtester
 * @CreateTime : 2019/7/2  下午10:41
 * @ContentUse ：
 */
public enum Gender {

    F("女", 0), M("男", 1);

    private String content;

    private int type;

    Gender(String content, int type) {
        this.content = content;
        this.type = type;
    }

    @Override
    public String toString() {
        return this.content + "-对应的类型值为-" + this.type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static String contentOf(int type){
        for (Gender info : Gender.values())
            if(info.type == type){
                return info.content;
            }
        return null;
    }


    public static int typeOf(String content){
        for (Gender info : Gender.values())
            if(info.content.equals(content)){
                return info.type;
            }
        return -1;
    }
}
