package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.animation.Animation;
import cs3500.animator.model.shape.ModelShape;

/**
 * Displays the given animation view in a text view.
 */

public class TextView implements AnimatorView {
  private Appendable output;

  /**
   * A constructor for the textView that forms a text view and outputs it to the given appendable.
   *
   * @param output where the text gets appended to
   * @throws IllegalArgumentException if the output is null
   */

  public TextView(Appendable output) throws IllegalArgumentException {
    if (output == null) {
      throw new IllegalArgumentException("output is null");
    }
    this.output = output;
  }

  @Override
  public void display(AnimatorModel model) {
    try {
      StringBuilder sb = new StringBuilder();
      sb.append("canvas" + " " + model.getCanvasX() + " " + model.getCanvasY() + " "
              + model.getCanvasWidth()
              + " " + model.getCanvasHeight() + "\n");
      for (ModelShape shape : model.getShapeStates()) {
        sb.append("shape" + " " + shape.getName() + " " + shape.getType() + "\n");
        for (Animation c : shape.getCopyOfAnimations()) {
          sb.append(c.getType() + " " + shape.getName() + " " + c.getStart() + " "
                  + c.getStartShape().getX() + " " + c.getStartShape().getY() + " "
                  + c.getStartShape().getWidth() + " " + c.getStartShape().getHeight() + " "
                  + c.getStartShape().getColor().getRed() + " "
                  + c.getStartShape().getColor().getGreen() + " "
                  + c.getStartShape().getColor().getBlue() + "   " + c.getEnd() + " "
                  + c.getEndShape().getX() + " " + c.getEndShape().getY() + " "
                  + c.getEndShape().getWidth() + " " + c.getEndShape().getHeight() + " "
                  + c.getEndShape().getColor().getRed() + " "
                  + c.getEndShape().getColor().getGreen() + " "
                  + c.getEndShape().getColor().getBlue() + "\n");

        }
      }

      this.output.append(sb.toString());
    } catch (Exception e) {
      throw new IllegalStateException("append failed");
    }
  }

}
