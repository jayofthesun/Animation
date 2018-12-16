package cs3500.animator.provider.controller;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionListener;

/**
 * An interface representing a general action listener to both action events from buttons and list
 * selection events.
 */
public interface EditorListener extends ActionListener, ListSelectionListener {

  /**
   * Edits or adds a keyframe with the given parameters to the model.
   *
   * @param x      the x value
   * @param y      the y value
   * @param width  the width
   * @param height the height
   * @param color  the color
   * @param time   the time of the keyframe
   */
  void editKeyframe(int x, int y, int width, int height, Color color, int time);

  /**
   * Adds a shape with the given parameters to the model.
   *
   * @param shapeName the name of the shape
   * @param shapeType the type of shape it is
   */
  void addShape(String shapeName, String shapeType);

  /**
   * Removes a shape with the given name from the model.
   *
   * @param name the name of the shape
   */
  void removeShape(String name);

  /**
   * Removes a keyframe at the given time from the currently selected shape.
   *
   * @param time the time of the keyframe
   */
  void removeKeyframe(int time);

  /**
   * Sets this animation to be looping or not.
   *
   * @param isLooping whether the animation should loop
   */
  void setLooping(boolean isLooping);

  /**
   * Download the current animation with the given file type.
   *
   * @param type the type of file to download.
   */
  void download(String type);
}

