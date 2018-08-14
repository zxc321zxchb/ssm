package com.itheima.springmvc.exception;

/**
 * 1. 首先要自定义异常类 CustomerException.java
 *     区别不同的异常通常根据异常类型自定义异常类，
 *     这里我们创建一个自定义系统异常，
 *     如果controller、service、dao抛出此类异常说明是
 *          系统预期处理的异常信息
 */
public class CustomerException extends Exception{

    private String expMessage;


    public CustomerException(){

    }
    public CustomerException(String msg){
        this.expMessage = msg;
    }



    public String getExpMessage() {
        return expMessage;
    }

    public void setExpMessage(String expMessage) {
        this.expMessage = expMessage;
    }
}
