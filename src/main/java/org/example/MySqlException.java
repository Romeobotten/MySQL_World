package org.example;

public class MySqlException extends Exception{
    private String msg;

    public MySqlException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}