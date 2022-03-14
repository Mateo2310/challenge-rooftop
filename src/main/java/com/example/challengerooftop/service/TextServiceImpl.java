package com.example.challengerooftop.service;

import com.example.challengerooftop.entity.Result;
import com.example.challengerooftop.entity.Text;
import com.example.challengerooftop.exception.NotFoundTextException;
import com.example.challengerooftop.model.*;
import com.example.challengerooftop.repository.ITextDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TextServiceImpl implements ITextService {

    ITextDao textDao;
    IResultService resultService;

    public TextServiceImpl(ITextDao textDao, IResultService resultService) {
        this.textDao = textDao;
        this.resultService = resultService;
    }

    @Override
    public Map<String, Object> analyzerText(TextCriteria textCriteria) {
        String hashWord = textCriteria.getSearchWord()+ textCriteria.getChars();
        Map<String, Object> textResponse = findByHash(toHashMD5(hashWord));
        if (Objects.nonNull(textResponse.get("id"))){
            return textResponse;
        }
        int chars = textCriteria.getChars();
        if (chars > textCriteria.getSearchWord().length()){
            textCriteria.setChars(textCriteria.getSearchWord().length());
        }
        List<ResultCriteria> textResult = separedText(textCriteria);
        Text text = new Text();
        text.setChars(chars);
        text.setHash(toHashMD5(hashWord));
        text.setResults(resultService.mapToResult(textResult));
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
    public void deleteEntity(Integer id) {
        Text textDelete = textDao.findById(id).orElseThrow(()-> new NotFoundTextException("Id "+id+" not found"));
        textDelete.setDeleted(true);
        textDao.save(textDelete);
    }

    @Override
    public TextDto findById(Integer id) {
        Text text = textDao.findById(id).orElseThrow(()-> new NotFoundTextException("ID "+id+" not found"));
        return mapperToTextDto(text);
    }

    @Override
    public PaginatorTextDto findAllPaginator(AnalisysCriteria criteria) {
        normalizeAnalisysCriteria(criteria);
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getRpp());
        Page<Text> pageEntity = textDao.findAllByChars(pageable, criteria.getChars());
        return Optional.ofNullable(mapToPaginatorDto(pageEntity))
                .orElseThrow(() -> new NotFoundTextException("Not found"));
    }


    private Map<String, Object> findByHash(String hash) {
        Map<String, Object> response = new HashMap<>();
        Integer id = textDao.findByHash(hash);
        response.put("id", id);
        response.put("url", "/text/"+id);
        return response;
    }

    private List<ResultCriteria> separedText(TextCriteria textCriteria) {
        textCriteria.setSearchWord(textCriteria.getSearchWord().toLowerCase());
        List<String> result = new ArrayList<>();
        int end = textCriteria.getChars();
        String text = textCriteria.getSearchWord();
        char[] charsArray = text.toLowerCase(Locale.ROOT).toCharArray();
        for (int i = 0; i < charsArray.length; i++) {
            if (end <= charsArray.length){
                String word = text.substring(i, end);
                result.add(word);
                end++;
            }
        }

        return searchWordInText(result);
    }

    private List<ResultCriteria> searchWordInText(List<String> separateWords){
        Map<String, Integer> results = new HashMap<>();
        ResultCriteria result;
        List<ResultCriteria> listResult = new ArrayList<>();
        int position = 0;
        for (String key:separateWords) {
            int count = 0;
            result = new ResultCriteria();
            for (String key2:separateWords){
                if (key.equals(key2)){
                    count++;
                }
            }
            result.setSearchWord(key);
            result.setMatchCount(count);
            result.setPosition(position);
            results.put(key, count);
            position++;
            listResult.add(result);
        }

        return listResult;
    }

    private String toHashMD5(String element){
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

    private TextDto mapperToTextDto(Text entity){
        TextDto textDto = new TextDto();
        textDto.setId(entity.getId());
        textDto.setHash(entity.getHash());
        textDto.setChars(entity.getChars());
        textDto.setResults(order(entity.getResults()));

        return textDto;
    }

    private Map<String, Object> order(List<Result> results){
        Map<String, Object> response = new LinkedHashMap<>();
        for (int f = 0; f < results.size(); f++){
            int i = 0;
            while (i<results.size()) {
                int position = results.get(i).getPosition();
                if (f == position){
                    String searchWord = results.get(i).getSearchWord();
                    Integer count = results.get(i).getMatchCount();
                    response.put(searchWord, count);

                    break;
                }
                i++;
            }
        }

        return response;
    }

    private PaginatorTextDto mapToPaginatorDto(Page<Text> pageEntity){
        PaginatorTextDto pageDto = new PaginatorTextDto();
        if (pageEntity.getContent().isEmpty()){
            return null;
        }
        pageDto.setTotalPages(pageEntity.getTotalPages());
        pageDto.setTotalElements(pageEntity.getTotalElements());
        pageDto.setSizePage(pageEntity.getSize());
        List<TextDto> textDtoList = pageEntity.getContent().stream()
                .map(this::mapperToTextDto)
                .collect(Collectors.toList());
        pageDto.setListTextDto(textDtoList);

        return pageDto;
    }

    private void normalizeAnalisysCriteria(AnalisysCriteria criteria){
        criteria.setPage(criteria.getPage()-1);
        if (criteria.getChars() < 2){
            criteria.setChars(2);
        }
        if (criteria.getRpp() < 10){
            criteria.setRpp(10);
        }else if (criteria.getRpp() > 100){
            criteria.setRpp(100);
        }
        if (criteria.getPage() < 1){
            criteria.setPage(0);
        }

    }
}
