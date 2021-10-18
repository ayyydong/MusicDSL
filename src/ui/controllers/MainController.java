package ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.GuiHelpers;

public class MainController {
    @FXML private TextArea mainInput;
    @FXML private TextField outputFile;
    @FXML private Button generateButton;

    private final GuiHelpers guiHelpers;

    public MainController(){
        this.guiHelpers = new GuiHelpers();
    }

    public void generate() {
        String result = guiHelpers.init(mainInput.getText(), outputFile.getText());
        System.out.println(result);
    }
}


