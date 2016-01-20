//Garret Stevens
package edu.ssu;

import java.util.*;

public class Util {
    public static ArrayList getEven(ArrayList input){
        ArrayList output = new ArrayList();
        int cmp; //compare number
        for (Object input1 : input) {
            cmp = (Integer) input1 & 1;
            if(cmp == 0) output.add(input1);
        }
        return output;
    }
    
    public static ArrayList getOdd(ArrayList input){
        ArrayList output = new ArrayList();
        int cmp; //compare number
        for (Object input1 : input) {
            cmp = (Integer) input1 & 1;
            if(cmp != 0) output.add(input1);
        }
        return output;
    }
}
