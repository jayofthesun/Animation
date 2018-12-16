
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModelImpl;
import cs3500.animator.model.animation.Animation;
import cs3500.animator.model.animation.Hide;
import cs3500.animator.model.animation.Motion;
import cs3500.animator.model.animation.Show;
import cs3500.animator.model.shape.Attributes;
import cs3500.animator.model.shape.ModelEllipse;
import cs3500.animator.model.shape.ModelRectangle;
import cs3500.animator.model.shape.ModelShape;
import cs3500.animator.model.shape.ShapeAttributes;

import static junit.framework.Assert.assertNotSame;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class TestAnimatorModelImpl {
  private AnimatorModel example;
  List<Animation> animations;

  /**
   * Initializes test fixture.
   */

  @Before
  public void createAnimatorModelImpl() {
    example = new AnimatorModelImpl();
    Attributes state1 = new ShapeAttributes(Color.WHITE, 50, 50, 5, 3);
    Attributes state2 = new ShapeAttributes(Color.WHITE, 59, 41, 5, 3);
    Attributes state3 = new ShapeAttributes(Color.BLACK, 60, 40, 5, 3);
    Attributes state4 = new ShapeAttributes(Color.BLACK, 60, 40, 3, 5);
    Attributes state5 = new ShapeAttributes(Color.WHITE, 56, 44, 7, 9);

    animations = new ArrayList<>();
    animations.add(new Motion(state1, state2, 1, 10));
    animations.add(new Motion(state2, state3, 10, 12));
    animations.add(new Motion(state3, state4, 12, 14));
    animations.add(new Motion(state4, state5, 14, 18));
    animations.add(new Hide(state5, 18, 30));
    animations.add(new Show(state5, 30, 40));
  }

  @Test
  public void testTickwithShapes() {
    example.addShape(new ModelEllipse("E", new ShapeAttributes()));
    example.setAnimation("E", animations);

    example.tick(1);
    assertEquals("X:50 Y:50 Width:5 Height:3 Red:255 Green:255 Blue:255",
            example.getShapeStates().get(0).getAttributes().toString());
    example.tick(5);
    assertEquals("X:54 Y:46 Width:5 Height:3 Red:255 Green:255 Blue:255",
            example.getShapeStates().get(0).getAttributes().toString());

    example.tick(10);
    assertEquals("X:59 Y:41 Width:5 Height:3 Red:255 Green:255 Blue:255",
            example.getShapeStates().get(0).getAttributes().toString());

  }

  @Test
  public void testGetShapeStates() {
    ModelShape ellipse1 = new ModelEllipse("E", new ShapeAttributes());
    ModelShape rectangle1 = new ModelEllipse("R", new ShapeAttributes());

    example.addShape(ellipse1);
    example.addShape(rectangle1);

    assertEquals(2, example.getShapeStates().size());
    assertEquals("E", example.getShapeStates().get(0).getName());
    assertEquals("R", example.getShapeStates().get(1).getName());


  }

  @Test
  public void testAddAndRemoveShape() {
    ModelShape ellipse1 = new ModelEllipse("E", new ShapeAttributes());
    ModelShape ellipse2 = new ModelEllipse("E", new ShapeAttributes());
    ModelShape rectangle1 = new ModelEllipse("R", new ShapeAttributes());

    ellipse1.show();
    rectangle1.show();

    example.addShape(ellipse1);
    example.addShape(rectangle1);

    List<ModelShape> list = Arrays.asList(ellipse1, rectangle1);
    assertEquals(list.get(0).getName(), example.getShapeStates().get(0).getName());
    assertEquals(list.get(1).getName(), example.getShapeStates().get(1).getName());

    list = Arrays.asList(ellipse1);
    example.removeShape("R");
    assertEquals(list.get(0).getName(), example.getShapeStates().get(0).getName());

    example.removeShape("NOT IN LIST");
    assertEquals(list.size(), example.getShapeStates().size());

    try {
      example.addShape(ellipse2);
      fail();
    } catch (Exception e) {
      assertEquals("name already taken", e.getMessage());
    }

    try {
      example.addShape(null);
      fail();
    } catch (Exception e) {
      assertEquals("Null Shape", e.getMessage());
    }
  }

  @Test
  public void testGetAndSetAnimationsOfShapes() {
    example.addShape(new ModelEllipse("E", new ShapeAttributes()));
    example.setAnimation("E", animations);
    List<Animation> list = example.getAnimationsOfShapeToEdit("E");

    assertEquals(animations.size(), list.size());

    list.remove(0);
    example.setAnimation("E", list);
    List<Animation> list2 = example.getAnimationsOfShapeToEdit("E");

    assertEquals(list.size(), list2.size());
    try {
      example.setAnimation("B", list);
    } catch (Exception e) {
      assertEquals("Shape not found.", e.getMessage());
    }

    list2.remove(3);
    try {
      example.setAnimation("E", list2);
    } catch (Exception e) {
      assertEquals("Invalid animation", e.getMessage());
    }

    try {
      example.getAnimationsOfShapeToEdit("NOT IN LIST");
      fail();
    } catch (Exception e) {
      assertEquals("Shape not found.", e.getMessage());
    }
  }

  @Test
  public void testGetAndSetBounds() {
    assertEquals(0, this.example.getCanvasX());
    assertEquals(0, this.example.getCanvasY());
    assertEquals(0, this.example.getCanvasWidth());
    assertEquals(0, this.example.getCanvasHeight());

    example.setBounds(1, 2, 3, 4);

    assertEquals(1, this.example.getCanvasX());
    assertEquals(2, this.example.getCanvasY());
    assertEquals(3, this.example.getCanvasWidth());
    assertEquals(4, this.example.getCanvasHeight());

    try {
      example.setBounds(0, 0, -1, 0);
      fail();
    } catch (Exception e) {
      assertEquals(e.getMessage(), "invalid width or height for canvas");
    }

    try {
      example.setBounds(0, 0, 0, -1);
      fail();
    } catch (Exception e) {
      assertEquals(e.getMessage(), "invalid width or height for canvas");
    }
  }

  @Test
  public void testGetListOfShapeNames() {
    assertEquals(Arrays.asList(), example.getListOfShapeNames());
    example.addShape(new ModelRectangle("R", new ShapeAttributes()));
    assertEquals(Arrays.asList("R"), example.getListOfShapeNames());
    example.addShape(new ModelRectangle("C", new ShapeAttributes()));
    assertEquals(Arrays.asList("R", "C"), example.getListOfShapeNames());
  }

  @Test

  public void testGetCopy() {
    ModelRectangle r = new ModelRectangle("R", new ShapeAttributes());
    example.addShape(r);
    ModelShape copy = example.getCopy("R");
    assertEquals(r.getName(), copy.getName());
    assertEquals(r.getType(), copy.getType());
    assertEquals(r.getAttributes().toString(), copy.getAttributes().toString());
    Attributes a = r.getAttributes();
    a.changeShapeSize(10, 10);
    r.setAttributes(a);
    assertNotSame(r.toString(), copy.toString());
  }

  @Test
  public void testAddSingleAnimation() {
    example.addShape(new ModelRectangle("R", new ShapeAttributes()));
    assertEquals(example.getAnimationsOfShapeToEdit("R").size(), 0);
    example.addSingleAnimation("R", new Show(new ShapeAttributes(), 1, 10));
    assertEquals(example.getAnimationsOfShapeToEdit("R").size(), 1);

  }

}
