package cs3500.animator.model.adaptations;

import cs3500.animator.model.shape.Attributes;
import cs3500.animator.model.shape.ShapeAttributes;
import cs3500.animator.provider.model.IKeyframe;

/**
 * Adapter class that implements the behavior of an IKeyframe using our implementation of
 * Attributes.
 * Represents a single frame of a shape in an animation.
 */
public class Keyframe extends ShapeAttributes implements IKeyframe {
  private int time;

  /**
   * Constructor for a Keyframe.
   *
   * @param time       the time of the frame
   * @param attributes the attributes of the shape at the time
   */
  public Keyframe(int time, Attributes attributes) {
    super(attributes.getColor(), attributes.getX(), attributes.getY(),
            attributes.getWidth(), attributes.getHeight());
    this.time = time;
  }

  @Override
  public int getTime() {
    return time;
  }
}
