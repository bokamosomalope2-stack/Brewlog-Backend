package com.brewlog.brewlog.service;

import com.brewlog.brewlog.entity.Brewlog;
import com.brewlog.brewlog.repository.BrewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrewService {

    private final BrewRepository brewRepository;



    public List<Brewlog> getBrewlog() {
        return brewRepository.findAll();
    }

    public Brewlog getBrewlogById(Long id) {
        Brewlog brewlog = brewRepository.findById(id).orElse(null);
        return brewRepository.findById(id).get();
    }

//    public Brewlog getBrewlogByName(String name) {
//        return brewRepository.findByNameIgnoreCase(name).get();
//    }

    public Brewlog createBrew(Brewlog brewlog) {
        return brewRepository.save(brewlog);
    }

    public Brewlog updateBrew(Long id, Brewlog brewlog) {
        Brewlog existingBrewlog = brewRepository.findById(id).get();

        existingBrewlog.setBeans(brewlog.getBeans());
        existingBrewlog.setMethod(brewlog.getMethod());
        existingBrewlog.setCoffeeGrams(brewlog.getCoffeeGrams());
        existingBrewlog.setWaterGrams(brewlog.getWaterGrams());
        existingBrewlog.setRating(brewlog.getRating());
        existingBrewlog.setTestingNotes(brewlog.getTestingNotes());

        return brewRepository.save(existingBrewlog);
    }

    public void deleteBrew(Long id) {
        Brewlog delete = brewRepository.findById(id).get();
        brewRepository.delete(delete);
    }

}
