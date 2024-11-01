package org.learn.gobang.model;


import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Snake {
    private List<Point> body;
    private int direction;
    private int score;
    private Point last;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Point> getBody() {
        return body;
    }

    public void setBody(List<Point> body) {
        this.body = body;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Snake(int x, int y, int direction) {
        this.body =new LinkedList<>();
        this.body.add(new Point(x,y));
        this.direction = direction;
    }

    public int getSize() {
        return body.size();
    }

    public void changeDirection(int direction) {
        if(Math.abs(this.direction-direction)!=2){
            this.direction = direction;
            move();
        }

    }

    public void eat(int x,int y){
        body.add(last);
        score++;
    }

    public void move(){
        Point head=new Point(body.get(0));
        switch (direction){
            case 0:
                head.x--;
                break;
            case 1:
                head.y--;
                break;
            case 2:
                head.x++;
                break;
            case 3:
                head.y++;
                break;
        }
        last=body.get(body.size()-1);
        body.remove(last);
        body.add(0, head);

    }
}