package com.assignment.urlshortener.controller;

import com.assignment.urlshortener.dto.Request;
import com.assignment.urlshortener.service.UrlService;
import com.assignment.urlshortener.util.UrlConversionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;
    @GetMapping("/")
    public String viewHomePage(Model model) {
        Request request = new Request();
        model.addAttribute("request", request);
        return "index";
    }
    @PostMapping("/createUrl")
    public String createShortUrl(Model model, @ModelAttribute("request") Request request)
    {
        String result = urlService.convertToShortUrl(request);
        model.addAttribute("urlResult", UrlConversionUtil.baseURL+result);
        return "result";
    }
    @PostMapping("/getOriginalURL")
    public String getOriginalUrl(Model model, @ModelAttribute("request") Request request)
    {
        String result = urlService.getOriginalUrl(request.getUrlText()
                .substring(UrlConversionUtil.baseURL.length()));

        model.addAttribute("urlResult", Objects.requireNonNullElse(result, "URL Not Found."));
        return "result";
    }
}
