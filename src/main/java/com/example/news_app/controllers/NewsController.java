package com.example.news_app.controllers;

import com.example.news_app.entity.News;
import com.example.news_app.entity.Origin;
import com.example.news_app.entity.Topic;
import com.example.news_app.exceptions.NotFoundException;
import com.example.news_app.services.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/news")
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public List<News> getAllNews(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        return newsService.getAllNews(PageRequest.of(page, size));
    }

    @GetMapping("/topic")
    public List<Topic> getNewsTopics(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        return newsService.getAllTopic(PageRequest.of(page, size));
    }

    @GetMapping("/topic/{id}")
    public List<News> getNewsByTopicId(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        try {
            return newsService.getNewsByTopicId(id, PageRequest.of(page, size));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/origin")
    public List<Origin> getNewsOrigins(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        return newsService.getAllOrigins(PageRequest.of(page, size));
    }

    @GetMapping("/origin/{id}")
    public List<News> getNewsByOriginId(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        try {
            return newsService.getNewsByOriginId(id, PageRequest.of(page, size));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }
}
