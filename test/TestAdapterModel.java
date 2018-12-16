import org.junit.Test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Arrays;
import java.util.List;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModelImpl;
import cs3500.animator.model.adaptations.AnimatorModelImplToProvider;
import cs3500.animator.model.adaptations.Shape;
import cs3500.animator.model.shape.ModelEllipse;
import cs3500.animator.model.shape.ModelRectangle;
import cs3500.animator.model.shape.ShapeAttributes;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests for our implementation of our providers AnimationModel interface.
 */
public class TestAdapterModel {

  @Test
  public void testSetBounds() {
    AnimatorModel model = new AnimatorModelImpl();
    AnimatorModelImplToProvider example = new AnimatorModelImplToProvider(model);
    assertEquals(0.0, example.getCanvasLocation().getX());
    assertEquals(0.0, example.getCanvasLocation().getY());
    assertEquals(0.0, example.getCanvasDimension().getWidth());
    assertEquals(0.0, example.getCanvasDimension().getHeight());

    example.setBounds(new Dimension(1, 2), new Point(3, 4));

    assertEquals(3.0, example.getCanvasLocation().getX());
    assertEquals(4.0, example.getCanvasLocation().getY());
    assertEquals(1.0, example.getCanvasDimension().getWidth());
    assertEquals(2.0, example.getCanvasDimension().getHeight());

    try {
      example.setBounds(new Dimension(0, 0), new Point(-1, 0));
    } catch (Exception e) {
      assertEquals(e.getMessage(), "invalid width or height for canvas");
    }

    try {
      example.setBounds(new Dimension(0, 0), new Point(0, -1));
    } catch (Exception e) {
      assertEquals(e.getMessage(), "invalid width or height for canvas");
    }

  }

  @Test
  public void testReturnShapes() {
    AnimatorModel model = new AnimatorModelImpl();
    AnimatorModelImplToProvider example = new AnimatorModelImplToProvider(model);
    Shape ellipse1 = new Shape(new ModelEllipse("E", new ShapeAttributes()));
    Shape rectangle1 = new Shape(new ModelRectangle("R", new ShapeAttributes()));

    example.addShape(ellipse1.getName(), ellipse1.getType());
    example.addShape(rectangle1.getName(), rectangle1.getType());

    assertEquals(2, example.returnShapes().size());
    assertEquals("E", example.returnShapes().get(0).getName());
    assertEquals("R", example.returnShapes().get(1).getName());

  }

  @Test
  public void testAddRemoveShape() {
    AnimatorModel model = new AnimatorModelImpl();
    AnimatorModelImplToProvider example = new AnimatorModelImplToProvider(model);
    Shape ellipse1 = new Shape(new ModelEllipse("E", new ShapeAttributes()));
    Shape ellipse2 = new Shape(new ModelEllipse("E", new ShapeAttributes()));
    Shape rectangle1 = new Shape(new ModelRectangle("R", new ShapeAttributes()));

    example.addShape(ellipse1.getName(), ellipse1.getType());
    example.addShape(rectangle1.getName(), rectangle1.getType());

    List<Shape> list = Arrays.asList(ellipse1, rectangle1);
    assertEquals(list.get(0).getName(), example.returnShapes().get(0).getName());
    assertEquals(list.get(1).getName(), example.returnShapes().get(1).getName());

    list = Arrays.asList(ellipse1);
    example.removeShape("R");
    assertEquals(list.get(0).getName(), example.returnShapes().get(0).getName());

    example.removeShape("NOT IN LIST");
    assertEquals(list.size(), example.returnShapes().size());

    try {
      example.addShape(ellipse2.getName(), ellipse2.getType());
    } catch (Exception e) {
      assertEquals("name already taken", e.getMessage());
    }

    try {
      example.addShape(null, "rectangle");
    } catch (Exception e) {
      assertEquals("null name", e.getMessage());
    }

    try {
      example.addShape("bob", null);
    } catch (Exception e) {
      assertEquals("null type of shape", e.getMessage());
    }

  }

