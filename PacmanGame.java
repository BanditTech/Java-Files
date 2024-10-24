import java.util.Scanner;

public class PacmanGame {
    static int width = 10;
    static int height = 10;
    static char[][] grid = new char[height][width];
    static int pacmanX = 1;
    static int pacmanY = 1;
    static int ghostX = 8;
    static int ghostY = 8;
    static int score = 0;
    static boolean gameOver = false;

    public static void main(String[] args) {
        initializeGrid();
        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            printGrid();
            System.out.println("Score: " + score);
            System.out.println("Use W (up), A (left), S (down), D (right) to move Pac-Man");

            char move = scanner.next().toUpperCase().charAt(0);
            updatePacmanPosition(move);
            moveGhost();
            checkCollision();
        }
        System.out.println("Game Over! Final Score: " + score);
    }

    static void initializeGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    grid[i][j] = '#'; // Wall
                } else {
                    grid[i][j] = '.'; // Points
                }
            }
        }
        grid[pacmanY][pacmanX] = 'P'; // Pac-Man
        grid[ghostY][ghostX] = 'G'; // Ghost
    }

    static void printGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void updatePacmanPosition(char move) {
        grid[pacmanY][pacmanX] = ' '; // Clear Pac-Man's old position
        switch (move) {
            case 'W': // Move up
                if (grid[pacmanY - 1][pacmanX] != '#') pacmanY--;
                break;
            case 'S': // Move down
                if (grid[pacmanY + 1][pacmanX] != '#') pacmanY++;
                break;
            case 'A': // Move left
                if (grid[pacmanY][pacmanX - 1] != '#') pacmanX--;
                break;
            case 'D': // Move right
                if (grid[pacmanY][pacmanX + 1] != '#') pacmanX++;
                break;
        }
        if (grid[pacmanY][pacmanX] == '.') {
            score++;
        }
        grid[pacmanY][pacmanX] = 'P'; // Set new Pac-Man position
    }

    static void moveGhost() {
        grid[ghostY][ghostX] = ' '; // Clear Ghost's old position
        if (ghostX > pacmanX) {
            ghostX--;
        } else if (ghostX < pacmanX) {
            ghostX++;
        }

        if (ghostY > pacmanY) {
            ghostY--;
        } else if (ghostY < pacmanY) {
            ghostY++;
        }
        grid[ghostY][ghostX] = 'G'; // Set new Ghost position
    }

    static void checkCollision() {
        if (pacmanX == ghostX && pacmanY == ghostY) {
            gameOver = true;
        }
    }
}
