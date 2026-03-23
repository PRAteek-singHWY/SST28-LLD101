package com.example.snakesladders;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter the board size N (forming N x N board): ");
            int n = scanner.nextInt();

            System.out.println("Enter the number of players: ");
            int x = scanner.nextInt();
            if (x < 2) {
                System.out.println("Minimum 2 players required to play Snakes and Ladders!");
                System.exit(0);
            }

            System.out.println("Enter difficulty level (EASY / HARD): ");
            String diffStr = scanner.next().toUpperCase();
            DifficultyLevel difficultyLevel;
            if (diffStr.equals("HARD")) {
                difficultyLevel = DifficultyLevel.HARD;
            } else {
                difficultyLevel = DifficultyLevel.EASY; // default
            }

            Game game = new Game(n, x, difficultyLevel);
            game.start();
        } catch (Exception e) {
            System.out.println("Invalid input provided");
        } finally {
            scanner.close();
        }
    }
}
