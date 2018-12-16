package cs3500.animator.model.adaptations;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.animation.Animation;
import cs3500.animator.model.shape.ModelShape;
import cs3500.animator.model.shape.ShapeAttributes;
import cs3500.animator.provider.model.IKeyframe;
import cs3500.animator.provider.model.IMotion;
import cs3500.animator.provider.model.IShape;

/**
 * Adapter class that implements the behavior of an IShape using our implementation of ModelShape.
 * Represents a single shape in an animation.
 */
public class Shape implements IShape {
  private ModelShape shape;

  /**
   * Wrapper for our implementation of ModelShape.
   *
   * @param shape shape to convert to IShape
   */
  public Shape(ModelShape shape) {
    this.shape = shape;
  }

  @Override
  public void addMotion(IMotion motion) {
    List<Animation> anime = this.shape.getCopyOfAnimations();
    anime.add((Animation) motion);
    this.shape.setAnimations(anime);
  }

  @Override
  public void addKeyframe(IKeyframe keyframe) {
    this.shape.addKeyFrame(keyframe.getTime(),
            new ShapeAttributes(keyframe.getColor(), keyframe.getX(), keyframe.getY(),
                    keyframe.getWidth(), keyframe.getWidth()));
  }

  @Override
  public void removeKeyframe(int time) {
    this.shape.removeKeyFrame(
            AnimatorModelImplToProvider.timeToIndex(this.shape.getCopyOfAnimations(), time));
  }

  @Override
  public void editKeyframe(IKeyframe newKeyframe) {
    this.removeKeyframe(newKeyframe.getTime());
    this.addKeyframe(newKeyframe);
  }

  @Override
  public List<IMotion> getMotions() {
    List<Animation> shapes = shape.getCopyOfAnimations();
    List<IMotion> rList = new ArrayList<>();
    for (Animation a : shapes) {
      rList.add(new Motion(a.getStartShape(), a.getEndShape(), a.getStart(), a.getEnd()));
    }
    return rList;
  }

  @Override
  public List<IKeyframe> getKeyframes() {
    List<IKeyframe> lok = new ArrayList<>();
    List<Animation> anime = this.shape.getCopyOfAnimations();
    if (anime.size() == 0) {
      return lok;
    } else {
      for (Animation a : anime) {
        lok.add(new Keyframe(a.getStart(), a.getStartShape()));
      }
      lok.add(new Keyframe(
              anime.get(anime.size() - 1).getEnd(), anime.get(anime.size() - 1).getEndShape()));
      return lok;
    }
  }


  @Override
  public void calculateMotionAtTick(int tick) {
    this.shape.goTo(tick);
  }

  @Override
  public String getType() {
    if (this.shape.getType().equals("rectangle")) {
      return "Rectangle";
    } else {
      return "Oval";
    }
  }

  @Override
  public Dimension getDimensions() {
    return new Dimension(this.shape.getAttributes().getWidth(),
            this.shape.getAttributes().getHeight());
  }

  @Override
  public Color getColor() {
    return this.shape.getAttributes().getColor();
  }

  @Override
  public Point getPosition() {
    return new Point(this.shape.getAttributes().getX(), this.shape.getAttributes().getY());
  }

  @Override
  public boolean isPlayable() {
    return true;
  }

  @Override
  public int getFinalTick() {
    List<Animation> anime = this.shape.getCopyOfAnimations();
    if (anime.size() != 0) {
      return anime.get(anime.size() - 1).getEnd();
    } else {
      return 0;
    }
  }

  @Override
  public int getFirstTick() {
    List<Animation> anime = this.shape.getCopyOfAnimations();
    if (anime.size() != 0) {
      return anime.get(0).getStart();
    } else {
      return 0;
    }
  }

  @Override
  public String getName() {
    return this.shape.getName();
  }

  @Override
  public boolean isRunningDuringTick(int tick) {
    return this.getFirstTick() <= tick && this.getFinalTick() > tick;
  }
}
