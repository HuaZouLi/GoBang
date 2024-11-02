package org.learn.gobang.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.GUIState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.KeyCode;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.learn.gobang.common.BaseContext;
import org.learn.gobang.pojo.User;
import org.learn.gobang.service.UserService;
import org.learn.gobang.utils.SpringContextUtil;
import org.learn.gobang.view.GameView;
import org.learn.gobang.view.RegisterView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
@Scope("prototype")
public class IndexController implements Initializable {
    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {
        GUIState.getStage().setWidth(1080);
        GUIState.getStage().setHeight(600);
        System.out.println(GUIState.getStage().getWidth());
        System.out.println(GUIState.getStage().getHeight());
        GUIState.getStage().setResizable(false);

        GUIState.getStage().centerOnScreen();

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtPassword.setOnKeyPressed((event) -> {
            if(event.getCode()== KeyCode.ENTER){
                login();
            }
        });
    }

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private Hyperlink hplRegister;

    @FXML
    void onClickHplRegister(ActionEvent event) {
        AbstractJavaFxApplicationSupport.showView(RegisterView.class);
    }

    @FXML
    void onClickBtnLogin(ActionEvent event) {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom().build();
        HttpGet httpGet = new HttpGet("https://baidu.com");
        httpclient.start();
        httpclient.execute(httpGet, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse httpResponse) {
                try {
                    System.out.println(EntityUtils.toString(httpResponse.getEntity()));
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Exception e) {

            }

            @Override
            public void cancelled() {

            }
        });
       login();
    }

    private void login(){
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        User user=User.builder().username(username).password(password).build();
        User u=userService.login(user);
        if(u==null){
            ViewController.alertWindow("提示","登录失败","确认",()->{

            },1);
        }else{
            BaseContext.setUser(u);
            AbstractJavaFxApplicationSupport.showView(GameView.class);

        }
    }

}
