package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by alinka on 6.3.17.
 */

public class Item {

    private Vector2 posCake,posHeart,posPizza,posDead;
    private Texture cake,heart,pizza,dead;

    public static final int FLUCTUATION =700;//диапозан отклонений по высоте на кот будут созд предметы
    public static final int ITEM_WIDTH=100;

    private Rectangle cakeB,heartB,pizzaB,deadB;
    private Random rand;

    public Vector2 getPosCake() {
        return posCake;
    }

    public Vector2 getPosHeart() {
        return posHeart;
    }

    public Vector2 getPosPizza() {
        return posPizza;
    }

    public Vector2 getPosDead() {
        return posDead;
    }

    public Texture getCake() {
        return cake;
    }

    public Texture getHeart() {
        return heart;
    }

    public Texture getPizza() {
        return pizza;
    }

    public Texture getDead() {
        return dead;
    }

    public Item(float x){
        cake = new Texture("cake4.png");
        pizza =  new Texture("pizza1.png");
        heart =new Texture("heart2.png");
        rand = new Random();


        posCake = new Vector2(x,rand.nextInt(FLUCTUATION));
        posPizza = new Vector2(x-50,rand.nextInt(FLUCTUATION));
        posHeart = new Vector2(x+50,rand.nextInt(FLUCTUATION));

        cakeB = new Rectangle(posCake.x,posCake.y,cake.getWidth(),cake.getHeight());
        pizzaB =new Rectangle(posPizza.x,posPizza.y,pizza.getWidth(),pizza.getHeight());
        heartB = new Rectangle(posHeart.x,posHeart.y,heart.getWidth(),heart.getHeight());
    }

    public void reposition (float x){
        posCake.set(x,rand.nextInt(FLUCTUATION));
        posPizza.set(x-50,rand.nextInt(FLUCTUATION));
        posHeart.set(x+50,rand.nextInt(FLUCTUATION));
        cakeB.setPosition(getPosCake().x,getPosCake().y);
        pizzaB.setPosition(getPosPizza().x,getPosPizza().y);
        heartB.setPosition(getPosHeart().x,getPosHeart().y);

    }

    public boolean collides(Rectangle player, Bird bird){
         if (player.overlaps(cakeB)){
             bird.set_jump(bird.get_jump()-5);
             return player.overlaps(cakeB);
         }
        if (player.overlaps(heartB)){
            bird.setLife(bird.getLife()+1);
            return player.overlaps(heartB);
        }
        return false;

    }



}
