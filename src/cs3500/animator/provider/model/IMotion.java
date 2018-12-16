package cs3500.animator.provider.model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;

public interface IMotion {

  /**
   * Gets the dimension of this motion at the given tick value.
   *
   * @param time the tick value to check the dimension at
   * @return a Dimension object with the width and height of the shape at that time.
   * @throws IllegalArgumentException if time is negative or if it is not within this motion
   */
  Dimension dimensionAtTime(int time);

  /**
   * Gets the position of this motion at the given tick value.
   *
   * @param time the tick value to check the position at
   * @return a Point object with the x and y coordinates of the shape at that time.
   * @throws IllegalArgumentException if time is negative or if it is not within this motion
   */
  Point positionAtTime(int time);

  /**
   * Gets the color of this motion at the given tick value.
   *
   * @param time the tick value to check the color at
   * @return a Color object representing the color of the shape at that time.
   * @throws IllegalArgumentException if time is negative or if it is not within this motion
   */
  Color colorAtTime(int time);

  /**
   * Returns whether a certain time is within the range for this movement.
   *
   * @param time the time to check.
   * @return true if the time is within this motion, false if not.
   */
  boolean timeIsWithin(int time);

  /**
   * Checks if the motions overlap, returns false if they don't.
   *
   * @param motion the other motion whose duration to check
   * @return true if the two motions overlap, false otherwise
   */
  boolean overlaps(IMotion motion);

  /**
   * Checks if this motion is right before the given motion.
   *
   * @param motion the other motion to compare
   * @return true if this motion has the same end tick as the given motion's start tick.
   */
  boolean consecutive(IMotion motion);

  /**
   * Returns this motion's start time.
   *
   * @return this motion's start time
   */
  int getStartTime();

  /**
   * Gets the last tick of this motion.
   *
   * @return the last tick of the motion
   */
  int getEndTime();

  /**
   * Return the prevX value of this motion.
   *
   * @return the prevX value.
   */
  int getPrevX();

  /**
   * Return the prevY value of this motion.
   *
   * @return the prevY value.
   */
  int getPrevY();

  /**
   * Return the prevWidth value of this motion.
   *
   * @return the prevWidth value.
   */
  int getPrevWidth();

  /**
   * Return the prevHeight value of this motion.
   *
   * @return the prevHeight value.
   */
  int getPrevHeight();

  /**
   * Return the prevColor value of this motion.
   *
   * @return the prevColor value.
   */
  Color getPrevColor();

  /**
   * Return the newX value of this motion.
   *
   * @return the newX value.
   */
  int getNewX();

  /**
   * Return the newY value of this motion.
   *
   * @return the newY value.
   */
  int getNewY();

  /**
   * Return the newWidth value of this motion.
   *
   * @return the newWidth value.
   */
  int getNewWidth();

  /**
   * Return the newHeight value of this motion.
   *
   * @return the newHeight value.
   */
  int getNewHeight();

  /**
   * Return the newColor value of this motion.
   *
   * @return the newColor value.
   */
  Color getNewColor();

}
