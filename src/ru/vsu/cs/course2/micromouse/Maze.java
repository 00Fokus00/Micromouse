package ru.vsu.cs.course2.micromouse;

import java.util.*;

public class Maze {

    public Point start;
    public Point finish;
    public int[][] matrix;

    public static class Point {
        private int y;
        private int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    public List<Point> findPath() {
        int rows = matrix.length;
        int col = matrix[0].length;
        boolean[][] visit = new boolean[rows][col];
        visit[start.y][start.x] = true;

        Queue<Point> queue = new LinkedList<>();
        queue.add(start);

        Map<Point, Point> map = new HashMap<>();
        map.put(start, null);

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int x = current.getY();
            int y = current.getX();

            if (x == finish.y && y == finish.x) {
                return constructPath(map, current);
            }

            int[][] neighbors = {
                    {-1, 0},
                    {1, 0},
                    {0, -1},
                    {0, 1}
            };

            for (int[] neighbor : neighbors) {
                int nx = x + neighbor[0];
                int ny = y + neighbor[1];
                if (nx >= 0 && nx < rows && ny >= 0 && ny < col) {
                    if (matrix[nx][ny] != 1 && !visit[nx][ny]) {
                        Point next = new Point(nx, ny);
                        visit[nx][ny] = true;
                        queue.add(next);
                        map.put(next, current);
                    }
                }
            }
        }
        return new ArrayList<>();
    }

    public static List<Point> constructPath(Map<Point, Point> map, Point finish) {
        List<Point> path = new ArrayList<>();
        Point cur = finish;
        while (cur != null) {
            path.add(cur);
            cur = map.get(cur);
        }
        Collections.reverse(path);
        return path;
    }

    public static int[][] generateMaze(int rows, int cols) {
        int[][] maze = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            Arrays.fill(maze[i], 1);
        }
        Random rand = new Random();
        Stack<Maze.Point> stack = new Stack<>();
        Maze.Point start = new Maze.Point(0, 0);
        maze[start.getY()][start.getX()] = 0;
        stack.push(start);


        int[][] directions = {{-2, 0}, {2, 0}, {0, -2}, {0, 2}};

        while (!stack.isEmpty()) {
            Maze.Point current = stack.pop();
            Collections.shuffle(Arrays.asList(directions));

            for (int[] direction : directions) {
                int nx = current.getX() + direction[0];
                int ny = current.getY() + direction[1];


                if (ny >= 0 && ny <= rows && nx >= 0 && nx <= cols && maze[ny][nx] == 1) {

                    maze[ny - direction[1] / 2][nx - direction[0] / 2] = 0;
                    maze[ny][nx] = 0;
                    stack.push(new Maze.Point(ny, nx));
                }
            }
        }
        maze[0][0] = 2;
        maze[rows - 1][cols - 1] = 3;
        return maze;
    }
}