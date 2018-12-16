package cs3500.animator.model.shape;

import java.awt.Color;

/**
 * A class that represents a shape's attributes.
 */
public class ShapeAttributes implements Attributes {
  private Color color;
  private int x;
  private int y;
  private int height;
  private int width;

  /**
   * Constructor that takes in five parameters for the shape's attributes.
   *
   * @param color  color of the shape
   * @param x      x position of the shape
   * @param y      y position of the shape
   * @param width  width of the shape
   * @param height height of the shape
   * @throws IllegalArgumentException if the given color is null, the width or height is not valid
   */
  public ShapeAttributes(Color color, int x, int y, int width, int height)
          throws IllegalArgumentException {
    if (color == null) {
      throw new IllegalArgumentException("null color");
    }
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("invalid width or height");
    }
    this.x = x;
    this.y = y;
    this.color = color;
    this.width = width;
    this.height = height;
  }

  /**
   * Default constructor of shapeAttributes with all values set to 0 (Black for Color).
   */
  public ShapeAttributes() {
    this(Color.BLACK, 0, 0, 0, 0);
  }

  @Override
  public void changeShapeSize(int width, int height) throws IllegalArgumentException {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("invalid width or height");
    }
    this.width = width;
    this.height = height;
  }

  @Override
  public void moveShape(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public void setColor(Color color) throws IllegalArgumentException {
    if (color == null) {
      throw new IllegalArgumentException("null color");
    }

    this.color = color;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public Attributes copy() {
    return new ShapeAttributes(
            new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue()),
            this.x, this.y, this.width, this.height);
  }

  @Override
  public int hashCode() {
    return this.x + this.y + this.width + this.height + 2 * this.color.getGreen()
            + 3 * this.color.getRed() + 5 * this.color.getBlue();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof ShapeAttributes) {
      ShapeAttributes a = (ShapeAttributes) o;
      return this.color.equals(a.getColor())
              && this.x == a.getX() && this.y == a.getY()
              && this.height == a.getHeight()
              && this.width == a.getWidth();
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("X:");
    sb.append(this.x);
    sb.append(" Y:");
    sb.append(this.y);
    sb.append(" Width:");
    sb.append(this.width);
    sb.append(" Height:");
    sb.append(this.height);
    sb.append(" Red:");
    sb.append(this.color.getRed());
    sb.append(" Green:");
    sb.append(this.color.getGreen());
    sb.append(" Blue:");
    sb.append(this.color.getBlue());

    return sb.toString();
  }
}
