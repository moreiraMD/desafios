package domain;

public class Thread {
    private final String subReddit;
    private final String title;
    private final int score;
    private final String uri;
    private final String commentsUri;

    public Thread(Builder builder) {
        this.subReddit = builder.subReddit;
        this.title = builder.title;
        this.score = builder.score;
        this.uri = builder.uri;
        this.commentsUri = builder.commentsUri;
    }

    public static class Builder {
        private String subReddit;
        private String title;
        private int score = 0;
        private String uri;
        private String commentsUri;

        public Builder subReddit(String subReddit) {
            this.subReddit = subReddit;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder score(int score) {
            this.score = score;
            return this;
        }

        public Builder uri(String uri) {
            this.uri = uri;
            return this;
        }

        public Builder commentsUri(String commentsUri) {
            this.commentsUri = commentsUri;
            return this;
        }

        public Thread build() {
            return new Thread(this);
        }
    }

    public String getSubReddit() {
        return subReddit;
    }

    public String getTitle() {
        return title;
    }

    public int getScore() {
        return score;
    }

    public String getUri() {
        return uri;
    }

    public String getCommentsUri() {
        return commentsUri;
    }

    @Override
    public String toString() {
        return "Thread{" +
                "subReddit='" + subReddit + '\'' +
                ", title='" + title + '\'' +
                ", score=" + score +
                ", uri='" + uri + '\'' +
                ", commentsUri='" + commentsUri + '\'' +
                '}';
    }
}
