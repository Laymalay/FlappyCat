package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
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
        sound =  Gdx.audio.newSound(Gdx.files.internal("kill.wav"));
    }


    @Override
    public void effect(Blur blur) {
        blur.setLife(blur.getLife()-3);
        blur.setScore(100);
        blur.set_jump(blur.get_jump()-20);
        sound.play();
        blur.movement+=5;
    }

}


