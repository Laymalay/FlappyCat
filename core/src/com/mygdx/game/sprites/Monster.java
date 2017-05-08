package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by alinka on 7.5.17.
 */

public class Monster extends Item {


    public Monster(Vector2 v){
        super(v);
        texture = new Texture("ship.png");
        width = 100;
        height = 60;
        bounds = new Rectangle(pos.x,pos.y,width,height);
    }


    @Override
    public void effect(Blur blur) {
        blur.setLife(blur.getLife()-3);
        blur.setScore(3);
    }

}

