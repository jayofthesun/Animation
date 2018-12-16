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
import cs3500.animator.view.SVGView;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

/**
 * Tests that that the output of sgv view is correct.
 */
public class TestSVGView {

  @Test
  public void testDisplay() {
    try {
      AnimatorView view = new SVGView(10, null);
      fail();
    } catch (Exception e) {
      assertEquals("output is null", e.getMessage());
    }


    try {
      AnimatorView view = new SVGView(0, new StringBuilder());
      fail();
    } catch (Exception e) {
      assertEquals("ticks per second must be positive", e.getMessage());
    }

    Appendable output1 = new StringBuilder();
    AnimatorView view = new SVGView(10, output1);
    AnimatorModel model1 = new AnimatorModelImpl();
    view.display(model1);
    assertEquals(
            "<svg width=\"0\" height=\"0\" version=\"1.1\" " +
                    "xmlns=\"http://www.w3.org/2000/svg\">\n" +
                    "</svg>", output1.toString());

    Appendable output2 = new StringBuilder();
    AnimatorView view2 = new SVGView(10, output2);
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
    assertEquals("<svg width=\"60\" height=\"60\" version=\"1.1\" xmlns=\"http://www.w3.org"
            + "/2000/svg\">\n" +
            "<rect id=\"R\" x=\"0\" y=\"0\" width=\"0\" height=\"0\" fill=\"rgb(0,0,0)\" " +
            "visibility=\"visible\">\n\n" +
            "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"900.0ms\" " +
            "attributeName=\"x\" from=\"50\" to=\"59\" fill=\"freeze\"/>\n\n" +
            "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"900.0ms\" " +
            "attributeName=\"y\" from=\"50\" to=\"41\" fill=\"freeze\"/>\n\n" +
            "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"900.0ms\" " +
            "attributeName=\"width\" from=\"5\" to=\"5\" fill=\"freeze\"/>\n\n" +
            "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"900.0ms\" " +
            "attributeName=\"height\" from=\"3\" to=\"3\" fill=\"freeze\"/>\n\n" +
            "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"900.0ms\" " +
            "attributeName=\"fill\" from=\"rgb(255, 255, 255)\" to=\"rgb(255, 255, 255)\" " +
            "fill=\"freeze\"/>\n\n" +
            "</rect>\n\n" +
            "<ellipse id=\"E\" cx=\"0\" cy=\"0\" rx=\"0\" ry=\"0\" fill=\"rgb(0,0,0)\" " +
            "visibility=\"visible\">\n\n" +
            "<animate attributeType=\"xml\" begin=\"400.0ms\" dur=\"800.0ms\" " +
            "attributeName=\"cx\" from=\"60\" to=\"60\" fill=\"freeze\"/>\n\n" +
            "<animate attributeType=\"xml\" begin=\"400.0ms\" dur=\"800.0ms\" " +
            "attributeName=\"cy\" from=\"40\" to=\"40\" fill=\"freeze\"/>\n\n" +
            "<animate attributeType=\"xml\" begin=\"400.0ms\" dur=\"800.0ms\" " +
            "attributeName=\"rx\" from=\"5\" to=\"3\" fill=\"freeze\"/>\n\n" +
            "<animate attributeType=\"xml\" begin=\"400.0ms\" dur=\"800.0ms\" " +
            "attributeName=\"ry\" from=\"3\" to=\"5\" fill=\"freeze\"/>\n\n" +
            "<animate attributeType=\"xml\" begin=\"400.0ms\" dur=\"800.0ms\" " +
            "attributeName=\"fill\" from=\"rgb(0, 0, 0)\" to=\"rgb(0, 0, 0)\" " +
            "fill=\"freeze\"/>\n\n" +
            "</ellipse>\n\n" +
            "</svg>", output2.toString());
  }

}
