package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;

import java.io.IOException;

public class FlappyCat extends ApplicationAdapter {
	public static final int WIDTH=480;
	public static final int HEIGHT=800;
	public static final String TITLE="Flappy Bird";

	private GameStateManager gsm;

	private  SpriteBatch batch;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		try {
			gsm.update(Gdx.graphics.getRawDeltaTime());//возвр время между посл и текущим кадром в сек
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		gsm.render(batch);//отрисовывает верхний экран в стеке
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
