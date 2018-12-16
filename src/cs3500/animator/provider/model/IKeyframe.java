package cs3500.animator.provider.model;

import java.awt.Color;

/**
 * The interface for a Keyframe, has observer methods for getting values of a keyframe.
 */
public interface IKeyframe {

  /**
   * Get the x value at this keyframe.
   *
   * @return the x value.
   */
  int getX();

  /**
   * Get the y value at this keyframe.
   *
   * @return the y value.
   */
  int getY();

  /**
   * Get the width at this keyframe.
   *
   * @return the width.
   */
  int getWidth();

  /**
   * Get the height at this keyframe.
   *
   * @return the height.
   */
  int getHeight();

  /**
   * Get the color at this keyframe.
   *
   * @return the color.
   */
  Color getColor();

  /**
   * Get the time of this keyframe.
   *
   * @return the time.
   */
  int getTime();

}
