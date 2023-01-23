package com.example.news_app.services;

import com.example.news_app.entity.News;
import com.example.news_app.entity.Origin;
import com.example.news_app.entity.Topic;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface NewsService {

    List<News> getAllNews(PageRequest pageRequest);

    List<News> getNewsByTopicId(Long id, PageRequest of);

    List<News> getNewsByOriginId(Long id, PageRequest of);

    List<Origin> getAllOrigins(PageRequest of);

    List<Topic> getAllTopic(PageRequest of);
}
