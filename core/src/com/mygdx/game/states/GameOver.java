package com.mygdx.game.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.FlappyCat;
import java.io.*;
import java.util.*;
/**
 * Created by alinka on 26.2.17.
 */
class ScoreList implements Serializable {
    ArrayList<Integer> scorelist = new ArrayList<Integer>();

    public void add(int newscore) {
        scorelist.add(newscore);

    }
    public int pop(int i){
        return scorelist.get(i);
    }
}
class GameOver extends State {
    private Texture background;
    private Texture gameover;
    private BitmapFont font;
    private  ArrayList<Integer> scores;

    GameOver(GameStateManager gsm, int newscore) throws IOException, ClassNotFoundException {
        super(gsm);
//        background = new Texture("bg3.png");
        gameover = new Texture("bg3.png");
        scores = UpdateScore(newscore);


    }

    protected ArrayList<Integer> UpdateScore(int score)throws IOException, ClassNotFoundException{
        File file = new File("score.out");
        if (!file.exists()){
            file.createNewFile();
        }

        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));
        ArrayList<Integer> list = (ArrayList<Integer>) oin.readObject();
        oin.close();
        list.add(score);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(list);
        oos.flush();
        oos.close();
        return list;
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
//        sb.draw(background,0,0);
          sb.draw(gameover,camera.position.x ,camera.position.y);
//        sb.draw(gameover, camera.position.x - gameover.getWidth()/2,camera.position.y);
        font = new BitmapFont();
        for (int i=0; i< scores.size();i++){
            font.draw(sb, " " + scores.get(i) ,camera.position.x ,camera.position.y);
        }

        sb.end();
    }

    @Override
    public void create() {

    }

    @Override
    public void dispose() {
//        background.dispose();
        gameover.dispose();
    }
}
