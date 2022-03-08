package com.example.challengerooftop.controller;

import com.example.challengerooftop.service.ITextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/text")
public class ChallengeRooftopController {

    @Autowired
    private ITextService iTextService;

    @PostMapping(value = "/")
    public ResponseEntity<?> createText(@RequestParam("searchWord") String searchWord, @RequestParam("chars") int chars){

        return ResponseEntity.ok(iTextService.analyseText(searchWord, chars));
    }
}
