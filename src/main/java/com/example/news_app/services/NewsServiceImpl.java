package com.example.news_app.services;

import com.example.news_app.entity.News;
import com.example.news_app.entity.Origin;
import com.example.news_app.entity.Topic;
import com.example.news_app.exceptions.NotFoundException;
import com.example.news_app.repositories.NewsRepository;
import com.example.news_app.repositories.OriginRepository;
import com.example.news_app.repositories.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public List<News> getAllNews(PageRequest pageRequest) {
        Page<News> page = newsRepository.findAll(pageRequest);
        return page.getContent();
    }

    @Override
    public List<News> getNewsByTopicId(Long id, PageRequest pageRequest) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (optionalTopic.isEmpty()) {
            throw new NotFoundException("Topic does not exist");
        }
        Topic topic = optionalTopic.get();
        Page<News> page = newsRepository.findAllByTopic(topic, pageRequest);
        return page.getContent();
    }

    @Override
    public List<News> getNewsByOriginId(Long id, PageRequest pageRequest) {
        Optional<Origin> optionalOrigin = originRepository.findById(id);
        if (optionalOrigin.isEmpty()) {
            throw new NotFoundException("Origin does not exist");
        }
        Origin origin = optionalOrigin.get();
        Page<News> page = newsRepository.findAllByOrigin(origin, pageRequest);
        return page.getContent();
    }

    @Override
    public List<Origin> getAllOrigins(PageRequest pageRequest) {
        Page<Origin> page = originRepository.findAll(pageRequest);
        return page.getContent();
    }

    @Override
    public List<Topic> getAllTopic(PageRequest pageRequest) {
        Page<Topic> page = topicRepository.findAll(pageRequest);
        return page.getContent();
    }
}
