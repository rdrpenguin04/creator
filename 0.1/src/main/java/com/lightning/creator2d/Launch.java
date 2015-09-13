/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightning.creator2d;

import java.io.IOException;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author Lightning Studios
 */
public class Launch {
    
    public static Game game;
    
    public static Texture creatorTexture; 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException{
        if(args.length<1){
            throw new IllegalStateException("Too few arguments!!!");
        } else if(args[0].equals("1")){
            
        } else if(args[0].equals("0")){
            
        } else {
            throw new IllegalStateException("Online/Offline flag set in invalid state!");
        }
        start();
    }
    
    public static void start() throws InterruptedException{
        //initialize LWJGL/OpenGL
        try{
            Display.setDisplayMode(new DisplayMode(1000, 1000));
            Display.create();
            glOrtho(0, 2000, 2000, 0, -1, 1);
            glDisable(GL_DEPTH_TEST);
            glClearColor(0,0.75f,1,0.5f);
            glClear(GL_COLOR_BUFFER_BIT);
            glEnable(GL_TEXTURE_2D);
            glEnable(GL_BLEND); glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            creatorTexture=TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("assets/logos/main.png"));
            Color.white.bind();
            creatorTexture.bind();
            glBegin(GL_QUADS);{
                glTexCoord2f(0,0);
                glVertex2f(0,200);
                glTexCoord2f(1,0);
                glVertex2f(3400,200);
                glTexCoord2f(1,1);
                glVertex2f(3400,1600);
                glTexCoord2f(0,1);
                glVertex2f(0,1600);
            }glEnd();
            Display.update();
            Thread.sleep(2000);
            game = new Game();
            game.run();
            Display.destroy();
        }catch(LWJGLException e){
            throw new RuntimeException("org.lwjgl.LWJGLException with message: "+e.getMessage(), e.getCause());
        } catch (IOException e) {
            throw new RuntimeException("java.io.IOException with message: "+e.getMessage(), e.getCause());
        }
    }
    
}
