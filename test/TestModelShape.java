import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.animation.Animation;
import cs3500.animator.model.animation.Hide;
import cs3500.animator.model.animation.Motion;
import cs3500.animator.model.animation.Show;
import cs3500.animator.model.shape.AbstractShape;
import cs3500.animator.model.shape.ModelEllipse;
import cs3500.animator.model.shape.ModelRectangle;
import cs3500.animator.model.shape.ModelShape;
import cs3500.animator.model.shape.ShapeAttributes;

import static org.junit.Assert.assertEquals;

public class TestModelShape {

  @Test

  public void testEllipseConstructor() {
    ShapeAttributes defaultAttributes = new ShapeAttributes();
    AbstractShape ex = new ModelEllipse("EllipseBob", defaultAttributes);
    assertEquals("EllipseBob", ex.getName());
    assertEquals(new ShapeAttributes(), ex.getAttributes());

  }

  @Test

  public void testRectangleConstructor() {
    ShapeAttributes defaultAttributes = new ShapeAttributes();
    AbstractShape ex = new ModelRectangle("RectangleRick", defaultAttributes);
    assertEquals("RectangleRick", ex.getName());
    assertEquals(new ShapeAttributes(), ex.getAttributes());
  }

  @Test(expected = IllegalArgumentException.class)

  public void testNullNameEllipse() {
    ShapeAttributes defaultAttributes = new ShapeAttributes();
    AbstractShape ex = new ModelEllipse(null, defaultAttributes);

  }

  @Test(expected = IllegalArgumentException.class)

  public void testNullNameRectanngle() {
    ShapeAttributes defaultAttributes = new ShapeAttributes();
    AbstractShape ex = new ModelRectangle(null, defaultAttributes);

  }

  @Test(expected = IllegalArgumentException.class)

  public void testNullAttributesEllipse() {
    AbstractShape ex = new ModelEllipse("EllipseMoon", null);

  }

  @Test(expected = IllegalArgumentException.class)

  public void testNullAttributesRectangle() {
    AbstractShape ex = new ModelRectangle("RectangleRoll", null);

  }

  @Test

  public void testVisibility() {
    ShapeAttributes defaultAttributes = new ShapeAttributes();
    AbstractShape ex = new ModelEllipse("EllipseBob", defaultAttributes);
    assertEquals(true, ex.isVisible());
  }

  @Test

  public void testShow() {
    ShapeAttributes defaultAttributes = new ShapeAttributes();
    AbstractShape ex = new ModelEllipse("EllipseBob", defaultAttributes);
    assertEquals(true, ex.isVisible());
    ex.show();
    assertEquals(true, ex.isVisible());

  }

  @Test

  public void testHide() {
    ShapeAttributes defaultAttributes = new ShapeAttributes();
    AbstractShape ex = new ModelEllipse("EllipseBob", defaultAttributes);
    ex.show();
    assertEquals(true, ex.isVisible());
    ex.hide();
    assertEquals(false, ex.isVisible());

  }

  @Test

  public void testGetName() {
    ShapeAttributes defaultAttributes = new ShapeAttributes();
    AbstractShape ex = new ModelEllipse("EllipseBob", defaultAttributes);
    assertEquals("EllipseBob", ex.getName());
  }

  @Test

  public void GetCopyofAnimations() {
    ShapeAttributes defaultAttributes = new ShapeAttributes();
    AbstractShape ex = new ModelEllipse("EllipseBob", defaultAttributes);
    assertEquals(new ArrayList<>(), ex.getCopyOfAnimations());
    List<Animation> list = new ArrayList<>();
    list.add(new Hide(defaultAttributes, 5, 10));
    ex.setAnimations(list);
    assertEquals(5, ex.getCopyOfAnimations().get(0).getStart());
    assertEquals(10, ex.getCopyOfAnimations().get(0).getEnd());
    assertEquals(defaultAttributes, ex.getCopyOfAnimations().get(0).getStartShape());
    assertEquals(defaultAttributes, ex.getCopyOfAnimations().get(0).getEndShape());

  }

  @Test

  public void setAnimationsTest() {
    ShapeAttributes defaultAttributes = new ShapeAttributes();
    AbstractShape ex = new ModelEllipse("EllipseBob", defaultAttributes);
    List<Animation> list = new ArrayList<>();
    list.add(new Show(defaultAttributes, 12, 100));
    ex.setAnimations(list);
    assertEquals(12, ex.getCopyOfAnimations().get(0).getStart());
    assertEquals(100, ex.getCopyOfAnimations().get(0).getEnd());
    assertEquals(defaultAttributes, ex.getCopyOfAnimations().get(0).getStartShape());
    assertEquals(defaultAttributes, ex.getCopyOfAnimations().get(0).getEndShape());
  }

  @Test(expected = IllegalStateException.class)

  public void setAnimationsInvalidTest() {
    ShapeAttributes defaultAttributes = new ShapeAttributes();
    AbstractShape ex = new ModelEllipse("EllipseBob", defaultAttributes);
    List<Animation> list = new ArrayList<>();
    list.add(new Hide(defaultAttributes, 5, 10));
    list.add(new Hide(defaultAttributes, 6, 7));
    ex.setAnimations(list);

  }

