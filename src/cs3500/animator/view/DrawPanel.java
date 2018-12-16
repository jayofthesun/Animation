package cs3500.animator.view;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import cs3500.animator.model.shape.Attributes;
import cs3500.animator.model.shape.ModelShape;

/**
 * A JPanel dedicated to drawing the list of shapes it contains.
 */
class DrawPanel extends JPanel {
  private List<ModelShape> shapes;

  public DrawPanel() {
    this.shapes = new ArrayList<>();
  }

  /**
   * Updates the panels shapes that it needs to draws.
   */
  public void setShapes(List<ModelShape> shapes) {
    this.shapes = shapes;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Attributes a;
    for (ModelShape s : this.shapes) {
      a = s.getAttributes();
      g.setColor(a.getColor());
      if (s.getType().equals("ellipse")) {
        g.fillOval(a.getX(), a.getY(), a.getWidth(), a.getHeight());
      } else if (s.getType().equals("rectangle")) {
        g.fillRect(a.getX(), a.getY(), a.getWidth(), a.getHeight());
      }
    }
  }
}