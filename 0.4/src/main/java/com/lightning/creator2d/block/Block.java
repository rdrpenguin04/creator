/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lightning.creator2d.block;

/**
 *
 * @author Lightning Suudios
 */
public abstract class Block {

    public static class Grass extends Block {

        @Override
        public String getTextureFile() {
            return "assets/textures/blocks/grass.png";
        }

        @Override
        public String getSoundFile() {
            return "assets/sounds/blocks/grass.png";
        }

        @Override
        public long getBlockID() {
            return 3;
        }
    };

    public static class Stone extends Block {

        @Override
        public String getTextureFile() {
            return "assets/textures/blocks/stone.png";
        }

        @Override
        public String getSoundFile() {
            return "assets/sounds/blocks/stone.png";
        }

        @Override
        public long getBlockID() {
            return 1;
        }
    };

    public static class Dirt extends Block {

        @Override
        public String getTextureFile() {
            return "assets/textures/blocks/dirt.png";
        }

        @Override
        public String getSoundFile() {
            return "assets/sounds/blocks/dirt.png";
        }

        @Override
        public long getBlockID() {
            return 2;
        }
    };

    public static class Air extends Block {
        
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
    }

    public abstract String getTextureFile();

    public abstract String getSoundFile();

    public abstract long getBlockID();

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Block)) {
            return false;
        }
        return getBlockID() == ((Block) other).getBlockID();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode() * 4 + getTextureFile().hashCode() * 2 + getSoundFile().hashCode();
    }
}
