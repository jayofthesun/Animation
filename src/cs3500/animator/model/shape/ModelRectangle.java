package cs3500.animator.model.shape;

/**
 * Concrete ModelShape implementation of a rectangle.
 */
public class ModelRectangle extends AbstractShape {
  /**
   * Constructor for a rectangle.
   *
   * @param name       name to identify the rectangle
   * @param attributes attributes of the rectangle
   */
  public ModelRectangle(String name, Attributes attributes) {
    super(name, attributes);
  }

  @Override
  public ModelShape copy() {
    ModelShape r = new ModelRectangle(this.getName(), this.getAttributes());
    r.setAnimations(this.getCopyOfAnimations());
    return r;
  }

  @Override
  public String getType() {
    return "rectangle";
  }

}
