package com.example.shorturl.controller;

import com.example.shorturl.service.UrlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultMatchersDsl;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import org.springframework.http.HttpHeaders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest(UrlController.class)
public class UrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlService urlService;

    @Test
    public void testRootPage() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/root"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("newpage"));
    }

    @Test
    public void testRedirect() throws Exception{
        String id = "HmK31D";
        String exampleUrl = "http://example.com";

        when(urlService.getUrl(id)).thenReturn(exampleUrl);

        mockMvc.perform(MockMvcRequestBuilders.get("/my/{id}",id))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.header().string(HttpHeaders.LOCATION, exampleUrl))
                .andExpect(MockMvcResultMatchers.header().string(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate"));
    }


}
