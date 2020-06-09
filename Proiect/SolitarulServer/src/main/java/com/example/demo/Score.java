package com.example.demo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Score {
    private int id;
    private String playerName;
    private int score;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PLAYER_NAME")
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Basic
    @Column(name = "SCORE")
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return id == score1.id &&
                Objects.equals(playerName, score1.playerName) &&
                Objects.equals(score, score1.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, playerName, score);
    }
}
