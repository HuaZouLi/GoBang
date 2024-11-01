package org.learn.gobang.controller;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.GUIState;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.util.Duration;

import org.learn.gobang.model.Snake;
import org.learn.gobang.model.SnakeMap;
import org.learn.gobang.ui.SnakePane;
import org.learn.gobang.view.GameView;
import org.learn.gobang.view.IndexView;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.awt.*;
import java.net.URL;
import java.util.*;
import java.util.List;

@FXMLController
@Scope("prototype")
public class GameController implements Initializable {
    @FXML
    private BorderPane bpane_root;

    @FXML
    private Text txt_p1_score;

    @FXML
    private Text txt_p2_score;

    @PostConstruct
    public void init() {

    }

    @PreDestroy
    public void destroy() {

    }

    private boolean isRunning;
    public int food_x,food_y;
    private int row,col;
    private Snake snake1;
    private Snake snake2;
    private SnakeMap map;
    private SnakePane snakePane ;

    private Map<KeyCode,Integer> keyMap1;
    private Map<KeyCode,Integer> keyMap2;

    public int[] getMap(){

        int row=this.map.getRow();
        int col=this.map.getCol();
        int[] map = Arrays.copyOf(this.map.getMap(),this.map.getMap().length);

        List<Point> snake1Body=snake1.getBody();
        List<Point> snake2Body=snake2.getBody();

        for(int i=0;i<snake1Body.size();i++){
            int x=snake1Body.get(i).x;
            int y=snake1Body.get(i).y;
            if(x*col+y<0||x*col+y>map.length-1){continue;}
            map[x*col+y]=1;
            if(i==0)map[x*col+y]=10;
        }
        for(int i=0;i<snake2Body.size();i++){
            int x=snake2Body.get(i).x;
            int y=snake2Body.get(i).y;
            if(x*col+y<0||x*col+y>map.length-1){continue;}
            map[x*col+y]=2;
            if(i==0)map[x*col+y]=20;
        }
        map[food_x*col+food_y]=3;
        return map;
    }

    public boolean checkIsSnake(int x,int y){
        for(int i=0;i<snake1.getBody().size();i++){
            if(x==snake1.getBody().get(i).x&&y==snake1.getBody().get(i).y){
                return true;
            }
        }
        for(int i=0;i<snake2.getBody().size();i++){
            if(x==snake2.getBody().get(i).x&&y==snake2.getBody().get(i).y){
                return true;
            }
        }
        return false;
    }

    public void generate_food(){
        while(true){
            int x=new Random().nextInt(row);
            int y=new Random().nextInt(col);
            if(!checkIsSnake(x,y)){
                food_x=x;
                food_y=y;
                break;
            }
        }
    }

    public void check(int k) {

        if(!isRunning)return;
        int row=map.getRow();
        int col=map.getCol();
        List<Point> snake1Body=snake1.getBody();
        List<Point> snake2Body=snake2.getBody();
        if(k==2){
            List<Point> t=snake1Body;
            snake1Body=snake2Body;
            snake2Body=t;
        }
        for(int i=0;i<snake1Body.size();i++){
            int x=snake1Body.get(i).x;
            int y=snake1Body.get(i).y;
            if(x==food_x&&y==food_y){
                if(k==1)snake1.eat(food_x, food_y);
                else if(k==2)snake2.eat(food_x, food_y);
                generate_food();
            }
            if(x<0||x>=row||y<0||y>=col){
                gameOver(k);
                return;
            }
            for(int j=0;j<snake2Body.size();j++){
                if(x==snake2Body.get(j).x&&y==snake2Body.get(j).y){
                    gameOver(k);
                    return ;
                }
            }
        }
        if(snake1.getScore()==15){
            gameOver(k==1?2:1);
        }
    }

    Timeline time;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        keyMap1=new HashMap<>();
        keyMap2=new HashMap<>();
        keyMap2.put(KeyCode.UP,0);
        keyMap2.put(KeyCode.LEFT,1);
        keyMap2.put(KeyCode.DOWN,2);
        keyMap2.put(KeyCode.RIGHT,3);
        keyMap1.put(KeyCode.W,0);
        keyMap1.put(KeyCode.A,1);
        keyMap1.put(KeyCode.S,2);
        keyMap1.put(KeyCode.D,3);
        this.row=20;this.col=20;
        snakePane = new SnakePane(row, col,40);
        map=new SnakeMap(row,col);
        snake1=new Snake(0,0,2);
        snake2=new Snake(row-1,col-1,0);
        GUIState.getStage().setWidth(810);
        GUIState.getStage().setHeight(1000);
        GUIState.getStage().centerOnScreen();
        bpane_root.setCenter(snakePane);
        generate_food();
        isRunning=true;
        snakePane.updateUI(getMap());

        EventHandler<ActionEvent> event = e -> {
            try {
                snake1.move();
                check(1);
                snake2.move();
                check(2);
                snakePane.updateUI(getMap());
                txt_p1_score.setText(String.format("P1分数: %d",snake1.getScore()));
                txt_p2_score.setText(String.format("P2分数: %d",snake2.getScore()));

            } catch (NullPointerException ee){
                GUIState.getStage().close();
            }
        };
        time = new Timeline(new KeyFrame(Duration.millis(500), event));
        time.setCycleCount(Timeline.INDEFINITE);
        time.play();

        GUIState.getScene().setOnKeyPressed(e1->{
            if(isRunning){
                KeyCode key=e1.getCode();
                if(keyMap1.containsKey(key)){
                    snake1.changeDirection(keyMap1.get(key));
                    check(1);

                }else if(keyMap2.containsKey(key)){
                    snake2.changeDirection(keyMap2.get(key));
                    check(2);

                }
                snakePane.updateUI(getMap());
                txt_p1_score.setText(String.format("P1分数: %d",snake1.getScore()));
                txt_p2_score.setText(String.format("P2分数: %d",snake2.getScore()));
            }
        });
    }

    public void gameOver(int k){
        isRunning=false;
        time.stop();

        if(k==1)k=2;
        else k=1;
        String s=String.format("玩家%d获胜",k);
        ViewController.alertWindow("提示",s,"确认",()->{
            AbstractJavaFxApplicationSupport.showView(IndexView.class);
        },0);
    }
}
