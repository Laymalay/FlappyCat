package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by alinka on 7.3.17.
 */

public class Rock extends Item {
    @Override
    public void effect(Bird bird) {
        bird.setLife(bird.getLife()-1);
    }


    public Rock(Vector2 v){
        super(v);
        pos.x=0;
        texture = new Texture("stone4.png");
        bounds = new Rectangle(pos.x,pos.y,texture.getWidth(),texture.getHeight());

    }


}
