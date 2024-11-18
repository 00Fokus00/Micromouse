package ru.vsu.cs.course2.micromouse;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MazeMouseLogic extends JPanel {

    private final int BACKGROUND_WIDTH = 800;
    private final int BACKGROUND_HEIGHT = 800;
    private int[][] matrix = Maze.generateMaze(13, 13);
    private DrawMaze drawMaze;
    private Mouse mouse;
    public List<Maze.Point> mousePath;
    public MazeMouseLogic(){
        drawMaze = new DrawMaze();
        mouse = new Mouse(75,75,0.2, Math.PI / 2);
        drawMaze.maze.matrix = matrix;
        drawMaze.maze.start = new Maze.Point(0, 0);
        drawMaze.maze.finish = new Maze.Point(matrix.length - 1, matrix[0].length - 1);
        mousePath = drawMaze.maze.findPath();
    }
    public void Logic(int ticksFromStart){
        int x = mousePath.get(ticksFromStart).getX() * drawMaze.dw + 75;
        int y = mousePath.get(ticksFromStart).getY() * drawMaze.dh + 75;
        if (x > mouse.x){
            mouse.moveTo(x, y, Math.PI / 2);
        }
        if (x < mouse.x){
            mouse.moveTo(x, y, -Math.PI / 2);
        }
        if (y > mouse.y){
            mouse.moveTo(x, y, Math.PI);
        }
        if (y < mouse.y) {
            mouse.moveTo(x, y, 0.0);
        }


    }
    public void paint(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);

        drawMaze.paint(g);
        mouse.paint(g);
    }
}
