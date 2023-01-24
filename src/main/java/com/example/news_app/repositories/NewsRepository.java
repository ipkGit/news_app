package com.example.news_app.repositories;

import com.example.news_app.entity.News;
import com.example.news_app.entity.Origin;
import com.example.news_app.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    Page<News> findAllByTopic(Topic topic, Pageable pageable);

    Page<News> findAllByOrigin(Origin origin, Pageable pageable);

    @Query(value = "select t.name, count(n.topic_id) from News n left join Topic t ON n.topic_id = t.id " +
            "where n.origin_id = :origin " +
            "group by n.topic_id, t.name",
            nativeQuery = true)
    List<List<String>> getCountNewsByTopicFromOrigin(@Param("origin") Long origin);

}
