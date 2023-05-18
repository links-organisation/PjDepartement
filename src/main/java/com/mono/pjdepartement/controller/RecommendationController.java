package com.mono.pjdepartement.controller;

import java.util.List;

import com.mono.pjdepartement.entity.metier.Recommendation;
import com.mono.pjdepartement.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/Recommendation")
public class RecommendationController {

	@Autowired
    RecommendationService recommendationService;
	
	@PostMapping(path = "/create")
    public ResponseEntity<String> createRecommendation(@RequestBody Recommendation recommendation){
        return recommendationService.create(recommendation);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<String> updateRecommendation(@RequestBody Recommendation recommendation, @PathVariable Long id){
        return recommendationService.update(recommendation, id);
    }

    @GetMapping(path = "/read")
    public List<Recommendation> readAllRecommendation(){
        return recommendationService.getAll();
    }

    @DeleteMapping(path = "/delete/{id}")
    public String deleteRecommendation(@PathVariable Long id){
        return recommendationService.deleteRecommendztion(id);
    }

}