package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;

/**
 * Classes that implement this interface will in some way display an animation whether through text
 * or images.
 */

public interface AnimatorView {

  /**
   * executes a display for the type of view based off an animation model that it is given.
   *
   * @param model the given animation model
   */
  void display(AnimatorModel model);

}
