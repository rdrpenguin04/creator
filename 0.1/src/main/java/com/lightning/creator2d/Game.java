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
    
    public void run(){
        textures=new Texture[3];
        try{
            textures[0]=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/textures/blocks/stone.png"));
            textures[1]=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/textures/blocks/dirt.png"));
            textures[2]=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/textures/blocks/grass.png"));
        }catch(IOException e){
            throw new RuntimeException("java.io.IOException with message: "+e.getMessage(), e.getCause());
        }
        Generate generator = new Generate();
        Random r = new Random(6446);
        terrain = generator.generate2DMain(r);
        charPosX=0;
        charPosY=67.5;
        while(!Display.isCloseRequested()){
            getInput();
            render();
            Display.update();
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
        for(int i=0;i<16;i++){
            for(int j=0;j<256;j++){
                int x =  i;
                int y = -j;
                if(!terrain[i][j].equals(new Block.Air())){
                    glPushMatrix();
                    Color.white.bind();
                    textures[terrain[i][j].equals(new Block.Grass())?2:terrain[i][j].equals(new Block.Dirt())?1:terrain[i][j].equals(new Block.Stone())?0:9].bind();
                    glBegin(GL_QUADS);
                    {
                        glTexCoord2f(0,0);
                        glVertex2d((Display.getWidth()/16)*(x+8.0+charPosX),(Display.getHeight()/16)*(y+charPosY));
                        glTexCoord2f(1,0);
                        glVertex2d((Display.getWidth()/16)*(x+8.0+charPosX)+64,(Display.getHeight()/16)*(y+charPosY));
                        glTexCoord2f(1,1);
                        glVertex2d((Display.getWidth()/16)*(x+8.0+charPosX)+64,(Display.getHeight()/16)*(y+charPosY)+64);
                        glTexCoord2f(0,1);
                        glVertex2d((Display.getWidth()/16)*(x+8.0+charPosX),(Display.getHeight()/16)*(y+charPosY)+64);
                    }
                    glEnd();
                    glPopMatrix();
                }
            }
        }
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
        System.out.println("Character position: (" + charPosX + ", " + charPosY + ")");
    }
}
