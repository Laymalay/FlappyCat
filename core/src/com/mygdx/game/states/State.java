package com.mygdx.game.states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by alinka on 26.2.17.
 */

public abstract class State {
    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    public State (GameStateManager gsm){
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();

    }
    protected abstract void handleInput() throws IOException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeyException, XmlPullParserException;
    public abstract void update(float dt) throws IOException, ClassNotFoundException, InterruptedException, NoSuchAlgorithmException, InvalidKeyException, XmlPullParserException;
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
