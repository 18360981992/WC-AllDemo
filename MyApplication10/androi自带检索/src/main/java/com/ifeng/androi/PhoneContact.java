package com.ifeng.androi;

/**
 * Created by zzt on 2018/9/6.
 */

public  class PhoneContact {

    int i;
    String name;
    String s;
    String concat;

    public PhoneContact(int i, String name, String s, String concat) {
        this.i = i;
        this.name = name;
        this.s = s;
        this.concat = concat;
    }

    public int getI() {
        return i;
    }

    public String getName() {
        return name;
    }

    public String getS() {
        return s;
    }

    public String getConcat() {
        return concat;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setS(String s) {
        this.s = s;
    }

    public void setConcat(String concat) {
        this.concat = concat;
    }
}
