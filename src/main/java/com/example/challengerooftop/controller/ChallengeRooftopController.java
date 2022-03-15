package com.example.challengerooftop.controller;

import com.example.challengerooftop.model.AnalisysCriteria;
import com.example.challengerooftop.model.TextCriteria;
import com.example.challengerooftop.service.ITextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/text")
@CrossOrigin(value = "*")
public class ChallengeRooftopController {

    @Autowired
    private ITextService iTextService;

    @PostMapping(value = "/")
    public ResponseEntity<?> createText(@RequestBody TextCriteria textCriteria){
        System.out.println("INICIANDO PROCESO CON: "+ textCriteria.getSearchWord());
        return ResponseEntity.ok(iTextService.analyzerText(textCriteria));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getText(@PathVariable(value = "id") Integer id){
        return ResponseEntity.ok(iTextService.findById(id));
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getPaginator(@RequestBody AnalisysCriteria criteria){
        return ResponseEntity.ok(iTextService.findAllPaginator(criteria));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteText(@PathVariable(value = "id") Integer id){
        iTextService.deleteEntity(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Id "+id+" deleted successfully!");
        response.put("statusCode", 200);
        return ResponseEntity.ok(response);
    }
}
