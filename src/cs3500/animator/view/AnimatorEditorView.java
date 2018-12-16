package cs3500.animator.view;

import java.awt.Color;
import java.util.List;

public interface AnimatorEditorView {

  /**
   * pauses the animation.
   */
  void pause();

  /**
   * resumes the animation.
   */
  void resume();

  /**
   * restarts the animation to the beginning.
   */
  void restart();

  /**
   * toggles the loop variable so that the animation is either looping or not.
   */
  void toggleLoop();

  /**
   * increases the speed of the animation.
   */
  void increaseSpeed();

  /**
   * decreases the speed of the animation.
   */
  void decreaseSpeed();

  /**
   * gets the speed of the animation based off of the timer's delay.
   *
   * @return the speed of the animation
   */
  int getSpeed();

  /**
   * gets a boolean of whether or not the animation is looping.
   *
   * @return if the animation is looping
   */
  boolean isLooping();

  /**
   * gets the animation's running state, if the animation is paused or running.
   *
   * @return if the animation is playing or paused.
   */
  String getAnimationState();

  /**
   * gets the names of the shapes of the animations.
   *
   * @return a list of names of the shapes in the animation
   */

  List<String> getShapes();

  /**
   * gets the keyframes for the given shape using its name.
   *
   * @param s the name of the shape
   * @return the keyframes for the shape with the given name
   */
  List<String> getKeyframes(String s);

  /**
   * adds a shape to the animation.
   *
   * @param s    the name of the shape
   * @param type the type of shape
   */
  void addShape(String s, String type);

  /**
   * removes a shape from the animation.
   *
   * @param s the name of the shape
   */
  void deleteShape(String s);

  /**
   * deletes a keyframe from the index of the given name's shape.
   *
   * @param index the index of the keyframe
   * @param name  the name of the shape
   */
  void deleteKeyFrame(int index, String name);

  /**
   * Adds a keyframe with the given attributes to the given name's shape at the given time.
   *
   * @param name   the name of the shape to be added to
   * @param time   the time to be added to
   * @param height the height of the shape at the keyframe
   * @param width  the width of the shape at the keyframe
   * @param color  the color of the shape at the keyframe
   * @param x      the x of the shape at the keyframe
   * @param y      the y of the shape at the keyframe
   */
  void addKeyFrame(String name, int time, int height, int width, Color color, int x, int y);
}
