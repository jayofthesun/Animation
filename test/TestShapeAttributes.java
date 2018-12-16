import org.junit.Test;


import java.awt.Color;

import cs3500.animator.model.shape.Attributes;
import cs3500.animator.model.shape.ShapeAttributes;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class TestShapeAttributes {

  @Test
  public void testMakeShapeAttribute() {
    ShapeAttributes ex = new ShapeAttributes(Color.BLUE, 3, 2, 5, 10);
    assertEquals(Color.blue, ex.getColor());
    assertEquals(3, ex.getX());
    assertEquals(2, ex.getY());
    assertEquals(5, ex.getWidth());
    assertEquals(10, ex.getHeight());

  }

  @Test(expected = IllegalArgumentException.class)

  public void testInvalidColor() {
    ShapeAttributes ex = new ShapeAttributes(null, 3, 2, 5, 10);
  }

  @Test(expected = IllegalArgumentException.class)

  public void testInvalidWidth() {
    ShapeAttributes ex = new ShapeAttributes(Color.BLACK, 3, 2, -4, 10);
  }

  @Test

  public void testValidZeroWidth() {
    ShapeAttributes ex = new ShapeAttributes(Color.BLACK, 3, 2, 0, 10);
    assertEquals(Color.BLACK, ex.getColor());
    assertEquals(3, ex.getX());
    assertEquals(2, ex.getY());
    assertEquals(0, ex.getWidth());
    assertEquals(10, ex.getHeight());
  }

  @Test(expected = IllegalArgumentException.class)

  public void testInvalidHeight() {
    ShapeAttributes ex = new ShapeAttributes(Color.BLACK, 3, 2, 4, -5);
  }

  @Test

  public void testValidZeroHeight() {
    ShapeAttributes ex = new ShapeAttributes(Color.BLACK, 3, 2, 5, 0);
    assertEquals(Color.BLACK, ex.getColor());
    assertEquals(3, ex.getX());
    assertEquals(2, ex.getY());
    assertEquals(5, ex.getWidth());
    assertEquals(0, ex.getHeight());
  }

  @Test
  public void testDefaultConstructor() {
    ShapeAttributes ex = new ShapeAttributes();
    assertEquals(0, ex.getX());
    assertEquals(0, ex.getY());
    assertEquals(Color.BLACK, ex.getColor());
    assertEquals(0, ex.getHeight());
    assertEquals(0, ex.getWidth());
  }

  @Test
  public void testChangeshapeSize() {
    ShapeAttributes ex = new ShapeAttributes(Color.BLUE, 3, 2, 5, 10);
    assertEquals(5, ex.getWidth());
    assertEquals(10, ex.getHeight());
    ex.changeShapeSize(20, 4);
    assertEquals(20, ex.getWidth());
    assertEquals(4, ex.getHeight());
  }

  @Test(expected = IllegalArgumentException.class)

  public void testChangeShapeSizeInvalidWidth() {
    ShapeAttributes ex = new ShapeAttributes(Color.BLUE, 3, 2, 5, 10);
    ex.changeShapeSize(-1, 12);
  }

  @Test

  public void testChangeShapeSizeValidZeroWidth() {
    ShapeAttributes ex = new ShapeAttributes(Color.BLUE, 3, 2, 5, 10);
    assertEquals(5, ex.getWidth());
    assertEquals(10, ex.getHeight());
    ex.changeShapeSize(0, 12);
    assertEquals(0, ex.getWidth());
    assertEquals(12, ex.getHeight());


  }

  @Test(expected = IllegalArgumentException.class)

  public void testChangeShapeSizeInvalidHeight() {
    ShapeAttributes ex = new ShapeAttributes(Color.BLUE, 3, 2, 5, 10);
    ex.changeShapeSize(40, -6);
  }

  @Test

  public void testChangeShapeSizeValidZeroHeight() {
    ShapeAttributes ex = new ShapeAttributes(Color.BLUE, 3, 2, 5, 10);
    assertEquals(5, ex.getWidth());
    assertEquals(10, ex.getHeight());
    ex.changeShapeSize(40, 0);
    assertEquals(40, ex.getWidth());
    assertEquals(0, ex.getHeight());

  }

  @Test(expected = IllegalArgumentException.class)

  public void testChangeShapeSizeInvalidWidthHeight() {
    ShapeAttributes ex = new ShapeAttributes(Color.BLUE, 3, 2, 5, 10);
    ex.changeShapeSize(-10, -6);
  }

  @Test

  public void testMoveShapeLeft() {
    ShapeAttributes ex = new ShapeAttributes(Color.BLUE, 3, 2, 5, 10);
    assertEquals(3, ex.getX());
    assertEquals(2, ex.getY());
    ex.moveShape(1, 2);
    assertEquals(1, ex.getX());
    assertEquals(2, ex.getY());

  }

  @Test

  public void testMoveShapeLeftDefaultConstructor() {
    ShapeAttributes ex = new ShapeAttributes();
    assertEquals(0, ex.getX());
    assertEquals(0, ex.getY());
    ex.moveShape(1, 2);
    assertEquals(1, ex.getX());
    assertEquals(2, ex.getY());

  }

  @Test

  public void testMoveShapeLeftNegative() {
    ShapeAttributes ex = new ShapeAttributes(Color.BLUE, 3, 2, 5, 10);
    assertEquals(3, ex.getX());
    assertEquals(2, ex.getY());
    ex.moveShape(-10, 2);
    assertEquals(-10, ex.getX());
    assertEquals(2, ex.getY());

  }

  @Test

  public void testMoveShapeRight() {
    ShapeAttributes ex = new ShapeAttributes(Color.BLUE, 3, 2, 5, 10);
    assertEquals(3, ex.getX());
    assertEquals(2, ex.getY());
    ex.moveShape(10, 2);
    assertEquals(10, ex.getX());
    assertEquals(2, ex.getY());

  }

  @Test

  public void testMoveShapeDown() {
    ShapeAttributes ex = new ShapeAttributes(Color.BLUE, 3, 2, 5, 10);
    assertEquals(3, ex.getX());
    assertEquals(2, ex.getY());
    ex.moveShape(3, 10);
    assertEquals(3, ex.getX());
    assertEquals(10, ex.getY());

  }

  @Test

  public void testMoveShapeUp() {
    ShapeAttributes ex = new ShapeAttributes(Color.BLUE, 3, 2, 5, 10);
    assertEquals(3, ex.getX());
    assertEquals(2, ex.getY());
    ex.moveShape(3, 1);
    assertEquals(3, ex.getX());
    assertEquals(1, ex.getY());

  }

  @Test

  public void testMoveShapeUpNegative() {
    ShapeAttributes ex = new ShapeAttributes(Color.BLUE, 3, 2, 5, 10);
    assertEquals(3, ex.getX());
    assertEquals(2, ex.getY());
    ex.moveShape(3, -10);
    assertEquals(3, ex.getX());
    assertEquals(-10, ex.getY());

  }

  @Test

  public void testMoveShapeUpLeft() {
    ShapeAttributes ex = new ShapeAttributes(Color.BLUE, 3, 2, 5, 10);
    assertEquals(3, ex.getX());
    assertEquals(2, ex.getY());
    ex.moveShape(1, 1);
    assertEquals(1, ex.getX());
    assertEquals(1, ex.getY());

  }

  @Test

  public void testMoveShapeUpRight() {
    ShapeAttributes ex = new ShapeAttributes(Color.BLUE, 3, 2, 5, 10);
    assertEquals(3, ex.getX());
    assertEquals(2, ex.getY());
    ex.moveShape(104, 1);
    assertEquals(104, ex.getX());
    assertEquals(1, ex.getY());

  }

  @Test

  public void testMoveShapeDownRight() {
    ShapeAttributes ex = new ShapeAttributes(Color.BLUE, 3, 2, 5, 10);
    assertEquals(3, ex.getX());
    assertEquals(2, ex.getY());
    ex.moveShape(104, 42);
    assertEquals(104, ex.getX());
    assertEquals(42, ex.getY());

  }

  @Test
  public void testMoveShapeDownLeft() {
    ShapeAttributes ex = new ShapeAttributes(Color.BLUE, 3, 2, 5, 10);
    assertEquals(3, ex.getX());
    assertEquals(2, ex.getY());
    ex.moveShape(2, 42);
    assertEquals(2, ex.getX());
    assertEquals(42, ex.getY());

  }

  @Test
  public void testSetColor() {
    ShapeAttributes ex = new ShapeAttributes(Color.BLUE, 3, 2, 5, 10);
    assertEquals(Color.BLUE, ex.getColor());
    ex.setColor(Color.WHITE);
    assertEquals(Color.WHITE, ex.getColor());
  }

  @Test
  public void testSetColorDefaultConstructor() {
    ShapeAttributes example = new ShapeAttributes();
    assertEquals(Color.BLACK, example.getColor());
    example.setColor(Color.PINK);
    assertEquals(Color.PINK, example.getColor());
  }

  @Test
  public void testGetColor() {
    ShapeAttributes ex = new ShapeAttributes(Color.GRAY, 3, 2, 5, 10);
    assertEquals(Color.GRAY, ex.getColor());
  }

  @Test
  public void testGetColorDefaultConstructor() {
    ShapeAttributes defaultcon = new ShapeAttributes();
    assertEquals(Color.BLACK, defaultcon.getColor());

  }

  @Test
  public void testGetX() {
    ShapeAttributes ex = new ShapeAttributes(Color.GRAY, 3, 2, 5, 10);
    assertEquals(3, ex.getX());
  }

  @Test
  public void testGetXDefaultConstructor() {
    ShapeAttributes defaultcon = new ShapeAttributes();
    assertEquals(0, defaultcon.getX());
  }

  @Test
  public void testGetY() {
    ShapeAttributes ex = new ShapeAttributes(Color.GRAY, 3, 2, 5, 10);
    assertEquals(2, ex.getY());
  }

  @Test
  public void testGetYDefaultConstructor() {
    ShapeAttributes defaultcon = new ShapeAttributes();
    assertEquals(0, defaultcon.getY());
  }

  @Test
  public void testGetWidth() {
    ShapeAttributes ex = new ShapeAttributes(Color.GRAY, 3, 2, 5, 10);
    assertEquals(5, ex.getWidth());
  }

  @Test
  public void testGetWidthDefaultConstructor() {
    ShapeAttributes defaultcon = new ShapeAttributes();
    assertEquals(0, defaultcon.getWidth());
  }

  @Test
  public void testGetHeight() {
    ShapeAttributes ex = new ShapeAttributes(Color.GRAY, 3, 2, 5, 10);
    assertEquals(10, ex.getHeight());
  }

  @Test
  public void testGetHeightDefaultConstructor() {
    ShapeAttributes defaultcon = new ShapeAttributes();
    assertEquals(0, defaultcon.getHeight());
  }

  @Test
  public void testToString() {
    ShapeAttributes ex = new ShapeAttributes(Color.GRAY, 3, 2, 5, 10);
    assertEquals("X:3 Y:2 Width:5 Height:10 Red:128 Green:128 Blue:128", ex.toString());

  }

  @Test

  public void testToStringDefault() {
    ShapeAttributes defaultcon = new ShapeAttributes();
    assertEquals("X:0 Y:0 Width:0 Height:0 Red:0 Green:0 Blue:0", defaultcon.toString());
  }

  @Test

  public void testCopy() {
    ShapeAttributes ex = new ShapeAttributes(Color.GRAY, 3, 2, 5, 10);
    Attributes copy = ex.copy();
    assertEquals(ex, copy);
    ex.setColor(Color.PINK);
    assertNotEquals(ex, copy);
  }

  @Test
  public void testCopyDefaultCon() {
    Attributes ex = new ShapeAttributes();
    Attributes copy = ex.copy();
    assertEquals(ex, copy);
    ex.setColor(Color.WHITE);
    assertNotEquals(ex, copy);
  }

  @Test

  public void testEquals() {
    Attributes ex = new ShapeAttributes(Color.GRAY, 3, 2, 5, 10);
    Attributes ex1 = new ShapeAttributes(Color.GRAY, 3, 2, 5, 10);
    assertTrue(ex.equals(ex1));

  }

  @Test

  public void testEqualsDefault() {
    Attributes ex = new ShapeAttributes();
    Attributes ex1 = new ShapeAttributes();
    assertTrue(ex.equals(ex1));

  }

  @Test

  public void testEqualsFalse() {
    Attributes ex = new ShapeAttributes(Color.GRAY, 3, 2, 5, 10);
    Attributes ex1 = new ShapeAttributes();
    assertFalse(ex.equals(ex1));
  }

}
