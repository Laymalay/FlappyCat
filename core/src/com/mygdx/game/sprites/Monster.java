package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
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
        width = 70;
        height = 50;
        bounds = new Rectangle(pos.x,pos.y,width,height);
        sound =  Gdx.audio.newSound(Gdx.files.internal("kill.wav"));
    }


    @Override
    public void effect(Blur blur) {
        blur.setLife(blur.getLife()-2);
        blur.setScore(70);
        sound.play();
    }

}

