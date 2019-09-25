package fetcher;

import domain.Thread;

import java.io.IOException;
import java.util.List;

public interface Fetcher {
    List<Thread> fetch(String subreddit) throws IOException;
}
