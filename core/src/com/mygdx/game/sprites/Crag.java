package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

/**
 * Created by alinka on 7.5.17.
 */

public class Crag  extends Item implements Serializable {
    @Override
    public void effect(Blur blur) {
        blur.setLife(blur.getLife()-1);
    }

    public Crag(Vector2 v){
        super(v);
        pos.x=0;
        texture = new Texture("sliz.png");
        width = 150;
        height = 200;
        bounds = new Rectangle(pos.x,pos.y,width,height);
    }


}
