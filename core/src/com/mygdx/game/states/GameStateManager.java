package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Stack;

/**
 * Created by alinka on 26.2.17.
 */

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager() {
        states = new Stack<State>();
    }

    public void push(State state) {
        states.push(state);
    }

    public void pop() {
        states.pop().dispose();
    }

    public void set(State state) {
        states.pop().dispose();
        states.push(state);
    }
    public  void  update(float dt) throws IOException, ClassNotFoundException, InterruptedException, NoSuchAlgorithmException, InvalidKeyException, XmlPullParserException {//через промежутки времени дельта обновляет
        states.peek().update(dt);// peek - возвращает верхний эл не удал его при этом
    }
    public void render (SpriteBatch sb) throws InterruptedException {
        states.peek().render(sb);
    }
}
