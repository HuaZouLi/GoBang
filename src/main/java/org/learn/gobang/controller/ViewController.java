package org.learn.gobang.controller;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.GUIState;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.learn.gobang.view.IndexView;

import java.io.IOException;

public class ViewController {
    static FXMLLoader fxmlLoader;
    static Scene scene;
    public static void goIndex()  {
        try{
            fxmlLoader = new FXMLLoader(ViewController.class.getResource("/fxml/index.fxml"));
            scene = new Scene(fxmlLoader.load());
            GUIState.getStage().setScene(scene);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void goGame(){
        try{
            fxmlLoader = new FXMLLoader(ViewController.class.getResource("/fxml/game.fxml"));
            scene = new Scene(fxmlLoader.load());

            GUIState.getStage().setScene(scene);
        }
        catch (Exception e){  e.printStackTrace();}
    }

    public static void alertWindow(String header, String content,String op, Runnable action,int type){
        JFXDialogLayout dialogLayout = new JFXDialogLayout();

        dialogLayout.getStylesheets().add(ViewController.class.getResource("/css/main.css").toExternalForm());
        JFXAlert jfxAlert=new JFXAlert(GUIState.getStage());

        jfxAlert.initStyle(StageStyle.TRANSPARENT);

        JFXButton jfxButton=new JFXButton(op);
        Label label_head=new Label(header);
        label_head.setFont(new Font(25));
        jfxAlert.initModality(Modality.APPLICATION_MODAL);
        jfxAlert.setOverlayClose(false);
        dialogLayout.setActions(jfxButton);
        jfxButton.setOnAction((ActionEvent e)->{
            action.run();
            jfxAlert.close();
        });
        dialogLayout.setHeading(label_head);
        dialogLayout.setBody(new Label(content));
        jfxAlert.setContent(dialogLayout);
        jfxButton.getStyleClass().add("dialog-accept");
        dialogLayout.getStyleClass().add("dialog");
        if(type==1)jfxButton.getStyleClass().add("dialog-failure");

        jfxAlert.show();



    }
}
