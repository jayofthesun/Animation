package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import cs3500.animator.model.animation.Animation;
import cs3500.animator.model.animation.Motion;
import cs3500.animator.model.shape.ModelEllipse;
import cs3500.animator.model.shape.ModelRectangle;
import cs3500.animator.model.shape.ModelShape;
import cs3500.animator.model.shape.ShapeAttributes;
import cs3500.animator.util.AnimationBuilder;

/**
 * Concrete implementation of Animator cs3500.animator.model. Creates a representation of an
 * animation by adding shapes to the model which contain animations. Also keeps track of the tick in
 * the model and updates the shapes accordingly.
 */

public class AnimatorModelImpl implements AnimatorModel {
  private List<ModelShape> shapes;

  // add fields for canvas
  private int canvasWidth;
  private int canvasHeight;
  private int canvasX;
  private int canvasY;

  /**
   * Sets time to 0 and initializes the list of shapes in the animation.
   */
  public AnimatorModelImpl() {
    this.shapes = new ArrayList<ModelShape>();
    this.canvasHeight = 0;
    this.canvasWidth = 0;
    this.canvasX = 0;
    this.canvasY = 0;
  }

  @Override
  public void tick(int time) {
    for (ModelShape s : this.shapes) {
      s.goTo(time);
    }
  }

  @Override
  public boolean isOver(int time) {
    List<Animation> animationList;

    for (ModelShape shape : this.shapes) {
      animationList = shape.getCopyOfAnimations();
      if (animationList.size() == 0) {
        continue;
      }
      if (animationList.get(animationList.size() - 1).getEnd() > time) {
        return false;
      }
    }
    return true;
  }

  @Override
  public List<String> getKeyFrames(String name) {
    List<Animation> a = this.getAnimationsOfShapeToEdit(name);
    List<String> keyFrames = new ArrayList<>();
    if (a.size() == 0) {
      return keyFrames;
    }

    for (Animation anime : a) {
      keyFrames.add("T:" + anime.getStart() + " " + anime.getStartShape().toString());
      keyFrames.add("T:" + anime.getStart() + " " + anime.getStartShape().toString());
    }
    Animation last = a.get(a.size() - 1);

    keyFrames.add("T:" + last.getEnd() + " " + last.getEndShape().toString());
    return keyFrames;
  }

  @Override
  public List<ModelShape> getShapeStates() {
    List<ModelShape> toDraw = new ArrayList<>();
    for (ModelShape shape : this.shapes) {
      if (shape.isVisible()) {
        toDraw.add(shape.copy());
      }
    }
    return toDraw;
  }

  /**
   * Adds a shape to the animation.
   *
   * @param shape modelShape
   * @throws IllegalArgumentException if the given shape is null or if the a shape is already in the
   *                                  cs3500.animator.model with the same name
   */
  @Override
  public void addShape(ModelShape shape) throws IllegalArgumentException {
    if (shape == null) {
      throw new IllegalArgumentException("Null Shape");
    }
    if (this.getListOfShapeNames().contains(shape.getName())) {
      throw new IllegalArgumentException("name already taken");
    }
    this.shapes.add(shape);
  }

  @Override
  public void removeShape(String shapeName) {
    int i = indexOfShape(shapeName);
    if (i != -1) {
      this.shapes.remove(i);
    }
  }

  @Override
  public List<Animation> getAnimationsOfShapeToEdit(String shapeName) {
    int i = indexOfShape(shapeName);
    if (i != -1) {
      return this.shapes.get(i).getCopyOfAnimations();
    } else {
      throw new IllegalArgumentException("Shape not found.");
    }
  }

  @Override
  public void setAnimation(String shapeName, List<Animation> animations)
          throws IllegalStateException, IllegalArgumentException {
    int i = indexOfShape(shapeName);
    if (i != -1) {
      this.shapes.get(i).setAnimations(animations);
    } else {
      throw new IllegalArgumentException("Shape not found.");
    }
  }

