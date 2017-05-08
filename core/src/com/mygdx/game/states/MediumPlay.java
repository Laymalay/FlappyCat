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
import com.mygdx.game.sprites.Crag;
import com.mygdx.game.sprites.Heart;
import com.mygdx.game.sprites.Item;
import com.mygdx.game.sprites.Killer;
import com.mygdx.game.sprites.Monster;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;

/**
 * Created by alinka on 5.5.17.
 */

public class MediumPlay extends State {

    public static final int ITEM_COUNT=6;
    public static final int GROUND_Y = -40;
    private Blur blur;
    private Texture bg,heart,ground;
    private Vector2 ground1,ground2, bg1, bg2;
    private Random rand;
    private BitmapFont font;
    private Array<Crag> crags;
    private Array<Item> items;
    private  int maxscore;



    public MediumPlay(GameStateManager gsm, int newmaxscore, Vector3 position) {
        super(gsm);
        blur = new Blur(50,300,120,-17);
        camera.setToOrtho(false, Flyingblur.WIDTH/2, Flyingblur.HEIGHT/2);
        maxscore = newmaxscore;
        bg =  new Texture("space.jpg");
        heart = new Texture("heart5.png");
        ground = new Texture("ground2.png");
        font = new BitmapFont();
        ground1 = new Vector2(camera.position.x - camera.viewportWidth/2,GROUND_Y);
        ground2 = new Vector2((camera.position.x - camera.viewportWidth/2)+ground.getWidth(),GROUND_Y);
        bg1 = new Vector2(camera.position.x - camera.viewportWidth/2,0);
        bg2 = new Vector2((camera.position.x - camera.viewportWidth/2)+bg.getWidth(),0);
        crags = new Array<Crag>();
        items = new Array<Item>();

        rand = new Random();
        for(int i = 0 ;i <4;i++){
            crags.add(new Crag(new Vector2(i*100,Flyingblur.HEIGHT-i*70)));
        }
        Item  item=null;
        rand = new Random();
        for(int i =0 ;i <ITEM_COUNT;i++){
            switch (i){
                case 0 :
                    item = new Heart(new Vector2(rand.nextInt(400),rand.nextInt(700)));
                    break;
                case 1:
                    item = new Monster(new Vector2(rand.nextInt(400),rand.nextInt(700))) ;
                    break;
                case 2:
                    item = new Killer(new Vector2(rand.nextInt(400),rand.nextInt(700))) ;
                    break;
                case 3 :
                    item = new Heart(new Vector2(rand.nextInt(400),rand.nextInt(700)));
                    break;
                case 4:
                    item = new Monster(new Vector2(rand.nextInt(400),rand.nextInt(700))) ;
                    break;
                case 5:
                    item = new Killer(new Vector2(rand.nextInt(400),rand.nextInt(700))) ;
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
    public void update(float dt) throws IOException, ClassNotFoundException {
        handleInput();
        UpdateGround();
        UpdateBG();
        blur.update(dt);
        camera.position.x=blur.getPosition().x+80;
        boolean b = false;

        for (int i=0; i< items.size; i++){
            Item item = items.get(i);
            for (int j=0; j< crags.size; j++) {
                Crag crag = crags.get(j);
            if (item.collides(crag.getBounds()))
                item.reposition(new Vector2(item.getPos().x+crag.getWidth(), item.getPos().y));
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
        for (int i=0; i< crags.size; i++){
            Crag crag = crags.get(i);
            if(camera.position.x - (camera.viewportWidth/2) > crag.getPos().x +
                    crag.getWidth()){
                crag.reposition(new Vector2(crag.getPos().x+400,300));
            }
            if(crag.collides(blur.getBounds())){
                blur.move();
                b=true;
                break;
//                rock.effect(bird);
            }
        }

        if (blur.getLife() ==0){
            b =true;
        }
        if (b){

            ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream("position.out"));
            oos2.writeObject(blur.getPosition());
            oos2.flush();
            oos2.close();

            gsm.set(new GameOver(gsm, blur.getScore()));
        }

        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, bg1.x, bg1.y);
        sb.draw(bg, bg2.x, bg2.y);
//        sb.draw(bg,camera.position.x -200,0, Flyingblur.WIDTH,Flyingblur.HEIGHT);
        font.draw(sb, " Score: "+(blur.getScore()),camera.position.x - (camera.viewportWidth/2),355);
        font.draw(sb, " MaxScore: "+ this.maxscore ,camera.position.x - (camera.viewportWidth/2)+130,355);
        for (int i=0;i<blur.getLife();i++){
            sb.draw(heart,camera.position.x - (camera.viewportWidth/2)+i*10,355);
        }


        for (Item  item:items){
            item.render(sb);
        }

        for (Crag  crag:crags) {
            crag.render(sb);
        }
        sb.draw(blur.getBird(),blur.getPosition().x,blur.getPosition().y);
//        sb.draw(ground, ground1.x, ground1.y);
//        sb.draw(ground, ground2.x, ground2.y);
        sb.end();
    }
    public void UpdateGround(){
        if (camera.position.x - camera.viewportWidth/2 > ground1.x + ground.getWidth())
            ground1.add(ground.getWidth()*2 ,0);
        if (camera.position.x - camera.viewportWidth/2 > ground2.x + ground.getWidth())
            ground2.add(ground.getWidth()*2 ,0);
    }
    public void UpdateBG(){
        if (camera.position.x - camera.viewportWidth/2 > bg1.x + bg.getWidth())
            bg1.add(bg.getWidth()*2 ,0);
        if (camera.position.x - camera.viewportWidth/2 > bg2.x + bg.getWidth())
            bg2.add(bg.getWidth()*2 ,0);
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