  @Test

  public void getTypeEllipse() {
    ShapeAttributes defaultAttributes = new ShapeAttributes();
    AbstractShape ex = new ModelEllipse("EllipseBob", defaultAttributes);
    assertEquals("ellipse", ex.getType());
  }

  @Test

  public void getTypeRectangle() {
    ShapeAttributes defaultAttributes = new ShapeAttributes();
    AbstractShape ex = new ModelRectangle("RectangleMorty", defaultAttributes);
    assertEquals("rectangle", ex.getType());
  }

  @Test

  public void goToTest() {
    ShapeAttributes defaultAttributes = new ShapeAttributes();
    ShapeAttributes diffAtt = new ShapeAttributes(Color.GREEN, 10, 20, 40, 30);

    ModelShape ex = new ModelEllipse("Ellipse", defaultAttributes);

    List<Animation> list = new ArrayList<>();
    list.add(new Motion(defaultAttributes, diffAtt, 1, 5));
    list.add(new Show(diffAtt, 5, 6));
    ex.setAnimations(list);

    ex.goTo(1);
    assertEquals(defaultAttributes, ex.getAttributes());
    ex.goTo(2);
    assertEquals("X:3 Y:5 Width:10 Height:8 Red:0 Green:64 Blue:0",
            ex.getAttributes().toString());
    ex.goTo(3);
    assertEquals("X:5 Y:10 Width:20 Height:15 Red:0 Green:128 Blue:0",
            ex.getAttributes().toString());
    ex.goTo(4);
    assertEquals("X:8 Y:15 Width:30 Height:23 Red:0 Green:191 Blue:0",
            ex.getAttributes().toString());
    ex.goTo(5);
    assertEquals("X:10 Y:20 Width:40 Height:30 Red:0 Green:255 Blue:0",
            ex.getAttributes().toString());
    ex.goTo(6);
    assertEquals("X:10 Y:20 Width:40 Height:30 Red:0 Green:255 Blue:0",
            ex.getAttributes().toString());
  }

  @Test(expected = IllegalArgumentException.class)

  public void goToInvalidTest() {
    ShapeAttributes defaultAttributes = new ShapeAttributes();
    AbstractShape ex = new ModelEllipse("EllipseBob", defaultAttributes);
    ex.goTo(0);
  }

  @Test

  public void getAttributesTest() {
    ShapeAttributes diffAtt = new ShapeAttributes(Color.BLUE, 10, 20, 40, 30);
    AbstractShape ex1 = new ModelEllipse("EllipseBob", diffAtt);
    assertEquals(diffAtt, ex1.getAttributes());
  }

  @Test
  public void addKeyFrameMiddleTest() {
    ShapeAttributes diffAtt = new ShapeAttributes(Color.BLUE, 10, 20, 40, 30);
    ShapeAttributes defaultAttributes = new ShapeAttributes();
    ShapeAttributes diff2Att =
            new ShapeAttributes(Color.ORANGE, 40, 100, 20, 100);
    AbstractShape ex1 = new ModelEllipse("EllipseBob", defaultAttributes);
    List<Animation> list = new ArrayList<>();
    list.add(new Motion(defaultAttributes, diffAtt, 1, 5));
    list.add(new Motion(diffAtt, diff2Att, 5, 10));
    ex1.setAnimations(list);
    assertEquals(2, ex1.getCopyOfAnimations().size());
    ex1.goTo(3);
    assertEquals("X:5 Y:10 Width:20 Height:15 Red:0 Green:0 Blue:128",
            ex1.getAttributes().toString());
    ex1.addKeyFrame(3, new ShapeAttributes(Color.GREEN, 78, 49, 20, 49));
    ex1.goTo(3);
    assertEquals("X:78 Y:49 Width:20 Height:49 Red:0 Green:255 Blue:0",
            ex1.getAttributes().toString());
    assertEquals(5, ex1.getCopyOfAnimations().get(1).getEnd());
    assertEquals(3, ex1.getCopyOfAnimations().get(1).getStart());
    assertEquals(3, ex1.getCopyOfAnimations().size());
  }

  @Test

  public void addKeyFrameStartTest() {
    ShapeAttributes diffAtt = new ShapeAttributes(Color.BLUE, 10, 20, 40, 30);
    ShapeAttributes defaultAttributes = new ShapeAttributes();
    ShapeAttributes diff2Att =
            new ShapeAttributes(Color.ORANGE, 40, 100, 20, 100);
    AbstractShape ex1 = new ModelEllipse("EllipseBob", defaultAttributes);
    List<Animation> list = new ArrayList<>();
    list.add(new Motion(defaultAttributes, diffAtt, 1, 5));
    list.add(new Motion(diffAtt, diff2Att, 5, 10));
    ex1.setAnimations(list);
    assertEquals(2, ex1.getCopyOfAnimations().size());
    ex1.goTo(1);
    assertEquals("X:0 Y:0 Width:0 Height:0 Red:0 Green:0 Blue:0",
            ex1.getAttributes().toString());
    ex1.addKeyFrame(1, new ShapeAttributes(Color.GREEN, 78, 49, 20, 49));
    ex1.goTo(1);
    assertEquals("X:78 Y:49 Width:20 Height:49 Red:0 Green:255 Blue:0",
            ex1.getAttributes().toString());
    assertEquals(3, ex1.getCopyOfAnimations().size());
  }

