package cs3500.animator.model.adaptations;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import cs3500.animator.model.shape.Attributes;
import cs3500.animator.provider.model.IMotion;

/**
 * Adapter class that implements the behavior of an IShape using our implementation of ModelShape.
 * Represents a single shape in an animation.
 */
public class Motion extends cs3500.animator.model.animation.Motion implements IMotion {
  /**
   * Constructor for a motion animation.
   *
   * @param startShape the starting attributes of animation of shape
   * @param endShape   the ending attributes of animation of shape
   * @param start      start time
   * @param end        end time
   */
  public Motion(Attributes startShape, Attributes endShape, int start, int end) {
    super(startShape, endShape, start, end);
  }

  @Override
  public Dimension dimensionAtTime(int time) {
    return new Dimension(super.getAverage(this.getPrevWidth(), this.getNewWidth(), time),
            super.getAverage(this.getPrevHeight(), this.getNewHeight(), time));
  }

  @Override
  public Point positionAtTime(int time) {
    return new Point(super.getAverage(this.getPrevX(), this.getNewX(), time),
            super.getAverage(this.getPrevY(), this.getNewY(), time));
  }

  @Override
  public Color colorAtTime(int time) {
    return new Color(this.getAverage(this.startShape.getColor().getRed(),
            this.endShape.getColor().getRed(), time),
            this.getAverage(this.startShape.getColor().getGreen(),
                    this.endShape.getColor().getGreen(), time),
            this.getAverage(this.startShape.getColor().getBlue(),
                    this.endShape.getColor().getBlue(), time));
  }

  @Override
  public boolean timeIsWithin(int time) {
    return super.isContained(time);
  }

  @Override
  public boolean overlaps(IMotion motion) {
    return false; //doesn't need to be implemented
  }

  @Override
  public boolean consecutive(IMotion motion) {
    return super.linesUp((cs3500.animator.model.animation.Animation) motion);
  }

  @Override
  public int getStartTime() {
    return super.getStart();
  }

  @Override
  public int getEndTime() {
    return super.getEnd();
  }

  @Override
  public int getPrevX() {
    return super.getStartShape().getX();
  }

  @Override
  public int getPrevY() {
    return super.getStartShape().getY();
  }

  @Override
  public int getPrevWidth() {
    return super.getStartShape().getWidth();
  }

  @Override
  public int getPrevHeight() {
    return super.getStartShape().getHeight();
  }

  @Override
  public Color getPrevColor() {
    return super.getStartShape().getColor();
  }

  @Override
  public int getNewX() {
    return super.getEndShape().getX();
  }

  @Override
  public int getNewY() {
    return super.getEndShape().getY();
  }

  @Override
  public int getNewWidth() {
    return super.getEndShape().getWidth();
  }

  @Override
  public int getNewHeight() {
    return super.getEndShape().getHeight();
  }

  @Override
  public Color getNewColor() {
    return super.getEndShape().getColor();
  }
}
