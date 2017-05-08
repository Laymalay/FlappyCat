package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by alinka on 5.5.17.
 */

public class Killer extends Item {


    public Killer(Vector2 v){
        super(v);
        texture = new Texture("alien2.png");
        width = 30;
        height = 50;
        bounds = new Rectangle(pos.x,pos.y,width,height);
    }


    @Override
    public void effect(Blur blur) {
        blur.setLife(blur.getLife()-1);
        blur.setScore(3);
    }

}
