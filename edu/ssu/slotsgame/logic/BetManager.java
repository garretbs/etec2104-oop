package edu.ssu.slotsgame.logic;

import edu.ssu.slotsgame.config.GameParameters;

public class BetManager{
    private int currentBet_;

    public void increaseBet(){
        currentBet_++;
    }

    public void decreaseBet(){
        currentBet_--;
    }
    
    public BetManager(){
    }
    
    public static void main(String[] args){
        BetManager betManager = new BetManager();
        //Test 1: increaseBet increases currentBet_ by one
        //assume MIN_BET < MAX_BET
        betManager.currentBet_ = GameParameters.MIN_BET;
        betManager.increaseBet();
        
        String failStr = "Test 1 (increaseBet): FAILED " + "(currentBet_ was " +
                betManager.currentBet_ + ", should have been " +
                (GameParameters.MIN_BET + 1) + ")";
        assert (betManager.currentBet_ == GameParameters.MIN_BET + 1) : failStr;
        
        String passStr = "Test 1 (increaseBet): PASSED " +
            "(currentBet_ was increased by 1)";
        System.out.println(passStr);
        
        //Test 2: increaseBet will not increase currentBet_ if it’s already the
        //maximum allowed
        //assume MIN_BET < MAX_BET
        failStr = "Test 2 (increaseBet): FAILED " + "(currentBet_ was " +
                betManager.currentBet_ + ", cannot be greater than " +
                GameParameters.MAX_BET + ")";
        assert (betManager.currentBet_ <= GameParameters.MAX_BET) : failStr;
        
        passStr = "Test 2 (increaseBet): PASSED " +
            "(currentBet_ not greater than " + GameParameters.MAX_BET + ")";
        System.out.println(passStr);
        
        //Test 3: increaseBet decreases currentBet_ by one
        //assume MIN_BET < MAX_BET
        betManager.decreaseBet();
        
        failStr = "Test 3 (decreaseBet): FAILED " + "(currentBet_ was " +
                betManager.currentBet_ + ", should have been " +
                (GameParameters.MIN_BET) + ")";
        assert (betManager.currentBet_ == GameParameters.MIN_BET) : failStr;
        
        passStr = "Test 3 (decreaseBet): PASSED " +
            "(currentBet_ was decreased by 1)";
        System.out.println(passStr);
        
        //Test 4: decreaseBet will not decrease currentBet_ if it’s already the
        //minimum allowed
        //assume MIN_BET < MAX_BET
        failStr = "Test 4 (decreaseBet): FAILED " + "(currentBet_ was " +
                betManager.currentBet_ + ", cannot be less than " +
                GameParameters.MIN_BET + ")";
        assert (betManager.currentBet_ >= GameParameters.MIN_BET) : failStr;
        
        passStr = "Test 4 (decreaseBet): PASSED " +
            "(currentBet_ not less than " + GameParameters.MIN_BET + ")";
        System.out.println(passStr);
    }
}