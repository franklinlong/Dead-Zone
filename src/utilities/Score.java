/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

/**
 *
 * @author USER
 */
public class Score {

    private int score;
    private String player;

    public Score(String player, int score) {
        this.score = score;
        this.player = player;
    }

    public String getPlayer() {
        return this.player;
    }

    public int getScore() {
        return this.score;
    }

    @Override
    public String toString() {
        return this.getPlayer() + " " + Integer.toString(this.getScore());
    }

}
