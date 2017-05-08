package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by alinka on 26.2.17.
 */

public class Blur {
    public int movement;
    private int gravity;
    private Vector3 position;
    private  Vector3 velosity;//вектор скорости
    private int life;
    private  int Score=0;
    private Animation blurAnimation;
    private Texture blur;
    private Sound flap;

    public void setScore(int score) {
        this.Score +=score;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public int getScore() {
        return Score;
    }

    public void setLife(int life) {
        if (life<=9){
        this.life = life;}
        if (life<0){
            this.life=0;
        }
    }

    public void set_jump(int _jump) {

        this._jump = _jump;
    }

    public int getLife() {

        return life;
    }

    public int get_jump() {
        return _jump;
    }

    private int _jump;
    private Rectangle bounds;

    public Blur(int x, int y, int _movement, int _gravity){
        gravity = _gravity;
        movement = _movement;
        position = new Vector3(x,y,0);
        velosity = new Vector3(0,0,0);
        blur =  new Texture("ani.png");
        blurAnimation = new Animation(new TextureRegion(blur), 3, 0.5f);
        bounds = new Rectangle(x, y, blur.getWidth() /3, blur.getHeight());
        life=9;
        _jump=250;
        flap = Gdx.audio.newSound(Gdx.files.internal("up3.wav"));
    }
    public TextureRegion getBlur() {
        return blurAnimation.getFrame();
    }

    public Vector3 getPosition() {
        return position;
    }


    public void update(float dt){
        blurAnimation.update(dt);
        if(position.y>0)
            velosity.add(0, gravity,0);// добавляет значение к вектору (конст гравити к коорд y)
        velosity.scl(dt);// умнож вектор скорости на скаляр промежутка времени
        position.add(movement *dt,velosity.y,0);
        if(position.y<0){
            position.y= 0;
        }
        if(position.y>365){
            position.y= 365;
        }

        velosity.scl(1/dt);
        bounds.setPosition(position.x,position.y);

    }

    public Rectangle getBounds() {
        return bounds;
    }



    public  void jump(){
        flap.play();
        velosity.y= _jump;
    }
    public  void jump(int x){
        velosity.y= x;
    }
    public  void move(){ position.x +=200;}
    public void dispose() {
        blur.dispose();
        flap.dispose();

    }
}
