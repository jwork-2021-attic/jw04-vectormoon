package com.anish.calabashbros;

public class World {

    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    private int[][] maze;

    private Tile<Thing>[][] tiles;

    public World() {
        MazeGenerator mazeGenerator = new MazeGenerator(30);
        mazeGenerator.generateMaze();
        maze = mazeGenerator.getRawMaze();

        if (tiles == null) {
            tiles = new Tile[WIDTH][HEIGHT];
        }

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tiles[i][j] = new Tile<>(i, j);
                if (maze[i][j] == 0)
                    tiles[i][j].setThing(new Wall(this));
                else
                    tiles[i][j].setThing(new Floor(this));
            }
        }
    }

    public Thing get(int x, int y) {
        return this.tiles[x][y].getThing();
    }

    public void put(Thing t, int x, int y) {
        this.tiles[x][y].setThing(t);
    }

    public int[][] getMaze() {
        return maze;
    }

    public void setFloor(int x, int y) {
        this.tiles[x][y].setThing(new Floor(this));
    }
}
