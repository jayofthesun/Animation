package cs3500.animator.provider.view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;
import java.util.List;
import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.ListSelectionModel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import cs3500.animator.provider.controller.EditorListener;
import cs3500.animator.provider.model.IAnimationModelWrapper;
import cs3500.animator.provider.model.IKeyframe;
import cs3500.animator.provider.model.IShape;

/**
 * An editor view, allows the user to view, edit, and download an animation.
 */
public class EditorAnimationViewImpl extends JFrame implements AnimationView {

  private AnimationPanel animationPanel;
  private EditorListener controller;
  private JPanel buttonPanel;
  private JList<String> shapes;
  private JList<Integer> keyframes;
  private DefaultListModel<String> shapesModel;
  private DefaultListModel<Integer> keyframeModel;
  private JTextField shapeNameTextField;
  private JTextField shapeTypeTextField;
  private JButton addShapeButton;
  private JButton removeShapeButton;
  private JButton editKeyframeButton;
  private JButton playButton;
  private JButton pauseButton;
  private JButton restartButton;
  private JButton setSpeedButton;
  private JButton downloadButton;
  private JComboBox downloadTypeDropdown;
  private JButton deleteKeyframeButton;
  private JTextField xTextField;
  private JTextField yTextField;
  private JTextField widthTextField;
  private JTextField heightTextField;
  private JTextField redTextField;
  private JTextField greenTextField;
  private JTextField blueTextField;
  private JTextField timeTextField;
  private JTextField speedTextField;
  private JCheckBox isLooping;
  private Dimension animationPanelDimensions;

  private final String[] downloadTypes = {"SVG", "Text"};

