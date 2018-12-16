package cs3500.animator.controller;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.view.AnimatorView;


/**
 * Implements a controller for the animation model so that a view can be created for the model.
 */

public class AnimatorControllerImpl implements cs3500.animator.controller.AnimatorController {
  private AnimatorModel model;
  private AnimatorView view;

  /**
   * Constructs a new controller.
   *
   * @param model the animator model
   * @param view  the view to be constructed
   * @throws IllegalArgumentException if model or view is null
   */

  public AnimatorControllerImpl(AnimatorModel model, AnimatorView view)
          throws IllegalArgumentException {

    if (model == null) {
      throw new IllegalArgumentException("the model is null");
    }

    if (view == null) {
      throw new IllegalArgumentException("the view is null");
    }

    this.model = model;
    this.view = view;
  }

  @Override
  public void animationGo() {
    this.view.display(model);
  }
}
