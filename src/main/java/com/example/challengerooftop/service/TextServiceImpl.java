package com.example.challengerooftop.service;

import com.example.challengerooftop.entity.Result;
import com.example.challengerooftop.entity.Text;
import com.example.challengerooftop.model.TextDto;
import com.example.challengerooftop.repository.IResultDao;
import com.example.challengerooftop.repository.ITextDao;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class TextServiceImpl implements ITextService{

    ITextDao textDao;
    IResultDao resultDao;

    public TextServiceImpl(ITextDao textDao, IResultDao resultDao) {
        this.textDao = textDao;
        this.resultDao = resultDao;
    }

    @Override
    public Map<String, Object> createText(TextDto textDto) {
        String hashWord = textDto.getSearchWord()+textDto.getChars();
        Map<String, Object> textResponse = findByHash(hashWord);
        if (Objects.nonNull(textResponse) && !textResponse.isEmpty()){
            return textResponse;
        }
        Map<String, Integer> textResult = analyzeText(textDto);
        Text text = new Text();
        text.setChars(textDto.getChars());
        text.setHash(toHashMD5(hashWord));
        text.setResults(mapToResult(textResult));
        textDao.createText(text);
        textResponse = new HashMap<>();
        textResponse.put("id", text.getId());
        textResponse.put("url", "/text/"+text.getId());
        return textResponse;
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
    public Map<String, Object> findByHash(String hash) {
        Map<String, Object> response = new HashMap<>();
        Integer id = textDao.findByHash(hash);
        response.put("id", id);
        response.put("url", "/text/"+id);
        return response;
    }

    public Map<String, Integer> analyzeText(TextDto textDto) {
        List<String> result = new ArrayList<>();
        int end = textDto.getChars();
        String text = textDto.getSearchWord();
        char[] charsArray = text.toLowerCase(Locale.ROOT).toCharArray();
        for (int i = 0; i < charsArray.length; i++) {
            if (end <= charsArray.length){
                String word = text.substring(i, end);
                System.out.println("palabras: "+word);
                result.add(word);
                end++;
            }
        }

        return searchWordInText(result);
    }

    public Map<String, Integer> searchWordInText(List<String> separateWords){
        Map<String, Integer> results = new HashMap<>();
        for (String key:separateWords) {
            System.out.println("key: "+key);
            int count = 0;
            for (String key2:separateWords){
                System.out.println("key2: "+key2);
                if (key.equals(key2)){
                    count++;
                }
            }
            results.put(key, count);
        }

        return results;
    }

    public List<Result> mapToResult(Map<String, Integer> results){
        List<Result> resultList = new ArrayList<>();
        results.forEach((k, v)-> resultList.add(resultDao.createResult(new Result(k, v))));
        return resultList;
    }

    public String toHashMD5(String element){
        String hash = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(element.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            hash = DatatypeConverter.printHexBinary(digest).toLowerCase(Locale.ROOT);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hash;
    }


}
