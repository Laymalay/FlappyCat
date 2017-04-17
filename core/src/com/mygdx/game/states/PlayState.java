package com.mygdx.game.states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.FlappyCat;

import com.mygdx.game.sprites.Bird;
import com.mygdx.game.sprites.Heart;
import com.mygdx.game.sprites.Cake;
import com.mygdx.game.sprites.Pizza;
import com.mygdx.game.sprites.Item;
import com.mygdx.game.sprites.Rock;

import java.io.IOException;
import java.io.Serializable;


import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Random;

/**
 * Created by alinka on 26.2.17.
 */


public class PlayState extends State {

    public static final int ITEM_COUNT=4;
    public static final int GROUND_Y = -40;
    private Bird bird;
    private Texture bg,heart,ground;
    private Vector2 ground1,ground2;
    private Random rand;
    private BitmapFont font;
    private Array<Rock> rocks;
    private Array<Item> items;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        bird = new Bird(50,300);
        camera.setToOrtho(false, FlappyCat.WIDTH/2, FlappyCat.HEIGHT/2);

        bg =  new Texture("bg3.png");
        heart = new Texture("heart5.png");
        ground = new Texture("ground1.png");
        font = new BitmapFont();
        ground1 = new Vector2(camera.position.x - camera.viewportWidth/2,GROUND_Y);
        ground2 = new Vector2((camera.position.x - camera.viewportWidth/2)+ground.getWidth(),GROUND_Y);

        rocks = new Array<Rock>();

        items = new Array<Item>();


        for(int i = 0 ;i <4;i++){
            rand = new Random();
            rocks.add(new Rock(new Vector2(100,-30)));
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
    public void update(float dt) throws IOException, ClassNotFoundException {
        handleInput();

        UpdateGround();
        bird.update(dt);
        camera.position.x=bird.getPosition().x+80;


        if(bird.getLife()==0){
            gsm.set(new PlayState(gsm));
        }



        for (int i=0; i< items.size; i++){
            Item item = items.get(i);
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

        for (int i=0; i< rocks.size; i++){
            Rock rock = rocks.get(i);
            if(camera.position.x - (camera.viewportWidth/2) > rock.getPos().x +
                    rock.getTexture().getWidth()){
                rock.reposition(new Vector2(rock.getPos().x+400,-30));
            }
            if(rock.collides(bird.getBounds())){

                gsm.set(new GameOver(gsm, bird.getScore()));

                //bird.jump(500);
                //rock.effect(bird);
            }
        }


        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg,camera.position.x - (camera.viewportWidth/2),0);
        font.draw(sb, " Score: "+(bird.getScore()),camera.position.x - (camera.viewportWidth/2),355);
        for (int i=0;i<bird.getLife();i++){
            sb.draw(heart,camera.position.x - (camera.viewportWidth/2)+i*10,355);
        }


        for (Item  item:items){
            item.render(sb);
        }

        for (Rock  rock:rocks) {
            rock.render(sb);
        }
        sb.draw(bird.getBird(),bird.getPosition().x,bird.getPosition().y);
        sb.draw(ground, ground1.x, ground1.y);
        sb.draw(ground, ground2.x, ground2.y);
        sb.end();
    }
    public void UpdateGround(){
        if (camera.position.x - camera.viewportWidth/2 > ground1.x + ground.getWidth())
            ground1.add(ground.getWidth()*2 ,0);
        if (camera.position.x - camera.viewportWidth/2 > ground2.x + ground.getWidth())
            ground2.add(ground.getWidth()*2 ,0);
    }

    @Override
    public void create() {

    }

    @Override
    public void dispose() {
        bg.dispose();
        ground.dispose();
        bird.dispose();
        for (Item item: items)
            item.dispose();

    }
}
