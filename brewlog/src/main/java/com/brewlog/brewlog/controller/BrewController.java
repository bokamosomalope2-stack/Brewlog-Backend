package com.brewlog.brewlog.controller;

import com.brewlog.brewlog.entity.Brewlog;
import com.brewlog.brewlog.service.BrewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brews")
@RequiredArgsConstructor
public class BrewController {
    private final BrewService brewService;

    @GetMapping
    public ResponseEntity<List<Brewlog>> getAllBrewlog(){
        return ResponseEntity.ok(brewService.getBrewlog());
    }

    @PostMapping
    public ResponseEntity<Brewlog> saveBrewlog(@RequestBody Brewlog brewlog){
        Brewlog response = brewService.createBrew(brewlog);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brewlog> getBrewlogById(@PathVariable Long id){
        return ResponseEntity.ok(brewService.getBrewlogById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brewlog> updateBrewlog(@PathVariable Long id, @RequestBody Brewlog brewlog){
        Brewlog update = brewService.updateBrew(id, brewlog);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrewlog(@PathVariable Long id){
        brewService.deleteBrew(id);
        return ResponseEntity.noContent().build();
    }
}
