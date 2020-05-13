/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;
 
import javax.swing.border.AbstractBorder;

/**
 *
 * @author miran
 */
public class RoundedBorder extends AbstractBorder {
    
    private List<Color> colors;
    private int thickness;
    private int cornersArc;
    private int borders;

    /**
     * Colores de los bordes.
     */
    private List<GradientPaint> gradients;
    /**
     * Formas del bordes.
     */
    private List<RoundRectangle2D> rectangles;

    /**
     * @param color
     * @param thickness
     * @param roundedCorners
     */
    public RoundedBorder(List<Color> colors, int thickness, int cornersArc, int borders) {
        this.colors = colors;
        this.thickness = thickness;
        this.cornersArc = cornersArc;
        // Mínimo un borde
        this.borders = borders > 0 ? borders : 1;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {

        createShapes(w, h, x, y);

        createGradients(c);

        Graphics2D g2d = ((Graphics2D) g);

        // Set a higher-quality rendering
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

        for (int i = 0; i < borders; i++) {
            g2d.setPaint(gradients.get(i));

            g2d.setStroke(new BasicStroke(thickness));

            RoundRectangle2D auxRect = rectangles.get(i);
            g2d.drawRoundRect((int) auxRect.getX(), (int) auxRect.getY(), (int) auxRect.getWidth(), (int) auxRect.getHeight(), (int) auxRect.getArcWidth(), (int) auxRect.getArcHeight());
        }

    }

    /**
     * Creamos los gradientes.
     *
     * @param c Component
     */
    private void createGradients(Component c) {

        gradients = new ArrayList<GradientPaint>();
        for (int i = 0; i < borders; i++) {
            Color color = colors.get(i);
            GradientPaint gra = new GradientPaint(0, 0, color.darker(),
                    0, c.getHeight(), color.brighter(), false);
            gradients.add(gra);
        }
    }

    /**
     * Creamos las formas del borde
     *
     * @param w ancho
     * @param h alto
     * @param x coordenada X
     * @param y coordenada Y
     */
    public void createShapes(int w, int h, int x, int y) {

        rectangles = new ArrayList<RoundRectangle2D>();
        int rectangleWidth = w, rectangleHeight = h;
        int xCoord = x;
        int yCoord = y;

        for (int i = 0; i < borders; i++) {
            if (i == 0) {
                xCoord += thickness / 2;
                yCoord += thickness / 2;
                rectangleWidth -= thickness;
                rectangleHeight -= thickness;
            } else {
                xCoord += thickness;
                yCoord += thickness;
                rectangleWidth -= 2 * thickness;
                rectangleHeight -= 2 * thickness;
            }

            RoundRectangle2D rectangle = new RoundRectangle2D.Float(xCoord, yCoord,
                    rectangleWidth, rectangleHeight, cornersArc, cornersArc);

            rectangles.add(rectangle);

        }

    }

    /**
     * Este método nos dice cuanto espacio necesita el borde para dibujarse.
     */
    @Override
    public Insets getBorderInsets(Component c) {
        Insets insets = super.getBorderInsets(c);
        insets.left = insets.top = insets.right = insets.bottom += thickness * borders;
        return insets;
    }

}
