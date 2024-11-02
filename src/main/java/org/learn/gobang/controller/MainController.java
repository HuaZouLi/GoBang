package org.learn.gobang.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.GUIState;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import org.learn.gobang.view.GameView;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@FXMLController
@Scope("prototype")
public class MainController implements Initializable {
    @FXML
    private JFXButton btnPatch;

    @FXML
    private JFXComboBox<String> jcbAction;



    @PostConstruct
    public void init() {

    }

    @FXML
    void onClickBtnPatch(ActionEvent event) {
        AbstractJavaFxApplicationSupport.showView(GameView.class);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GUIState.getStage().setWidth(600);
        GUIState.getStage().setHeight(400);
        System.out.println(GUIState.getStage().getWidth());
        System.out.println(GUIState.getStage().getHeight());
        GUIState.getStage().setResizable(false);
        GUIState.getStage().centerOnScreen();

        ObservableList<String> options=FXCollections.observableArrayList();
        options.add("亲自出战");
        options.add("Bot出战");
        jcbAction.setItems(options);
        jcbAction.setValue(options.get(0));
    }
}
