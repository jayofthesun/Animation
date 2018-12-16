package cs3500.animator.model.animation;

import java.awt.Color;

import cs3500.animator.model.shape.Attributes;
import cs3500.animator.model.shape.ShapeAttributes;
import cs3500.animator.model.shape.ModelShape;

/**
 * Describes a transformation to a shape based off of a start and end state.
 */
public class Motion extends AbstractAnimation {

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


  /**
   * Applies the motion to the shape.
   *
   * @param s the shape being animated
   * @param t the time
   * @throws IllegalArgumentException if the shape given is null or the time is not contained within
   *                                  the animation time
   */
  @Override
  public void applyAnimation(ModelShape s, int t) throws IllegalArgumentException {
    super.applyAnimation(s, t);
    Attributes a = new ShapeAttributes();
    a.setColor(new Color(this.getAverage(this.startShape.getColor().getRed(),
            this.endShape.getColor().getRed(), t),
            this.getAverage(this.startShape.getColor().getGreen(),
                    this.endShape.getColor().getGreen(), t),
            this.getAverage(this.startShape.getColor().getBlue(),
                    this.endShape.getColor().getBlue(), t)));

    a.changeShapeSize(this.getAverage(this.startShape.getWidth(), this.endShape.getWidth(), t),
            this.getAverage(this.startShape.getHeight(), this.endShape.getHeight(), t));

    a.moveShape(this.getAverage(this.startShape.getX(), this.endShape.getX(), t),
            this.getAverage(this.startShape.getY(), this.endShape.getY(), t));
    s.setAttributes(a);
    s.show();
  }

  /**
   * Gets the name of the animation.
   *
   * @return the type of animation
   */
  @Override
  public String getType() {
    return "motion";
  }

  @Override
  public Animation getCopy() {
    Animation copy = new Motion(this.startShape.copy(), this.endShape.copy(), this.start, this.end);
    return copy;
  }

  /**
   * Calculates the value of an attribute during a motion at the given t.
   *
   * @param x1 starting value
   * @param x2 end value
   * @param t  time
   * @return the value of the attribute at given t
   */
  protected int getAverage(int x1, int x2, int t) {
    return Math.round((float) (x2 - x1) / (this.end - this.start) * (t - this.start) + x1);
  }
  //changed the above method to protected to use for adappting class

}
