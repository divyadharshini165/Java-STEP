// Base class
class ArtWork {
    protected String title;
    protected String artist;
    protected int year;

    public ArtWork(String title, String artist, int year) {
        this.title = title;
        this.artist = artist;
        this.year = year;
    }

    public void displayInfo() {
        System.out.println("Artwork: " + title + " by " + artist + " (" + year + ")");
    }
}

// Painting class
class Painting extends ArtWork {
    private String brushTechnique;
    private String colorPalette;
    private String frame;

    public Painting(String title, String artist, int year, String brushTechnique, String colorPalette, String frame) {
        super(title, artist, year);
        this.brushTechnique = brushTechnique;
        this.colorPalette = colorPalette;
        this.frame = frame;
    }

    public void showPaintingDetails() {
        System.out.println(" Painting Details:");
        System.out.println("Brush Technique: " + brushTechnique);
        System.out.println("Color Palette: " + colorPalette);
        System.out.println("Frame: " + frame);
    }
}

// Sculpture class
class Sculpture extends ArtWork {
    private String material;
    private String dimensions;
    private String lighting;

    public Sculpture(String title, String artist, int year, String material, String dimensions, String lighting) {
        super(title, artist, year);
        this.material = material;
        this.dimensions = dimensions;
        this.lighting = lighting;
    }

    public void showSculptureDetails() {
        System.out.println(" Sculpture Details:");
        System.out.println("Material: " + material);
        System.out.println("Dimensions: " + dimensions);
        System.out.println("Lighting: " + lighting);
    }
}

// DigitalArt class
class DigitalArt extends ArtWork {
    private String resolution;
    private String fileFormat;
    private boolean interactive;

    public DigitalArt(String title, String artist, int year, String resolution, String fileFormat, boolean interactive) {
        super(title, artist, year);
        this.resolution = resolution;
        this.fileFormat = fileFormat;
        this.interactive = interactive;
    }

    public void showDigitalArtDetails() {
        System.out.println(" Digital Art Details:");
        System.out.println("Resolution: " + resolution);
        System.out.println("File Format: " + fileFormat);
        System.out.println("Interactive: " + (interactive ? "Yes" : "No"));
    }
}

// Photography class
class Photography extends ArtWork {
    private String cameraSettings;
    private String editingDetails;
    private String printSpecs;

    public Photography(String title, String artist, int year, String cameraSettings, String editingDetails, String printSpecs) {
        super(title, artist, year);
        this.cameraSettings = cameraSettings;
        this.editingDetails = editingDetails;
        this.printSpecs = printSpecs;
    }

    public void showPhotographyDetails() {
        System.out.println(" Photography Details:");
        System.out.println("Camera Settings: " + cameraSettings);
        System.out.println("Editing Details: " + editingDetails);
        System.out.println("Print Specifications: " + printSpecs);
    }
}

// Test class
public class DigitalArtGallery {
    public static void main(String[] args) {
        ArtWork[] gallery = new ArtWork[4];
        gallery[0] = new Painting("Sunset Bliss", "Alice", 2020, "Oil on Canvas", "Warm Tones", "Classic Frame");
        gallery[1] = new Sculpture("The Thinker", "Bob", 1880, "Bronze", "180x60x70 cm", "Spotlight");
        gallery[2] = new DigitalArt("Virtual Dreams", "Carol", 2023, "4K", "MP4", true);
        gallery[3] = new Photography("Nature Lens", "Dave", 2021, "f/2.8, 1/125s", "Lightroom edits", "A3 Print");

        // General info
        for (ArtWork art : gallery) {
            art.displayInfo();
        }

        System.out.println("\n Specific Details:");
        for (ArtWork art : gallery) {
            if (art instanceof Painting) {
                ((Painting) art).showPaintingDetails();
            } else if (art instanceof Sculpture) {
                ((Sculpture) art).showSculptureDetails();
            } else if (art instanceof DigitalArt) {
                ((DigitalArt) art).showDigitalArtDetails();
            } else if (art instanceof Photography) {
                ((Photography) art).showPhotographyDetails();
            }
            System.out.println("--------------------------------");
        }
    }
}

