import java.time.LocalDateTime;

// Base Post class
class Post {
    protected String author;
    protected String content;
    protected LocalDateTime time;

    public Post(String author, String content, LocalDateTime time) {
        this.author = author;
        this.content = content;
        this.time = time;
    }

    public void displayPost() {
        System.out.println("Generic Post by " + author + " at " + time);
        System.out.println("Content: " + content);
    }
}

// InstagramPost class
class InstagramPost extends Post {
    private int likes;
    private String hashtags;

    public InstagramPost(String author, String content, LocalDateTime time, int likes, String hashtags) {
        super(author, content, time);
        this.likes = likes;
        this.hashtags = hashtags;
    }

    @Override
    public void displayPost() {
        System.out.println("Instagram Post by " + author);
        System.out.println("Posted on: " + time);
        System.out.println("Content: " + content);
        System.out.println("Hashtags: " + hashtags);
        System.out.println("Likes: " + likes);
        System.out.println("---------------------------------");
    }
}

// TwitterPost class
class TwitterPost extends Post {
    private int retweets;

    public TwitterPost(String author, String content, LocalDateTime time, int retweets) {
        super(author, content, time);
        this.retweets = retweets;
    }

    @Override
    public void displayPost() {
        System.out.println("Twitter Post by " + author);
        System.out.println("Posted on: " + time);
        System.out.println("Content (" + content.length() + " chars): " + content);
        System.out.println("Retweets: " + retweets);
        System.out.println("---------------------------------");
    }
}

// LinkedInPost class
class LinkedInPost extends Post {
    private int connections;

    public LinkedInPost(String author, String content, LocalDateTime time, int connections) {
        super(author, content, time);
        this.connections = connections;
    }

    @Override
    public void displayPost() {
        System.out.println("LinkedIn Post by " + author);
        System.out.println("Posted on: " + time);
        System.out.println("Content (Professional): " + content);
        System.out.println("🔗 Connections Engaged: " + connections);
        System.out.println("---------------------------------");
    }
}

// Test class
public class SocialMediaFeed {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();

        Post insta = new InstagramPost("Alice", "Enjoying the sunset!", now, 120, "#sunset #vibes");
        Post twitter = new TwitterPost("Bob", "Coding in Java is fun!", now, 45);
        Post linkedin = new LinkedInPost("Charlie", "Excited to start a new role at TechCorp!", now, 300);

        // Polymorphism in action
        insta.displayPost();
        twitter.displayPost();
        linkedin.displayPost();
    }
}
