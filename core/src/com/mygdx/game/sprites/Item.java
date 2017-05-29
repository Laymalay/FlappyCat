package com.mygdx.game.sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by alinka on 7.3.17.
 */

public abstract  class Item   {


    protected Vector2 pos;
    protected Texture texture;
    protected Rectangle bounds;
    protected int width;
    protected Sound sound;
    protected  int height;
    public Rectangle getBounds(){
        return  bounds;
    }
    public Vector2 getPos() {
        return pos;
    }

    public void setPos (Vector2 v){pos=v;}

    public int getWidth(){
        return width;
    }
    public  Item(Vector2 v) {
        pos = new Vector2(v);

    }


    public void render (SpriteBatch sb){
        sb.draw(this.texture,this.getPos().x,this.getPos().y,this.width,this.height);
    }

    public void dispose (){
        texture.dispose();
        sound.dispose();

    }


    public  boolean collides(Rectangle player){
        return player.overlaps(bounds);
    }
    public abstract void effect(Blur blur);


    public void reposition (Vector2 v){

        pos.set(v);

        bounds.setPosition(pos.x,pos.y);

    }

    public Texture getTexture() {
        return texture;
    }
}

