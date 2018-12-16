package cs3500.animator.model.shape;

import java.awt.Color;

/**
 * Interface to represent a shape's attributes (height, color, position, etc.).
 */

public interface Attributes {

  /**
   * Changes the shape attribute's size according to the given width and height.
   *
   * @param width  the new shape width
   * @param height the new shape height
   * @throws IllegalArgumentException if the width or height are negative
   */
  void changeShapeSize(int width, int height);

  /**
   * Sets the shape attribute's x and y to the given position.
   *
   * @param x the new x position of the shape
   * @param y the new y position of the shape
   */
  void moveShape(int x, int y);

  /**
   * Sets the new color of the shape.
   *
   * @param color the new color of the shape
   * @throws IllegalArgumentException if the given color is null
   */
  void setColor(Color color);

  /**
   * Gets the current color of shape.
   *
   * @return the color of the shape
   */
  Color getColor();

  /**
   * Gets the x value of the shape.
   *
   * @return the x position of the shape
   */
  int getX();

  /**
   * Gets the y value of the shape.
   *
   * @return the y position of the shape
   */
  int getY();

  /**
   * Gets the height of the shape.
   *
   * @return the height of the shape
   */
  int getHeight();

  /**
   * The width of the shape.
   *
   * @return the width of the shape
   */
  int getWidth();

  /**
   * Returns a copy of the attributes.
   * @return a copy of the attributes
   */
  Attributes copy();


}
