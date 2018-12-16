package cs3500.animator.view;

import java.awt.Color;
import java.util.List;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.animation.Animation;
import cs3500.animator.model.shape.Attributes;
import cs3500.animator.model.shape.ModelShape;

/**
 * Displays the given animation model in a svg view.
 */
public class SVGView implements AnimatorView {
  private long tps;
  private Appendable output;

  /**
   * A constructor for the svgView that forms a svg view based on the given ticks per second and
   * output.
   *
   * @param tps    ticks per seconds
   * @param output where the text gets appended to
   * @throws IllegalArgumentException if the ticks per seconds are not positive or zero OR if the
   *                                  output is null
   */
  public SVGView(long tps, Appendable output) throws IllegalArgumentException {
    if (tps <= 0) {
      throw new IllegalArgumentException("ticks per second must be positive");
    }
    if (output == null) {
      throw new IllegalArgumentException("output is null");
    }
    this.tps = tps;
    this.output = output;
  }

  /**
   * converts ticks to milliseconds.
   *
   * @param time tick number
   * @return the milliseconds for that time
   */

  private double converter(int time) {
    return 1000 / tps * (long) time;
  }

  /**
   * Makes a string for the svg text for an ellipse.
   *
   * @param s the given ellipse shape to be converted to svg text
   * @return a svg text representation of the given ellipse
   */

  private String svgEllipse(ModelShape s) {
    StringBuilder sb = new StringBuilder();
    Attributes a = s.getAttributes();
    sb.append("<ellipse id=\"");
    sb.append(s.getName());
    sb.append("\" cx=\"");
    sb.append(a.getX());
    sb.append("\" cy=\"");
    sb.append(a.getY());
    sb.append("\" rx=\"");
    sb.append(a.getWidth());
    sb.append("\" ry=\"");
    sb.append(a.getHeight());
    sb.append("\" fill=\"");
    sb.append("rgb(");
    sb.append(a.getColor().getRed());
    sb.append(",");
    sb.append(a.getColor().getGreen());
    sb.append(",");
    sb.append(a.getColor().getBlue());
    sb.append(")\" visibility=\"visible\">\n\n");
    return sb.toString();
  }

  /**
   * Makes a string for the svg text for an rectangle.
   *
   * @param s the given rectangle shape to be converted to svg text
   * @return a svg text representation of the given rectangle
   */

  private String svgRect(ModelShape s) {
    StringBuilder sb = new StringBuilder();
    Attributes a = s.getAttributes();
    sb.append("<rect id=\"");
    sb.append(s.getName());
    sb.append("\" x=\"");
    sb.append(a.getX());
    sb.append("\" y=\"");
    sb.append(a.getY());
    sb.append("\" width=\"");
    sb.append(a.getWidth());
    sb.append("\" height=\"");
    sb.append(a.getHeight());
    sb.append("\" fill=\"");
    sb.append("rgb(");
    sb.append(a.getColor().getRed());
    sb.append(",");
    sb.append(a.getColor().getGreen());
    sb.append(",");
    sb.append(a.getColor().getBlue());
    sb.append(")\" visibility=\"visible\">\n\n");

    return sb.toString();
  }

  /**
   * Makes a string for the svg text of a motion animation.
   *
   * @param a    the animation to be converted to svg text
   * @param type the type of shape that the animation is from
   * @return     a svg string representation of the animation motion of
   *             a given animation and shape type.
   */

  private String svgMotion(Animation a, String type) {
    StringBuilder sb = new StringBuilder();
    if (type.equals("rectangle")) {
      sb.append(svgNumbers("x", a.getStartShape().getX(),
              a.getEndShape().getX(), a.getStart(), a.getEnd()));
      sb.append(svgNumbers("y", a.getStartShape().getY(),
              a.getEndShape().getY(), a.getStart(), a.getEnd()));
      sb.append(svgNumbers("width", a.getStartShape().getWidth(),
              a.getEndShape().getWidth(), a.getStart(), a.getEnd()));
      sb.append(svgNumbers("height", a.getStartShape().getHeight(),
              a.getEndShape().getHeight(), a.getStart(), a.getEnd()));
      sb.append(svgColors("fill", a.getStartShape().getColor(),
              a.getEndShape().getColor(), a.getStart(), a.getEnd()));
    } else {
      sb.append(svgNumbers("cx", a.getStartShape().getX(),
              a.getEndShape().getX(), a.getStart(),
              a.getEnd()));
      sb.append(svgNumbers("cy", a.getStartShape().getY(),
              a.getEndShape().getY(), a.getStart(), a.getEnd()));
      sb.append(svgNumbers("rx", a.getStartShape().getWidth(),
              a.getEndShape().getWidth(), a.getStart(), a.getEnd()));
      sb.append(svgNumbers("ry",
              a.getStartShape().getHeight(),
              a.getEndShape().getHeight(), a.getStart(), a.getEnd()));
      sb.append(svgColors("fill", a.getStartShape().getColor(), a.getEndShape().getColor(),
              a.getStart(), a.getEnd()));
    }
    return sb.toString();
  }

