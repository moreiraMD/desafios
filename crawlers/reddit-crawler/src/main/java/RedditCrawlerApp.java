import domain.Thread;
import fetcher.Fetcher;
import fetcher.RedditThreadFetcher;

import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;

import static java.util.Arrays.stream;

public class RedditCrawlerApp {

    public static void main(String[] args) throws IOException {
        Fetcher fetcher = new RedditThreadFetcher();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite um os subreddits separados por ';' (ex: cats;dogs;food):");


        String subReddits = scanner.nextLine();
        String[] messages = subReddits.split(";");


        stream(messages).forEach(message -> {
            try {
                fetcher.fetch(message).stream()
                        .filter(thread -> thread.getScore() >= 5000)
                        .sorted(Comparator.comparing(Thread::getScore).reversed())
                        .forEach(RedditCrawlerApp::print);
            } catch (IOException e) {
                throw new RuntimeException(String.format("Failed on try fetch subReddit %s.", message), e);
            }
        });
    }

    private static void print(Thread thread) {
        int size = (int) Math.log10(thread.getScore()) + 1;
        System.out.printf("%-" + size + "s   Title:    %s %s%n", "↑", thread.getTitle(), "(/r/" + thread.getSubReddit() + ")");
        System.out.printf("%-" + size + "s   Link:     %s%n", thread.getScore(), thread.getUri());
        System.out.printf("%-" + size + "s   Comments: %s%n", "↓", thread.getCommentsUri());
        System.out.println();
    }
}
