package cs3500.animator.model.shape;

/**
 * Concrete ModelShape implementation of an ellipse.
 */
public class ModelEllipse extends AbstractShape {

  /**
   * Constructor for an ellipse.
   *
   * @param name       name to identify the ellipse
   * @param attributes attributes of the ellipse
   */
  public ModelEllipse(String name, Attributes attributes) {
    super(name, attributes);
  }

  @Override
  public ModelShape copy() {
    ModelShape e = new ModelEllipse(this.getName(), this.getAttributes());
    e.setAnimations(this.getCopyOfAnimations());
    return e;
  }

  @Override
  public String getType() {
    return "ellipse";
  }

}
