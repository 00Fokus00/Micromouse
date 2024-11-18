package ru.vsu.cs.course2.micromouse;

import javax.swing.*;
import java.awt.*;

public class Mouse extends JFrame {

    public int x, y;
    private double scale, angle;

    public Mouse(int x, int y, double scale, double angle){
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.angle = angle;

    }
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.rotate(angle, x, y);

        // Координаты относительно центра мыши
        int bodyX = (int) (x - 80 * scale);
        int bodyY = (int) (y - 100 * scale);

        // Рисуем лапки
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillOval((int) (bodyX + 10 * scale), (int) (bodyY + 150 * scale),
                (int) (30 * scale), (int) (50 * scale));  // Левая задняя лапка
        g2d.fillOval((int) (bodyX + 120 * scale), (int) (bodyY + 150 * scale),
                (int) (30 * scale), (int) (50 * scale));  // Правая задняя лапка
        g2d.fillOval((int) (bodyX + 130 * scale), (bodyY),
                (int) (30 * scale), (int) (50 * scale));  // Правая передняя лапка
        g2d.fillOval((bodyX), (bodyY),
                (int) (30 * scale), (int) (50 * scale));  // Левая передняя лапка


        // Рисуем тело мыши
        g2d.setColor(Color.GRAY);
        g2d.fillOval(bodyX, bodyY, (int) (160 * scale), (int) (200 * scale)); // Тело

        // Рисуем голову мыши
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillOval((int) (bodyX + 30 * scale), (int) (bodyY - 70 * scale),
                (int) (100 * scale), (int) (100 * scale)); // Голова

        // Рисуем нос
        g2d.setColor(Color.LIGHT_GRAY);
        int noseX = (int) (bodyX + 83 * scale);  // X координата начала носа
        int noseY = (int) (bodyY - 120 * scale);  // Y координата начала носа
        int[] noseXPoints = {noseX, (int) (noseX - 30 * scale), (int) (noseX + 30 * scale)};
        int[] noseYPoints = {noseY, (int) (noseY + 80 * scale), (int) (noseY + 80 * scale)};
        g2d.fillPolygon(noseXPoints, noseYPoints, 3);  // Рисуем треугольник (нос)
        g2d.setColor(Color.PINK);
        g2d.fillOval((int) (bodyX + 73 * scale),  (int) (bodyY - 130 * scale), (int) (20 * scale), (int)(20 * scale));


        // Рисуем уши мыши
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillOval((int) (bodyX + 10 * scale), (int) (bodyY - 29 * scale),
                (int) (60 * scale), (int) (60 * scale));  // Левое ухо
        g2d.fillOval((int) (bodyX + 90 * scale), (int) (bodyY - 29 * scale),
                (int) (60 * scale), (int) (60 * scale));  // Правое ухо

        // Рисуем глазки
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillOval((int) (bodyX + 50 * scale), (int) (bodyY - 50 * scale),
                (int) (10 * scale), (int) (10 * scale));  // Левое ухо
        g2d.fillOval((int) (bodyX + 100 * scale), (int) (bodyY - 50 * scale),
                (int) (10 * scale), (int) (10 * scale));  // Правое ухо


        // Рисуем хвост мыши
        g2d.setColor(Color.GRAY);
        g2d.setStroke(new BasicStroke((float) (3 * scale))); // Толщина линии
        g2d.drawArc((int) (bodyX + 80 * scale), (int) (bodyY + 150 * scale),
                (int) (150 * scale), (int) (100 * scale), 180, 200);  // Хвост


        //Рисуем усы
        g2d.setColor(Color.BLACK);
        int whiskerLength = (int) (40 * scale); // Длина усов
        int usX = (int) (bodyX + 83 * scale);
        int usY = (int) (bodyY - 65 * scale);

        g2d.drawLine(usX - whiskerLength, usY - (int) (10 * scale), usX - (int) (16 * scale), usY - (int) (15 * scale));
        g2d.drawLine(usX - whiskerLength, usY - (int) (20 * scale), usX - (int) (16 * scale), usY - (int) (15 * scale));
        g2d.drawLine(usX + (int) (16 * scale), usY - (int) (15 * scale), usX + whiskerLength, usY - (int) (10 * scale));
        g2d.drawLine(usX + (int) (16 * scale), usY - (int) (15 * scale), usX + whiskerLength, usY - (int) (20 * scale));


    }
    public void moveTo(int moveToX, int moveToY, Double angle){
        this.x = moveToX;
        this.y = moveToY;
        this.angle = angle;
    }

}
