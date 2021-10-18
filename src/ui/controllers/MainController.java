package ui.controllers;

import exceptions.FailedStaticCheckException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import ui.GuiHelpers;

import java.io.FileNotFoundException;

public class MainController {
    @FXML private TextArea mainInput;
    @FXML private TextField outputFile;
    @FXML private Label errorLabel;
    @FXML private Label successLabel;

    private final GuiHelpers guiHelpers;

    public MainController(){
        this.guiHelpers = new GuiHelpers();
    }

    public void generate() {
        String result;
        try {
            result = guiHelpers.init(mainInput.getText(), outputFile.getText());
        } catch (FileNotFoundException | FailedStaticCheckException | ParseCancellationException e) {
            successLabel.setVisible(false);
            errorLabel.setVisible(true);
            errorLabel.setText(e.getMessage());
            return;
        }

        errorLabel.setVisible(false);
        successLabel.setVisible(true);
        successLabel.setText(result);
    }

    public void play() {
        if (!guiHelpers.play()) {
            successLabel.setVisible(false);
            errorLabel.setVisible(true);
            errorLabel.setText("Generate first!");
        }
    }
}


