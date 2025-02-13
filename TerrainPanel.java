import javax.swing.*;
import java.awt.*;

public class TerrainPanel extends JPanel {
    private WorldGenerator worldGenerator;
    private double[][] terrain;

    public TerrainPanel(WorldGenerator worldGenerator) {
        this.worldGenerator = worldGenerator;
        this.terrain = worldGenerator.generateTerrain();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        worldGenerator.renderTerrain(g, terrain);  // Render the terrain using the noise values
    }
}
