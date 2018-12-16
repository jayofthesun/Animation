import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

import java.awt.Color;

import cs3500.animator.model.adaptations.Keyframe;
import cs3500.animator.model.shape.ShapeAttributes;
import cs3500.animator.provider.model.IKeyframe;

/**
 * Tests for our implementation of our providers IKeyframe interface.
 */
public class TestAdapterKeyframe {
  private IKeyframe keyframe;

  @Before
  public void initKeyframe() {
    this.keyframe = new Keyframe(10,
            new ShapeAttributes(Color.BLACK, 6, 7, 8, 9));
  }

  @Test
  public void testGetters() {
    assertEquals(10, keyframe.getTime());
    assertEquals(6, keyframe.getX());
    assertEquals(7, keyframe.getY());
    assertEquals(8, keyframe.getWidth());
    assertEquals(9, keyframe.getHeight());
    assertEquals(Color.BLACK, keyframe.getColor());
  }
}
