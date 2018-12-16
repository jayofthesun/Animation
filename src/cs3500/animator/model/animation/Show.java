package cs3500.animator.model.animation;

import cs3500.animator.model.shape.Attributes;
import cs3500.animator.model.shape.ModelShape;

/**
 * A n animation that shows the shape/prevents it from being drawn.
 */

public class Show extends AbstractAnimation {

  /**
   * Constructor for a show animation.
   *
   * @param start start time
   * @param end   end time
   */
  public Show(Attributes state, int start, int end) {
    super(state, state, start, end);
  }

  /**
   * Turns the given shapes visibility to true, does not change the shapes
   * attributes.
   *
   * @param s the shape being animated
   * @param t the time
   */
  @Override
  public void applyAnimation(ModelShape s, int t) {
    super.applyAnimation(s, t);
    s.setAttributes(this.startShape);
    s.show();
  }

  @Override
  public Animation getCopy() {
    Animation copy = new Show(this.startShape.copy(), this.start, this.end);
    return copy;
  }

  @Override
  public String getType() {
    return "show";
  }
}