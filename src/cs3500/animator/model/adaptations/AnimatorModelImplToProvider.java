package cs3500.animator.model.adaptations;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.animation.Animation;
import cs3500.animator.model.animation.Motion;
import cs3500.animator.model.shape.ModelEllipse;
import cs3500.animator.model.shape.ModelRectangle;
import cs3500.animator.model.shape.ModelShape;
import cs3500.animator.model.shape.ShapeAttributes;
import cs3500.animator.provider.model.IAnimationModelWrapper;
import cs3500.animator.provider.model.IShape;

public class AnimatorModelImplToProvider implements IAnimationModelWrapper {
  private AnimatorModel model;

  public AnimatorModelImplToProvider(AnimatorModel model) {
    this.model = model;
  }

  @Override
  public void setBounds(Dimension d, Point p) {
    this.model.setBounds((int) p.getX(), (int) p.getY(), (int) d.getWidth(), (int) d.getHeight());
  }

  @Override
  public List<IShape> returnShapes() {
    List<ModelShape> shapes = this.model.getShapeStates();
    List<IShape> rList = new ArrayList<>();
    for (ModelShape s : shapes) {
      rList.add(new Shape(s));
    }
    return rList;
  }

  @Override
  public void addShape(String shapeName, String typeOfShape) {
    if (typeOfShape.equals("rectangle")) {
      this.model.addShape(new ModelRectangle(shapeName, new ShapeAttributes()));
    } else {
      this.model.addShape(new ModelEllipse(shapeName, new ShapeAttributes()));
    }
  }

  @Override
  public void removeShape(String name) {
    this.model.removeShape(name);
  }

  @Override
  public void addKeyframe(String shapeName, Dimension dimension, Point position,
                          Color color, int time) {
    this.model.addKeyFrame(shapeName, time, (int) dimension.getHeight(), (int) dimension.getWidth(),
            color, (int) position.getX(), (int) position.getY());
  }

  @Override
  public void removeKeyframe(String shapeName, int time) {
    this.model.removeKeyFrame(shapeName,
            timeToIndex(this.model.getAnimationsOfShapeToEdit(shapeName), time));

  }

  /**
   * Converts time of an animation to an index in the given list.
   *
   * @param loa       list of animations
   * @param shapeTime the animation time
   * @return index number for the animation at that time
   */

  public static int timeToIndex(List<Animation> loa, int shapeTime) {
    for (int i = 0; i < loa.size(); i++) {
      if (loa.get(i).getStart() == shapeTime) {
        return i;
      }
    }
    if (loa.get(loa.size() - 1).getEnd() == shapeTime) {
      return loa.size();
    }
    return -1;
  }

  @Override
  public boolean isKeyframeAtTime(String shapeName, int time) {
    return timeToIndex(this.model.getAnimationsOfShapeToEdit(shapeName), time) != -1;
  }

  @Override
  public void editKeyframe(String shapeName, int x, int y, int width, int height,
                           Color color, int time) {
    if (isKeyframeAtTime(shapeName, time)) {
      this.model.removeKeyFrame(shapeName,
              timeToIndex(this.model.getAnimationsOfShapeToEdit(shapeName), time));
    }
    this.model.addKeyFrame(shapeName, time, height, width, color, x, y);
  }

  @Override
  public void addMotion(String shapeName, Dimension prevDimension, Point prevPosition,
                        Color prevColor, Dimension newDimension, Point newPosition,
                        Color newColor, int startTime, int endTime) {
    this.model.addSingleAnimation(shapeName, new Motion(new ShapeAttributes(prevColor,
            (int) prevPosition.getX(), (int) prevPosition.getY(), (int) prevDimension.getWidth(),
            (int) prevDimension.getHeight()), new ShapeAttributes(newColor,
            (int) newPosition.getX(), (int) newPosition.getY(), (int) newDimension.getWidth(),
            (int) newDimension.getHeight()), startTime, endTime));
  }

  @Override
  public int getFinalTick() {
    int i = 0;
    while (!this.model.isOver(i)) {
      i++;
    }
    return i;
  }

  @Override
  public Dimension getCanvasDimension() {
    return new Dimension(this.model.getCanvasWidth(), this.model.getCanvasHeight());
  }

  @Override
  public Point getCanvasLocation() {
    return new Point(this.model.getCanvasX(), this.model.getCanvasY());
  }

  @Override
  public boolean isPlayable() {
    return true;
  }
}
