package ui.controllers;

import exceptions.FailedStaticCheckException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.GuiHelpers;

import java.io.FileNotFoundException;

public class MainController {
    @FXML private TextArea mainInput;
    @FXML private TextField outputFile;
    @FXML private Button generateButton;
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
        } catch (FileNotFoundException | FailedStaticCheckException e) {
            successLabel.setVisible(false);
            errorLabel.setVisible(true);
            errorLabel.setText(e.getMessage());
            return;
        }

        errorLabel.setVisible(false);
        successLabel.setVisible(true);
        successLabel.setText(result);
    }
}


