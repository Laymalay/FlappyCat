package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
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

/**
 * Created by alinka on 5.5.17.
 */

public class About extends State{
    private Texture background;
    private BitmapFont font;
    About(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg3.png");
        camera.setToOrtho(false, Flyingblur.WIDTH/2, Flyingblur.HEIGHT/2);
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(3);
//        System.out.println("--------------");
//        for (int i=0; i< scores.scorelist.size();i++){
//            System.out.println(scores.scorelist.get(i));
//        }
    }



    @Override
    protected void handleInput() throws IOException, ClassNotFoundException {
        if (Gdx.input.justTouched()){
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    public void update(float dt) throws IOException, ClassNotFoundException {
        handleInput();
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb = new SpriteBatch();
        sb.begin();
        sb.draw(background,0,0, Flyingblur.WIDTH, Flyingblur.HEIGHT );
        font.draw(sb,"ABOUT",100,100);
        sb.end();
    }



    @Override
    public void dispose() {
        background.dispose();
        font.dispose();
    }
}
