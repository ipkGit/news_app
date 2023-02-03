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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class Scheduler {
    private final OriginRepository originRepository;
    private final NewsRepository newsRepository;

    @Scheduled(cron = "30 44 10 * * *")
    public void csvWriter() {
        List<Origin> origins = originRepository.findAll();

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (Origin origin : origins) {
            Runnable task = () -> {
                String file = origin.getName() + ".csv";
                try (CSVWriter csvWriter = new CSVWriter(new FileWriter(file))) {
                    List<String[]> topicsFromOrigin = newsRepository.getCountNewsByTopicFromOrigin(origin.getId());
                    for (String[] topics : topicsFromOrigin) {
                        csvWriter.writeNext(topics, false);
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    System.out.println(Arrays.toString(e.getStackTrace()));
                }
            };
            executorService.execute(new Thread(task));
        }
        executorService.shutdown();
    }
}

//@RequiredArgsConstructor
//class WriterCSV implements Runnable {
//    private final Origin origin;
//    private final NewsRepository newsRepository;
//
//    @Override
//    public void run() {
//        String file = origin.getName() + ".csv";
//        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(file))) {
//            List<String[]> topicsFromOrigin = newsRepository.getCountNewsByTopicFromOrigin(origin.getId());
//            for (String[] topics : topicsFromOrigin) {
//                csvWriter.writeNext(topics, false);
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            System.out.println(Arrays.toString(e.getStackTrace()));
//        }
//    }
//}
