package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by alinka on 5.5.17.
 */


public class ButtonForStates{
    private Sprite skin;
    private Texture button_pressed_texture;
    private Texture button_unpressed_texture;
    public boolean is_pressed = false;
    public boolean was_pressed = false;
    private float _x,_y, _width,_height;
    private Sound sound;

    public ButtonForStates(float x, float y, float width, float height) {
        button_pressed_texture = new Texture("buttonpress.png");
        button_unpressed_texture = new Texture("button.png");
        skin = new Sprite(button_unpressed_texture ); // your image
        skin.setPosition(x, y);
        skin.setSize(width, height);
        _x=x;
        _y=y;
        _width = width;
        _height = height;
        sound =  Gdx.audio.newSound(Gdx.files.internal("button.mp3"));
    }


    public void draw(SpriteBatch sb) {
        skin.draw(sb);
    }
    public void UpdateTexture() {
        if (is_pressed) {
            skin.set(new Sprite(button_pressed_texture));
            skin.setPosition(_x, _y);
            skin.setSize(_width, _height);
        }
    }
    boolean checkIfClicked(float ix, float iy) {
        if (ix > skin.getX() && ix < skin.getX() + skin.getWidth()) {
            if (iy > skin.getY() && iy < (skin.getY() + skin.getHeight())) {
                return true;
            }
        }
        return false;
    }
    public void dispose(){
        button_pressed_texture.dispose();
        button_unpressed_texture.dispose();
    }
    public boolean Activated(){
        if (!is_pressed && was_pressed) {
            sound.play();
            return true;
        }
        else
            return false;
    }
}