  @Override
  public void setBounds(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("invalid width or height for canvas");
    }
    this.canvasX = x;
    this.canvasY = y;
    this.canvasWidth = width;
    this.canvasHeight = height;
  }

  @Override
  public List<String> getListOfShapeNames() {
    List<String> los = new ArrayList<>();
    for (ModelShape shape : this.shapes) {
      los.add(shape.getName());
    }
    return los;
  }

  @Override
  public int getCanvasX() {
    return this.canvasX;
  }

  @Override
  public int getCanvasY() {
    return this.canvasY;
  }

  @Override
  public int getCanvasWidth() {
    return this.canvasWidth;
  }

  @Override
  public int getCanvasHeight() {
    return this.canvasHeight;
  }

  @Override

  public void addKeyFrame(String name, int time, int height, int width, Color color, int x, int y) {
    if (indexOfShape(name) == -1) {
      throw new IllegalArgumentException("not a valid shape");
    } else {
      shapes.get(indexOfShape(name)).addKeyFrame(time,
              new ShapeAttributes(color, x, y, width, height));
    }
  }

  @Override
  public void removeKeyFrame(String name, int index) {
    if (index < 0) {
      throw new IllegalArgumentException("Keyframe is not contained");
    } else if (indexOfShape(name) == -1) {
      throw new IllegalArgumentException("not a valid shape");
    } else {
      shapes.get(indexOfShape(name)).removeKeyFrame(index);
    }
  }

  /**
   * Gets the index of the shape in the list of shapes.
   *
   * @param name the name of the shape being searched for
   * @return the index of the shape in the list, -1 if the shape is not found in the list
   */
  private int indexOfShape(String name) {
    for (int i = 0; i < this.shapes.size(); i++) {
      if (this.shapes.get(i).getName().equals(name)) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public ModelShape getCopy(String name) {
    int i = indexOfShape(name);
    if (i != -1) {
      return this.shapes.get(i).copy();
    } else {
      throw new IllegalArgumentException("Shape not found.");
    }
  }

  @Override
  public void addSingleAnimation(String name, Animation animation) {
    List<Animation> anime = this.getAnimationsOfShapeToEdit(name);
    anime.add(animation);
    this.setAnimation(name, anime);
  }


  /**
   * A builder which helps to create a fully fleshed out model from a file with the help of the
   * animationreader class.
   */
  public static final class Builder implements AnimationBuilder<AnimatorModel> {
    private AnimatorModel model;

    /**
     * Constructs a builder with an blank Animator Model.
     */
    public Builder() {
      this.model = new AnimatorModelImpl();
    }

    @Override
    public AnimatorModel build() {
      return this.model;
    }

    @Override
    public AnimationBuilder<AnimatorModel> setBounds(int x, int y, int width, int height) {
      this.model.setBounds(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> declareShape(String name, String type) {
      if (type.equals("ellipse")) {
        this.model.addShape(new ModelEllipse(name, new ShapeAttributes()));
      } else {
        this.model.addShape(new ModelRectangle(name, new ShapeAttributes()));
      }
      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> addMotion(String name, int t1, int x1, int y1, int w1,
                                                     int h1, int r1, int g1, int b1, int t2, int x2,
                                                     int y2, int w2, int h2,
                                                     int r2, int g2, int b2) {

      List<Animation> animations = this.model.getAnimationsOfShapeToEdit(name);
      animations.add(new Motion(new ShapeAttributes(new Color(r1, g1, b1), x1, y1, w1, h1),
              new ShapeAttributes(new Color(r2, g2, b2), x2, y2, w2, h2), t1, t2));
      this.model.setAnimation(name, animations);
      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> addKeyframe(String name, int t, int x, int y, int w,
                                                       int h, int r, int g, int b) {
      this.model.addKeyFrame(name, t, h, w, new Color(r, g, b), x, y);
      return this;
    }
  }
}
