package com.example.news_app.repositories;

import com.example.news_app.entity.News;
import com.example.news_app.entity.Origin;
import com.example.news_app.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {

    Page<News> findAllByTopic(Topic topic, Pageable pageable);

    Page<News> findAllByOrigin(Origin origin, Pageable pageable);

}
