package Utilidades;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ImagenRedonda implements Icon{
	private BufferedImage image;

    public ImagenRedonda(ImageIcon imageIcon) {
        int size = Math.min(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.setClip(new Ellipse2D.Float(0, 0, size, size));
        g2.drawImage(imageIcon.getImage(), 0, 0, size, size, null);
        g2.dispose();
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.drawImage(image, x, y, getIconWidth(), getIconHeight(), null);
    }

    @Override
    public int getIconWidth() {
        return image.getWidth();
    }

    @Override
    public int getIconHeight() {
        return image.getHeight();
    }
}
