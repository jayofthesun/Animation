import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.animation.Animation;
import cs3500.animator.model.animation.Hide;
import cs3500.animator.model.animation.Motion;
import cs3500.animator.model.animation.Show;
import cs3500.animator.model.shape.Attributes;
import cs3500.animator.model.shape.ModelEllipse;
import cs3500.animator.model.shape.ModelShape;
import cs3500.animator.model.shape.ShapeAttributes;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

/**
 * Test class for Animations.
 */
public class TestAnimation {
  private Attributes state1;
  private Attributes state2;
  private Attributes state3;
  private Attributes state4;
  private Attributes state5;
  private Animation move;
  private Animation changeColor;
  private Animation changeSize;
  private Animation changeAll;
  private Animation hide;
  private Animation show;

  /**
   * Initializes test fixture.
   */

  @Before
  public void init() {
    state1 = new ShapeAttributes(Color.WHITE, 50, 50, 5, 3);
    state2 = new ShapeAttributes(Color.WHITE, 59, 41, 5, 3);
    state3 = new ShapeAttributes(Color.BLACK, 60, 40, 5, 3);
    state4 = new ShapeAttributes(Color.BLACK, 60, 40, 3, 5);
    state5 = new ShapeAttributes(Color.WHITE, 56, 44, 7, 9);

    move = new Motion(state1, state2, 1, 10);
    changeColor = new Motion(state2, state3, 10, 12);
    changeSize = new Motion(state3, state4, 12, 14);
    changeAll = new Motion(state4, state5, 14, 18);
    hide = new Hide(state5, 18, 30);
    show = new Show(state5, 30, 40);

  }

  @Test
  public void testApplyAnimation() {
    ModelShape ellipse = new ModelEllipse("E", new ShapeAttributes());
    assertTrue(ellipse.isVisible()); //shape is visible by defaualt

    move.applyAnimation(ellipse, 1);
    assertEquals(state1, ellipse.getAttributes());
    move.applyAnimation(ellipse, 5);
    assertEquals("X:54 Y:46 Width:5 Height:3 Red:255 Green:255 Blue:255",
            ellipse.getAttributes().toString());
    move.applyAnimation(ellipse, 9);
    assertEquals("X:58 Y:42 Width:5 Height:3 Red:255 Green:255 Blue:255",
            ellipse.getAttributes().toString());

    changeAll.applyAnimation(ellipse, 14);
    assertEquals(state4.toString(), ellipse.getAttributes().toString());
    changeAll.applyAnimation(ellipse, 16);
    assertEquals("X:58 Y:42 Width:5 Height:7 Red:128 Green:128 Blue:128",
            ellipse.getAttributes().toString());

    assertTrue(ellipse.isVisible()); //visible after motion
    hide.applyAnimation(ellipse, 18);
    assertEquals(state5.toString(), ellipse.getAttributes().toString());
    assertFalse(ellipse.isVisible()); //not visible after hide

    show.applyAnimation(ellipse, 34);
    assertEquals(state5.toString(), ellipse.getAttributes().toString());
    assertTrue(ellipse.isVisible()); //visible after show

    try {
      move.applyAnimation(ellipse, 19);
      fail();
    } catch (Exception e) {
      e.getMessage().equals("the time is not contained within the animation");
    }

    try {
      move.applyAnimation(null, 5);
      fail();
    } catch (Exception e) {
      e.getMessage().equals("null shape");
    }
  }


  @Test
  public void testMotionConstructor() {
    assertEquals(1, move.getStart());
    assertEquals(10, move.getEnd());
    assertEquals("X:50 Y:50 Width:5 Height:3 Red:255 Green:255 Blue:255",
            move.getStartShape().toString());
    assertEquals("X:59 Y:41 Width:5 Height:3 Red:255 Green:255 Blue:255",
            move.getEndShape().toString());


    try {
      new Motion(state1, state2, 8, -2);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "start time or end time is less than 0");
    }

