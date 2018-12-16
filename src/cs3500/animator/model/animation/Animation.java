package cs3500.animator.model.animation;

import cs3500.animator.model.shape.Attributes;
import cs3500.animator.model.shape.ModelShape;

/**
 * Describes the objects for different animations that can be applied to ModelShapes.
 */
public interface Animation {

  /**
   * Applies the animation to the shape.
   *
   * @param s the shape being animated
   * @param t the time
   */
  void applyAnimation(ModelShape s, int t);

  /**
   * Checks if the time is contained within the given animations's time-frame.
   *
   * @param t time
   * @return if the time is contained within the animation
   */
  boolean isContained(int t);

  /**
   * Gets the type of the animation.
   *
   * @return the type of animation
   */
  String getType();

  /**
   * Returns a copy of the animation.
   *
   * @return copy of animation
   */
  Animation getCopy();

  /**
   * Returns true if the end time of this and start time of that are equal
   * and if the end state of this and the start state are equal.
   *
   * @param that another animation
   * @return whether these animations can go next to each other in a valid animation
   */
  boolean linesUp(Animation that);

  /**
   * Gets the start time of an animation.
   *
   * @return start time
   */
  int getStart();

  /**
   * Gets the end time of an animation.
   *
   * @return end time
   */
  int getEnd();

  /**
   * Gets the starting shape attribute of an animation.
   *
   * @return starting shape attributes
   */
  Attributes getStartShape();

  /**
   * Gets the ending shape attribute of an animation.
   *
   * @return ending shape attributes
   */
  Attributes getEndShape();

  /**
   * Sets the start time to the given int.
   *
   * @param start start time that is less than end time and greater than 0.
   */
  void setStart(int start);

  /**
   * Sets the end time to the given int.
   *
   * @param end end time that is greater than start time
   */
  void setEnd(int end);

}
