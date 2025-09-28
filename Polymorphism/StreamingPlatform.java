// Base class
class Content {
    protected String title;

    public Content(String title) {
        this.title = title;
    }

    public void play() {
        System.out.println(" Playing content: " + title);
    }
}

// Movie class
class Movie extends Content {
    private double rating;
    private int duration; // in minutes
    private boolean subtitles;

    public Movie(String title, double rating, int duration, boolean subtitles) {
        super(title);
        this.rating = rating;
        this.duration = duration;
        this.subtitles = subtitles;
    }

    public void showMovieDetails() {
        System.out.println(" Movie: " + title + " | Rating: " + rating + 
                           " | Duration: " + duration + " mins | Subtitles: " + (subtitles ? "Yes" : "No"));
    }
}

// TV Series class
class TVSeries extends Content {
    private int seasons;
    private int episodes;

    public TVSeries(String title, int seasons, int episodes) {
        super(title);
        this.seasons = seasons;
        this.episodes = episodes;
    }

    public void suggestNextEpisode() {
        System.out.println(" TV Series: " + title + " | Next episode out of " + episodes + " episodes.");
    }
}

// Documentary class
class Documentary extends Content {
    private String[] tags;

    public Documentary(String title, String[] tags) {
        super(title);
        this.tags = tags;
    }

    public void showTags() {
        System.out.print(" Documentary: " + title + " | Tags: ");
        for (String tag : tags) {
            System.out.print(tag + " ");
        }
        System.out.println();
    }
}

// Test class
public class StreamingPlatform {
    public static void main(String[] args) {
        // Upcasting: All stored as Content
        Content[] watchlist = new Content[3];
        watchlist[0] = new Movie("Inception", 8.8, 148, true);
        watchlist[1] = new TVSeries("Breaking Bad", 5, 62);
        watchlist[2] = new Documentary("Planet Earth", new String[]{"Nature", "Wildlife", "Education"});

        // Play all contents (general method)
        for (Content c : watchlist) {
            c.play();
        }

        System.out.println("\n Accessing specific features using Downcasting:");
        for (Content c : watchlist) {
            if (c instanceof Movie) {
                ((Movie) c).showMovieDetails();
            } else if (c instanceof TVSeries) {
                ((TVSeries) c).suggestNextEpisode();
            } else if (c instanceof Documentary) {
                ((Documentary) c).showTags();
            }
        }
    }
}

