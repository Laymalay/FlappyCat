package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

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
    protected abstract void handleInput();
    public abstract void update(float dt) ;
    public abstract void render(SpriteBatch sb);//предоставляет текстуру для рисования фигур sprite batch
    public abstract void dispose();
}
