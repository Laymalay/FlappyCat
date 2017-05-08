package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by alinka on 8.5.17.
 */

public class Zombie extends Item {


    public Zombie(Vector2 v){
        super(v);
        texture = new Texture("zombie.png");
        width = 50;
        height = 50;
        bounds = new Rectangle(pos.x,pos.y,width,height);
    }


    @Override
    public void effect(Blur blur) {
        blur.setLife(blur.getLife()-1);
        blur.setScore(5);
    }

}


