package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by alinka on 8.5.17.
 */

public class IceMonster extends Item {


    public IceMonster(Vector2 v){
        super(v);
        texture = new Texture("kill3.png");
        width = 70;
        height = 70;
        bounds = new Rectangle(pos.x,pos.y,width,height);
        sound =  Gdx.audio.newSound(Gdx.files.internal("monster.ogg"));
    }


    @Override
    public void effect(Blur blur) {
        blur.setLife(blur.getLife()-1);
        blur.setScore(50);
        blur.set_jump(blur.get_jump()-20);
        sound.play();
        blur.movement+=5;
    }

}

