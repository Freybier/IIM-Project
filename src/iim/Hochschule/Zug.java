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

    private final String name;//Name of the Zug
    private final List<LV> lvList = new ArrayList<>();//LV object list of all LVs the Zug has to visit 

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


    @Override
    public String toString() {
        return name;

    }
}
