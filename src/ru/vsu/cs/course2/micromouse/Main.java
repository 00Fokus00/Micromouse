package ru.vsu.cs.course2.micromouse;

import javax.swing.*;
import java.awt.event.*;

class DrawingPanel {
    static JFrame frame = new JFrame("Micromouse");
    private static Timer timer;
    static int ticksFromStart = 0;
    private static MazeMouseLogic mazeMouseLogic;

    public static void main(String[] args) {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(815, 815);
        frame.setLocation(250,30);

        mazeMouseLogic = new MazeMouseLogic();
        frame.add(mazeMouseLogic);
        frame.setVisible(true);

        timer = new Timer(400, null);

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (timer.isRunning()) {
                    timer.stop();
                }
                ticksFromStart = 0;
                mazeMouseLogic = new MazeMouseLogic();
                frame.add(mazeMouseLogic);
                frame.revalidate();
                timer.start();
            }
        });

        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mazeMouseLogic.mousePath.size() <= ticksFromStart) {
                    timer.stop();
                    return;
                }
                mazeMouseLogic.Logic(ticksFromStart);
                mazeMouseLogic.repaint();
                ticksFromStart++;
            }
        });
        timer.start();
    }
}

