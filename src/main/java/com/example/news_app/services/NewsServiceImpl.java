package com.example.news_app.services;

import com.example.news_app.entity.News;
import com.example.news_app.entity.Origin;
import com.example.news_app.entity.Topic;
import com.example.news_app.repositories.NewsRepository;
import com.example.news_app.repositories.OriginRepository;
import com.example.news_app.repositories.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    private final TopicRepository topicRepository;

    private final OriginRepository originRepository;

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public List<News> getNewsByHeadingId(Long id) {
        Optional<Topic> optionalHeading = topicRepository.findById(id);
        if (optionalHeading.isEmpty()) {
            throw new NotFoundException("Topic does not exist");
        }
        Topic topic = optionalHeading.get();
        return topic.getNews();
    }

    @Override
    public List<News> getNewsByOriginId(Long id) {
        Optional<Origin> optionalOrigin = originRepository.findById(id);
        if (optionalOrigin.isEmpty()) {
            throw new NotFoundException("Origin does not exist");
        }
        Origin origin = optionalOrigin.get();
        return origin.getNews();
    }

    @Override
    public List<Origin> getAllOrigins() {
        return originRepository.findAll();
    }

    @Override
    public List<Topic> getAllTopic() {
        return topicRepository.findAll();
    }
}
