package com.example.challengerooftop.controller;

import com.example.challengerooftop.model.TextDto;
import com.example.challengerooftop.service.ITextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/text")
public class ChallengeRooftopController {

    @Autowired
    private ITextService iTextService;

    @PostMapping(value = "/")
    public ResponseEntity<?> createText(@RequestBody TextDto textDto){
        System.out.println("INICIANDO PROCESO CON: "+textDto.getSearchWord());
        return ResponseEntity.ok(iTextService.analyzerText(textDto));
    }

    @GetMapping(value = "/{textId}")
    public ResponseEntity<?> getText(@PathVariable(value = "textId") Integer id){
        if (iTextService.findById(id)!=null)
           return ResponseEntity.ok(iTextService.findById(id));
        Map<String, String> response = new HashMap<>();
        response.put("statusCode", "404");
        response.put("Message", "id "+id+" not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
