package cs3500.animator.view;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.JColorChooser;

/**
 * Panel that allows the users to interact with view such that they are able to edit settings of the
 * animation.
 */
public class EditorPanel extends JPanel {
  private AnimatorEditorView view;
  private JLabel isLoop;
  private JLabel speed;
  private JLabel state;
  private JTextField shapeName;
  private JTextField xValue;
  private JTextField yValue;
  private JTextField width;
  private JTextField height;
  private JTextField time;
  private ShapesCombo shapes;
  private JComboBox<String> keyFrames;
  private TypeCombo type;
  private ColorButton color;

  /**
   * takes in an AnimatorEditorView which gives the information needed to create the editor panel.
   *
   * @param view the animator editor view
   */

  EditorPanel(AnimatorEditorView view) {
    isLoop = new JLabel("Looping: " + view.isLooping());
    speed = new JLabel("Speed: " + view.getSpeed());
    state = new JLabel("State: " + view.getAnimationState());
    shapeName = new JTextField();
    xValue = new JTextField();
    yValue = new JTextField();
    width = new JTextField();
    height = new JTextField();
    time = new JTextField();
    color = new ColorButton();
    xValue.setPreferredSize(new Dimension(40, 20));
    yValue.setPreferredSize(new Dimension(40, 20));
    width.setPreferredSize(new Dimension(40, 20));
    height.setPreferredSize(new Dimension(40, 20));
    time.setPreferredSize(new Dimension(40, 20));
    shapeName.setPreferredSize(new Dimension(100, 20));
    type = new TypeCombo();
    shapes = new ShapesCombo();

    keyFrames = new JComboBox<>();
    this.add(shapes);
    this.add(type);
    this.add(new JLabel("New Shape Name:"));
    this.add(shapeName);
    this.add(new AddShapeButton());
    this.add(new DeleteShapeButton());
    this.add(new JLabel("Color chooser:"));
    this.add(color);
    this.add(new JLabel("X:"));
    this.add(xValue);
    this.add(new JLabel("Y:"));
    this.add(yValue);
    this.add(new JLabel("Width:"));
    this.add(width);
    this.add(new JLabel("Height:"));
    this.add(height);
    this.add(new JLabel("Time:"));
    this.add(time);
    this.add(keyFrames);
    this.add(new AddKeyFramesButton());
    this.add(new EditKeyFramesButton());
    this.add(new DeleteKeyFrameButton());
    this.add(state);
    this.add(speed);
    this.add(isLoop);
    this.view = view;
    this.add(new PauseButton());
    this.add(new PlayButton());
    this.add(new LoopButton());
    this.add(new RestartButton());
    this.add(new IncreaseSpeedButton());
    this.add(new DecreaseSpeedButton());
  }

  /**
   * initializes the shapes with the given names in the editor panel.
   *
   * @param names the names of the shapes in the animation that can be edited
   */

  public void initShapes(List<String> names) {
    for (String s : names) {
      shapes.addItem(s);
    }
    Object name = shapes.getSelectedItem();
    if (name != null) {
      setKeyFrames(view.getKeyframes(name.toString()));
    }
  }

  /**
   * sets the keyFrames to a comboBox using a given list of strings that identify the attributes of
   * the keyframes.
   *
   * @param keyframes the keyframes to be added to the combobox
   */

  public void setKeyFrames(List<String> keyframes) {
    this.keyFrames.removeAllItems();
    for (String a : keyframes) {
      this.keyFrames.addItem(a);
    }
  }

  /**
   * the pause button for the editor panel.
   */

  private class PauseButton extends JButton {

    /**
     * displays pause which when clicked will pause the animation and change the visible status of
     * the state of the animation to paused.
     */

