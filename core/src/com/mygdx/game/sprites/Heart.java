package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by alinka on 7.3.17.
 */

public class Heart extends Item {



   public Heart(Vector2 v){
       super(v);
       texture = new Texture("heart2.png");
       bounds = new Rectangle(pos.x,pos.y,texture.getWidth(),texture.getHeight());

   }


    @Override
    public void effect(Bird bird) {
        bird.setLife(bird.getLife()+1);
    }




}
