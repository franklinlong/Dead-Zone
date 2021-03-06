/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone.utilities;

import java.io.*;
import java.util.*;

/**
 *
 * @author niko and angelo
 */
public class Scoreboard {

    private final String FILENAME = "resources/scoreboard/scores.txt";
    private final int SIZE = 10;
    private LinkedList<Score> scoreboard;

    public Scoreboard() {
        this.scoreboard = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line = null;

            while ((line = br.readLine()) != null) {
                String[] tmp = line.split(" ");
                scoreboard.add(new Score(tmp[0], Integer.parseInt(tmp[1])));
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public List<Score> getScoreboard() {
        return this.scoreboard;
    }

    public void addScore(String player, int score) {
        if(this.scoreboard.size() == this.SIZE){
            if(score <= this.scoreboard.getLast().getScore()){
                return;
            }else{
                this.scoreboard.removeLast();
            }
        }

        int index = this.findGT(score);
        this.scoreboard.add(index, new Score(player, score));

        this.saveScoreboard();
    }

    public void saveScoreboard() {
        System.out.println("Salvataggio risultato...");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            for (Score item : this.scoreboard) {
                bw.write(item.toString());
                bw.newLine();
                bw.flush();
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    private int findGT(int score) {
        for (Score item : this.scoreboard) {
            if (score >= item.getScore()) {
                return this.scoreboard.indexOf(item);
            }
        }
        return this.scoreboard.size();
    }

}
