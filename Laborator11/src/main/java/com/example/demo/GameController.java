package com.example.demo;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/api/game")
public class GameController {
    @Autowired
    private GamesRepository gamesRepository;

    @Autowired
    private PlayersRepository playersRepository;


    @GetMapping("/getAll")
    public @ResponseBody
    Iterable<Games> getAllGames() {
        return gamesRepository.findAll();
    }

    @PostMapping("/addGame")
    public ResponseEntity<String>
    addPlayer(@RequestParam String content, long playersNumber) throws JSONException {
        Games game = new Games();
        game.setId((int)gamesRepository.count() + 1);
        game.setContent(content);
        game.setPlayersNumber(playersNumber);
        gamesRepository.save(game);
        JSONObject response = new JSONObject();
        response.put("id", game.getId());
        response.put("content", game.getContent());
        response.put("result", game.getResult());
        response.put("playersNumber", game.getPlayersNumber());
        return new ResponseEntity<>(
                response.toString(), HttpStatus.CREATED);
    }

}