package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Flyingblur;
import com.mygdx.game.sprites.Blur;
import com.mygdx.game.sprites.Cake;
import com.mygdx.game.sprites.Donut;
import com.mygdx.game.sprites.Heart;
import com.mygdx.game.sprites.IceMonster;
import com.mygdx.game.sprites.IceStone;
import com.mygdx.game.sprites.Item;
import com.mygdx.game.sprites.Rock;
import com.mygdx.game.sprites.Zombie;

import org.xmlpull.v1.XmlPullParserException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by alinka on 5.5.17.
 */

public class HardPlay  extends State{
    public static final int ITEM_COUNT=4;
    public static final int GROUND_Y = -40;
    private Blur blur;
    private Texture bg,heart,ground;
    private Vector2 ground1,ground2, bg1, bg2;
    private Random rand;
    private BitmapFont font;
    private Array<Rock> rocks;
    private Array<Item> items;
    private Array<IceStone> stones;
    private int maxscore;



    public HardPlay(GameStateManager gsm, int newmaxscore) {
        super(gsm);
        blur = new Blur(50,300,160,-20);
        camera.setToOrtho(false, Flyingblur.WIDTH/2, Flyingblur.HEIGHT/2);
        maxscore = newmaxscore;
        bg =  new Texture("bgice.png");
        heart = new Texture("heart5.png");
        ground = new Texture("ground1.png");
        font = new BitmapFont();
        ground1 = new Vector2(camera.position.x - camera.viewportWidth/2,GROUND_Y);
        ground2 = new Vector2((camera.position.x - camera.viewportWidth/2)+ground.getWidth(),GROUND_Y);
        rocks = new Array<Rock>();
        stones = new Array<IceStone>();
        items = new Array<Item>();
        rand = new Random();

        for(int i = 0 ;i <4;i++){
            rocks.add(new Rock(new Vector2(300+i*rand.nextInt(3)*20,-30)));
            stones.add(new IceStone(new Vector2(i*100,Flyingblur.HEIGHT-i*70)));
        }
        Item  item=null;
        for(int i =0 ;i <ITEM_COUNT;i++){
            switch (i){
                case 0 :
                    item = new Heart(new Vector2(rand.nextInt(400),rand.nextInt(700)));
                    break;
                case 1:
                    item = new IceMonster(new Vector2(rand.nextInt(400),rand.nextInt(700))) ;
                    break;
                case 2:
                    item = new Zombie(new Vector2(rand.nextInt(400),rand.nextInt(700))) ;
                    break;
                case 3 :
                    item = new Heart(new Vector2(rand.nextInt(400),rand.nextInt(700)));
                    break;
                case 4:
                    item = new Heart(new Vector2(rand.nextInt(400),rand.nextInt(700))) ;
                    break;
                case 5:
                    item = new IceMonster(new Vector2(rand.nextInt(400),rand.nextInt(700))) ;
                    break;
            }
            items.add(item);

        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            blur.jump();
        }
    }

    @Override
    public void update(float dt) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeyException, XmlPullParserException {
        handleInput();
        UpdateGround();
        blur.update(dt);
        camera.position.x=blur.getPosition().x+80;
        boolean b = false;

        for (int i=0; i< items.size; i++){
            Item item = items.get(i);
            rand=new Random();
            for (int j=0; j< rocks.size; j++) {
                Rock rock = rocks.get(j);
                if (item.collides(rock.getBounds()))
                    item.reposition(new Vector2(item.getPos().x+rock.getWidth(), item.getPos().y));
            }
            for (int j=0; j< stones.size; j++) {
                IceStone stone = stones.get(j);
                if (item.collides(stone.getBounds()))
                    item.reposition(new Vector2(item.getPos().x+stone.getWidth(), item.getPos().y));
            }
            if(camera.position.x - (camera.viewportWidth/2) > item.getPos().x +
                    item.getWidth()){
                item.reposition(new Vector2(item.getPos().x+400,rand.nextInt(360)));
            }
            if (item.collides(blur.getBounds())){
                item.reposition(new Vector2(item.getPos().x+400,rand.nextInt(360)));
                item.effect(blur);
            }
        }

        for (int i=0; i< rocks.size; i++) {
            Rock rock = rocks.get(i);
            if (camera.position.x - (camera.viewportWidth / 2) > rock.getPos().x +
                    rock.getWidth()) {
                rock.reposition(new Vector2(rock.getPos().x + 400, -30));
            }
            if (rock.collides(blur.getBounds())) {
                blur.move();
                b = true;
                break;
            }
        }
        for (int i=0; i< stones.size; i++) {
            IceStone stone = stones.get(i);
            if (camera.position.x - (camera.viewportWidth / 2) > stone.getPos().x +
                    stone.getWidth()) {
                stone.reposition(new Vector2(stone.getPos().x + 300, 330));
            }
            if (stone.collides(blur.getBounds())) {
                blur.move();
                b = true;
                break;
            }
        }


        if (blur.getLife() ==0){
            b =true;
        }
        if (b){
            gsm.set(new GameOver(gsm, blur.getScore(), maxscore));
        }

        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg,camera.position.x - (camera.viewportWidth/2),0);
        font.draw(sb, " Score: "+(blur.getScore()),camera.position.x - (camera.viewportWidth/2),355);
        font.draw(sb, " MaxScore: "+ this.maxscore ,camera.position.x - (camera.viewportWidth/2)+130,355);
        for (int i=0;i<blur.getLife();i++){
            sb.draw(heart,camera.position.x - (camera.viewportWidth/2)+i*10,355);
        }
        for (Item  item:items){
            item.render(sb);
        }
        for (Rock  rock:rocks) {
            rock.render(sb);
        }
        for (IceStone  stone:stones) {
            stone.render(sb);
        }
        sb.draw(blur.getBlur(),blur.getPosition().x,blur.getPosition().y);
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
    public void dispose() {
        bg.dispose();
        ground.dispose();
        blur.dispose();
        font.dispose();
        for (Item item: items)
            item.dispose();

    }
}
