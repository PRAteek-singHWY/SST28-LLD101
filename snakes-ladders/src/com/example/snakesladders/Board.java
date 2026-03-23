package com.example.snakesladders;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Board {
    private int size;
    private int maxPosition;
    private Map<Integer, Jump> snakes;
    private Map<Integer, Jump> ladders;

    public Board(int n) {
        this.size = n;
        this.maxPosition = n * n;
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
        generateJumps(n);
    }

    private void generateJumps(int n) {
        Random random = new Random();
        Set<Integer> starts = new HashSet<>();
        Set<Integer> ends = new HashSet<>();

        // Generate N snakes
        int snakesCount = 0;
        while (snakesCount < n) {
            int head = random.nextInt(maxPosition - 2) + 2; // Head cannot be at 1 or maxPosition
            int tail = random.nextInt(head - 1) + 1;        // Tail is strictly less than head

            // We ensure start positions are unique, and no jump ends at another jump's start
            // This prevents any cycle from occurring.
            if (!starts.contains(head) && !ends.contains(head) && !starts.contains(tail)) {
                snakes.put(head, new Jump(head, tail));
                starts.add(head);
                ends.add(tail);
                snakesCount++;
            }
        }

        // Generate N ladders
        int laddersCount = 0;
        while (laddersCount < n) {
            int start = random.nextInt(maxPosition - 2) + 2; // Start cannot be at 1 or maxPosition
            int end = random.nextInt(maxPosition - start) + start + 1; // End is strictly greater than start

            if (!starts.contains(start) && !ends.contains(start) && !starts.contains(end) && end <= maxPosition) {
                ladders.put(start, new Jump(start, end));
                starts.add(start);
                ends.add(end);
                laddersCount++;
            }
        }
    }

    public int getNewPosition(int position) {
        if (snakes.containsKey(position)) {
            System.out.println("Oh no! Bitten by a snake at " + position + ", going down to " + snakes.get(position).getEnd());
            return snakes.get(position).getEnd();
        }
        if (ladders.containsKey(position)) {
            System.out.println("Yay! Climbed a ladder at " + position + ", going up to " + ladders.get(position).getEnd());
            return ladders.get(position).getEnd();
        }
        return position;
    }

    public int getMaxPosition() {
        return maxPosition;
    }
}
