package webBrowser;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;

public class WebServer extends Application {
    private View view;
    private Controller controller;
    private Model model;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Standard MVC
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Part of the GUI will contain log output from our own handler
        TextAreaHandler textAreaHandler = new TextAreaHandler();
        textAreaHandler.setLevel(Level.INFO);
        Logger defaultLogger = Logger.getLogger("");
        defaultLogger.addHandler(textAreaHandler);
        
        // Initialize the GUI
        model = new Model();
        view = new View(primaryStage, model, textAreaHandler.getTextArea());
        controller = new Controller(model, view);

        // Display the GUI after all initialization is complete
        view.start();
    }
}
