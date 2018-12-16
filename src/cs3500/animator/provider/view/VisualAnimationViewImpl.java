package cs3500.animator.provider.view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;


import cs3500.animator.provider.controller.EditorListener;
import cs3500.animator.provider.model.IAnimationModelWrapper;

public class VisualAnimationViewImpl extends JFrame implements AnimationView {

  private AnimationPanel animationPanel;

  /**
   * A view that displays the animation visually using the Swing Library.
   */
  public VisualAnimationViewImpl() {
    super();
    this.setTitle("Animation");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // create new panel with list of shapes to animate
    this.animationPanel = new AnimationPanel();
    animationPanel.setLayout(null);
    // add scroll bar
    JScrollPane scrollPane = new JScrollPane(animationPanel);
    this.add(scrollPane, BorderLayout.CENTER);
    this.pack();
  }

  @Override
  public void makeVisible() {
    this.animationPanel.play();
    this.setVisible(true);
  }

  @Override
  public void setAnimationTempo(int speed) {
    this.animationPanel.setAnimationTempo(speed);
  }

  @Override
  public void setModel(IAnimationModelWrapper wrapper) {
    this.animationPanel.setShapes(wrapper.returnShapes());
    this.animationPanel.setFinalTick(wrapper.getFinalTick());
    this.setSize(animationPanel.getSize());
  }

  @Override
  public void setCanvas(Point canvasLeftCorner, Dimension canvasSize) {
    this.animationPanel.setCanvas(canvasLeftCorner, canvasSize);
  }

  @Override
  public void setEditorListener(EditorListener l) {
    // This method does nothing because the Textual View has no listeners.
  }

}
