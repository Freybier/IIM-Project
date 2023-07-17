/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iim;

/**
 *
 * @author Yann Leymann
 */
public class Professor {
    private String name;
    private String zeiten;

    public Professor(String name, String zeiten) {
        this.name = name;
        this.zeiten = zeiten;
    }

    public String getName() {
        return name;
    }

    public String getZeiten() {
        return zeiten;
    }
    
    
public void wochenZeiten() {
        System.out.println(this.getName() + " | 1. block | 2. block | 3. block | 4. block | 5. block | 6. block");

        String[] tage = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"};
        String[] blocks = {"1. block", "2. block", "3. block", "4. block", "5. block", "6. block"};
        int countTage = 0;
        
        for (int i = 0; i < this.getZeiten().length(); i++) {
            char zeit = this.getZeiten().charAt(i);
            String status;
            if(i%6 == 0)
            {
                System.out.print(tage[countTage] + " | ");
                countTage++;
            }
            
            if (zeit == ' ') {
                
                    status = "verfügbar";
                
            } else if (zeit == 'X') {
                status = "kann nicht";
            } else if (zeit == 'x') {
                status = "will nicht";
            } else {
                status = "";
            }

            System.out.print(status + " | ");

            if (i % 6 == 5) {
                System.out.println();
                if (i < this.getZeiten().length() - 1) {
                    //System.out.print("         | ");
                }
            }
            
        }
        System.out.println();
    }
}