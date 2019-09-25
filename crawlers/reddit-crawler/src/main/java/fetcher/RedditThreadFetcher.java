package fetcher;

import domain.Thread;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class RedditThreadFetcher implements Fetcher {

    private static final String REDDIT_URI = "https://old.reddit.com";

    @Override
    public List<Thread> fetch(String subreddit) throws IOException {
        Document document = Jsoup.connect(REDDIT_URI + "/r/" + subreddit).get();

        Elements elements = document.body().select("div.thing");

        List<Thread> threads = new ArrayList<>();

        elements.forEach(element -> {

            Elements titles = element.getElementsByAttributeValue("data-event-action", "title");

            Elements scores = element.getElementsByClass("score unvoted");

            if (!titles.isEmpty() && !scores.isEmpty()) {
                Thread.Builder builder = new Thread.Builder();

                Element title = titles.first();
                Element score = scores.first();

                String subReddit = element.attr("data-subreddit");
                builder.subReddit(subReddit);

                String titleText = title.text();
                builder.title(titleText);

                String uri = title.attr("href");

                String dataPermalink = element.attr("data-permalink");

                String redditUri = uri.startsWith("/") ? REDDIT_URI + uri : uri;
                builder.uri(redditUri);

                String commentsUri = REDDIT_URI + dataPermalink;
                builder.commentsUri(commentsUri);

                int scoreValue;
                try {
                    scoreValue = parseInt(score.attr("title"));
                    builder.score(scoreValue);
                } catch (NumberFormatException e) {
                    return;
                }

                threads.add(builder.build());
            }
        });

        return threads;
    }
}
