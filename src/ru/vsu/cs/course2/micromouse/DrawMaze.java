package ru.vsu.cs.course2.micromouse;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DrawMaze extends JFrame {

    Maze maze = new Maze();
    public final int dw = 50;
    public final int dh = 50;
    public final int ox = 50;
    public final int oy = 50;

    public void paint(Graphics g){
        getPreferredSize().getWidth();
        drawMaze(g);
        /*drawSolveMaze(g);*/
    }
    public void drawMaze(Graphics g){
        g.setColor(Color.BLACK);

        for (int i = 0; i <= maze.matrix[0].length ; i++){
            g.drawLine(dw * i + ox, oy, dw * i + ox, dh * maze.matrix.length + oy);
        }
        for (int i = 0; i <= maze.matrix.length; i++){
            g.drawLine(ox, dh * i + oy, dw * maze.matrix[0].length + ox,dh * i + oy);
        }

        for (int i = 0; i < maze.matrix.length; i++){
            for (int j = 0; j < maze.matrix[0].length; j++){
                if(maze.matrix[i][j] == 1){
                    g.setColor(Color.DARK_GRAY);
                    drawSquare(i,j,g);
                }
                if(maze.matrix[i][j] == 2){
                    maze.start = new Maze.Point(i, j);
                    g.setColor(Color.GREEN);
                    drawSquare(i,j,g);
                }
                if(maze.matrix[i][j] == 3){
                    maze.finish = new Maze.Point(i, j);
                    g.setColor(Color.RED);
                    drawSquare(i,j,g);
                }
            }
        }
    }
    public void drawSolveMaze(Graphics g){
        List<Maze.Point> path = maze.findPath();
        path.remove(path.size() - 1);
        path.remove(0);
        g.setColor(Color.BLUE);
        for(Maze.Point point : path){
            drawSquare(point.getY(), point.getX(),g);
        }
    }
    public void drawSquare(int i, int j, Graphics g){
        int x = j * dw + ox;
        int y = i * dh + oy;
        g.fillRect(x + 2, y + 2, dw - 3, dh - 3);
    }
}