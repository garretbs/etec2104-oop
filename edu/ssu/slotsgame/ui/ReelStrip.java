package edu.ssu.slotsgame.ui;

import edu.ssu.slotsgame.logic.PayTable;
import edu.ssu.slotsgame.logic.ReelController;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ReelStrip{
    public static final int ROTATE_DOWN = 0;
    public static final int ROTATE_UP = 1;
    public static final int COUNTDOWN = 25;

    public static HashMap<String, BufferedImage> imageMap = null;

    public ArrayList<String> symbolList_;
    
    public int numSymbols_;
    public int position_;
    
    public ReelSymbol midSymbol;
    public ReelSymbol bottomSymbol;
    public ReelSymbol partialSymbol;
    public ReelSymbol topSymbol;

    public int x;
    public int y;
    public int index = 0;
    public int timeleft = COUNTDOWN;
    public boolean stopped = true;

    public static int symbolWidth;
    public static int symbolHeight;
    float offset = 0;
    static float doffset;

    public int rotateState = ROTATE_DOWN;

    public ReelStrip(){
        PayTable.getInstance().getSymbols();
        symbolList_ = new ArrayList<>();
        addSymbol("watermelon");
        addSymbol("bell");
        addSymbol("seven");
        addSymbol("lime");
        addSymbol("cherry");
        addSymbol("plum");
        addSymbol("grapes");
        addSymbol("orange");
        addSymbol("bar");
        
        Collections.shuffle(symbolList_);
        numSymbols_ = symbolList_.size();
        position_ = numSymbols_;
        
        midSymbol = new ReelSymbol(symbolList_.get(0));
        bottomSymbol = new ReelSymbol(symbolList_.get(1));
        partialSymbol = new ReelSymbol(symbolList_.get(numSymbols_-1));
        topSymbol = new ReelSymbol(symbolList_.get(numSymbols_-2));

        if(this.imageMap == null){
                this.imageMap = new HashMap<>();

                try{
                    BufferedImage symbolMap = ImageIO.read(new File("resources/simage.png"));
                    BufferedImage[][] symbols = new BufferedImage[3][3];
                    int width = symbolMap.getWidth() / ReelController.numReels;
                    int height = symbolMap.getHeight() / ReelController.numCells;
                    for(int row = 0; row < 3; row++){
                        for (int col = 0; col < 3; col++){
                            symbols[row][col] = symbolMap.getSubimage(width * col, height * row,
                                width, height);
                        }
                    }

                    imageMap.put("watermelon", symbols[0][0]);
                    imageMap.put("bell", symbols[0][1]);
                    imageMap.put("seven", symbols[0][2]);

                    imageMap.put("lime", symbols[1][0]);
                    imageMap.put("cherry", symbols[1][1]);
                    imageMap.put("plum", symbols[1][2]);

                    imageMap.put("grapes", symbols[2][0]);
                    imageMap.put("orange", symbols[2][1]);
                    imageMap.put("bar", symbols[2][2]);

                    symbolWidth = symbols[0][0].getWidth();
                    symbolHeight = symbols[0][0].getHeight();

                    doffset = symbolHeight / 100.0f;
                }catch (Exception e){
                    System.out.println("Error loading symbol map");
                }
        }

        x = 0;
        y = 0;
    }

    public void increment(int dtime){
        if(this.rotateState == ReelStrip.ROTATE_DOWN){
            offset += doffset * dtime;
            while (offset >= symbolHeight){
                offset -= symbolHeight;
                index--;
                if (index == -1){
                    index += symbolList_.size();
                }
            }
        }else if(this.rotateState == ReelStrip.ROTATE_UP){
            offset -= doffset * dtime;
            while(offset <= -symbolHeight){
                offset += symbolHeight;
                index++;
                if(index == symbolList_.size()){
                    index -= symbolList_.size();
                }
            }
        }
    }

    public void paint(int reelX, int reelY, Graphics g){
        int idx = this.index - 1;
        if(idx < 0){
            idx += symbolList_.size();
        }

        int tmpy = this.y + (int)this.offset;

        BufferedImage s = ReelStrip.imageMap.get(symbolList_.get(idx));
        g.drawImage(s, reelX + this.x, reelY + tmpy, null);
        midSymbol.setSymbol(symbolList_.get(idx));

        s = imageMap.get(symbolList_.get(this.index));
        g.drawImage(s, reelX + this.x, reelY + tmpy + symbolHeight, null);
        bottomSymbol.setSymbol(symbolList_.get(this.index));

        idx = this.index + 1;
        if(idx == symbolList_.size()){
            idx = 0;
        }

        s = imageMap.get(symbolList_.get(idx));
        g.drawImage(s, reelX + this.x, reelY + tmpy + symbolHeight * 2, null);
        partialSymbol.setSymbol(symbolList_.get(idx));

        if (this.rotateState == ReelStrip.ROTATE_DOWN){
            idx = this.index - 2;
            if(idx < 0){
                idx += symbolList_.size();
            }

            s = imageMap.get(symbolList_.get(idx));
            g.drawImage(s, reelX + this.x, reelY + tmpy - symbolHeight, null);
            topSymbol.setSymbol(symbolList_.get(idx));
            
        }else if(this.rotateState == ReelStrip.ROTATE_UP){
            idx = this.index + 2;
            if (idx >= symbolList_.size()){
                idx -= symbolList_.size();
            }

            s = imageMap.get(symbolList_.get(idx));
            g.drawImage(s, reelX + this.x, reelY + tmpy + symbolHeight * 3, null);
            topSymbol.setSymbol(symbolList_.get(idx));
        }
    }
    
    private void addSymbol(String symbol){
        int i=0;
        while(i < PayTable.getInstance().getSymbolAmounts().get(symbol)){
            symbolList_.add(symbol);
            i++;
        }
    }
}