  @Test

  public void addKeyFrameEndTest() {
    ShapeAttributes diffAtt = new ShapeAttributes(Color.BLUE, 10, 20, 40, 30);
    ShapeAttributes defaultAttributes = new ShapeAttributes();
    ShapeAttributes diff2Att =
            new ShapeAttributes(Color.ORANGE, 40, 100, 20, 100);
    AbstractShape ex1 = new ModelEllipse("EllipseBob", defaultAttributes);
    List<Animation> list = new ArrayList<>();
    list.add(new Motion(defaultAttributes, diffAtt, 1, 5));
    list.add(new Motion(diffAtt, diff2Att, 5, 10));
    ex1.setAnimations(list);
    assertEquals(2, ex1.getCopyOfAnimations().size());
    ex1.goTo(5);
    assertEquals("X:10 Y:20 Width:40 Height:30 Red:0 Green:0 Blue:255",
            ex1.getAttributes().toString());
    ex1.addKeyFrame(5, new ShapeAttributes(Color.GREEN, 78, 49, 20, 49));
    ex1.goTo(5);
    assertEquals("X:78 Y:49 Width:20 Height:49 Red:0 Green:255 Blue:0",
            ex1.getAttributes().toString());
    assertEquals(3, ex1.getCopyOfAnimations().size());
  }

  @Test

  public void addKeyFrameEmptyTest() {
    ShapeAttributes defaultAttributes = new ShapeAttributes();
    AbstractShape ex1 = new ModelEllipse("EllipseBob", defaultAttributes);
    List<Animation> list = new ArrayList<>();
    assertEquals(0, ex1.getCopyOfAnimations().size());
    ex1.addKeyFrame(2, new ShapeAttributes(Color.GREEN, 78, 49, 20, 49));
    assertEquals("X:78 Y:49 Width:20 Height:49 Red:0 Green:255 Blue:0",
            ex1.getCopyOfAnimations().get(0).getStartShape().toString());
    assertEquals(2, ex1.getCopyOfAnimations().get(0).getStart());
    assertEquals(2, ex1.getCopyOfAnimations().get(0).getEnd());
    assertEquals(1, ex1.getCopyOfAnimations().size());
  }

  @Test

  public void deleteKeyFrameTest() {
    ShapeAttributes diffAtt = new ShapeAttributes(Color.BLUE, 10, 20, 40, 30);
    ShapeAttributes defaultAttributes = new ShapeAttributes();
    ShapeAttributes diff2Att =
            new ShapeAttributes(Color.ORANGE, 40, 100, 20, 100);
    AbstractShape ex1 = new ModelEllipse("EllipseBob", defaultAttributes);
    List<Animation> list = new ArrayList<>();
    list.add(new Motion(defaultAttributes, diffAtt, 1, 5));
    list.add(new Motion(diffAtt, diff2Att, 5, 10));
    ex1.setAnimations(list);
    assertEquals(2, ex1.getCopyOfAnimations().size());
    assertEquals("X:0 Y:0 Width:0 Height:0 Red:0 Green:0 Blue:0",
            ex1.getCopyOfAnimations().get(0).getStartShape().toString());
    assertEquals("X:10 Y:20 Width:40 Height:30 Red:0 Green:0 Blue:255",
            ex1.getCopyOfAnimations().get(0).getEndShape().toString());
    assertEquals(5, ex1.getCopyOfAnimations().get(0).getEnd());
    assertEquals(1, ex1.getCopyOfAnimations().get(0).getStart());

    ex1.removeKeyFrame(0);
    assertEquals(5, ex1.getCopyOfAnimations().get(0).getStart());
    assertEquals(10, ex1.getCopyOfAnimations().get(0).getEnd());
    assertEquals("X:10 Y:20 Width:40 Height:30 Red:0 Green:0 Blue:255",
            ex1.getCopyOfAnimations().get(0).getStartShape().toString());
    assertEquals("X:40 Y:100 Width:20 Height:100 Red:255 Green:200 Blue:0",
            ex1.getCopyOfAnimations().get(0).getEndShape().toString());

    assertEquals(1, ex1.getCopyOfAnimations().size());
  }

  @Test

  public void deleteKeyFrameEmptyTest() {
    ShapeAttributes defaultAttributes = new ShapeAttributes();
    AbstractShape ex1 = new ModelEllipse("EllipseBob", defaultAttributes);
    assertEquals(0, ex1.getCopyOfAnimations().size());
    ex1.removeKeyFrame(2);
    assertEquals(0, ex1.getCopyOfAnimations().size());
  }


}
