package com.example.game2048.Model;

/**
 * Created by Administrator on 2018/2/5.
 * description:描述用户信息，用户的bean
 */

public class Gamer {

    private  int id;
    private String name;
    private int score;

    public Gamer(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
