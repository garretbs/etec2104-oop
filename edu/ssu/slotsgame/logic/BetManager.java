package edu.ssu.slotsgame.logic;

import edu.ssu.slotsgame.config.GameParameters;

public class BetManager{
    private final static BetManager instance_ = new BetManager();
    private int currentBet_;

    public static BetManager getInstance(){
        return instance_;
    }

    private BetManager(){
        currentBet_ = GameParameters.MIN_BET;
    }

    public int getCurrentBet(){
        return currentBet_;
    }

    public void increaseBet(){
        int newBet=currentBet_+1;

        if(validateBet(newBet)){
            currentBet_ ++;
            EventManager.getInstance().notify(new BetEvent(currentBet_));
        }
    }
    
    public void setMaxBet(){
        int newBet = CreditManager.getInstance().getCurrentCredits();
        if(validateBet(newBet)){
            currentBet_ = newBet;
        }else{
            currentBet_ = GameParameters.MAX_BET;
        }
    }

    public void decreaseBet(){
        int newBet;
        if(currentBet_ > CreditManager.getInstance().getCurrentCredits()){
            newBet = CreditManager.getInstance().getCurrentCredits();
        }else{
            newBet=currentBet_-1;
        }
        
        if(validateBet(newBet)){
            currentBet_ --;
            EventManager.getInstance().notify(new BetEvent(currentBet_));
        }
    }

    public boolean updateBetButtons(){
        boolean activated = false;

        if(currentBet_==GameParameters.MAX_BET || currentBet_==GameParameters.MIN_BET){
            activated=true;
        }
        return activated;
    }

    private boolean validateBet(int newBet){
        if(newBet <= GameParameters.MAX_BET && newBet >= GameParameters.MIN_BET && newBet <= CreditManager.getInstance().getCurrentCredits()){
            return true;
        }else
            return false;
    }

    public static void main(String[] args){
        BetManager betManager = new BetManager();
        // Test 1: increaseBet increases currentBet_ by one
        int currentbet = betManager.currentBet_;
        betManager.increaseBet();
        int newbet = betManager.currentBet_;
        String test1= "Test 1 (increaseBet): FAILED (currentBet_ was" + currentbet + ", It should have been" + (currentbet+ 1)+ ")";
        assert(currentbet+1== newbet):test1;
        System.out.println("TEST 1 the test worked perfectly you bet was increased correctly");


        //Test 2: that increaseBet will not increase currentBet_ if it’s already the maximum allowed
        betManager.currentBet_=GameParameters.MAX_BET;
        currentbet = betManager.currentBet_;
        betManager.increaseBet();
        newbet=betManager.currentBet_;
        String test2= "this new bet should not be increase because it already is the maximum bet. current= " +currentbet+ "newbet= " +newbet+ "";
        assert(currentbet==newbet):test2;
        System.out.println("TEST 2 the bet did not increase because you are in the maximum bet. this test is correct!!");


        // Test3:Test that decreaseBet decreases currentBet_ by one.
        currentbet = betManager.currentBet_;
        betManager.decreaseBet();
        newbet=betManager.currentBet_;
        String test3="Test 3 (decreaseBet): FAILED (currentBet_ was" + currentbet + ", It should have been" + (currentbet- 1)+ ")";
        assert(currentbet-1==newbet):test3;
        System.out.println("TEST 3 (decreaseBet): PASSED " + "(currentBet_ was decreased by 1)");


        //test4 Test that decreaseBet will not decrease currentBet_ if it’s already the minimum allowed.
        betManager.currentBet_=GameParameters.MIN_BET;
        currentbet = betManager.currentBet_;
        betManager.decreaseBet();
        newbet=betManager.currentBet_;
        String test4= "WRONG!!!this new bet should not be decrease because it already is the minum bet, and it did. current= " +currentbet+ "newbet= " +newbet+ "";
        assert(currentbet==newbet):test4;
        System.out.println("TEST 4 the bet did not decrease because you are in the minimum bet. this test is correct!!");
    }
}