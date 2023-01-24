package com.example.news_app.schedul;

import com.example.news_app.entity.Origin;
import com.example.news_app.repositories.NewsRepository;
import com.example.news_app.repositories.OriginRepository;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class Scheduler {
    private final OriginRepository originRepository;

    private final NewsRepository newsRepository;

    @Scheduled(cron = "0 55 * * * *")
    public void csvWriter() {
        List<Origin> origins = originRepository.findAll();
        for (Origin origin : origins) {
            WriterCSV writerCSV = new WriterCSV(origin, newsRepository);
            Thread thread = new Thread(writerCSV);
            thread.start();
        }
    }
}

@RequiredArgsConstructor
class WriterCSV implements Runnable {
    private final Origin origin;
    private final NewsRepository newsRepository;

    @Override
    public void run() {
        String file = origin.getName() + ".csv";
        try (FileWriter fileWriter = new FileWriter(file)) {
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            List<List<String>> topicsFromOrigin = newsRepository.getCountNewsByTopicFromOrigin(origin.getId());
            for (List<String> topics : topicsFromOrigin) {
                 String[] array = topics.toArray(new String[0]);
                 csvWriter.writeNext(array);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
