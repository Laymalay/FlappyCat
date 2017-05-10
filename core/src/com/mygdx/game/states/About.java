package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Flyingblur;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by alinka on 5.5.17.
 */

public class About extends State{
    private Texture background, cup;
    private BitmapFont font;
    private ScoreList scores;
    private Vector3 touchPos;
    private ButtonForStates menu_button;
    About(GameStateManager gsm, ScoreList _scores) {
        super(gsm);
        scores = _scores;
        background = new Texture("bg3.png");
        camera.setToOrtho(false, Flyingblur.WIDTH/2, Flyingblur.HEIGHT/2);
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        cup = new Texture("cup.png");
        menu_button = new ButtonForStates(100, 100, 280, 100);
        touchPos = new Vector3();
    }



    @Override
    protected void handleInput() throws IOException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeyException, XmlPullParserException {
        if(menu_button.Activated())
            gsm.set(new MenuState(gsm));
    }

    @Override
    public void update(float dt) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeyException, XmlPullParserException {
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
        sb.draw(background,0,0, Flyingblur.WIDTH, Flyingblur.HEIGHT);
        sb.draw(cup, 0 , 200 , 480,600);
        font.setColor(Color.BLACK);
        font.getData().setScale(2);
        font.draw(sb," "+scores.getmax().name,180,310);
        font.draw(sb," "+ scores.getmax().score ,180,280);
        font.setColor(Color.WHITE);
        font.getData().setScale(2);
        font.draw(sb,"Author: Alina Zhukovskaya",60,70);
        menu_button.draw(sb);
        font.getData().setScale(3);
        font.draw(sb,"menu",180,170);
        sb.end();
    }



    @Override
    public void dispose() {
        background.dispose();
        font.dispose();
    }
}
