// Interface
interface Playable {
    void play();
    void pause();
}

// MusicPlayer class implementing Playable
class MusicPlayer implements Playable {
    @Override
    public void play() {
        System.out.println("Music is now playing...");
    }

    @Override
    public void pause() {
        System.out.println("Music has been paused.");
    }
}

// VideoPlayer class implementing Playable
class VideoPlayer implements Playable {
    @Override
    public void play() {
        System.out.println("Video is now playing...");
    }

    @Override
    public void pause() {
        System.out.println("Video has been paused.");
    }
}

// Test class
public class PlayerTest {
    public static void main(String[] args) {
        // Polymorphism: using interface reference
        Playable ref;

        ref = new MusicPlayer();   // Playable reference -> MusicPlayer
        ref.play();
        ref.pause();
        System.out.println();

        ref = new VideoPlayer();   // Playable reference -> VideoPlayer
        ref.play();
        ref.pause();
    }
}
