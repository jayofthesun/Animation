package cs3500.animator.provider.model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;
import java.util.List;

/**
 * An interface defining an animation cs3500.animator.model, which respresents an animation
 * containing multiple shapes and their movements.
 */
public interface AnimationModel {

  /**
   * Sets the dimensions and location of the canvas to draw the animation.
   *
   * @param d the dimensions of the canvas.
   * @param p the location of the canvas.
   */
  void setBounds(Dimension d, Point p);

  /**
   * Returns all shapes in this model.
   *
   * @return the list of all shapes.
   */
  List<IShape> returnShapes();

  /**
   * Adds a new shape to this animation, which can be edited using motions.
   *
   * @param shapeName   the name of the shape to make
   * @param typeOfShape the type of shape to construct
   */
  void addShape(String shapeName, String typeOfShape);

  /**
   * Removes the first shape with the given name from this animation.
   *
   * @param name the name of the shape to remove
   */
  void removeShape(String name);

  /**
   * Adds a keyframe to this animation with the given parameters.
   *
   * @param shapeName the name of the shape to add the keyframe to.
   * @param dimension the dimension of the shape at this frame.
   * @param position  the position of the shape at this frame.
   * @param color     the color of the shape at this frame.
   * @param time      the time at which this keyframe is.
   */
  void addKeyframe(String shapeName,
                   Dimension dimension,
                   Point position,
                   Color color,
                   int time);

  /**
   * Removes a keyframe in the shape with the given shapeName at the given time.
   *
   * @param shapeName the name of the shape to remove the keyframe from.
   * @param time      the time to remove the keyframe at.
   */
  void removeKeyframe(String shapeName, int time);

  /**
   * Returns whether the given shape has a keyframe at the given time.
   *
   * @param shapeName the name of the shape.
   * @param time      the time of the keyframe.
   * @return if the shape has a keyframe at the given time.
   */
  boolean isKeyframeAtTime(String shapeName, int time);

  /**
   * Edits a keyframe with the given time to the given parameters.
   *
   * @param shapeName the name of the shape
   * @param x         the x value
   * @param y         the y value
   * @param width     the width
   * @param height    the height
   * @param color     the color
   * @param time      the time of the keyframe
   */
  void editKeyframe(String shapeName, int x, int y, int width, int height,
                    Color color, int time);

  /**
   * Adds a new motion to this animation, which moves or changes the size of a shape in the
   * animation.
   *
   * @param shapeName     the name of the shape to be edited
   * @param prevDimension the previous location of this shape
   * @param prevPosition  the previous position of this shape
   * @param prevColor     the previous color of this shape
   * @param newDimension  the new dimensions of the shape
   * @param newPosition   the new position of the shape
   * @param newColor      the new color of the shape
   * @param startTime     the starting time of the motion
   * @param endTime       the ending time of the motion
   */
  void addMotion(String shapeName,
                 Dimension prevDimension,
                 Point prevPosition,
                 Color prevColor,
                 Dimension newDimension,
                 Point newPosition,
                 Color newColor,
                 int startTime,
                 int endTime);


  /**
   * Returns the final tick value in this animation, or the length of the animation.
   *
   * @return the final tick value
   */
  int getFinalTick();


  /**
   * Returns the size of this animation's canvas.
   *
   * @return the dimension of this canvas.
   */
  Dimension getCanvasDimension();

  /**
   * Returns the location of this animation.
   *
   * @return the location of this canvas.
   */
  Point getCanvasLocation();

  /**
   * Checks if all of the shapes in this model are playable.
   *
   * @return true if the model is playable, false otherwise
   */
  boolean isPlayable();

}