    public PauseButton() {
      this.setText("pause");
      this.setSize(30, 30);
      this.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          view.pause();
          state.setText("State: " + view.getAnimationState());
        }
      });
    }
  }

  /**
   * the play button for the editor panel.
   */

  private class PlayButton extends JButton {

    /**
     * displays play which when clicked will play the animation and change the visible status of the
     * state of the animation to playing.
     */

    public PlayButton() {
      this.setText("play");
      this.setSize(30, 30);
      this.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          view.resume();
          state.setText("State: " + view.getAnimationState());
        }
      });
    }

  }

  /**
   * the loop button for the editor panel.
   */

  private class LoopButton extends JButton {
    /**
     * displays loop which when clicked will toggle the status of the looping of the animation based
     * on its current looping state.
     */
    public LoopButton() {
      this.setText("toggle loop");
      this.setSize(30, 30);
      this.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          view.toggleLoop();
          isLoop.setText("Looping: " + view.isLooping());
        }
      });
    }

  }

  /**
   * the restart button for the editor panel.
   */

  private class RestartButton extends JButton {

    /**
     * displays restart which when clicked will restart the animation and refresh the animation's
     * visible status of the animation to playing.
     */
    public RestartButton() {
      this.setText("restart");
      this.setSize(30, 30);
      this.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          view.restart();
          state.setText("State: " + view.getAnimationState());
        }
      });
    }
  }

  /**
   * the increase speed button for the editor panel.
   */

  private class IncreaseSpeedButton extends JButton {
    /**
     * displays increase speed which when clicked will increase the speed of the animation.
     */

    public IncreaseSpeedButton() {
      this.setText("increase speed");
      this.setSize(30, 30);
      this.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          view.increaseSpeed();
          speed.setText("Speed: " + view.getSpeed());
        }
      });
    }
  }

  /**
   * the decrease speed button for the editor panel.
   */

  private class DecreaseSpeedButton extends JButton {

    /**
     * displays decrease speed which when clicked will decrease the speed of the animation.
     */

    public DecreaseSpeedButton() {
      this.setText("decrease speed");
      this.setSize(30, 30);
      this.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          view.decreaseSpeed();
          speed.setText("Speed: " + view.getSpeed());
        }
      });
    }
  }

  /**
   * the delete a shape button for the editor panel.
   */

  private class DeleteShapeButton extends JButton {

    /**
     * displays delete selected shape which when clicked will delete the selected shape from the
     * list of shapes' combo box.
     */

    public DeleteShapeButton() {
      this.setText("delete selected shape");
      this.setSize(30, 30);
      this.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          Object name = shapes.getSelectedItem();
          if (name != null) {
            view.deleteShape(name.toString());
            shapes.removeItem(name.toString());
          }
        }
      });
    }
  }

  /**
   * the add a shape button for the editor panel.
   */

  private class AddShapeButton extends JButton {

    /**
     * displays add the shape button which when clicked will add the shape from the list of shape's
     * combo box.
     */

    public AddShapeButton() {
      this.setText("add shape");
      this.setSize(30, 30);
      this.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          try {
            view.addShape(shapeName.getText(), type.getSelectedItem().toString());
            shapes.addItem(shapeName.getText());
          } catch (Exception e1) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), e1.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
          }
        }
      });
    }
  }

  /**
   * a combo box for the list of shapes with their names.
   */

  private class ShapesCombo extends JComboBox<String> {

    /**
     * adds the name of the shapes to the combo box along with updating the keyframes combo box for
     * the selected shape.
     */

    public ShapesCombo() {
      this.setBorder(BorderFactory.createTitledBorder("Shapes"));
      this.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          Object name = getSelectedItem();
          if (name != null) {
            setKeyFrames(view.getKeyframes(name.toString()));
          }
        }
      });
    }
  }

  /**
   * the type combo box for the editor panel.
   */

  private class TypeCombo extends JComboBox<String> {
    /**
     * contains the different types of shapes that are available for the user to add.
     */
    public TypeCombo() {
      this.setBorder(BorderFactory.createTitledBorder("Types"));
      this.addItem("rectangle");
      this.addItem("ellipse");
    }
  }

  /**
   * the color button for the editor panel.
   */
  private class ColorButton extends JButton {
    Color c = Color.WHITE;

    /**
     * displays select color which when clicked will display a color chooser dialog which will allow
     * the user to choose a color and once chosen displays the color as the background of the button
     * so they have a visual view of the selected color.
     */

    public ColorButton() {
      this.setText("select color");
      this.setSize(30, 30);
      this.setBackground(c);
      this.setOpaque(true);
      this.setBorderPainted(false);
      this.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          c = JColorChooser.showDialog(null, "Choose a color", c);
          setBackground(c);

        }
      });
    }

    /**
     * gets the selected color.
     *
     * @return the color
     */

    public Color getColor() {
      return this.c;
    }
  }

  /**
   * the addkeyframe button for the editor panel.
   */

  private class AddKeyFramesButton extends JButton {

    /**
     * displays add keyframe which when clicked adds the keyframe with the user's input of
     * attributes and time along with the selected shape to the list of keyframes for the
     * animation.
     */

    public AddKeyFramesButton() {
      this.setText("add keyframe");
      this.setSize(30, 30);
      this.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          Object name = shapes.getSelectedItem();
          try {
            int xKey = Integer.parseInt(xValue.getText());
            int yKey = Integer.parseInt(yValue.getText());
            int widthKey = Integer.parseInt(width.getText());
            int heightKey = Integer.parseInt(height.getText());
            int timeKey = Integer.parseInt(time.getText());
            if (name != null) {
              view.addKeyFrame(name.toString(), timeKey, heightKey, widthKey, color.getColor(),
                      xKey, yKey);
              setKeyFrames(view.getKeyframes(name.toString()));
            }
          } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "Missing information",
                    "Error", JOptionPane.ERROR_MESSAGE);

          }

        }
      });
    }
  }

  /**
   * an edit key frames button for the editor panel.
   */

  private class EditKeyFramesButton extends JButton {

    /**
     * displays edit selected keyframe which when clicked edits the keyframe with the user's input
     * of attributes and time along with the selected shape to the keyframe at the time for the
     * animation.
     */
    public EditKeyFramesButton() {
      this.setText("edit selected keyframe");
      this.setSize(30, 30);
      this.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          Object name = shapes.getSelectedItem();
          int i = keyFrames.getSelectedIndex();
          if (name != null && i != -1) {
            view.deleteKeyFrame(i, name.toString());
            keyFrames.removeItemAt(i);
            try {
              int xKey = Integer.parseInt(xValue.getText());
              int yKey = Integer.parseInt(yValue.getText());
              int widthKey = Integer.parseInt(width.getText());
              int heightKey = Integer.parseInt(height.getText());
              int timeKey = Integer.parseInt(time.getText());


              view.addKeyFrame(name.toString(), timeKey, heightKey, widthKey, color.getColor(),
                      xKey, yKey);
              setKeyFrames(view.getKeyframes(name.toString()));

            } catch (Exception e1) {
              JOptionPane.showMessageDialog(null, "Missing information",
                      "Error", JOptionPane.ERROR_MESSAGE);

            }
          }
        }
      });
    }
  }

  /**
   * delete keyframe button for the editor panel.
   */
  private class DeleteKeyFrameButton extends JButton {

    /**
     * displays delete keyframe which when clicked deletes the selected keyframe from the list of
     * keyframes in the animation.
     */

    public DeleteKeyFrameButton() {
      this.setText("delete selected keyframe");
      this.setSize(30, 30);
      this.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          Object name = shapes.getSelectedItem();
          int i = keyFrames.getSelectedIndex();
          if (name != null && i != -1) {
            view.deleteKeyFrame(i, name.toString());
            keyFrames.removeItemAt(i);
          }
        }
      });
    }
  }
}

