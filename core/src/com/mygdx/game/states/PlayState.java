package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.FlappyDemo;
import com.mygdx.game.sprites.Bird;
import com.mygdx.game.sprites.Heart;
import com.mygdx.game.sprites.Cake;
import com.mygdx.game.sprites.Pizza;
import com.mygdx.game.sprites.Item;
import com.mygdx.game.sprites.Rock;


import java.util.Random;

/**
 * Created by alinka on 26.2.17.
 */

public class PlayState extends State {

    public static final int ITEM_COUNT=6;
    private Bird bird;
    private Texture bg,heart;

    private Random rand;


    private Array<Rock> rocks;
    private Array<Item> items;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,300);
        camera.setToOrtho(false, FlappyDemo.WIDTH/2, FlappyDemo.HEIGHT/2);
        bg =  new Texture("bg2.png");
        heart = new Texture("heart2.png");


        rocks = new Array<Rock>();

        items = new Array<Item>();


        for(int i = 0 ;i <4;i++){
            rand = new Random();
            rocks.add(new Rock(new Vector2(100,0)));
        }

        Item  item=null;
        for(int i =0 ;i <ITEM_COUNT;i++){
            rand = new Random();
            switch (rand.nextInt(3)){
                case 0 :
                    item = new Heart(new Vector2(100*i,i*40));
                    break;
                case 1:
                    item = new Cake(new Vector2(10*i,i*40)) ;
                    break;
                case 2:
                    item = new Pizza(new Vector2(100*i,i*40)) ;
                    break;
            }
            items.add(item);

        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        camera.position.x=bird.getPosition().x+80;


        if(bird.getLife()==0){
            gsm.set(new PlayState(gsm));
        }



        for (Rock rock:rocks){
            if(camera.position.x - (camera.viewportWidth/2) > rock.getPos().x +
                    rock.getTexture().getWidth()){
                    rock.reposition(new Vector2(rock.getPos().x+400,0));
            }
            if(rock.collides(bird.getBounds())){
                   bird.jump(500);
                   rock.effect(bird);
            }
        }




        for (Item item:items){

            rand=new Random();
            if(camera.position.x - (camera.viewportWidth/2) > item.getPos().x +
                    item.getTexture().getWidth()){
                item.reposition(new Vector2(item.getPos().x+400,rand.nextInt(360)));
            }


            if (item.collides(bird.getBounds())){
                item.reposition(new Vector2(item.getPos().x+400,rand.nextInt(360)));
                item.effect(bird);
            }

        }










        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg,camera.position.x - (camera.viewportWidth/2),0);
        for (int i=0;i<bird.getLife();i++){
            sb.draw(heart,camera.position.x - (camera.viewportWidth/2)+i*10,355);
        }
        for (Item  item:items){
            item.render(sb);
        }

        for (Rock  rock:rocks){
            rock.render(sb);
        }
        sb.draw(bird.getBird(),bird.getPosition().x,bird.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
