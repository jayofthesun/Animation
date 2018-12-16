package cs3500.animator.controller.adaptations;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;

import cs3500.animator.controller.AnimatorController;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.adaptations.AnimatorModelImplToProvider;
import cs3500.animator.provider.controller.EditorListener;
import cs3500.animator.provider.model.IAnimationModelWrapper;
import cs3500.animator.provider.view.AnimationView;

/**
 * A controller that uses our provider's view and our model to run an animation.
 */
public class ProviderController implements AnimatorController {
  private IAnimationModelWrapper wrapper;
  private AnimationView view;

  /**
   * Constructor for a ProviderController.
   *
   * @param model our model implementation
   * @param view  the providers editor view
   * @param speed speed to start the animation at
   * @throws IllegalArgumentException throws exception if view or model is null or speed <= 0
   */
  public ProviderController(AnimatorModel model, AnimationView view, int speed)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("the model is null");
    }

    if (view == null) {
      throw new IllegalArgumentException("the view is null");
    }

    if (speed <= 0) {
      throw new IllegalArgumentException("ticks per second must be positive");
    }

    this.wrapper = new AnimatorModelImplToProvider(model);
    this.view = view;
    this.view.setAnimationTempo(speed);
  }

  @Override
  public void animationGo() {
    view.setCanvas(wrapper.getCanvasLocation(),
            wrapper.getCanvasDimension());
    view.setModel(wrapper);
    view.setEditorListener(new EditorListener() {
      private String shapeSelected;

      @Override
      public void editKeyframe(int x, int y, int width, int height, Color color, int time) {
        if (shapeSelected != null) {
          wrapper.editKeyframe(shapeSelected, x, y, width, height, color, time);
          view.setModel(wrapper);
        }
      }

      @Override
      public void addShape(String shapeName, String shapeType) {
        try {
          wrapper.addShape(shapeName, shapeType);
          view.setModel(wrapper);
        } catch (IllegalArgumentException e) {
          System.out.println("name already taken");
        }
      }

      @Override
      public void removeShape(String name) {
        wrapper.removeShape(name);
        view.setModel(wrapper);
      }

      @Override
      public void removeKeyframe(int time) {

        if (this.shapeSelected != null) {
          wrapper.removeKeyframe(this.shapeSelected, time);
          view.setModel(wrapper);
        }
      }

      @Override
      public void setLooping(boolean isLooping) {
        view.setLooping(isLooping);
      }

      @Override
      public void download(String type) {
        //we didnot implement the extra credit in our HW7
      }

      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
          case "play":
            view.play();
            break;
          case "pause":
            view.pause();
            break;
          case "restart":
            view.restart();
            break;
          case "set speed":
            view.setAnimationTempo(view.getNewSpeed());
            break;

          //nothing happens if none of the cases reached
          default:
            break;
        }
      }

      @Override
      public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() instanceof JList) {
          JList jList = (JList) e.getSource();
          Object o = jList.getSelectedValue();
          if (o instanceof String) {
            this.shapeSelected = (String) o;
            view.setKeyframeList(
                    wrapper.returnShapes().get(jList.getSelectedIndex()).getKeyframes());
          }
        }
      }
    });
    view.makeVisible();
  }
}
