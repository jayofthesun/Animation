Model changes:

For the model we took away the ability of the model to know the time/tick that it is on and delegated that information to the controller. Instead the method tick now takes in a time which will update shapes in the model to that tick. Thus the animation "ticking" is completely separate from the model. 

We also added more implementations for the model so that the model knows when the animation of all the shapes is over, which is called isOver(int time) in the AnimatorModel. 

We also added a method called getKeyFrames to get a list of string representations of a shape's keyframes in the AnimatorModel. 

We added the implementation of these other methods to help with the implementation of adding a keyFrame to a shape's animation which takes in a name, time, height, width color, x, and y that is called addKeyFrame along with a removeKeyFrame which takes in a name and index that removes a keyframe from a shapes animation in the AnimatorModel. We also added another addKeyFrame method and remove keyFrame model shape that will add the keyframe with the given attributes to the given time along with the other method removing the keyframe from a given index.

We also overrode the toString() in our attributes for future use for the view so that when we have a description of keyframes with their attributes in the view, we know what each number/string represents and how the attributes have changes from each other.

We fixed the name for the command lines of the views and corrected them to their proper names.

Added tests for the new methods


View and Controller Changes:


For this assignment we added the new view by extending the old visual view and implementing a new interface on top of the original view interface to add extra functionality. 

***** We understand that our view contains information that the controller should have however during our implementation of our view we made a design choice to keep the information as is because as we tried to change the implementation of our controller we felt that it was more extensible for the view in the future, along with our controller, otherwise it felt like it was being very tight coupled than it had to be. *****

Controller 
- Interface AnimatorController
- Class AnimatorControllerImpl

Takes in: model and a view
- cannot take a null model or null view

Method: go()
- starts the animation based on the given model and view
-

View 

AnimatorEditorView Interface:
Created an interface for our different functionalities available for the editor panel so that it can also be extended to other views for future use. 

ExtendedVisualView:
Takes in a AnimatorEditor view. Extends the VisualView so that we are able to add the editor panel to change the visual view as the animation plays through. Offers different functionality such as starting, pausing, restarting, increasing speed, slowing down, etc.

EditorPanel: Contains all the buttons, comboboxes, text boxes, and choosers necessary for each of the functionalities of the editor panel. Displays information visually for the user next to each selection or in some form. Displays error messages when criteria hasn't been met to perform a button's functionality and will allow a user to continue after the error.


DrawPanel: Draws the JFrame/Visual View as it was originally supposed to be drawn.


The screenshot of the editor is in the resources file under buildingEditorView.png


