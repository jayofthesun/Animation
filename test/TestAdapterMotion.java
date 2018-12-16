import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import cs3500.animator.model.adaptations.Motion;
import cs3500.animator.model.shape.ShapeAttributes;
import cs3500.animator.provider.model.IMotion;

/**
 * Tests for our implementation of our providers IMotion interface.
 */
public class TestAdapterMotion {
  private IMotion motion1;
  private IMotion motion2;
  private IMotion motion3;

  @Before
  public void initMotions() {
    motion1 = new Motion(new ShapeAttributes(Color.BLACK, 1, 2, 3, 4),
            new ShapeAttributes(Color.WHITE, 5, 6, 7, 8), 1, 10);
    motion2 = new Motion(new ShapeAttributes(Color.WHITE, 5, 6, 7, 8),
            new ShapeAttributes(Color.WHITE, 5, 6, 7, 8), 10, 20);
    motion3 = new Motion(new ShapeAttributes(Color.BLACK, 1, 2, 3, 4),
            new ShapeAttributes(Color.WHITE, 5, 6, 7, 8), 10, 20);

  }

  @Test
  public void testGetters() {
    assertEquals(1, motion1.getPrevX());
    assertEquals(2, motion1.getPrevY());
    assertEquals(3, motion1.getPrevWidth());
    assertEquals(4, motion1.getPrevHeight());
    assertEquals(Color.BLACK, motion1.getPrevColor());
    assertEquals(1, motion1.getStartTime());
    assertEquals(5, motion1.getNewX());
    assertEquals(6, motion1.getNewY());
    assertEquals(7, motion1.getNewWidth());
    assertEquals(8, motion1.getNewHeight());
    assertEquals(Color.WHITE, motion1.getNewColor());
    assertEquals(10, motion1.getEndTime());
  }

  @Test
  public void testTimeWithin() {
    assertTrue(motion1.timeIsWithin(1));
    assertTrue(motion1.timeIsWithin(9));
    assertFalse(motion1.timeIsWithin(10));
    assertFalse(motion1.timeIsWithin(0));
  }

  @Test
  public void testConsecutive() {
    assertTrue(motion1.consecutive(motion2));
    assertFalse(motion1.consecutive(motion3));
  }

  @Test
  public void testAtTimes() {
    assertEquals(new Dimension(5, 6), motion1.dimensionAtTime(5));
    assertEquals(new Point(3, 4), motion1.positionAtTime(5));
    assertEquals(new Color(113, 113, 113), motion1.colorAtTime(5));
  }
}
