package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.anish.calabashbros.BubbleSorter;
import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.Wall;
import com.anish.calabashbros.World;
import com.anish.calabashbros.Floor;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Calabash[][] bros;
    private Calabash bigBros;
    String[] sortSteps;
    private static final int n = 30;
    private int[][] maze;

    public WorldScreen() {
        world = new World();
        // MazeGenerator mazeGenerator = new MazeGenerator(30);
        // mazeGenerator.generateMaze();
        // maze = mazeGenerator.getRawMaze();
        // System.out.println("RAW MAZE\n" + mazeGenerator.getRawMaze());
        // System.out.println("SYMBOLIC MAZE\n" + mazeGenerator.getSymbolicMaze());
        // bros = new Calabash[n][n];
        bigBros = new Calabash(Color.BLUE, 0, world);
        world.put(bigBros, 0, 0);

        maze = world.getMaze();
        
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         if (maze[i][j] == 0)
        //             world.put()
        //     }
        // }
        // Random random = new Random();
        // int[] randomArray = getRandomArray(n*n);
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         int r, g, b;
        //         r = random.nextInt(256); g = random.nextInt(256); b = random.nextInt(256);
        //         bros[i][j] = new Calabash(new Color(r, g, b), randomArray[i*n+j], world);
        //         world.put(bros[i][j], 10+2*i, 10+2*j);
        //     }
        // }
        // bros = new Calabash[7];

        // bros[3] = new Calabash(new Color(204, 0, 0), 1, world);
        // bros[5] = new Calabash(new Color(255, 165, 0), 2, world);
        // bros[1] = new Calabash(new Color(252, 233, 79), 3, world);
        // bros[0] = new Calabash(new Color(78, 154, 6), 4, world);
        // bros[4] = new Calabash(new Color(50, 175, 255), 5, world);
        // bros[6] = new Calabash(new Color(114, 159, 207), 6, world);
        // bros[2] = new Calabash(new Color(173, 127, 168), 7, world);

        // world.put(bros[0], 10, 10);
        // world.put(bros[1], 12, 10);
        // world.put(bros[2], 14, 10);
        // world.put(bros[3], 16, 10);
        // world.put(bros[4], 18, 10);
        // world.put(bros[5], 20, 10);
        // world.put(bros[6], 22, 10);

        // BubbleSorter<Calabash> b = new BubbleSorter<>();
        // b.load(bros);
        // b.sort();

        // sortSteps = this.parsePlan(b.getPlan());
    }

    private int[] getRandomArray(int arraySize) {
        int[] selected = new int[arraySize];
        int[] result = new int[arraySize];
        for (int i = 0; i < arraySize; i++)
            selected[i] = 0;
        Random random = new Random();
        for (int i = 0; i < arraySize; i++) {
            int rank = random.nextInt(n*n);
            while (selected[rank] == 1)
                rank = random.nextInt(n*n);
            result[i] = rank;
            selected[rank] = 1;
        }
        return result;
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Calabash[][] bros, String step) {
        String[] couple = step.split("<->");
        getBroByRank(bros, Integer.parseInt(couple[0])).swap(getBroByRank(bros, Integer.parseInt(couple[1])));
    }

    private Calabash getBroByRank(Calabash[][] bros, int rank) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (bros[i][j].getRank() == rank)
                    return bros[i][j];
            }
        }
        return null;
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {

        int curX = bigBros.getX(), curY = bigBros.getY();
        int[][] maze = world.getMaze();
        world.setFloor(curX, curY);
        
        if (key.getKeyCode() == KeyEvent.VK_UP) {
            if (curY > 0 && maze[curX][curY-1] == 1) 
                bigBros.moveTo(curX, curY-1);
        } 
        else if (key.getKeyCode() == KeyEvent.VK_DOWN) {
            if (curY+1 < n && maze[curX][curY+1] == 1) 
                bigBros.moveTo(curX, curY+1);
        } 
        else if (key.getKeyCode() == KeyEvent.VK_LEFT) {
            if (curX > 0 && maze[curX-1][curY] == 1) 
                bigBros.moveTo(curX-1, curY);
        } 
        else if (key.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (curX+1 < n && maze[curX+1][curY] == 1) 
                bigBros.moveTo(curX+1, curY);
        }

        return this;
    }

}
