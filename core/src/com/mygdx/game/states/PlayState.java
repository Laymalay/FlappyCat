package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.FlappyDemo;
import com.mygdx.game.sprites.Bird;
import com.mygdx.game.sprites.Item;
import com.mygdx.game.sprites.Item;
import com.mygdx.game.sprites.Rock;


import java.util.Random;

/**
 * Created by alinka on 26.2.17.
 */

public class PlayState extends State {

    public static final int ITEM_SPACING = 100;
    public static final int ROCK_RANGE=400;
    public static final int ITEM_COUNT=6;
    private Bird bird;
    private Texture bg,heart;

    private Item  item;
    private Rock rock;
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


        for(int i = 0 ;i <ITEM_COUNT;i++){
            rand = new Random();
            rocks.add(new Rock(i*( rand.nextInt(ROCK_RANGE) + Rock.ROCK_WIDTH)));
        }


        for(int i =0 ;i <ITEM_COUNT;i++){
            items.add(new Item(i*(ITEM_SPACING + Item.ITEM_WIDTH)));
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
        for (Item item:items){
            if(camera.position.x - (camera.viewportWidth/2) > item.getPosCake().x +
                    item.getCake().getWidth()){
                item.reposition(item.getPosCake().x + ((Item.ITEM_WIDTH+ITEM_SPACING)*ITEM_COUNT));
            }
            if(item.collides(bird.getBounds(),bird)){
                ;
            }

        }
        for (Rock rock:rocks){
            if(camera.position.x - (camera.viewportWidth/2) > rock.getPosRock().x +
                    rock.getRock().getWidth()){
                rock.reposition(rock.getPosRock().x + ((Rock.ROCK_WIDTH+ITEM_SPACING)*ITEM_COUNT));
            }
            if(rock.collides(bird.getBounds())){

                bird.setLife(bird.getLife()-1);

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
            sb.draw(item.getCake(),item.getPosCake().x,item.getPosCake().y);//tube.getPosTopTube().x->tube.getPosBottomTube().x
            sb.draw(item.getPizza(),item.getPosPizza().x,item.getPosPizza().y);
            sb.draw(item.getHeart(),item.getPosHeart().x,item.getPosHeart().y);
        }
        for (Rock  rock:rocks){
            sb.draw(rock.getRock(),rock.getPosRock().x,rock.getPosRock().y);
        }


        sb.draw(bird.getBird(),bird.getPosition().x,bird.getPosition().y);




        sb.end();
    }

    @Override
    public void dispose() {

    }
}
