package com.example.news_app.controllers;

import com.example.news_app.entity.News;
import com.example.news_app.entity.Origin;
import com.example.news_app.entity.Topic;
import com.example.news_app.exceptions.NotFoundException;
import com.example.news_app.services.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/news")
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public List<News> getAllNews() {
        return newsService.getAllNews();
    }

    @GetMapping("/topic")
    public List<Topic> getNewsTopics() {
        return newsService.getAllTopic();
    }

    @GetMapping("/topic/{id}")
    public List<News> getNewsByTopicId(@PathVariable Long id) {
        try {
            return newsService.getNewsByHeadingId(id);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/origin")
    public List<Origin> getNewsOrigins() {
        return newsService.getAllOrigins();
    }

    @GetMapping("/origin/{id}")
    public List<News> getNewsByOriginId(@PathVariable Long id) {
        try {
            return newsService.getNewsByOriginId(id);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }
}
