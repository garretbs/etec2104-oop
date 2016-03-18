
package edu.ssu.slotsgame.logic;

import edu.ssu.slotsgame.ui.ReelPanel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class PayTable {
    private final String payTable_ = "resources/paytable.txt";
    private final String symbolOccurrences_ = "resources/symbol_occurrences.txt";
    private final static PayTable instance_ = new PayTable();
    private HashMap symbolAmounts_ = new HashMap<>();
    
    public static PayTable getInstance(){
        return instance_;
    }
    
    public HashMap<String, Integer> getSymbolAmounts(){
        return symbolAmounts_;
    }
    
    public void getSymbols(){
        String currentLine;
        String[] pair;

        File fn = new File(symbolOccurrences_);
        FileInputStream fileInput;
        
        try{
            fileInput = new FileInputStream(fn);
        }catch (FileNotFoundException ex){
            return;
        }

        Scanner inputScan = new Scanner(fileInput);
        while(inputScan.hasNextLine()){
            currentLine = inputScan.nextLine();
            pair = currentLine.split(",");
            symbolAmounts_.put(pair[0], Integer.parseInt(pair[1]));
        }
    }
    
    public int multiplier(){
        String currentLine;
        ArrayList combination;
        String[] symbols;

        File fn = new File(payTable_);
        FileInputStream fileInput;
        
        try{
            fileInput = new FileInputStream(fn);
        }catch (FileNotFoundException ex){
            return 0;
        }

        Scanner inputScan = new Scanner(fileInput);
        while(inputScan.hasNextLine()){
            combination = new ArrayList();
            currentLine = inputScan.nextLine();
            symbols = currentLine.split(",");
            for(int i=0;i<symbols.length-1;i++){
                combination.add(symbols[i]);
            }
            if(ReelPanel.getInstance().reelController_.results_.equals(combination)){
                return Integer.parseInt(symbols[3]);
            }
        }
        return 0;
    }
}
