package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by alinka on 26.2.17.
 */

public class Blur {
    private int movement;
    private int gravity;
    private Vector3 position;
    private  Vector3 velosity;//вектор скорости
    private int life;
    private  int Score=0;

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
        if (life<=6){
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

    transient private Texture blur =  new Texture("ani5.png");;

    public Blur(int x, int y, int _movement, int _gravity){
        gravity = _gravity;
        movement = _movement;
        position = new Vector3(x,y,0);
        velosity = new Vector3(0,0,0);

        bounds = new Rectangle(x,y, blur.getWidth(),blur.getHeight());
        life=3;
        _jump=250;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getBird() {
        return blur;
    }

    public void update(float dt){
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
        velosity.y= _jump;
    }
    public  void jump(int x){
        velosity.y= x;
    }
    public  void move(){ position.x +=200;}
    public void dispose() {
        blur.dispose();

    }
}
