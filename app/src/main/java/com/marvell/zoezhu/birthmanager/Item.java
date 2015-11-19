package com.marvell.zoezhu.birthmanager;

import java.io.Serializable;

/**
 * Created by zoezhu on 2015/11/4.
 */
public class Item implements Serializable {
    int id;
    String name;
    int birthyear;
    int birthmonth;
    int birthday;

    public int getId() {return  id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(int birthyear) {
        this.birthyear = birthyear;
    }

    public int getBirthmonth() {
        return birthmonth;
    }

    public void setBirthmonth(int birthmonth) {
        this.birthmonth = birthmonth;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public String getBirth()
    {
        return birthyear + "-" + birthmonth + "-" + birthday;
    }

    public Item(int id, String name, int year, int month, int day) {
        this.id = id;
        this.name = name;
        this.birthday = day;
        this.birthmonth = month;
        this.birthyear = year;
    }
}


