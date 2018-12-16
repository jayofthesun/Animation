import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import cs3500.animator.model.adaptations.Keyframe;
import cs3500.animator.model.adaptations.Motion;
import cs3500.animator.model.adaptations.Shape;
import cs3500.animator.model.shape.Attributes;
import cs3500.animator.model.shape.ModelEllipse;
import cs3500.animator.model.shape.ModelRectangle;
import cs3500.animator.model.shape.ShapeAttributes;
import cs3500.animator.provider.model.IShape;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests for our implementation of our providers IShape interface.
 */
public class TestAdapterShape {
  private IShape ellipse;
  private IShape rectangle;

  @Before
  public void initShapes() {
    this.ellipse = new Shape(new ModelEllipse("e", new ShapeAttributes()));
    this.rectangle = new Shape(new ModelRectangle("r", new ShapeAttributes()));

    Attributes a1 = new ShapeAttributes(Color.WHITE, 1, 2, 3, 4);
    Attributes a2 = new ShapeAttributes(Color.BLACK, 5, 6, 7, 8);

    this.ellipse.addMotion(new Motion(a1, a2, 1, 10));

    this.rectangle.addMotion(new Motion(a1.copy(), a1.copy(), 5, 10));
  }


  @Test
  public void testGoTo() {
    assertEquals(new Point(0, 0), ellipse.getPosition());
    assertEquals(new Dimension(0, 0), ellipse.getDimensions());
    assertEquals(Color.BLACK, ellipse.getColor());
    ellipse.calculateMotionAtTick(1);
    assertEquals(new Point(1, 2), ellipse.getPosition());
    assertEquals(new Dimension(3, 4), ellipse.getDimensions());
    assertEquals(Color.WHITE, ellipse.getColor());
  }

  @Test
  public void testGetters() {
    assertEquals(1, ellipse.getFirstTick());
    assertEquals(10, ellipse.getFinalTick());
    assertEquals("Oval", ellipse.getType());
    assertEquals("Rectangle", rectangle.getType());
    assertEquals("e", ellipse.getName());
  }

  @Test
  public void testKeyframes() {
    assertEquals(2, ellipse.getKeyframes().size());
    assertEquals(1, ellipse.getKeyframes().get(0).getTime());
    assertEquals(3, ellipse.getKeyframes().get(0).getWidth());
    assertEquals(4, ellipse.getKeyframes().get(0).getHeight());
    assertEquals(1, ellipse.getKeyframes().get(0).getX());
    assertEquals(2, ellipse.getKeyframes().get(0).getY());
    assertEquals(Color.WHITE, ellipse.getKeyframes().get(0).getColor());
    ellipse.addKeyframe(new Keyframe(20,
            new ShapeAttributes(Color.GRAY, 23, 10, 40, 40)));
    assertEquals(3, ellipse.getKeyframes().size());
    assertEquals(20, ellipse.getKeyframes().get(2).getTime());
    assertEquals(40, ellipse.getKeyframes().get(2).getWidth());
    assertEquals(40, ellipse.getKeyframes().get(2).getHeight());
    assertEquals(23, ellipse.getKeyframes().get(2).getX());
    assertEquals(10, ellipse.getKeyframes().get(2).getY());
    assertEquals(Color.GRAY, ellipse.getKeyframes().get(2).getColor());

    ellipse.removeKeyframe(20);
    assertEquals(2, ellipse.getKeyframes().size());

  }

}
