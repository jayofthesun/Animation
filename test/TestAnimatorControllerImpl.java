import org.junit.Test;

import cs3500.animator.controller.AnimatorController;
import cs3500.animator.controller.AnimatorControllerImpl;
import cs3500.animator.model.AnimatorModelImpl;
import cs3500.animator.view.ExtendedVisualView;

/**
 * Tests for an AnimationControllerImpl.
 */
public class TestAnimatorControllerImpl {

  @Test(expected = IllegalArgumentException.class)
  public void testAnimatorControllerImplModelNull() {

    AnimatorController controller = new AnimatorControllerImpl(null,
            new ExtendedVisualView(1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAnimatorControllerImplViewNull() {

    AnimatorController controller = new AnimatorControllerImpl(new AnimatorModelImpl(),
            null);
  }
}
