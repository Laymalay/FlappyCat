package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

/**
 * Created by alinka on 8.5.17.
 */

public class IceStone extends Item implements Serializable {
    @Override
    public void effect(Blur blur) {
        blur.setLife(blur.getLife()-1);
    }

    public IceStone(Vector2 v){
        super(v);
        pos.x=0;
        texture = new Texture("stone2.png");
        width = 100;
        height = 120;
        bounds = new Rectangle(pos.x,pos.y,width,height);
    }


}
