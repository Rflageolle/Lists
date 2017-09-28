/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classprojects;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author FlageMac
 */

public class Border {
    String[] bordersArray;
    // LinkedList bordersLL = new LinkedList();
    
    public static String[] populateLists(String fileName) {
        String[] hold = new String[10];
        String[] rtn = null;
        
        try {
            int count = 0;
                        
            File contain = new File(fileName);
            
            Scanner counter = new Scanner(contain);
            counter.useDelimiter(", ");
            
            while (counter.hasNext()){
                hold[count] = counter.next();
                count++;
            }
            
            rtn = new String[count];
            int x = 0;
            
            for (String o : hold) {
                if (o != null){
                    rtn[x] = o;
                    x++;
                }
            }
            
//            for (String in : rtn){
//                System.out.println(in);
//            }
            
        } catch (IOException e) {	
            System.out.println("File not Found");
        }
        
        return rtn;
        
    }

    public Border(String fileName){
        this.bordersArray = populateLists(fileName);
    }
    
    public static boolean doesContain(Border one, Border two){
        Boolean does = false;
        
        for (String x : one.bordersArray) {
            for (String y : two.bordersArray) {
                if (y.equals(x)){
                    does = true;
                }
            }
        }

        return does;
        
    }
        //"/Users/FlageMac/Desktop/germanyBorders.txt"
	// add an array for borders to the individual countries.
	// or just add a reference to the file in the Countries file and add that to the constructor
}
    

