package cs3500.animator.model.shape;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.animation.Animation;
import cs3500.animator.model.animation.Motion;

public abstract class AbstractShape implements ModelShape {
  private String name;
  private boolean visibility;
  private List<Animation> animations;
  private Attributes attributes;

  AbstractShape(String name, Attributes attributes) {
    if (name == null) {
      throw new IllegalArgumentException("name is null");
    }
    if (attributes == null) {
      throw new IllegalArgumentException("attributes is null");
    }
    this.name = name;
    this.visibility = true;
    this.animations = new ArrayList<>();
    this.setAttributes(attributes);
  }

  @Override
  public boolean isVisible() {
    return this.visibility;
  }

  @Override
  public void show() {
    this.visibility = true;
  }

  @Override
  public void hide() {
    this.visibility = false;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public List<Animation> getCopyOfAnimations() {
    List<Animation> copy = new ArrayList<>();
    for (Animation a : this.animations) {
      copy.add(a.getCopy());
    }
    return copy;
  }

  @Override

  public void setAnimations(List<Animation> animations) {
    if (isValid(animations)) {
      this.animations = animations;
    } else {
      throw new IllegalStateException("Invalid animation");
    }
  }

  /**
   * checks if the animation is valid.
   *
   * @param animations the list of animations to check
   * @return true if the animations is valid else returns false
   */

  private static boolean isValid(List<Animation> animations) {
    for (int i = 0; i < animations.size() - 1; i++) {
      if (!animations.get(i).linesUp(animations.get(i + 1))) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void setAttributes(Attributes a) throws IllegalArgumentException {
    if (a == null) {
      throw new IllegalArgumentException("invalid shape attributes");
    }
    this.attributes = a;
  }

  @Override
  public abstract String getType();

  @Override
  public void goTo(int time) throws IllegalArgumentException {
    if (time < 1) {
      throw new IllegalArgumentException("Time cannot be less than 1");
    }
    for (Animation c : this.animations) {
      if (c.isContained(time)) {
        c.applyAnimation(this, time);
      }
    }
  }

  @Override
  public Attributes getAttributes() {
    return this.attributes.copy();
  }

  @Override
  public void addKeyFrame(int time, Attributes attributes) {
    for (int i = 0; i < this.animations.size(); i++) {
      if (this.animations.get(i).isContained(time)) {
        Animation oldAnimation = this.animations.remove(i);
        this.animations.add(i, new Motion(oldAnimation.getStartShape(), attributes,
                oldAnimation.getStart(), time));
        this.animations.add(i + 1, new Motion(attributes, oldAnimation.getEndShape(), time
                , oldAnimation.getEnd()));
        return;
      }
    }

    if (this.animations.isEmpty()) {
      this.animations.add(new Motion(attributes, attributes.copy(), time, time));
      return;
    }

    if (time < this.animations.get(0).getStart()) {
      this.animations.add(new Motion(attributes,
              this.animations.get(0).getStartShape(), time, this.animations.get(0).getStart()));
      return;
    } else {
      this.animations.add(new Motion(this.animations.get(this.animations.size() - 1).getEndShape()
              , attributes, this.animations.get(this.animations.size() - 1).getEnd(), time));
      return;
    }

  }

  @Override
  public void removeKeyFrame(int index) {
    if (this.animations.isEmpty()) {
      return;
    } else if (index == 0) {
      this.animations.remove(index);
    } else if (index == this.animations.size()) {
      this.animations.remove(this.animations.size() - 1);
    } else if (index > this.animations.size()) {
      throw new IllegalArgumentException("Index not contained");
    } else {
      Animation end = this.animations.remove(index);
      Animation start = this.animations.remove(index - 1);
      this.animations.add(index - 1, new Motion(start.getStartShape(), end.getEndShape(),
              start.getStart(), end.getEnd()));
    }
  }
}
