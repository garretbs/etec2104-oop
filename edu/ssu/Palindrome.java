//Garret Stevens
package edu.ssu;

public class Palindrome {
    private String text;
    
    public Palindrome(String palInput){
        setText(palInput);
    }
    
    private boolean validateText(String input){
        String[] inputList = input.toUpperCase().split("");
        String letterOne;String letterTwo;
        for(int i=0;i<inputList.length/2;i++){
            letterOne = inputList[i];
            letterTwo = inputList[inputList.length - 1 - i];
            if(!letterOne.equals(letterTwo)) return false;
        }
        return true;
    }
    
    public String getText(){
        return text;
    }
    
    public void setText(String newText){
        text = validateText(newText) ? newText : null;
    }
}
