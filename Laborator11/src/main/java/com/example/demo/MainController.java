package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/api")
public class MainController {
    @Autowired
    private GamesRepository gamesRepository;

    @Autowired
    private PlayersRepository playersRepository;


    @GetMapping("/getAll")
    public @ResponseBody
    Iterable<Players> getAllPlayers() {
        return playersRepository.findAll();
    }

    @PostMapping("/addPlayer")
    public ResponseEntity<String>
    addPlayer(@RequestParam String name) {
        Players player = new Players();
        player.setId((int) playersRepository.count() + 1);
        player.setName(name);
        playersRepository.save(player);
        return new ResponseEntity<>(
                "Player created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(
            @PathVariable int id, @RequestParam String name) {
        Optional<Players> optionalPlayer = playersRepository.findById(id);

        if (optionalPlayer == null) {
            return new ResponseEntity<>(
                    "Player not found", HttpStatus.NOT_FOUND);
        }
        Players player = optionalPlayer.get();
        player.setName(name);
        playersRepository.save(player);
        return new ResponseEntity<>(
                "Player updated successsfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        Optional<Players> optionalPlayer = playersRepository.findById(id);
        if (optionalPlayer == null) {
            return new ResponseEntity<>(
                    "Player not found", HttpStatus.GONE);
        }
        Players player = optionalPlayer.get();
        playersRepository.delete(player);
        return new ResponseEntity<>("Player removed", HttpStatus.OK);
    }
}