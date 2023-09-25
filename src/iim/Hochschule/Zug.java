/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iim.Hochschule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yann Leymann
 */
public class Zug implements Serializable {

    private final String name;
    private long scheduledZug = 0;
    private final List<LV> lvList = new ArrayList<>();

    public Zug(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<LV> getLV() {
        return lvList;
    }

    public void addLV(LV lv) {
        this.lvList.add(lv);
    }

    public long getScheduledZug() {
        return scheduledZug;
    }

    public void setScheduledZug(long scheduled) {
        this.scheduledZug = scheduled;
    }



    @Override
    public String toString() {
        return name;

    }
}