  @Test
  public void testAddRemoveKeyFrame() {
    AnimatorModel model = new AnimatorModelImpl();
    AnimatorModelImplToProvider example = new AnimatorModelImplToProvider(model);
    example.addShape("aria", "rectangle");

    example.addKeyframe("aria", new Dimension(5, 3),
            new Point(2, 3), Color.BLUE, 2);
    assertEquals(false, example.isKeyframeAtTime("aria", 4));
    assertEquals(true, example.isKeyframeAtTime("aria", 2));
    assertEquals(2, example.returnShapes().get(0).getKeyframes().size());
    assertEquals(2, example.returnShapes().get(0).getKeyframes().get(0).getTime());
    assertEquals(5, example.returnShapes().get(0).getKeyframes().get(0).getWidth());
    assertEquals(3, example.returnShapes().get(0).getKeyframes().get(0).getHeight());
    assertEquals(2, example.returnShapes().get(0).getKeyframes().get(0).getX());
    assertEquals(3, example.returnShapes().get(0).getKeyframes().get(0).getY());
    assertEquals(Color.blue, example.returnShapes().get(0).getKeyframes().get(0).getColor());
    assertEquals(false, example.isKeyframeAtTime("aria", 4));
    example.addKeyframe("aria", new Dimension(40, 50),
            new Point(23, 10), Color.GRAY, 4);
    assertEquals(true, example.isKeyframeAtTime("aria", 4));
    assertEquals(3, example.returnShapes().get(0).getKeyframes().size());
    assertEquals(4, example.returnShapes().get(0).getKeyframes().get(2).getTime());
    assertEquals(40, example.returnShapes().get(0).getKeyframes().get(2).getWidth());
    assertEquals(50, example.returnShapes().get(0).getKeyframes().get(2).getHeight());
    assertEquals(23, example.returnShapes().get(0).getKeyframes().get(2).getX());
    assertEquals(10, example.returnShapes().get(0).getKeyframes().get(2).getY());
    assertEquals(Color.GRAY, example.returnShapes().get(0).getKeyframes().get(2).getColor());

    example.removeKeyframe("aria", 4);
    assertEquals(false, example.isKeyframeAtTime("aria", 4));
    assertEquals(2, example.returnShapes().get(0).getKeyframes().size());
    example.removeKeyframe("aria", 2);
    assertEquals(0, example.returnShapes().get(0).getKeyframes().size());

  }

  @Test
  public void testIsKeyFrameToTime() {
    AnimatorModel model = new AnimatorModelImpl();
    AnimatorModelImplToProvider example = new AnimatorModelImplToProvider(model);
    example.addShape("aria", "rectangle");

    example.addKeyframe("aria", new Dimension(50, 32), new Point(23,
                    31),
            Color.PINK, 1);
    example.addKeyframe("aria", new Dimension(5, 3),
            new Point(2, 3), Color.BLUE, 2);

    example.addKeyframe("aria", new Dimension(40, 50),
            new Point(23, 10), Color.GRAY, 4);
    assertEquals(true, example.isKeyframeAtTime("aria", 2));
    assertEquals(true, example.isKeyframeAtTime("aria", 1));
    assertEquals(true, example.isKeyframeAtTime("aria", 4));


  }

  @Test
  public void testEditKeyFrame() {
    AnimatorModel model = new AnimatorModelImpl();
    AnimatorModelImplToProvider example = new AnimatorModelImplToProvider(model);
    example.addShape("aria", "rectangle");

    example.addKeyframe("aria", new Dimension(5, 3),
            new Point(2, 3), Color.BLUE, 2);
    assertEquals(false, example.isKeyframeAtTime("aria", 4));
    assertEquals(true, example.isKeyframeAtTime("aria", 2));
    assertEquals(2, example.returnShapes().get(0).getKeyframes().size());
    assertEquals(2, example.returnShapes().get(0).getKeyframes().get(0).getTime());
    assertEquals(5, example.returnShapes().get(0).getKeyframes().get(0).getWidth());
    assertEquals(3, example.returnShapes().get(0).getKeyframes().get(0).getHeight());
    assertEquals(2, example.returnShapes().get(0).getKeyframes().get(0).getX());
    assertEquals(3, example.returnShapes().get(0).getKeyframes().get(0).getY());
    assertEquals(Color.blue, example.returnShapes().get(0).getKeyframes().get(0).getColor());
    assertEquals(true, example.isKeyframeAtTime("aria", 2));
    example.editKeyframe("aria", 200, 300, 4, 30,
            Color.GRAY, 2);
    assertEquals(2, example.returnShapes().get(0).getKeyframes().size());
    assertEquals(2, example.returnShapes().get(0).getKeyframes().get(1).getTime());
    assertEquals(4, example.returnShapes().get(0).getKeyframes().get(1).getWidth());
    assertEquals(30, example.returnShapes().get(0).getKeyframes().get(1).getHeight());
    assertEquals(200, example.returnShapes().get(0).getKeyframes().get(1).getX());
    assertEquals(300, example.returnShapes().get(0).getKeyframes().get(1).getY());
    assertEquals(Color.GRAY, example.returnShapes().get(0).getKeyframes().get(1).getColor());
  }

