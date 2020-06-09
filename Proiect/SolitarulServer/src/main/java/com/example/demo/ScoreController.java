package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/api/score")
public class ScoreController {
    @Autowired
    private ScoreRepository scoreRepository;


    @GetMapping("/getTopTen")
    public @ResponseBody
    Iterable<Score> getTopTen() {
        return scoreRepository.findTop10ByOrderByScoreDesc();
    }

    @PostMapping("/addScore")
    public ResponseEntity<String>
    addPlayer(@RequestParam String name, @RequestParam int points) {
        Optional<Score> scoreOptional = scoreRepository.findByPlayerName(name);
        if (!scoreOptional.isPresent()) {
            Score score = new Score();
            score.setId((int) scoreRepository.count() + 1);
            score.setPlayerName(name);
            score.setScore(points);
            scoreRepository.save(score);
            return new ResponseEntity<>(
                    "Score created successfully", HttpStatus.CREATED);
        }
        Score score = scoreOptional.get();
        if(points > score.getScore()){
            score.setScore(points);
            scoreRepository.save(score);
        }
        return new ResponseEntity<>(
                "Player updated successsfully", HttpStatus.OK);

    }


}