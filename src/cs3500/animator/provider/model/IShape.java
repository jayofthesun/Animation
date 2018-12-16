package cs3500.animator.provider.model;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Point;
import java.util.List;

/**
 * An interface representing a generic shape, with methods to modify and add motions and keyframes.
 */
public interface IShape {

  /**
   * Adds a new motion to the list if it does not overlap with any existing motion, and add the
   * keyframes that align with the start and end points of this motion.
   *
   * @param motion the motion to be added
   * @throws IllegalArgumentException if the motion overlaps with an existing motion in the list
   */
  void addMotion(IMotion motion);

  /**
   * Add the given keyframe to this animation, and edit the motions to align with the new keyframe.
   *
   * @param keyframe the keyframe to be added.
   */
  void addKeyframe(IKeyframe keyframe);

  /**
   * Removes the keyframe and reformats motions at the given time.
   *
   * @param time the tick to remove the keyframe at
   */
  void removeKeyframe(int time);

  /**
   * Adds the given keyframe to this shape, overwriting the one at the time it has.
   *
   * @param newKeyframe the new keyframe to add.
   */
  void editKeyframe(IKeyframe newKeyframe);

  /**
   * Returns all the motions of this shape object.
   *
   * @return a list containing all motions.
   */
  List<IMotion> getMotions();

  /**
   * Returns all keyframes of this shape object.
   *
   * @return a list containing all keyframes.
   */
  List<IKeyframe> getKeyframes();

  /**
   * Calculates the current position of this shape at the given tick, and sets the values.
   *
   * @param tick the tick to calculate position at
   * @throws IllegalArgumentException if tick is not positive or if no motion runs at that tick
   */
  void calculateMotionAtTick(int tick);

  /**
   * Returns a string containing the name of the type of shape this object is.
   *
   * @return the string representing the name of the type of this object.
   */
  String getType();

  /**
   * Gets dimensions field of this Shape.
   *
   * @return the dimension field
   */
  Dimension getDimensions();

  /**
   * Gets the color field of this Shape.
   *
   * @return the color field
   */
  Color getColor();

  /**
   * Gets the position field of this Shape.
   *
   * @return the position field
   */
  Point getPosition();

  /**
   * Checks that there are no gaps in the run time of this shape's motions.
   *
   * @return true if there are no gaps, false otherwise
   */
  boolean isPlayable();

  /**
   * Returns the final tick value for this shape.
   *
   * @return the final tick value
   */
  int getFinalTick();

  /**
   * Returns the first tick motions start for this shape.
   *
   * @return the first tick value.
   */
  int getFirstTick();

  /**
   * Gets the name of this shape.
   *
   * @return the name
   */
  String getName();

  /**
   * Checks if this shape has a motion during this time.
   *
   * @param tick the tick to check
   * @return if the shape has a motion
   */
  boolean isRunningDuringTick(int tick);

}
