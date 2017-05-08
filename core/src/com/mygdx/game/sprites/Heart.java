package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by alinka on 7.3.17.
 */

public class Heart extends Item {
   public Heart(Vector2 v){
       super(v);
       texture = new Texture("heart5.png");
       width = 50;
       height = 50;
       bounds = new Rectangle(pos.x,pos.y,width,height);
       sound = Gdx.audio.newSound(Gdx.files.internal("heart.mp3"));
   }


    @Override
    public void effect(Blur blur) {
        blur.setLife(blur.getLife()+1);
        blur.setScore(20);
        blur.set_jump(blur.get_jump()+10);
        sound.play();
        blur.movement-=5;
    }




}
