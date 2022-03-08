package com.example.challengerooftop.service;

import com.example.challengerooftop.entity.Text;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TextServiceImpl implements ITextService{
    @Override
    public void createText(Text text) {

    }

    @Override
    public void deleteText(String id) {

    }

    @Override
    public Text findById(String id) {
        return null;
    }

    @Override
    public List<Text> findText() {
        return null;
    }

    @Override
    public String analyseText(String text, int chars) {
        Map<String, Integer> result = new HashMap<>();
        String word = "";
        int count = chars;
        for (char s : text.toCharArray()){
            if (text.length() < count){
                word = word+s;
            }else {
                count = count+chars;
            }

        }

        return "";
    }
}
