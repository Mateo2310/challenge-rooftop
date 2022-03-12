package com.example.challengerooftop.service;

import com.example.challengerooftop.entity.Result;
import com.example.challengerooftop.entity.Text;
import com.example.challengerooftop.execption.NotFound;
import com.example.challengerooftop.model.ResultDto;
import com.example.challengerooftop.model.TextDto;
import com.example.challengerooftop.model.TextResponseDto;
import com.example.challengerooftop.repository.ITextDao;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class TextServiceImpl implements ITextService {

    ITextDao textDao;
    IResultService resultService;

    public TextServiceImpl(ITextDao textDao, IResultService resultService) {
        this.textDao = textDao;
        this.resultService = resultService;
    }

    @Override
    public Map<String, Object> analyzerText(TextDto textDto) {
        System.out.println("ANALIZANDO TEXTO");
        String hashWord = textDto.getSearchWord()+textDto.getChars();
        Map<String, Object> textResponse = findByHash(hashWord);
        if (Objects.nonNull(textResponse.get("id"))){
            return textResponse;
        }
        Map<String, Integer> textResult = analyzeText(textDto);
        Text text = new Text();
        text.setChars(textDto.getChars());
        text.setHash(toHashMD5(hashWord));
        System.out.println("HASH: "+text.getHash());
        text.setResults(resultService.mapToResult(textResult));
        System.out.println("EL MAPEADO DE RESULTS ES EXITOSO");
        textDao.save(text);
        textResponse = new HashMap<>();
        textResponse.put("id", text.getId());
        textResponse.put("url", "/text/"+text.getId());
        return textResponse;
    }

    @Override
    public Text createEntity(Text text) {
        return textDao.save(text);
    }

    @Override
    public void deleteEntity(String id) {

    }

    @Override
    public TextResponseDto findById(Integer id) {
        Text text = textDao.findById(id).orElseThrow(()-> new NotFound("ID "+id+" not found"));
        return mapperToTextDto(text);
    }

    @Override
    public List<Text> findAll() {
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
        ResultDto resultDto = new ResultDto();
        for (String key:separateWords) {
            int count = 0;
            for (String key2:separateWords){
                if (key.equals(key2)){
                    count++;
                }
            }
            results.put(key, count);
        }

        return results;
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

    public TextResponseDto mapperToTextDto(Text entity){
        TextResponseDto textDto = new TextResponseDto();
        textDto.setId(entity.getId());
        textDto.setHash(entity.getHash());
        textDto.setChars(entity.getChars());
        Map<String, Integer> results = new HashMap<>();
        entity.getResults().forEach(result ->
          results.put(result.getSearchWord(), result.getMatchCount())
        );
        textDto.setResults(results);

        return textDto;
    }

}
