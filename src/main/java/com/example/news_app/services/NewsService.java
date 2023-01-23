package com.example.news_app.services;

import com.example.news_app.entity.News;
import com.example.news_app.entity.Origin;
import com.example.news_app.entity.Topic;

import java.util.List;

public interface NewsService {

    List<News> getAllNews();

    List<News> getNewsByHeadingId(Long id);

    List<News> getNewsByOriginId(Long id);

    List<Origin> getAllOrigins();

    List<Topic> getAllTopic();
}
