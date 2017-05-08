package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by alinka on 7.3.17.
 */

public class Cake extends Item {


    public Cake(Vector2 v){
        super(v);
        texture = new Texture("cake.png");
        width = 40;
        height = 50;
        bounds = new Rectangle(pos.x,pos.y,width,height);
        sound =  Gdx.audio.newSound(Gdx.files.internal("monster.ogg"));
    }


    @Override
    public void effect(Blur blur) {
        blur.set_jump(blur.get_jump()-30);
        blur.setScore(10);
        sound.play();
    }

}
