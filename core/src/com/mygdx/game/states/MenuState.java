package com.mygdx.game.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Flyingblur;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by alinka on 26.2.17.
 */

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
    private GetObject getter_scores;


    public MenuState(GameStateManager gsm) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeyException, XmlPullParserException {
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
//        scores = LoadScores();
        getter_scores = new GetObject();
        scores = getter_scores.get();
        maxscore = scores.getmax().score;
    }

    @Override
    protected void handleInput() {
        if(easy_button.Activated())
           gsm.set(new EasyPlay(gsm, maxscore));
        if(medium_button.Activated())
            gsm.set(new MediumPlay(gsm, maxscore));
        if(hard_button.Activated())
            gsm.set(new HardPlay(gsm, maxscore));
        if(about_button.Activated())
            gsm.set(new About(gsm, scores));

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
      font.draw(sb,"champion",160,100);
      sb.end();
    }


    public ScoreList LoadScores()throws IOException, ClassNotFoundException{
        FileHandle file = Gdx.files.local("score.out");
        ScoreList list = new ScoreList();
        InputStream is = file.read();
        if (file.length() != 0){
            ObjectInputStream oin = new ObjectInputStream(is);
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

