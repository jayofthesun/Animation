package cs3500.animator.view;


import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.util.List;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.shape.ModelEllipse;
import cs3500.animator.model.shape.ModelRectangle;
import cs3500.animator.model.shape.ShapeAttributes;


/**
 * Visual view that allows a user to edit an animation as it is playing.
 */
public class ExtendedVisualView extends JFrameView implements AnimatorEditorView {
  private EditorPanel editor;

  public ExtendedVisualView(int tps) {
    super(tps);
    editor = new EditorPanel(this);
  }

  @Override
  public void pause() {
    this.timer.stop();
  }

  @Override
  public void resume() {
    this.timer.start();
  }

  @Override
  public void restart() {
    this.time = 0;
    this.resume();
  }

  @Override
  public void toggleLoop() {
    this.isLoop = !this.isLoop;
  }

  @Override
  public void increaseSpeed() {
    this.timer.setDelay(this.timer.getDelay() - 1);
  }

  @Override
  public void decreaseSpeed() {
    this.timer.setDelay(this.timer.getDelay() + 1);
  }

  @Override
  public int getSpeed() {
    return 1000 / this.timer.getDelay();
  }

  @Override
  public boolean isLooping() {
    return this.isLoop;
  }

  @Override
  public String getAnimationState() {
    if (this.timer.isRunning()) {
      return "playing";
    } else {
      return "paused";
    }
  }

  @Override
  public List<String> getShapes() {
    return this.model.getListOfShapeNames();
  }

  @Override
  public List<String> getKeyframes(String s) {
    return this.model.getKeyFrames(s);
  }

  @Override
  public void addShape(String s, String type) {
    if (type.equals("ellipse")) {
      this.model.addShape(new ModelEllipse(s, new ShapeAttributes()));
    } else {
      this.model.addShape(new ModelRectangle(s, new ShapeAttributes()));
    }
  }

  @Override
  public void deleteShape(String s) {
    this.model.removeShape(s);
  }

  @Override
  public void deleteKeyFrame(int index, String name) {
    this.model.removeKeyFrame(name, index);
  }

  @Override
  public void addKeyFrame(String name, int time, int height, int width, Color color, int x, int y) {
    this.model.addKeyFrame(name, time, height, width, color, x, y);
  }

  @Override
  public void display(AnimatorModel model) {
    this.model = model;
    this.canvas.setPreferredSize(new Dimension(model.getCanvasWidth() + model.getCanvasX(),
            model.getCanvasHeight() + model.getCanvasY()));

    JScrollPane scroll = new JScrollPane(this.canvas, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    this.frame.getContentPane().add(BorderLayout.CENTER, scroll);
    this.editor.setPreferredSize(new Dimension(500, 200));
    this.frame.getContentPane().add(BorderLayout.SOUTH, editor);
    this.frame.setVisible(true);
    this.frame.setSize(model.getCanvasWidth(), model.getCanvasHeight() + editor.getHeight());
    this.editor.initShapes(this.model.getListOfShapeNames());
  }
}
