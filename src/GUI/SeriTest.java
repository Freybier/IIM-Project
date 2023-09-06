/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.io.*;
/**
 *
 * @author altai
 */
public class SeriTest {
    public static void main(String[] args) {
        try {
            // Objekte erstellen
            MyClass obj1 = new MyClass("Objekt 1");
            MyClass obj2 = new MyClass("Objekt 2");

            // Objekte in Datei speichern
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("objects.ser"));
            out.writeObject(obj1);
            out.writeObject(obj2);
            out.close();

            // Objekte aus Datei wiederherstellen
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("objects.ser"));
            MyClass restoredObj1 = (MyClass) in.readObject();
            MyClass restoredObj2 = (MyClass) in.readObject();
            in.close();

            // Verwendung der wiederhergestellten Objekte
            System.out.println("Restored Object 1: " + restoredObj1);
            System.out.println("Restored Object 2: " + restoredObj2);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class MyClass implements Serializable {
    private String name;

    public MyClass(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyClass{" +
               "name='" + name + '\'' +
               '}';
    }
}


