package com.example.project;

/**
 * @Author : wecashtester
 * @CreateTime : 2019/6/26  上午12:44
 * @ContentUse ：
 */
public class Book {

    private String title;

    public Book(String title) {
        this.title = title;
    }


    /**
     *  1、factory method: a non-private, static method declared in the target type that accepts a single String argument and returns an instance of the target type. The name of the method can be arbitrary and need not follow any particular convention.
     *
     *  2、factory constructor: a non-private constructor in the target type that accepts a single String argument. Note that the target type must be declared as either a top-level class or as a static nested class.
     */
    public static Book fromTitle(String title){
        return new Book(title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
