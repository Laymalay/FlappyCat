package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by alinka on 6.3.17.
 */

public class Rock {
    public static final int GAP=300;
    private Vector2 posRock;
    private Texture rock;
    public static final int ROCK_WIDTH=300;


    private Random rand;
    private Rectangle boundsRock;



    public Rock(float x){
        rock= new Texture("stone2.png");
        rand =new Random();

        posRock = new Vector2(x,0);

        boundsRock =new Rectangle(posRock.x,posRock.y,rock.getWidth(),rock.getHeight());
    }

    public void reposition (float x){

        posRock.set(x,0);

        boundsRock.setPosition(posRock.x,posRock.y);

    }

    public Vector2 getPosRock() {
        return posRock;
    }

    public Texture getRock() {
        return rock;
    }

    public boolean collides(Rectangle player){

        return player.overlaps(boundsRock);


    }
}