    try {
      new Motion(state1, state2, 4, 2);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "start time is not before end time");
    }

    try {
      new Motion(null, state2, 2, 4);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "start and end shapes must be non null");
    }

    try {
      new Motion(state1, null, 2, 4);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "start and end shapes must be non null");
    }
  }

  @Test
  public void testHideConstructor() {
    assertEquals(30, hide.getEnd());
    assertEquals(18, hide.getStart());
    assertEquals("X:56 Y:44 Width:7 Height:9 Red:255 Green:255 Blue:255",
            hide.getEndShape().toString());
    assertEquals("X:56 Y:44 Width:7 Height:9 Red:255 Green:255 Blue:255",
            hide.getStartShape().toString());

    try {
      new Hide(state1, 8, -2);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "start time or end time is less than 0");
    }

    try {
      new Hide(state1, 4, 2);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "start time is not before end time");
    }

    try {
      new Hide(null, 2, 4);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "start and end shapes must be non null");
    }
  }

  @Test
  public void testShowConstructor() {
    assertEquals(30, show.getStart());
    assertEquals(40, show.getEnd());
    assertEquals("X:56 Y:44 Width:7 Height:9 Red:255 Green:255 Blue:255",
            show.getEndShape().toString());
    assertEquals("X:56 Y:44 Width:7 Height:9 Red:255 Green:255 Blue:255",
            show.getStartShape().toString());

    try {
      new Show(state1, 8, -2);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "start time or end time is less than 0");
    }

    try {
      new Show(state1, 4, 2);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "start time is not before end time");
    }

    try {
      new Show(null, 2, 4);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "start and end shapes must be non null");
    }
  }

  @Test
  public void testLinesUp() {
    Animation move2 = new Motion(state3, state4, 10, 12);

    assertTrue(move.linesUp(changeColor));
    assertFalse(move.linesUp(changeSize));
    assertFalse(move.linesUp(move2));
    assertFalse(changeAll.linesUp(show));
  }

  @Test
  public void testGetType() {
    assertEquals("motion", move.getType());
    assertEquals("hide", hide.getType());
    assertEquals("show", show.getType());
  }

  @Test
  public void testIsContained() {
    assertTrue(changeAll.isContained(14));
    assertTrue(changeAll.isContained(17));
    assertFalse(changeAll.isContained(18));
    assertFalse(changeAll.isContained(13));
    assertFalse(changeAll.isContained(25));
  }

  @Test
  public void testGetStart() {
    assertEquals(1, move.getStart());
    assertEquals(10, changeColor.getStart());
  }

  @Test
  public void testGetEnd() {
    assertEquals(10, move.getEnd());
    assertEquals(12, changeColor.getEnd());
  }

  @Test
  public void testGetStartShape() {
    assertEquals(state1, move.getStartShape());
    assertEquals(state2, changeColor.getStartShape());
  }

  @Test
  public void testGetEndShape() {
    assertEquals(state2, move.getEndShape());
    assertEquals(state3, changeColor.getEndShape());
  }

  @Test
  public void testSetStart() {
    assertEquals(1, move.getStart());
    move.setStart(3);
    assertEquals(3, move.getStart());

    try {
      move.setStart(12);
      fail();
    } catch (Exception e) {
      e.equals("invalid start");
    }

    try {
      move.setStart(-4);
      fail();
    } catch (Exception e) {
      e.equals("invalid start");
    }
  }

  @Test
  public void testSetEnd() {
    assertEquals(12, changeColor.getEnd());
    changeColor.setEnd(14);
    assertEquals(14, changeColor.getEnd());

    try {
      changeColor.setEnd(5);
      fail();
    } catch (Exception e) {
      e.equals("invalid end");
    }
  }

  @Test
  public void testGetCopy() {
    Animation moveCopy = move.getCopy();

    assertEquals(moveCopy.getStart(), move.getStart());
    assertEquals(moveCopy.getEnd(), move.getEnd());
    assertEquals(moveCopy.getStartShape().toString(), move.getStartShape().toString());
    assertEquals(moveCopy.getEndShape().toString(), move.getEndShape().toString());
    moveCopy.getStartShape().setColor(Color.GREEN);
    moveCopy.setStart(3);

    assertTrue(move.getStartShape().toString() != moveCopy.getStartShape().toString());
    assertTrue(move.getEndShape().toString() != moveCopy.getEndShape().toString());
  }
}
