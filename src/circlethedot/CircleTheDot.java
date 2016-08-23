/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circlethedot;

import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author Shuvanon
 */
public class CircleTheDot extends Application {
    
    final static int size = 10;
    final int radius = 30;
    final int offsetX = 30;
    final int offsetY = 25;
    final int col[] = {0, 1, 1, 1, 0, -1, -1, -1};
    final int row[] = {1, 1, 0, -1, -1, -1, 0, 1};

    Circle[][] grid = new Circle[size][size];
    ArrayList<Circle> circles = new ArrayList<>();

    private Stage stage;
    
    
    
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        initUI(stage);
    }

    private void initUI(Stage stage){
        grid = new Circle[size][size];
        circles = new ArrayList<>();
        
        Pane root = new Pane();
        
        for (int x = 0;x<size;x++){
            for(int  y=0; y<size; y++){
                
                int drawX = y * radius + y * offsetX + radius;
                int drawY = x * radius + x * offsetY + radius;
                if (x % 2 == 1) {
                    drawX += offsetX;
                }
                System.out.println("X: " + drawX + " ,Y: " + drawY);
                Circle circle = new Circle(drawX, drawY, radius);
                circle.setId(x + "," + y);
                circle.setFill(Color.GRAY);
                grid[x][y] = circle;
                circle.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {

                    @Override
                    public void handle(javafx.scene.input.MouseEvent t) {
                        Circle clickedCircle = (Circle) t.getSource();
                        if (clickedCircle.getFill() == Color.GRAY) {
                            clickedCircle.setFill(Color.ORANGE);
                            //nextMove();
                        }
                    }
                });

                circles.add(circle);
            }
        }
        Random rand = new Random();
        for (int i = 0; i < 15; i++) {
            circles.get(rand.nextInt(81)).setFill(Color.ORANGE);
        }
        circles.get(20 + rand.nextInt(20)).setFill(Color.BLUE);
        root.getChildren().addAll(circles);
        Scene scene = new Scene(root, 650, 580, Color.WHITESMOKE);
        stage.setTitle("Circle The Dot");
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void nextMove() {
        Pair pos = new Pair(-1, -1);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j].getFill() == Color.BLUE) {
                    pos = new Pair(i, j);
                    System.out.println("here" + pos);
                }
            }
        }
        if (pos.x == 0 || pos.x == size - 1 || pos.y == 0 || pos.y == size - 1) {
            grid[pos.x][pos.y].setFill(Color.GRAY);
            newGame(false);
            return;
        }
        System.out.println(pos);
        int minDist = 1 << 28;
        Pair nextPos = null;
        for (int p = 0; p < 8; p++) {
            Pair cur = new Pair(pos.x + col[p], pos.y + row[p]);
            System.out.println(cur);
            if (cur.x >= 0 && cur.x < size && cur.y >= 0 && cur.y < size && Math.abs(grid[cur.x][cur.y].getCenterX() - grid[pos.x][pos.y].getCenterX()) <= 2.1 * radius) {
                Circle curCircle = grid[cur.x][cur.y];
                if (curCircle.getFill() != Color.GREY) {
                    System.out.println(curCircle + "is not grey");
                    continue;
                }
                System.out.println(cur.x + "," + cur.y);
                int dist = getDist(cur);
                System.out.println("current dist = " + dist);
                if (dist < minDist) {
                    minDist = dist;
                    nextPos = cur;
                }
            }
        }
        if (minDist == 1 << 28) {
            grid[pos.x][pos.y].setFill(Color.BLUE);
            newGame(true);
            return;
        } else {
            grid[pos.x][pos.y].setFill(Color.GREY);
            grid[nextPos.x][nextPos.y].setFill(Color.BLUE);
        }
    }
    
}
