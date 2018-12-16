package cs3500.animator.model.animation;

import cs3500.animator.model.shape.Attributes;
import cs3500.animator.model.shape.ModelShape;

/**
 * A n animation that hides the shape/prevents it from being drawn.
 */

public class Hide extends AbstractAnimation {

  /**
   * Constructor for a hide animation.
   *
   * @param start start time
   * @param end   end time
   */
  public Hide(Attributes state, int start, int end) {
    super(state, state, start, end);
  }

  /**
   * Turns the given shapes visibility to false, does not change the shapes
   * attributes.
   *
   * @param s the shape being animated
   * @param t the time
   */
  @Override
  public void applyAnimation(ModelShape s, int t) {
    super.applyAnimation(s, t);
    s.setAttributes(this.startShape);
    s.hide();
  }

  @Override
  public Animation getCopy() {
    Animation copy = new Hide(this.startShape.copy(), this.start,this.end);
    return copy;
  }

  @Override
  public String getType() {
    return "hide";
  }

}
