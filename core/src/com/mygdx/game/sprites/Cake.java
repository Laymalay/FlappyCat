package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by alinka on 7.3.17.
 */

public class Cake extends Item {


    public Cake(Vector2 v){
        super(v);
        texture = new Texture("Cake.png");
        bounds = new Rectangle(pos.x,pos.y,texture.getWidth(),texture.getHeight());

    }


    @Override
    public void effect(Bird bird) {
        bird.set_jump(bird.get_jump()-50);
    }

}
