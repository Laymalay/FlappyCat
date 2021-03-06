package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.io.Serializable;

/**
 * Created by alinka on 7.3.17.
 */

public class Rock extends Item implements Serializable{
    @Override
    public void effect(Blur blur) {
        blur.setLife(blur.getLife()-1);
    }


    public Rock(Vector2 v){
        super(v);
        pos.x=0;
        texture = new Texture("stone4.png");
        width = 180;
        height = 150;
        bounds = new Rectangle(pos.x,pos.y,width,height);
    }


}
