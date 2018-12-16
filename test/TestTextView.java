import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModelImpl;
import cs3500.animator.model.animation.Motion;
import cs3500.animator.model.shape.Attributes;
import cs3500.animator.model.shape.ModelEllipse;
import cs3500.animator.model.shape.ModelRectangle;
import cs3500.animator.model.shape.ShapeAttributes;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.TextView;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;


/**
 * Test that the text output of of Text view is correct.
 */
public class TestTextView {

  @Test
  public void testDisplay() {
    try {
      AnimatorView view = new TextView(null);
      fail();
    } catch (Exception e) {
      assertEquals("output is null", e.getMessage());
    }

    Appendable output1 = new StringBuilder();
    AnimatorView view = new TextView(output1);
    AnimatorModel model1 = new AnimatorModelImpl();
    view.display(model1);
    assertEquals("canvas 0 0 0 0\n", output1.toString());

    Appendable output2 = new StringBuilder();
    AnimatorView view2 = new TextView(output2);
    AnimatorModel model2 = new AnimatorModelImpl();
    model2.addShape(new ModelRectangle("R", new ShapeAttributes()));
    model2.addShape(new ModelEllipse("E", new ShapeAttributes()));
    Attributes state1 = new ShapeAttributes(Color.WHITE, 50, 50, 5, 3);
    Attributes state2 = new ShapeAttributes(Color.WHITE, 59, 41, 5, 3);
    model2.addSingleAnimation("R", new Motion(state1, state2, 1, 10));
    Attributes state3 = new ShapeAttributes(Color.BLACK, 60, 40, 5, 3);
    Attributes state4 = new ShapeAttributes(Color.BLACK, 60, 40, 3, 5);
    model2.addSingleAnimation("E", new Motion(state3, state4, 4, 12));
    model2.setBounds(10, 10, 50, 50);
    view2.display(model2);
    assertEquals("canvas 10 10 50 50\n" +
                    "shape R rectangle\n" +
                    "motion R 1 50 50 5 3 255 255 255   10 59 41 5 3 255 255 255\n" +
                    "shape E ellipse\n" +
                    "motion E 4 60 40 5 3 0 0 0   12 60 40 3 5 0 0 0\n"
            , output2.toString());
  }
}
