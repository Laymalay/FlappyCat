package com.mygdx.game.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Flyingblur;
import java.io.*;
import java.util.*;
/**
 * Created by alinka on 26.2.17.
 */

public class GameOver extends State {
    private Texture background;
    private Texture gameover;
    private BitmapFont font;
    private int _newscore;
    private int _maxscore;
    private ButtonForStates menu_button;
    private Vector3 touchPos;
    private Music lose,win;

    public GameOver(GameStateManager gsm, int newscore,int maxscore) throws IOException, ClassNotFoundException {
        super(gsm);
        background = new Texture("bg3.png");
        gameover = new Texture("gameover.png");
        camera.setToOrtho(false, Flyingblur.WIDTH / 2, Flyingblur.HEIGHT / 2);
        _newscore = newscore;
        _maxscore = maxscore;
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(3);
        SaveScores(newscore);
        menu_button = new ButtonForStates(100, 30, 280, 100);
        touchPos = new Vector3();
        lose = Gdx.audio.newMusic(Gdx.files.internal("gameover.mp3"));
        win = Gdx.audio.newMusic(Gdx.files.internal("win1.wav"));
        if (_newscore > _maxscore) {
            win.play();
        } else
            lose.play();
    }
    @Override
    protected void handleInput() throws IOException, ClassNotFoundException {
        if(menu_button.Activated())
            gsm.set(new MenuState(gsm));
    }

    @Override
    public void update(float dt) throws IOException, ClassNotFoundException {
        handleInput();
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Flyingblur.HEIGHT - Gdx.input.getY(), 0);
            if (menu_button.checkIfClicked(touchPos.x, touchPos.y)) {
                menu_button.is_pressed = true;
                menu_button.was_pressed = true;
            }
        }
        else menu_button.is_pressed=false;
        menu_button.UpdateTexture();
        sb = new SpriteBatch();
        sb.begin();
        sb.draw(background,0,0, Flyingblur.WIDTH, Flyingblur.HEIGHT );
        sb.draw(gameover,70,400, 350,300);
        if (_newscore > _maxscore ){
            font.draw(sb, "NEW RECORD,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   ", 80,300);
            font.draw(sb, Flyingblur.pLayer_name, 80,240);
            font.draw(sb, ""+_newscore, 80,200);
        }
        else {
            font.draw(sb, Flyingblur.pLayer_name, 80, 240);
            font.draw(sb, "" + _newscore, 80, 200);
        }
        menu_button.draw(sb);
        font.draw(sb,"menu",180,100);
        sb.end();
    }

    public void SaveScores(int score)throws IOException, ClassNotFoundException{
        FileHandle file = Gdx.files.local("score.out");
        ScoreList list = new ScoreList();
        InputStream is = file.read();
        if (file.length() != 0){
            ObjectInputStream oin = new ObjectInputStream(is);
            list = (ScoreList) oin.readObject();
            oin.close();
        }
        list.add(score, Flyingblur.pLayer_name);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Gdx.files.local("score.out").file()));
        oos.writeObject(list);
        oos.flush();
        oos.close();
    }

    @Override
    public void dispose() {
        background.dispose();
        gameover.dispose();
        font.dispose();
        lose.dispose();
        win.dispose();
    }
}
