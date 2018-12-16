package cs3500.animator.provider.view;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;

import cs3500.animator.provider.controller.EditorListener;
import cs3500.animator.provider.model.IAnimationModelWrapper;
import cs3500.animator.provider.model.IKeyframe;

/**
 * An interface representing a generic type of view for the animation. Some operations are supported
 * by only some views.
 */
public interface AnimationView {

  /**
   * Make the view visible.
   */
  void makeVisible();

  /**
   * Set the current speed to run this animation.
   *
   * @param speed the speed to run the animation in ticks per second
   */
  default void setAnimationTempo(int speed) {
    throw new UnsupportedOperationException("Cannot set animation tempo");
  }

  /**
   * Pause the animation at the tick it is currently at, or do nothing if already paused.
   */
  default void pause() {
    throw new UnsupportedOperationException("Cannot pause this view");
  }

  /**
   * Play the animation if it is paused, else do nothing.
   */
  default void play() {
    throw new UnsupportedOperationException("Cannot play this view");
  }

  /**
   * Start the animation over from the first tick, regardless of the current tick.
   */
  default void restart() {
    throw new UnsupportedOperationException("Cannot restart this view");
  }

  /**
   * Set whether the animation should loop or not.
   *
   * @param looping true if the animation should loop, false if not
   */
  default void setLooping(boolean looping) {
    throw new UnsupportedOperationException("Cannot loop this view");
  }

  /**
   * Takes in the animation wrapper to be displayed by the editor view.
   *
   * @param wrapper the wrapper object to be edited.
   */
  void setModel(IAnimationModelWrapper wrapper);

  /**
   * Sets the editorListener for all components of this view.
   *
   * @param l the editorListener to set
   */
  default void setEditorListener(EditorListener l) {
    throw new UnsupportedOperationException("Cannot add editor listener");
  }

  /**
   * Set the list of keyframes that is currently displayed on this view.
   *
   * @param keyframes the list of keyframes to display.
   */
  default void setKeyframeList(List<IKeyframe> keyframes) {
    throw new UnsupportedOperationException("Cannot set list of keyframes");
  }


  /**
   * Sets the current visible keyframe on this view to edit.
   *
   * @param x      the x value of the keyframe
   * @param y      the y value of the keyframe
   * @param width  the width of the keyframe
   * @param height the height of the keyframe
   * @param r      the red value of the keyframe
   * @param g      the green value of the keyframe
   * @param b      the blue value of the keyframe
   * @param t      the time value of the keyframe
   */
  default void setVisibleKeyframe(int x, int y, int width, int height, int r, int g, int b, int t) {
    throw new UnsupportedOperationException("Cannot set Visible Keyframe");
  }

  /**
   * Clears the current visible keyframe of this view.
   */
  default void clearVisibleKeyframe() {
    throw new UnsupportedOperationException("Cannot clear visible keyframe");
  }


  /**
   * Gets the new speed value inputted by the user for this view.
   *
   * @return the int value in ticks per second of the new speed.
   */
  default int getNewSpeed() {
    throw new UnsupportedOperationException("Cannot get new speed");
  }


  /**
   * Sets the canvas of the animation panel to the given values.
   *
   * @param canvasLeftCorner the location of the left corner of the canvas.
   * @param canvasSize       the size of the canvas.
   */
  default void setCanvas(Point canvasLeftCorner, Dimension canvasSize) {
    throw new UnsupportedOperationException("Cannot set canvas");
  }

  /**
   * Displays an error message with the given string to the user.
   *
   * @param error the error message to be displayed.
   */
  default void showErrorMessage(String error) {
    throw new UnsupportedOperationException("Cannot display error message");
  }

}
