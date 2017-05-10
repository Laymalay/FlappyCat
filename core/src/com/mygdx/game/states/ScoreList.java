package com.mygdx.game.states;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by alinka on 10.5.17.
 */

class Player implements Serializable{
    public int score;
    public String name;
    public Player(String _name, int _score){
        name = _name;
        score = _score;
    }
}

class ScoreList implements Serializable {
    ArrayList<Player> scorelist;
    public ScoreList(){
        scorelist = new ArrayList<Player>();
    }

    public void add(int newscore, String name) {
        scorelist.add(new Player(name, newscore));
    }

    public Player getmax(){
        Player max = new Player(" ",0);
        for (int i = 0; i< scorelist.size(); i++){
            if (scorelist.get(i).score>max.score){
                max = scorelist.get(i);
            }
        }
        return max;
    }
}
