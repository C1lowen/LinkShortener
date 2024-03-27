package com.example.shorturl.controller;

import com.example.shorturl.dto.UrlDTO;
import com.example.shorturl.repository.UrlRepository;
import com.example.shorturl.service.UrlService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

@WebMvcTest(JSController.class)
public class JSControllerTest {
//ds
    @Value("${path.domen.site}")
    private String domen;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlService urlService;

    @Test
    public void testSaveUrl() throws Exception {
        Map<String, String> requestData = new HashMap<>();
        requestData.put("url", "http://example.com");
        when(urlService.save(new UrlDTO(requestData.get("url"), "", 0))).thenReturn("randomUrl");

        mockMvc.perform(MockMvcRequestBuilders.post("/short")
                        .content(asJsonString(requestData))
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.shortUrl", not("Not Found")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.originalUrl").value(domen));
    }

    @Test
    public void testGetClickCount() throws Exception {
        String shortUrl = "Hdm3Ad";
        when(urlService.getCount(shortUrl)).thenReturn(2L);

        mockMvc.perform(MockMvcRequestBuilders.get("/getClickCount/{shortUrl}", shortUrl))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("2"));
    }



    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
