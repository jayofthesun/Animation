package cs3500.animator.provider.controller;

import cs3500.animator.provider.controller.EditorListener;

/**
 * An interface which represents the controller for an animation, which mediates between the view
 * and model.
 */
public interface AnimationController extends EditorListener {

  /**
   * Runs the animation with the given view and model for this controller.
   */
  void run();
}
