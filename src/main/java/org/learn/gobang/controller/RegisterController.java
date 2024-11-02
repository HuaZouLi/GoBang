package org.learn.gobang.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import org.learn.gobang.pojo.User;
import org.learn.gobang.service.UserService;
import org.learn.gobang.view.IndexView;
import org.springframework.beans.factory.annotation.Autowired;

@FXMLController
public class RegisterController {
    @Autowired
    private UserService userService;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private Hyperlink hplLogin;

    @FXML
    private JFXButton btnReg;

    @FXML
    private JFXPasswordField txtPassword;


    @FXML
    void onClickBtnReg(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        User user=User.builder().username(username).password(password).build();
        boolean res=userService.register(user);
        if(res){
            ViewController.alertWindow("提示","注册成功","确认",()->{
                AbstractJavaFxApplicationSupport.showView(IndexView.class);
            },0);
        }else{
            ViewController.alertWindow("提示","注册失败","确认",()->{

            },1);
        }
    }


    @FXML
    void onClickHplLogin(ActionEvent event) {
        AbstractJavaFxApplicationSupport.showView(IndexView.class);
    }
}
