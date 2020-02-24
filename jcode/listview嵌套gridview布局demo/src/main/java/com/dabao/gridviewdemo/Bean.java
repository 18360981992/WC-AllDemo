package com.dabao.gridviewdemo;

import java.util.List;

/**
 * Created by zzt on 2018/10/30.
 */

public class Bean {

    public String title;
    public List<Item_Bean> list;

    public String getTitle() {
        return title;
    }

    public List<Item_Bean> getList() {
        return list;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setList(List<Item_Bean> list) {
        this.list = list;
    }

    public static class Item_Bean{
        int img;
        String name;

        public void setImg(int img) {
            this.img = img;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getImg() {

            return img;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Item_Bean{" +
                    "img=" + img +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Bean{" +
                "title='" + title + '\'' +
                ", list=" + list +
                '}';
    }
}
