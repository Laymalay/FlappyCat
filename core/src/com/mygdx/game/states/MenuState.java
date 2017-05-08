package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Flyingblur;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by alinka on 26.2.17.
 */

class ScoreList implements Serializable {
    ArrayList<Integer> scorelist;
    public ScoreList(){
        scorelist = new ArrayList<Integer>();
    }


    public void add(int newscore) {
        scorelist.add(newscore);

    }
    public int getmax(){
        int max =0;
        for (int i = 0; i< scorelist.size(); i++){
            if (scorelist.get(i)>max){
                max = scorelist.get(i);
            }
        }
        return max;
    }
}


public class MenuState extends State {
    private Texture background;
    private ButtonForStates easy_button;
    private ButtonForStates medium_button;
    private ButtonForStates hard_button;
    private ButtonForStates about_button;
    private Vector3 touchPos;

    protected ScoreList scores;
    private int maxscore;

    private BitmapFont font;


    public MenuState(GameStateManager gsm) throws IOException, ClassNotFoundException {
        super(gsm);
        camera = new OrthographicCamera();
        background = new Texture("bg3.png");
        easy_button = new ButtonForStates(100,630,280,100);
        medium_button = new ButtonForStates(100,430,280,100);
        hard_button = new ButtonForStates(100,230,280,100);
        about_button = new ButtonForStates(100,30,280,100);
        touchPos = new Vector3();
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(3);
        scores = LoadScores();
        maxscore = scores.getmax();
    }



    @Override
    protected void handleInput() {
        if(easy_button.Activated())
           gsm.set(new EasyPlay(gsm, maxscore, null));
        if(medium_button.Activated())
            gsm.set(new MediumPlay(gsm, maxscore, null));
        if(hard_button.Activated())
            gsm.set(new HardPlay(gsm, maxscore, null));
        if(about_button.Activated())
            gsm.set(new About(gsm));

    }

    @Override
    public void update(float dt) throws InterruptedException {
        handleInput();
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Flyingblur.HEIGHT-Gdx.input.getY(), 0);
            if (easy_button.checkIfClicked(touchPos.x,touchPos.y)){
                easy_button.is_pressed=true;
                easy_button.was_pressed = true;
            }
            if (medium_button.checkIfClicked(touchPos.x,touchPos.y)){
                medium_button.is_pressed=true;
                medium_button.was_pressed = true;
            }
            if (hard_button.checkIfClicked(touchPos.x,touchPos.y)){
                hard_button.is_pressed=true;
                hard_button.was_pressed = true;
            }
            if (about_button.checkIfClicked(touchPos.x,touchPos.y)){
                about_button.is_pressed=true;
                about_button.was_pressed = true;
            }
        }
        else {
            easy_button.is_pressed=false;
            medium_button.is_pressed=false;
            hard_button.is_pressed=false;
            about_button.is_pressed=false;
        }
      easy_button.UpdateTexture();
      medium_button.UpdateTexture();
      hard_button.UpdateTexture();
      about_button.UpdateTexture();
      sb = new SpriteBatch();
      sb.begin();
      sb.draw(background,0,0, Flyingblur.WIDTH, Flyingblur.HEIGHT );
      easy_button.draw(sb);
      medium_button.draw(sb);
      hard_button.draw(sb);
      about_button.draw(sb);
      font.draw(sb,"easy",180,700);
      font.draw(sb,"medium",170,500);
      font.draw(sb,"hard",180,300);
      font.draw(sb,"about",180,100);
      sb.end();
    }


    public ScoreList LoadScores()throws IOException, ClassNotFoundException{
        File file = new File("score.out");
        if (!file.exists()){
            file.createNewFile();
        }

        ScoreList list = new ScoreList();
        FileInputStream fis= new FileInputStream(file);
        if (file.length() != 0){
            ObjectInputStream oin = new ObjectInputStream(fis);
            list = (ScoreList) oin.readObject();
            oin.close();
        }
        return list;
    }

    @Override
    public void dispose(){
      background.dispose();
      font.dispose();
      easy_button.dispose();
      medium_button.dispose();
      about_button.dispose();
      hard_button.dispose();

    }
}

