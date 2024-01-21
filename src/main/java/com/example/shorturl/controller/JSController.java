package com.example.shorturl.controller;

import com.example.shorturl.dto.UrlDTO;
import com.example.shorturl.dto.UrlResponseDTO;
import com.example.shorturl.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class JSController {

    private final UrlService urlService;
    @Value("${path.domen.site}")
    private String originalUrl;
    public JSController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/short")
    public UrlResponseDTO saveUrl(@RequestBody Map<String, String> data, Model model) {
        String shortUrl = urlService.save(new UrlDTO(data.get("url"), "", 0));
        UrlResponseDTO urlResponseDTO = new UrlResponseDTO(urlService.getCount(shortUrl), originalUrl, shortUrl);

        model.addAttribute("urlResponse", urlResponseDTO);

        return urlResponseDTO;
    }

    @GetMapping("/getClickCount/{shortUrl}")
    public long getClickCount(@PathVariable String shortUrl) {
        return urlService.getCount(shortUrl);
    }
}
