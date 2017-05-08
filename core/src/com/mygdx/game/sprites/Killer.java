package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by alinka on 5.5.17.
 */

public class Killer extends Item {


    public Killer(Vector2 v){
        super(v);
        texture = new Texture("alien.png");
        width = 40;
        height = 50;
        bounds = new Rectangle(pos.x,pos.y,width,height);
        sound =  Gdx.audio.newSound(Gdx.files.internal("monster.ogg"));
    }


    @Override
    public void effect(Blur blur) {
        blur.setLife(blur.getLife()-2);
        blur.setScore(40);
        sound.play();
    }
}
