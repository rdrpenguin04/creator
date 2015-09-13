/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lightning.creator2d.generation;

import com.lightning.creator2d.block.Block;
import java.util.Random;

/**
 *
 * @author Lightning Studios
 */
public class Generate {
    public Block[][] generate2DMain(Random r){
        return oreGen(surfaceGenMain(r), r);
    }
    
    private Block[][] surfaceGenMain(Random r){
        Block[][] return_ = new Block[16][256];
        
        return_[7][60]=return_[8][60]=new Block.Grass();
        {
            double chanceDown=0.25;
            double chanceMiddle=0.5;
            double chanceUp=0.25;
            int last=60;
            for(int i=6;i>=0;i--){
                double randOut=r.nextDouble();

                if(randOut<=chanceDown){
                    return_[i][last--]=new Block.Grass();
                    chanceMiddle+=chanceDown/4;
                    chanceUp+=chanceDown/4;
                    chanceDown/=2;
                }else if(randOut<=chanceMiddle+chanceDown){
                    return_[i][last]=new Block.Grass();
                    chanceDown+=chanceMiddle/4;
                    chanceUp+=chanceMiddle/4;
                    chanceMiddle/=2;
                }else{
                    return_[i][last++]=new Block.Grass();
                    chanceDown+=chanceUp/4;
                    chanceMiddle+=chanceUp/4;
                    chanceUp/=2;
                }
            }
        }
        
        {
            double chanceDown=0.25;
            double chanceMiddle=0.5;
            double chanceUp=0.25;
            int last=60;
            for(int i=9;i<16;i++){
                double randOut=r.nextDouble();

                if(randOut<=chanceDown){
                    return_[i][last--]=new Block.Grass();
                    chanceMiddle+=chanceDown/4;
                    chanceUp+=chanceDown/4;
                    chanceDown/=2;
                }else if(randOut<=chanceMiddle+chanceDown){
                    return_[i][last]=new Block.Grass();
                    chanceDown+=chanceMiddle/4;
                    chanceUp+=chanceMiddle/4;
                    chanceMiddle/=2;
                }else{
                    return_[i][last++]=new Block.Grass();
                    chanceDown+=chanceUp/4;
                    chanceMiddle+=chanceUp/4;
                    chanceUp/=2;
                }
            }
        }
        
        return return_;
    }
    
    private Block[][] oreGen(Block[][] surface, Random r){
        for(int i=0;i<surface.length;i++){
            boolean foundSurface=false;
            for(int j=surface[i].length-1;j>=0;j--){
                if(surface[i][j] instanceof Block.Grass){
                    foundSurface=true;
                }else if(foundSurface){
                    if(j>55)surface[i][j]=new Block.Dirt();
                    if(j<=55)surface[i][j]=new Block.Stone();
                }else{
                    surface[i][j]=new Block(){

                        @Override
                        public String getTextureFile() {
                            return "";
                        }

                        @Override
                        public String getSoundFile() {
                            return "";
                        }

                        @Override
                        public long getBlockID() {
                            return 0;
                        }
                        
                    };
                }
            }
        }
        return surface;
    }
}
