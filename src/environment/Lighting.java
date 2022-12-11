package environment;

import main.GamePanel;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Lighting {
    GamePanel gp;
    BufferedImage darknessFilter;

    public Lighting(GamePanel gp, int circleSize) {
        this.gp = gp;

        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();

        Area screenArea = new Area(new Rectangle2D.Double(0,0,gp.screenWidth, gp.screenHeight));
        int centerX = gp.player.screenX + (gp.tileSize)/2;
        int centerY = gp.player.screenY + (gp.tileSize)/2;

        double x = centerX - (circleSize/2);
        double y = centerY - (circleSize/2);

        Shape circleShape = new Ellipse2D.Double(x, y, circleSize, circleSize);

        Area ligntArea = new Area(circleShape);

        screenArea.subtract(ligntArea);

        g2.setColor(new Color(0,0,0,00.95f));

        g2.fill(screenArea);
        g2.dispose();
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(darknessFilter, 0,0, null);
    }
}
