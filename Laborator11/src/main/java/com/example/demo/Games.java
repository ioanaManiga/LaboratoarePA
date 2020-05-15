package com.example.demo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Games {
    private int id;
    private String content;
    private String result;
    private Long playersNumber;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CONTENT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "RESULT")
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Basic
    @Column(name = "PLAYERS_NUMBER")
    public Long getPlayersNumber() {
        return playersNumber;
    }

    public void setPlayersNumber(Long playersNumber) {
        this.playersNumber = playersNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Games games = (Games) o;
        return id == games.id &&
                Objects.equals(content, games.content) &&
                Objects.equals(result, games.result) &&
                Objects.equals(playersNumber, games.playersNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, result, playersNumber);
    }
}
