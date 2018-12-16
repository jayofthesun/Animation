package cs3500.animator;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import cs3500.animator.controller.AnimatorController;
import cs3500.animator.controller.AnimatorControllerImpl;
import cs3500.animator.controller.adaptations.ProviderController;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModelImpl;
import cs3500.animator.provider.view.EditorAnimationViewImpl;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.ExtendedVisualView;
import cs3500.animator.view.JFrameView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;

/**
 * Executes the an animation based on the command line arguments. The string after -in is the path
 * to the input file. The string after -view is the type of view. The tree types of views are
 * TextView, JFrameView, ans SVGView. The string after -out is the output file. JFrame ignores this
 * field. If it is not included it prints output to the console. The int after -speed is the speed
 * of the animation. It is required for JFrameView and SVGView, and it is ignored by TextView.
 */

public final class Excellence {
  /**
   * Takes in a command argument to be used to start an animation.
   *
   * @param args takes in a command argument
   */
  public static void main(String[] args) {
    AnimationReader reader = new AnimationReader();
    AnimatorModelImpl.Builder builder = new AnimatorModelImpl.Builder();

    String file = findStringArg(args, "-in", 1);
    if (file == null) {
      throw new IllegalArgumentException("bad input");

    } else {
      Readable input;
      try {
        input = new FileReader(file);
      } catch (Exception e) {
        throw new IllegalArgumentException("file not found");
      }
      reader.parseFile(input, builder);
    }

    Appendable output;
    file = findStringArg(args, "-out", 1);
    if (file == null) {
      output = System.out;
    } else {
      try {
        output = new FileWriter(file);
      } catch (Exception e) {
        throw new IllegalArgumentException("file not found");
      }
    }

    int ticksPerSec = findPosIntArg(args, "-speed", 1); //this does not make tps necessary

    AnimatorModel model = builder.build();
    AnimatorController c;

    String viewType = findStringArg(args, "-view", 1);
    if (viewType == null) {
      throw new IllegalArgumentException("bad input");
    } else if (viewType.equals("visual")) {
      c = new AnimatorControllerImpl(model, new JFrameView(ticksPerSec));
    } else if (viewType.equals("svg")) {
      c = new AnimatorControllerImpl(model, new SVGView(ticksPerSec, output));
    } else if (viewType.equals("edit")) {
      c = new AnimatorControllerImpl(model, new ExtendedVisualView(ticksPerSec));
    } else if (viewType.equals("provider")) {
      c = new ProviderController(model, new EditorAnimationViewImpl(), ticksPerSec);
    } else {
      c = new AnimatorControllerImpl(model, new TextView(output));
    }

    c.animationGo();
    try {
      if (output instanceof FileWriter) {
        ((FileWriter) output).close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * Find the positive integer x many in the array after the search string, returns -1 if search is
   * not found in args or bad input is given.
   *
   * @param args   array to search through
   * @param search string to search for
   * @param x      indexes after search to look in
   * @return index of arg, -1 if search not in string
   */
  private static int findPosIntArg(String[] args, String search, int x) {
    try {
      for (int i = 0; i < args.length; i++) {
        if (args[i].equals(search)) {
          return Integer.parseInt(args[i + x]);
        }
      }
    } catch (Exception e) {
      return -1; //bad input
    }
    return -1;
  }

  /**
   * Find the string x many in the array after the search string, returns null if search is not
   * found in args or bad input is given.
   *
   * @param args   array to search through
   * @param search string to search for
   * @param x      indexes after search to look in
   * @return index of arg, null if search not in string
   */
  private static String findStringArg(String[] args, String search, int x) {
    try {
      for (int i = 0; i < args.length; i++) {
        if (args[i].equals(search)) {
          return args[i + x];
        }
      }
    } catch (Exception e) {
      return null; //bad input
    }
    return null;
  }
}