  /**
   * The constructor for the editor view, initializes the panels and controls for this view.
   */
  public EditorAnimationViewImpl() {
    super();
    this.setTitle("Editor View");
    this.animationPanel = new AnimationPanel();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(animationPanel, BorderLayout.CENTER);

    // Button panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
    buttonPanel.setPreferredSize(new Dimension(400, 800));

    JPanel tablesPanel = new JPanel();
    tablesPanel.setLayout(new BoxLayout(tablesPanel, BoxLayout.X_AXIS));

    JPanel shapesPanel = new JPanel();

    JPanel keyframesPanel = new JPanel();

    shapesModel = new DefaultListModel<>();
    shapes = new JList<>(shapesModel);
    shapes.setName("shapes");
    shapes.setFixedCellWidth(175);
    shapes.setVisibleRowCount(14);
    shapes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    shapesPanel.add(new JScrollPane(shapes));

    keyframeModel = new DefaultListModel<>();
    keyframes = new JList<>(keyframeModel);
    keyframes.setName("keyframes");
    keyframes.setFixedCellWidth(175);
    keyframes.setVisibleRowCount(15);
    keyframes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    keyframesPanel.add(new JScrollPane(keyframes));

    tablesPanel.add(shapesPanel, BorderLayout.LINE_START);
    tablesPanel.add(keyframesPanel, BorderLayout.LINE_END);
    buttonPanel.add(tablesPanel);

    JPanel textFieldPanel = new JPanel();
    textFieldPanel.setLayout(new BoxLayout(textFieldPanel, BoxLayout.Y_AXIS));
    textFieldPanel.setMaximumSize(new Dimension(350, 500));

    JPanel shapeTextFieldsPanel = new JPanel();
    shapeTextFieldsPanel.setLayout(new BoxLayout(shapeTextFieldsPanel, BoxLayout.X_AXIS));

    shapeNameTextField = new JTextField(5);
    shapeNameTextField.setBorder(BorderFactory.createTitledBorder("Shape name"));
    shapeTextFieldsPanel.add(shapeNameTextField);

    shapeTypeTextField = new JTextField(5);
    shapeTypeTextField.setBorder(BorderFactory.createTitledBorder("Shape type"));
    shapeTextFieldsPanel.add(shapeTypeTextField);

    textFieldPanel.add(shapeTextFieldsPanel);

    JPanel shapeButtonsPanel = new JPanel();
    shapeButtonsPanel.setLayout(new BoxLayout(shapeButtonsPanel, BoxLayout.X_AXIS));

    addShapeButton = new JButton("Add Shape");
    shapeButtonsPanel.add(addShapeButton);
    shapeButtonsPanel.setBorder(new EmptyBorder(0, 0, 15, 0));

    removeShapeButton = new JButton("Remove Shape");
    shapeButtonsPanel.add(removeShapeButton);

    textFieldPanel.add(shapeButtonsPanel);

    JPanel xyTextFieldsPanel = new JPanel();
    xyTextFieldsPanel.setLayout(new BoxLayout(xyTextFieldsPanel, BoxLayout.X_AXIS));
    xyTextFieldsPanel.setBorder(new EmptyBorder(0, 0, 15, 0));

    xTextField = new JTextField(5);
    xTextField.setBorder(BorderFactory.createTitledBorder("x-coordinate"));
    xyTextFieldsPanel.add(xTextField);

    yTextField = new JTextField(5);
    yTextField.setBorder(BorderFactory.createTitledBorder("y-coordinate"));
    xyTextFieldsPanel.add(yTextField);

    textFieldPanel.add(xyTextFieldsPanel);

    JPanel widthHeightTextFieldsPanel = new JPanel();
    widthHeightTextFieldsPanel.setLayout(new BoxLayout(widthHeightTextFieldsPanel,
            BoxLayout.X_AXIS));
    widthHeightTextFieldsPanel.setBorder(new EmptyBorder(0, 0, 15, 0));

    widthTextField = new JTextField(5);
    widthTextField.setBorder(BorderFactory.createTitledBorder("Width"));
    widthHeightTextFieldsPanel.add(widthTextField);

    heightTextField = new JTextField(5);
    heightTextField.setBorder(BorderFactory.createTitledBorder("Height"));
    widthHeightTextFieldsPanel.add(heightTextField);

    textFieldPanel.add(widthHeightTextFieldsPanel);

    JPanel rgbTextViewPanel = new JPanel();
    rgbTextViewPanel.setLayout(new BoxLayout(rgbTextViewPanel, BoxLayout.X_AXIS));
    rgbTextViewPanel.setBorder(new EmptyBorder(0, 0, 15, 0));

    redTextField = new JTextField(5);
    redTextField.setBorder(BorderFactory.createTitledBorder("Red"));
    rgbTextViewPanel.add(redTextField);

    greenTextField = new JTextField(5);
    greenTextField.setBorder(BorderFactory.createTitledBorder("Green"));
    rgbTextViewPanel.add(greenTextField);

    blueTextField = new JTextField(5);
    blueTextField.setBorder(BorderFactory.createTitledBorder("Blue"));
    rgbTextViewPanel.add(blueTextField);

    textFieldPanel.add(rgbTextViewPanel);

    timeTextField = new JTextField(5);
    timeTextField.setBorder(BorderFactory.createTitledBorder("Keyframe time"));
    textFieldPanel.add(timeTextField);

    buttonPanel.add(textFieldPanel);

    JPanel keyframeButtonsPanel = new JPanel();
    keyframeButtonsPanel.setLayout(new BoxLayout(keyframeButtonsPanel, BoxLayout.X_AXIS));
    keyframeButtonsPanel.setBorder(new EmptyBorder(0, 0, 15, 0));

    editKeyframeButton = new JButton("Update/Add Keyframe");
    keyframeButtonsPanel.add(editKeyframeButton);

    deleteKeyframeButton = new JButton("Delete Keyframe");
    keyframeButtonsPanel.add(deleteKeyframeButton);

    textFieldPanel.add(keyframeButtonsPanel);

    JPanel playbackButtonsPanel = new JPanel();
    playbackButtonsPanel.setLayout(new BoxLayout(playbackButtonsPanel, BoxLayout.X_AXIS));
    playbackButtonsPanel.setBorder(new EmptyBorder(0, 0, 15, 0));

    playButton = new JButton("Play");
    playButton.setActionCommand("play");
    playbackButtonsPanel.add(playButton);

    pauseButton = new JButton("Pause");
    pauseButton.setActionCommand("pause");
    playbackButtonsPanel.add(pauseButton);

    restartButton = new JButton("Restart");
    restartButton.setActionCommand("restart");
    playbackButtonsPanel.add(restartButton);

    textFieldPanel.add(playbackButtonsPanel);

    isLooping = new JCheckBox("Set Animation Looping");
    textFieldPanel.add(isLooping);

    JPanel speedPanel = new JPanel();
    speedPanel.setLayout(new BoxLayout(speedPanel, BoxLayout.X_AXIS));

    speedTextField = new JTextField(5);
    speedTextField.setBorder(BorderFactory.createTitledBorder("Animation Speed"));
    speedPanel.add(speedTextField);

    setSpeedButton = new JButton("Set Speed");
    setSpeedButton.setActionCommand("set speed");
    speedPanel.add(setSpeedButton);

    textFieldPanel.add(speedPanel);
    textFieldPanel.add(speedPanel);

    JPanel downloadPanel = new JPanel();
    downloadButton = new JButton("Download");
    downloadTypeDropdown = new JComboBox(downloadTypes);
    downloadTypeDropdown.setSelectedIndex(0);
    downloadPanel.add(downloadButton);
    downloadPanel.add(downloadTypeDropdown);

    textFieldPanel.add(downloadPanel);

    this.add(buttonPanel, BorderLayout.WEST);

    this.pack();
  }

  @Override
  public void makeVisible() {
    this.animationPanel.play();
    this.setVisible(true);
  }

  @Override
  public void setAnimationTempo(int speed) {
    if (speed < 1) {
      throw new IllegalArgumentException("Speed must be at least 1");
    }
    this.animationPanel.setAnimationTempo(speed);
  }

  @Override
  public void pause() {
    this.animationPanel.pause();
  }

  @Override
  public void play() {
    this.animationPanel.play();
  }

  @Override
  public void restart() {
    this.animationPanel.restart();
  }

  @Override
  public void setLooping(boolean looping) {
    this.animationPanel.setLooping(looping);
  }

