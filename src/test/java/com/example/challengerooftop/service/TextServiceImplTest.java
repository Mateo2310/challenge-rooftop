package com.example.challengerooftop.service;

import com.example.challengerooftop.model.TextCriteria;
import com.example.challengerooftop.repository.ITextDao;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TextServiceImplTest {

    @Test
    void analyzerText() {
        ITextDao textDao = Mockito.mock(ITextDao.class);
        IResultService resultService = Mockito.mock(IResultService.class);
        TextServiceImpl textService = new TextServiceImpl(textDao, resultService);
        TextCriteria textCriteria = new TextCriteria();
        textCriteria.setText("Hola");
        textCriteria.setChars(2);
        Map<String, Object> result = textService.analyzerText(textCriteria);

        System.out.println("Resultado -> "+result);
        assertTrue((result != null && !result.isEmpty()));
    }
}