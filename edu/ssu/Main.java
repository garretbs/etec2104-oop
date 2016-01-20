//Garret Stevens
package edu.ssu;

import java.util.Scanner;


public class Main {
    public static void main(String[] args){
       System.out.println("Please input a palindrome:");
       Scanner scanInput = new Scanner(System.in);
       String stringInput = scanInput.nextLine();
       Palindrome palinExample = new Palindrome(stringInput);
       System.out.println(palinExample.getText());
    }
    
}
