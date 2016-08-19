/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circlethedot;

import java.util.ArrayList;
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
        grid=new Circle[size][size];
        circles =new ArrayList<>();
        
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
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