  /**
   * Makes a string for the svg text of an animation for the changing of the color.
   *
   * @param name      the name of the attribute being changed during the animation
   * @param start     the start color of the shape in the animation
   * @param end       the end color of the shape in the animation
   * @param startTime the start time
   * @param endTime   the end time
   * @return the svg string representation of a color change animation
   */

  private String svgColors(String name, Color start, Color end, int startTime, int endTime) {
    StringBuilder sb = new StringBuilder();
    sb.append("<animate attributeType=\"xml\" begin=\"");
    sb.append(converter(startTime));
    sb.append("ms\" dur=\"");
    sb.append(converter(endTime - startTime));
    sb.append("ms\" attributeName=\"fill\" from=\"rgb(");
    sb.append(start.getRed());
    sb.append(", ");
    sb.append(start.getGreen());
    sb.append(", ");
    sb.append(start.getBlue());
    sb.append(")\" to=\"rgb(");
    sb.append(end.getRed());
    sb.append(", ");
    sb.append(end.getGreen());
    sb.append(", ");
    sb.append(end.getBlue());
    sb.append(")\" fill=\"freeze\"/>\n\n");
    return sb.toString();
  }

  /**
   * Makes a string for the svg text of an animation for the changing of a number attribute.
   *
   * @param name      the name of the attribute that is being changed during the animation
   * @param start     the start number attribute of the animation
   * @param end       the end number attribute of the animation
   * @param startTime the start time
   * @param endTime   the end time
   * @return the svg string representation of a number attribute change animation
   */

  private String svgNumbers(String name, int start, int end, int startTime, int endTime) {
    StringBuilder sb = new StringBuilder();
    sb.append("<animate attributeType=\"xml\" begin=\"");
    sb.append(converter(startTime));
    sb.append("ms\" dur=\"");
    sb.append(converter(endTime - startTime));
    sb.append("ms\" attributeName=\"");
    sb.append(name);
    sb.append("\"");
    sb.append(" from=\"");
    sb.append(start);
    sb.append("\" to=\"");
    sb.append(end);
    sb.append("\" fill=\"freeze\"/>\n\n");
    return sb.toString();
  }

  /**
   * The display for an svg view.
   *
   * @param model the given animation model
   * @throws IllegalArgumentException if the append of the string to the output fails
   */

  @Override
  public void display(AnimatorModel model) throws IllegalArgumentException {
    StringBuilder sb = new StringBuilder();
    sb.append("<svg width=\"" + (model.getCanvasWidth() + model.getCanvasX())
            + "\" height=\"" + (model.getCanvasHeight() + model.getCanvasY())
            + "\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">\n");

    List<String> shapes = model.getListOfShapeNames();
    ModelShape s;
    for (String name : shapes) {
      s = model.getCopy(name);
      if (s.getType().equals("ellipse")) {
        sb.append(svgEllipse(s));

      } else if (s.getType().equals("rectangle")) {
        sb.append(svgRect(s));
      }

      for (Animation anime : model.getAnimationsOfShapeToEdit(name)) {
        if (s.getType().equals("ellipse")) {
          sb.append(svgMotion(anime, "ellipse"));
        } else {
          sb.append(svgMotion(anime, "rectangle"));
        }
      }
      if (s.getType().equals("ellipse")) {
        sb.append("</ellipse>\n\n");
      } else {
        sb.append("</rect>\n\n");
      }

    }
    sb.append("</svg>");

    try {
      this.output.append(sb.toString());
    } catch (Exception e) {
      throw new IllegalStateException("append failed");
    }
  }
}
