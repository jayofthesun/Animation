package cs3500.animator.model.animation;

import cs3500.animator.model.shape.Attributes;
import cs3500.animator.model.shape.ModelShape;

/**
 * An abstract class for animations.
 */
public abstract class AbstractAnimation implements Animation {
  protected int start;
  protected int end;
  protected Attributes startShape;
  protected Attributes endShape;

  /**
   * Constructor for a motion animation.
   *
   * @param startShape the starting attributes of animation of shape
   * @param endShape   the ending attributes of animation of shape
   * @param start      start time
   * @param end        end time
   */
  public AbstractAnimation(Attributes startShape, Attributes endShape, int start, int end) {
    if (start < 0 || end < 0) {
      throw new IllegalArgumentException("start time or end time is less than 0");
    }

    if (start > end) {
      throw new IllegalArgumentException("start time is not before end time");
    }

    if (startShape == null || endShape == null) {
      throw new IllegalArgumentException("start and end shapes must be non null");
    }

    this.startShape = startShape;
    this.endShape = endShape;
    this.start = start;
    this.end = end;
  }

  @Override
  public void applyAnimation(ModelShape s, int t) {
    if (s == null) {
      throw new IllegalArgumentException("null shape");
    }
    if (!this.isContained(t)) {
      throw new IllegalArgumentException("the time is not contained within the animation");
    }
  }

  @Override
  public boolean isContained(int t) {
    return this.start <= t && t < this.end;
  }

  @Override
  public abstract String getType();

  @Override
  public abstract Animation getCopy();

  @Override
  public boolean linesUp(Animation that) {
    return this.end == that.getStart() && this.endShape.equals(that.getStartShape());
  }

  @Override
  public int getStart() {
    return this.start;
  }

  @Override
  public int getEnd() {
    return this.end;
  }

  @Override
  public Attributes getStartShape() {
    return this.startShape;
  }

  @Override
  public Attributes getEndShape() {
    return this.endShape;
  }

  @Override
  public void setStart(int start) {
    if (start >= 0 && start <= this.end) {
      this.start = start;
    } else {
      throw new IllegalArgumentException("invalid start");
    }
  }

  @Override
  public void setEnd(int end) {
    if (end >= this.start) {
      this.end = end;
    } else {
      throw new IllegalArgumentException("invalid end");
    }
  }

}

