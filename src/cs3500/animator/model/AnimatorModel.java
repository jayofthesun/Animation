package cs3500.animator.model;

import java.awt.Color;
import java.util.List;

import cs3500.animator.model.animation.Animation;
import cs3500.animator.model.shape.ModelShape;

/**
 * Represents animation of multiple 2D shapes. Can be played frame by frame or rendered as a text
 * description of the animation.
 */
public interface AnimatorModel {

  /**
   * Takes in a time which updates the shapes in the model to that tick.
   *
   * @param time the time to update the model to
   */
  void tick(int time);

  /**
   * Gets the list of shape to be drawn.
   *
   * @return a list of visible ModelShapes to be drawn for cs3500.animator.view
   */
  List<ModelShape> getShapeStates();

  /**
   * Adds a shape to the animation cs3500.animator.model.
   *
   * @param shape the shape to be added
   */
  void addShape(ModelShape shape);

  /**
   * Removes a shape from the animation cs3500.animator.model. Does nothing if the shape is not
   * found.
   *
   * @param shapeName the name of the shape to be removed from the animation cs3500.animator.model.
   */
  void removeShape(String shapeName);

  /**
   * Returns a copy of the shape with the given name's animations.
   *
   * @param shapeName the shape's name
   * @return a copy of the list of the animations for the given shape name
   */
  List<Animation> getAnimationsOfShapeToEdit(String shapeName);

  /**
   * Sets the given shape name's animation to the given list of animations. Does nothing if the
   * shape is not found.
   *
   * @param shapeName  the name of the shape
   * @param animations the new list of animations
   * @throws IllegalStateException if the new list of animations is not valid
   */
  void setAnimation(String shapeName, List<Animation> animations) throws IllegalStateException;

  //Added the methods below when creating views.

  /**
   * Sets the bounds for the canvas.
   *
   * @param x      the x value of the canvas
   * @param y      the y value of the canvas
   * @param width  the width of the canvas
   * @param height the height of the canvas
   * @throws IllegalArgumentException if the width or height are less than 0
   */
  void setBounds(int x, int y, int width, int height) throws IllegalArgumentException;

  /**
   * Gets a list of all of the shape names in the model.
   *
   * @return a list of strings for the shape names in the model
   */

  List<String> getListOfShapeNames();

  /**
   * Gets the x value of the canvas.
   *
   * @return the x value of the canvas
   */

  int getCanvasX();

  /**
   * Gets the y value of the canvas.
   *
   * @return the y value of the canvas
   */

  int getCanvasY();

  /**
   * Gets the width of the canvas.
   *
   * @return the width of the canvas
   */

  int getCanvasWidth();

  /**
   * Gets the height of the canvas.
   *
   * @return the height of the canvas.
   */

  int getCanvasHeight();

  /**
   * Gets a copy of the shape that has the given name in the model.
   *
   * @param name the name of the shape
   * @return gives the shape that matches the given name
   */

  ModelShape getCopy(String name);

  /**
   * Adds a single animation to the list of animations for a shape.
   *
   * @param name      the name of the shape that is having the animation being added to
   * @param animation the animation that is being added to the shape
   */
  void addSingleAnimation(String name, Animation animation);


  //Added these methods for hw7

  /**
   * Returns true if the animation is over at the given time.
   * @param time time of animation
   * @return a boolean whether or not the animation is over
   */
  boolean isOver(int time);

  /**
   * Returns a list of string representations of a shapes keyframes.
   *
   * @param name name of the shape
   * @return the list of string representations of a keyframe.
   */
  List<String> getKeyFrames(String name);

  /**
   * Edits a shapes animations to add a new key frame.
   *
   * @param name name of shape to edit
   * @param time time of keyframe
   * @param height height of the shape
   * @param width width of the shape
   * @param color color of shape
   * @param x x position of shape
   * @param y y position of shape
   */
  void addKeyFrame(String name, int time, int height, int width, Color color, int x, int y);

  /**
   * Removes a keyframe from a shapes animations.
   *
   * @param name name of the shape.
   * @param index thi index of the keyframe to remove.
   */
  void removeKeyFrame(String name, int index);
}

