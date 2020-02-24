package com.ifeng.demo;

/**
 * date:2017/8/15
 * author:易宸锋(dell)
 * function:
 */

public class HaoHan implements Comparable<HaoHan>{
    private String name;
    private String pinyin;

    public HaoHan(String name) {
        this.name = name.trim();
        //使用工具类,根据汉字拿到拼音
        this.pinyin = PinyinUtil.getPinyin(name.trim());
    }

    public String getName() {
        return name;
    }

    public String getPinyin() {
        return pinyin;
    }

    @Override
    public String toString() {
        return "HaoHan{" +
                "name='" + name + '\'' +
                ", pinyin='" + pinyin + '\'' +
                '}';
    }

    //主要为了排序,按照拼音排序
    @Override
    public int compareTo(HaoHan haoHan) {
        return this.pinyin.compareTo(haoHan.pinyin);
    }
}
