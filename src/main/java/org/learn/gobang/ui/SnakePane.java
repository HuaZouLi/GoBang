package org.learn.gobang.ui;

import javafx.geometry.Insets;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.learn.gobang.model.Snake;
import org.learn.gobang.model.SnakeMap;


import java.util.HashMap;

public class SnakePane extends GridPane {
    private int row;
    private int col;
    private final Rectangle[] rectangles;

    public SnakePane(int row,int col, int size){
        this.setWidth(row*size);
        this.setHeight(col*size);
        rectangles = new Rectangle[row*col];
        for(int i = 0 ; i < rectangles.length ; i++){
            rectangles[i] = new Rectangle(size,size);
            rectangles[i].setFill(Color.WHITE);
            this.add(rectangles[i], i%col, i/col);
        }
    }

    public void updateUI(int[] map){
        for(int i=0;i<rectangles.length;i++){
            switch(map[i]){
                case 1://1号玩家蛇身
                    rectangles[i].setFill(Color.YELLOW);
                    break;
                case 10://1号玩家蛇头
                    rectangles[i].setFill(Color.GREEN);
                    break;
                case 2://2号玩家蛇身
                    rectangles[i].setFill(Color.RED);
                    break;
                case 20://2号玩家蛇头
                    rectangles[i].setFill(Color.BLUE);
                    break;
                case 3://食物
                    rectangles[i].setFill(Color.BLACK);
                    break;
                default:
                    rectangles[i].setFill(Color.WHITE);
                    break;
            }
        }
    }


}
