package com.example.snakesladders;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Game {
    private Board board;
    private Dice dice;
    private Queue<Player> playersQueue;
    private List<Player> winners;
    private DifficultyLevel difficultyLevel;

    public Game(int boardSizeN, int numberOfPlayers, DifficultyLevel difficultyLevel) {
        this.board = new Board(boardSizeN);
        this.dice = new Dice();
        this.difficultyLevel = difficultyLevel; // Determines environment preset logically if extended
        this.playersQueue = new LinkedList<>();
        this.winners = new ArrayList<>();

        for (int i = 1; i <= numberOfPlayers; i++) {
            playersQueue.add(new Player("Player " + i));
        }
    }

    public void start() {
        System.out.println("=================================================");
        System.out.println("🐍 Starting Snakes & Ladders (" + difficultyLevel + " MODE) 🪜");
        System.out.println("Board Size: " + board.getMaxPosition() + " cells");
        System.out.println("Total Players: " + playersQueue.size());
        System.out.println("=================================================");

        while (playersQueue.size() >= 2) { // The game continues till at least 2 players are playing
            Player currentPlayer = playersQueue.poll();
            int currentPosition = currentPlayer.getCurrentPosition();
            int diceValue = dice.roll();

            int nextPosition = currentPosition + diceValue;

            System.out.println(currentPlayer.getName() + " rolled a " + diceValue + 
                               " and moves from " + currentPosition + " to target " + nextPosition);

            if (nextPosition > board.getMaxPosition()) {
                System.out.println(currentPlayer.getName() + " cannot move beyond board size. Turn skipped.");
                playersQueue.add(currentPlayer);
            } else if (nextPosition == board.getMaxPosition()) {
                currentPlayer.setCurrentPosition(nextPosition);
                System.out.println("🏆 " + currentPlayer.getName() + " has reached the finish line and won!");
                winners.add(currentPlayer);
            } else {
                nextPosition = board.getNewPosition(nextPosition);
                currentPlayer.setCurrentPosition(nextPosition);

                // Assuming no perfect overlaps to board max inside jump ends (Ladders don't auto-win unless specific)
                if (nextPosition == board.getMaxPosition()) {
                    System.out.println("🏆 " + currentPlayer.getName() + " has reached the finish line via a jump and won!");
                    winners.add(currentPlayer);
                } else {
                    playersQueue.add(currentPlayer);
                }
            }
            System.out.println("-------------------------------------------------");
        }

        if (!playersQueue.isEmpty()) {
            System.out.println("😢 Game Over. " + playersQueue.poll().getName() + " lost.");
        }
        
        System.out.println("\n🔥 Leaderboard 🔥");
        for (int i = 0; i < winners.size(); i++) {
            System.out.println("Rank " + (i + 1) + ": " + winners.get(i).getName());
        }
    }
}
