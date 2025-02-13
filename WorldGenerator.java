import javax.swing.*;
import java.awt.*;

public class WorldGenerator {
    private int width, height;
    private int pixel_size;
    private int seed;
    private final int freq = Config.FREQ;
    
    public WorldGenerator(int width, int height, int pixel_size, int seed) {
        this.width = width/pixel_size;
        this.height = height/pixel_size;
        this.pixel_size = pixel_size;
        this.seed = seed;
    }
    
    public double[][] generateTerrain() {
        double[][] terrain = new double[width][height];
        
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Generating noise values for terrain
                double nx = x/((double) this.width);
                double ny = y/((double) this.height);

                double noiseValue = 1 * OpenSimplex2.noise2(this.seed, this.freq*nx, this.freq*ny) / 2.0 + 0.5;
                terrain[x][y] = shape(noiseValue, x, y);
            }
        }
        
        return terrain;
    }

    // This fonction add the Central island constraint to the noiseValue
    public double shape(double noiseValue, int x, int y){
        // Calculer distance
        double nx = 2*x/((double) width) - 1.0;
        double ny = 2*y/((double) height) - 1.0;

        double d = 1 - (1-nx * nx) * (1-ny * ny);
        double new_e = noiseValue + Config.ILE * (1 - d - noiseValue);
        return new_e; 

    }

    // A method to draw the terrain on a JPanel
    public void renderTerrain(Graphics g, double[][] terrain) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double value = terrain[x][y];
                
                // Set colors based on noise value
                if (value < 0.2) {
                    g.setColor(new Color(0, 35, 110));  // Water
                } else if (value < 0.45) {
                    g.setColor(new Color(41, 109, 255));  // Shallow water
                } else if (value < 0.48) {
                    g.setColor(new Color(237, 211, 38));  // Sand
                } else if (value < 0.70) {
                    g.setColor(new Color(16, 194, 57));  // Grass
                } else if (value < 0.85) {
                    g.setColor(new Color(3, 102, 23));  // Forest
                } else if (value < 0.90) {
                    g.setColor(new Color(139, 137, 137));  // Mountains
                } else {
                    g.setColor(new Color(255,255,255));  // Snow
                } 

                // Draw a rectangle for each terrain cell
                g.fillRect(x * pixel_size, y * pixel_size, pixel_size, pixel_size);
            }
        }
    }
}
