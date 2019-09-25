import domain.Thread;
import fetcher.Fetcher;
import fetcher.RedditThreadFetcher;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.IOException;
import java.util.Comparator;
import java.util.concurrent.Callable;

import static java.util.Arrays.stream;
import static picocli.CommandLine.Parameters;

@Command(name = "nothingtodo")
public class RedditCrawlerApp implements Callable {

    @Parameters(index = "0", description = "Subreddits you wanna see")
    private String subreddits;

    public static void main(String[] args) throws IOException {
        int exitSys = new CommandLine(new RedditCrawlerApp()).execute(args);
        System.exit(exitSys);
    }

    private static void print(Thread thread) {
        int size = (int) Math.log10(thread.getScore()) + 1;
        System.out.printf("%-" + size + "s   Title:    %s %s%n", "↑", thread.getTitle(), "(/r/" + thread.getSubReddit() + ")");
        System.out.printf("%-" + size + "s   Link:     %s%n", thread.getScore(), thread.getUri());
        System.out.printf("%-" + size + "s   Comments: %s%n", "↓", thread.getCommentsUri());
        System.out.println();
    }

    @Override
    public Integer call() throws Exception {
        Fetcher fetcher = new RedditThreadFetcher();

        String[] messages = this.subreddits.split(";");

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

        return 0;
    }
}