  @Override
  public void setModel(IAnimationModelWrapper wrapper) {
    this.animationPanel.setShapes(wrapper.returnShapes());
    this.animationPanel.setFinalTick(wrapper.getFinalTick());
    this.setShapeList(wrapper.returnShapes());
    this.setSize(animationPanelDimensions.width + buttonPanel.getWidth(),
            Math.max(buttonPanel.getHeight(), animationPanelDimensions.height));
    this.animationPanel.repaint();
  }

  @Override
  public void setCanvas(Point canvasLeftCorner, Dimension canvasSize) {
    this.animationPanel.setCanvas(canvasLeftCorner, canvasSize);
    animationPanelDimensions = new Dimension(canvasSize.width, canvasSize.height);
  }

  private void setShapeList(List<IShape> shapes) {
    this.shapesModel.clear();
    for (IShape s : shapes) {
      this.shapesModel.addElement(s.getName());
    }
    this.shapes.setModel(this.shapesModel);
  }

  @Override
  public void setKeyframeList(List<IKeyframe> keyframes) {
    this.keyframeModel.clear();
    for (IKeyframe k : keyframes) {
      this.keyframeModel.addElement(k.getTime());
    }
  }

  @Override
  public void setEditorListener(EditorListener l) {
    this.controller = l;
    this.shapes.addListSelectionListener(l);
    this.keyframes.addListSelectionListener(l);
    this.addShapeButton.addActionListener((e) -> {
      if (!emptyShapeInputs()) {
        this.controller.addShape(
                this.shapeNameTextField.getText(),
                this.shapeTypeTextField.getText());
      } else {
        showErrorMessage("Empty shape info field(s)");
      }
    });
    this.removeShapeButton.addActionListener((e) -> {
      this.controller.removeShape(this.shapes.getSelectedValue());
    });
    this.editKeyframeButton.addActionListener((e) -> {
      if (!emptyKeyframeInputs()) {
        this.controller.editKeyframe(
                Integer.parseInt(this.xTextField.getText()),
                Integer.parseInt(this.yTextField.getText()),
                Integer.parseInt(this.widthTextField.getText()),
                Integer.parseInt(this.heightTextField.getText()),
                new Color(Integer.parseInt(this.redTextField.getText()),
                        Integer.parseInt(this.greenTextField.getText()),
                        Integer.parseInt(this.blueTextField.getText())),
                Integer.parseInt(this.timeTextField.getText()));
      } else {
        showErrorMessage("Empty keyframe info field(s)");
      }
    });
    this.deleteKeyframeButton.addActionListener((e) -> {
      if (this.keyframes.getModel().getSize() == 0) {
        showErrorMessage("This shape has no keyframes");
      } else {
        if (!emptyKeyframeInputs()) {
          this.controller.removeKeyframe(Integer.parseInt(this.timeTextField.getText()));
        } else {
          showErrorMessage("No keyframe selected");
        }
      }
    });
    this.playButton.addActionListener(l);
    this.pauseButton.addActionListener(l);
    this.restartButton.addActionListener(l);
    this.isLooping.addActionListener((e) ->
            this.controller.setLooping(this.isLooping.isSelected()));
    this.setSpeedButton.addActionListener(l);
    this.downloadButton.addActionListener((e) ->
            this.controller.download((String) this.downloadTypeDropdown.getSelectedItem()));
  }

  @Override
  public void setVisibleKeyframe(int x, int y, int width, int height, int r, int g, int b, int t) {
    this.xTextField.setText(String.valueOf(x));
    this.yTextField.setText(String.valueOf(y));
    this.widthTextField.setText(String.valueOf(width));
    this.heightTextField.setText(String.valueOf(height));
    this.redTextField.setText(String.valueOf(r));
    this.greenTextField.setText(String.valueOf(g));
    this.blueTextField.setText(String.valueOf(b));
    this.timeTextField.setText(String.valueOf(t));
    this.animationPanel.setTime(t);
  }

  @Override
  public void clearVisibleKeyframe() {
    this.xTextField.setText("");
    this.yTextField.setText("");
    this.widthTextField.setText("");
    this.heightTextField.setText("");
    this.redTextField.setText("");
    this.greenTextField.setText("");
    this.blueTextField.setText("");
    this.timeTextField.setText("");
  }

  @Override
  public int getNewSpeed() {
    return Integer.parseInt(this.speedTextField.getText());
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(null, error);
  }


  /**
   * Determines if the given information from the view contains any empty shape fields.
   *
   * @return if any of the fields are empty.
   */
  private boolean emptyShapeInputs() {
    return (this.shapeNameTextField.getText().equals("")
            || this.shapeTypeTextField.getText().equals(""));
  }

  /**
   * Determines if the given information from the view contains any empty keyframe fields.
   *
   * @return if any of the fields are empty.
   */
  private boolean emptyKeyframeInputs() {
    return (this.xTextField.getText().equals("") || this.yTextField.getText().equals("")
            || this.widthTextField.getText().equals("") ||
            this.heightTextField.getText().equals("") || this.redTextField.getText().equals("")
            || this.greenTextField.getText().equals("") ||
            this.blueTextField.getText().equals("") || this.timeTextField.getText().equals(""));
  }

}
