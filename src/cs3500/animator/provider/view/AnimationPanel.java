package cs3500.animator.provider.view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import cs3500.animator.provider.model.IShape;


/**
 * A Panel containing the animation.
 */
public class AnimationPanel extends JPanel {

  private List<IShape> shapeList;
  private Timer timer;
  private int currentTick;
  private int animationTempo = 1;
  private boolean isLooping;
  private int finalTick;
  private Point canvasLeftCorner;

  /**
   * Constructs the panel which contains the animation.
   */
  AnimationPanel() {
    super();
    currentTick = 0;
    shapeList = new ArrayList<>();
    // The time goes through each tick and repaints the animation.
    timer = new Timer((int) ((1 / (double) animationTempo) * 1000), new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        updateTick();
        repaint();
      }
    });
  }


  /**
   * Sets the canvas of this panel to the given values.
   *
   * @param canvasLeftCorner Sets the left corner of this panel.
   * @param canvasSize       Sets the size of this panel.
   */
  public void setCanvas(Point canvasLeftCorner, Dimension canvasSize) {
    Dimension canvasSize1 = new Dimension(canvasSize.width, canvasSize.height);
    this.canvasLeftCorner = canvasLeftCorner;
    this.setBounds(0, 0, canvasSize.width + canvasLeftCorner.x,
            canvasSize.height + canvasLeftCorner.y);
    this.setPreferredSize(canvasSize1);
  }

  /**
   * Sets the final tick of this animation.
   *
   * @param finalTick the final tick to set.
   */
  public void setFinalTick(int finalTick) {
    this.finalTick = finalTick;
  }

  /**
   * Sets the shapes of this animation.
   *
   * @param shapeList the list of shapes to set.
   */
  public void setShapes(List<IShape> shapeList) {
    this.shapeList = shapeList;
  }

  /**
   * Increments the current tick of this animation.
   */
  private void updateTick() {
    if (this.currentTick < this.finalTick) {
      this.currentTick++;
    } else if (isLooping) {
      this.currentTick = 0;
    } else {
      this.timer.stop();
    }
  }

  /**
   * Sets the current speed of this animation.
   *
   * @param speed the speed to set.
   */
  void setAnimationTempo(int speed) {
    this.animationTempo = speed;
    this.timer.setDelay((int) ((1 / (double) speed) * 1000));
  }

  /**
   * Pauses the current animation.
   */
  void pause() {
    this.timer.stop();
  }

  /**
   * Plays the current animation.
   */
  void play() {
    this.timer.start();
  }

  /**
   * Restarts the animation.
   */
  void restart() {
    this.currentTick = 0;
  }

  /**
   * Sets whether the animation is looping or not.
   *
   * @param looping if the animation is looping or not.
   */
  void setLooping(boolean looping) {
    this.isLooping = looping;
  }

  /**
   * Sets the current tick of this animation.
   *
   * @param time the time to set.
   */
  void setTime(int time) {
    this.currentTick = time;
    this.repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    for (IShape s : this.shapeList) {
      if (s.isRunningDuringTick(currentTick)) {
        s.calculateMotionAtTick(currentTick);
        // Print the shape at its correct position per tick
        if (s.getType().equals("Rectangle")) {
          g2d.setColor(s.getColor());
          g2d.fillRect(s.getPosition().x - canvasLeftCorner.x,
                  s.getPosition().y - canvasLeftCorner.y,
                  s.getDimensions().width,
                  s.getDimensions().height);
        }
        if (s.getType().equals("Oval")) {
          g2d.setColor(s.getColor());
          g2d.fillOval(s.getPosition().x - canvasLeftCorner.x,
                  s.getPosition().y - canvasLeftCorner.y,
                  s.getDimensions().width,
                  s.getDimensions().height);
        }
      }
    }
  }
}