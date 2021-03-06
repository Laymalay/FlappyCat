package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

class MyTextInputListener implements Input.TextInputListener {
	public String name = null;
	@Override
	public void input (String text) {
        name = text;
	}
	@Override
	public void canceled () {
	}
}
public class Flyingblur extends ApplicationAdapter {
	public static final int WIDTH=480;
	public static final int HEIGHT=800;
	public static final String TITLE="Flappy Bird";
	private GameStateManager gsm;
	private  SpriteBatch batch;
	private Music music;
	public MyTextInputListener listener;
	public static String pLayer_name;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("music.wav"));
		music.setLooping(true);
		music.setVolume(0.5f);
		listener = new  MyTextInputListener();
		Gdx.input.getTextInput(listener, "Enter your name", "name", "");
		Gdx.gl.glClearColor(255, 255, 255, 255);
        music.play();
		try {
			gsm.push(new MenuState(gsm));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void render () {
        if (listener.name!=null){
            pLayer_name = listener.name;
        }
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		try {
			gsm.update(Gdx.graphics.getRawDeltaTime());//возвр время между посл и текущим кадром в сек
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        try {
			gsm.render(batch);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void dispose () {
		super.dispose();
		music.dispose();
	}
}
