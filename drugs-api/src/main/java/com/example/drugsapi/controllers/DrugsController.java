package com.example.drugsapi.controllers;
import com.example.drugsapi.models.Drug;
import com.example.drugsapi.repositories.DrugsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class DrugsController {
    @Autowired
    private DrugsRepository drugsRepository;
    @GetMapping("/")
    public Iterable<Drug> findAllDrugs() {
        return drugsRepository.findAll();
    }
    @GetMapping("/{drugsId}")
    public Drug findDrugById(@PathVariable Long drugsId) {
        return drugsRepository.findOne(drugsId);
    }
    @DeleteMapping("/{drugsId}")
    public HttpStatus deleteUserById(@PathVariable Long drugsId) {
        drugsRepository.delete(drugsId);
        return HttpStatus.OK;
    }
    @PostMapping("/")
    public Drug createNewUser(@RequestBody Drug newDrug) {
        return drugsRepository.save(newDrug);
    }
    @PatchMapping("/{drugsId}")
    public Drug updateDrugById(@PathVariable Long drugsId, @RequestBody Drug drugRequest) {
        Drug drugFromDb = drugsRepository.findOne(drugsId);
        drugFromDb.setName(drugRequest.getName());
        drugFromDb.setQuant(drugRequest.getQuant());
        return drugsRepository.save(drugFromDb);
    }
}