package service;

import domain.Thread;
import fetcher.Fetcher;
import fetcher.RedditThreadFetcher;

import java.io.IOException;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class CrawlerService {
    public String getTrendings(String subreddits) {
        Fetcher fetcher = new RedditThreadFetcher();

        String[] messages = subreddits.split(";");

        AtomicReference<String> trendings = new AtomicReference<>();
        StringBuilder stringBuilder = new StringBuilder();

        stream(messages).forEach(message -> {
            try {
                trendings.set(fetcher.fetch(message).stream()
                        .filter(thread -> thread.getScore() >= 5000)
                        .sorted(Comparator.comparing(Thread::getScore).reversed())
                        .map(this::format)
                        .collect(Collectors.joining("\n\n")));

                stringBuilder.append(trendings);
            } catch (IOException e) {
                throw new RuntimeException(String.format("Failed on try fetch subReddit %s.", message), e);
            }
        });

        return stringBuilder.toString();
    }

    private String format(Thread thread) {
        StringBuilder builder = new StringBuilder();

        return builder.append("Title: ").append(thread.getTitle()).append("\n")
                .append("Subreddit: ").append("/r/").append(thread.getSubReddit()).append("\n")
                .append("Link: ").append(thread.getUri()).append("\n")
                .append("Comments: ").append(thread.getCommentsUri()).append("\n")
                .append("Score: ").append(thread.getScore()).toString();
    }
}
