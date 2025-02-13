import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Create a WorldGenerator for terrain
        WorldGenerator worldGen = new WorldGenerator(Config.WIDTH, Config.HEIGHT, Config.PIXEL_SIZE, Config.SEED);
        
        // Set up JFrame and JPanel for displaying the world
        JFrame frame = new JFrame("OpenSimplex Noise Terrain");
        TerrainPanel panel = new TerrainPanel(worldGen);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Config.WIDTH, Config.HEIGHT); 
        frame.add(panel);
        frame.setVisible(true);
    }
}
