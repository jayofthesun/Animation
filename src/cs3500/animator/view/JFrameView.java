package cs3500.animator.view;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;

import cs3500.animator.model.AnimatorModel;

/**
 * Displays the given animation model in a JFrame view.
 */

public class JFrameView implements AnimatorView {
  protected Timer timer;
  protected JFrame frame;
  protected DrawPanel canvas;
  protected AnimatorModel model;
  protected int time = 0;
  protected boolean isLoop = false;


  /**
   * A constructor for the JFrameView that forms a JFrame view based on the given ticks per second.
   *
   * @param tps ticks per second
   * @throws IllegalArgumentException if ticks are not zero or positive
   */
  public JFrameView(int tps) throws IllegalArgumentException {
    if (tps <= 0) {
      throw new IllegalArgumentException("ticks per second must be positive");
    }
    this.frame = new JFrame();
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame.setTitle("Animation");
    this.canvas = new DrawPanel();
    this.timer = new Timer((1000 / tps), new PaintAction());
  }

  private class PaintAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (model.isOver(time) && isLoop) {
        time = 0;
      } else {
        time++;
        model.tick(time);
        canvas.setShapes(model.getShapeStates());
        canvas.repaint();
      }
    }
  }

  @Override
  public void display(AnimatorModel model) {
    this.model = model;
    this.canvas.setPreferredSize(new Dimension(model.getCanvasWidth() + model.getCanvasX(),
            model.getCanvasHeight() + model.getCanvasY()));

    JScrollPane scroll = new JScrollPane(this.canvas, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    this.frame.getContentPane().add(BorderLayout.CENTER, scroll);
    this.frame.setVisible(true);
    this.frame.setSize(model.getCanvasWidth(), model.getCanvasHeight());
    this.timer.start();
  }
}