  @Test
  public void testGetFinalKeyframe() {
    AnimatorModel model = new AnimatorModelImpl();
    AnimatorModelImplToProvider example = new AnimatorModelImplToProvider(model);
    example.addShape("aria", "rectangle");

    example.addKeyframe("aria", new Dimension(5, 3), new Point(2, 3),
            Color.BLUE, 2);
    assertEquals(false, example.isKeyframeAtTime("aria", 4));
    assertEquals(true, example.isKeyframeAtTime("aria", 2));
    assertEquals(2, example.returnShapes().get(0).getKeyframes().size());
    assertEquals(2, example.returnShapes().get(0).getKeyframes().get(0).getTime());
    assertEquals(5, example.returnShapes().get(0).getKeyframes().get(0).getWidth());
    assertEquals(3, example.returnShapes().get(0).getKeyframes().get(0).getHeight());
    assertEquals(2, example.returnShapes().get(0).getKeyframes().get(0).getX());
    assertEquals(3, example.returnShapes().get(0).getKeyframes().get(0).getY());
    assertEquals(Color.blue, example.returnShapes().get(0).getKeyframes().get(0).getColor());
    assertEquals(false, example.isKeyframeAtTime("aria", 4));
    example.addKeyframe("aria", new Dimension(40, 50),
            new Point(23, 10), Color.GRAY, 4);
    assertEquals(true, example.isKeyframeAtTime("aria", 4));
    assertEquals(3, example.returnShapes().get(0).getKeyframes().size());
    assertEquals(4, example.returnShapes().get(0).getKeyframes().get(2).getTime());
    assertEquals(40, example.returnShapes().get(0).getKeyframes().get(2).getWidth());
    assertEquals(50, example.returnShapes().get(0).getKeyframes().get(2).getHeight());
    assertEquals(23, example.returnShapes().get(0).getKeyframes().get(2).getX());
    assertEquals(10, example.returnShapes().get(0).getKeyframes().get(2).getY());
    assertEquals(Color.GRAY, example.returnShapes().get(0).getKeyframes().get(2).getColor());
    assertEquals(4, example.getFinalTick());
  }

  @Test
  public void testGetCanvasDimension() {
    AnimatorModel model = new AnimatorModelImpl();
    AnimatorModelImplToProvider example = new AnimatorModelImplToProvider(model);
    assertEquals(0.0, example.getCanvasDimension().getWidth());
    assertEquals(0.0, example.getCanvasDimension().getHeight());
    example.setBounds(new Dimension(1, 2), new Point(3, 4));
    assertEquals(1.0, example.getCanvasDimension().getWidth());
    assertEquals(2.0, example.getCanvasDimension().getHeight());
  }

  @Test
  public void testGetCanvasLocation() {
    AnimatorModel model = new AnimatorModelImpl();
    AnimatorModelImplToProvider example = new AnimatorModelImplToProvider(model);
    assertEquals(0.0, example.getCanvasLocation().getX());
    assertEquals(0.0, example.getCanvasLocation().getY());
    example.setBounds(new Dimension(1, 2), new Point(3, 4));

    assertEquals(3.0, example.getCanvasLocation().getX());
    assertEquals(4.0, example.getCanvasLocation().getY());
  }
}
