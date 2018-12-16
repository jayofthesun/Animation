package cs3500.animator.model.shape;

import java.util.List;

import cs3500.animator.model.animation.Animation;

/**
 * Interface representing a single shape in an animation.
 */
public interface ModelShape {

  /**
   * Returns a copy of a shape and a copy of all its animations.
   *
   * @return a reference to the copy of shape
   */
  ModelShape copy();

  /**
   * Gets the name that identifies the shape.
   *
   * @return the name of the shape
   */
  String getName();

  /**
   * Gets the visibility of the shape; visibility is false by default.
   */
  boolean isVisible();

  /**
   * Sets the visibility of the object to true.
   */
  void show();

  /**
   * Sets the visibility of the object to false.
   */
  void hide();

  /**
   * Sets the shapes attributes to the given object.
   */
  void setAttributes(Attributes a);

  /**
   * Changes the shape's attributes to match what the should be at the given time.
   */
  void goTo(int time);

  /**
   * Returns a copy the attributes of the shape.
   *
   * @return the shapes attributes.
   */
  Attributes getAttributes();

  /**
   * Returns the type of shape.
   *
   * @return type of the shape
   */
  String getType();

  /**
   * Returns a copy of the animation of the shape. Going to be used by the controller to edit
   * animations.
   *
   * @return a copy of the list of animations for the shape
   */
  List<Animation> getCopyOfAnimations();

  /**
   * Sets the given List of Animations to the given list of animations if it is a valid list of
   * animations (no gaps in time, no overlaps, and end states and start states of the next animation
   * are equal.
   *
   * @param animations list of animations
   */
  void setAnimations(List<Animation> animations);


  /**
   * Adds a keyframe at the given time with the given attributes.
   *
   * @param time       the time the keyframe is added at
   * @param attributes the attributes of the shape at the keyframe's given time
   */

  void addKeyFrame(int time, Attributes attributes);

  /**
   * Removes a keyframe from the given index of the list of keyframes, does not remove anything if
   * there is nothing at the given keyframe's index.
   *
   * @param index the index of the keyframe.
   */

  void removeKeyFrame(int index);
}
