
package edu.ssu.slotsgame.ui;


public class ReelSymbol {
    private String symbol_;
    
    public ReelSymbol(String symbol){
        symbol_ = symbol;
    }
    
    public String getSymbol(){
        return symbol_;
    }
    
    public void setSymbol(String newSymbol){
        symbol_ = newSymbol;
    }
    
}
