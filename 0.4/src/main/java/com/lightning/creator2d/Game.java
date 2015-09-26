/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lightning.creator2d;

import com.lightning.creator2d.block.Block;
import com.lightning.creator2d.generation.Generate;
import java.io.IOException;
import java.util.Random;
import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.input.Keyboard.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author Lightning Studios
 */
public class Game {
    public double charPosX;
    public double charPosY;
    
    public boolean isDisplayClosing;
    
    public Block[][] terrain;
    public Texture[] textures;
    public Texture lava;
    
    public void run(){
        textures=new Texture[4];
        try{
            textures[0]=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/textures/blocks/air.png"));
            textures[1]=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/textures/blocks/stone.png"));
            textures[2]=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/textures/blocks/dirt.png"));
            textures[3]=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/textures/blocks/grass.png"));
            lava       =TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/textures/blocks/lava.png"));
        }catch(IOException e){
            throw new RuntimeException("java.io.IOException with message: "+e.getMessage(), e.getCause());
        }
        Generate generator = new Generate();
        long seed = System.nanoTime();
        Random r = new Random(seed);
        System.out.println(seed);
        terrain = generator.generate2DMain(r);
        charPosX=0;
        charPosY=67.5;
        long lastTime = System.nanoTime();
        long curTime;
        int frames = 0;
        while(!Display.isCloseRequested()){
            getInput();
            render();
            Display.update();
            curTime=System.nanoTime();
            frames++;
            if(curTime-lastTime>=1000000000L){
                System.out.println(frames);
                lastTime=curTime;
                frames=0;
            }
        }
    }
    
    public void render(){
        glClear(GL_COLOR_BUFFER_BIT);
//        for(int i=0;i<16;i++){
//            for(int j=0;j<256;j++){
//                if(terrain[i][j].equals(new Block.Air())){
//                    System.out.println("Found Air at" + i + ", " + j);
//                }
//            }
//        }
        glPushMatrix();
        Color.white.bind();
                    
        for(int i=0;i<terrain.length;i++){
            for(int j=0;j<256;j++){
                int x =  i;
                int y = -j;
                textures[(int)terrain[i][j].getBlockID()].bind();
                glBegin(GL_QUADS);
                {
                    glTexCoord2f(0,0);
                    glVertex2d((Display.getWidth()/ 16)*(x+8.0+charPosX),(Display.getHeight()/16)*(y+charPosY));
                    glTexCoord2f(1,0);
                    glVertex2d((Display.getWidth()/ 16)*(x+8.0+charPosX)+64,(Display.getHeight()/16)*(y+charPosY));
                    glTexCoord2f(1,1);
                    glVertex2d((Display.getWidth()/ 16)*(x+8.0+charPosX)+64,(Display.getHeight()/16)*(y+charPosY)+64);
                    glTexCoord2f(0,1);
                    glVertex2d((Display.getWidth()/ 16)*(x+8.0+charPosX),(Display.getHeight()/16)*(y+charPosY)+64);
                }
                glEnd();
            }
        }
        lava.bind();
        for(int i=0;i<terrain.length;i++){
            for(int j=0;j<256;j++){
                int x =  i;
                int y = -j+256;
                glBegin(GL_QUADS);
                {
                    glTexCoord2f(0,0);
                    glVertex2d((Display.getWidth()/ 16)*(x+8.0+charPosX),(Display.getHeight()/16)*(y+charPosY));
                    glTexCoord2f(1,0);
                    glVertex2d((Display.getWidth()/ 16)*(x+8.0+charPosX)+64,(Display.getHeight()/16)*(y+charPosY));
                    glTexCoord2f(1,1);
                    glVertex2d((Display.getWidth()/ 16)*(x+8.0+charPosX)+64,(Display.getHeight()/16)*(y+charPosY)+64);
                    glTexCoord2f(0,1);
                    glVertex2d((Display.getWidth()/ 16)*(x+8.0+charPosX),(Display.getHeight()/16)*(y+charPosY)+64);
                }
                glEnd();
            }
        }
        glPopMatrix();
    }

    public void getInput() {
        if(isKeyDown(KEY_UP))
            charPosY+=0.1;
        if(isKeyDown(KEY_DOWN))
            charPosY-=0.1;
        if(isKeyDown(KEY_LEFT))
            charPosX+=0.1;
        if(isKeyDown(KEY_RIGHT))
            charPosX-=0.1;
//        System.out.println("Character position: (" + charPosX + ", " + charPosY + ")");
    }
}
