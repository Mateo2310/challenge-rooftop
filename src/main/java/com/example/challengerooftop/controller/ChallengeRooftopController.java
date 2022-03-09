package com.example.challengerooftop.controller;

import com.example.challengerooftop.model.TextDto;
import com.example.challengerooftop.service.ITextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/text")
public class ChallengeRooftopController {

    @Autowired
    private ITextService iTextService;

    @PostMapping(value = "/")
    public ResponseEntity<?> createText(@RequestBody TextDto textDto){

        return ResponseEntity.ok(iTextService.createText(textDto));
    }
}
