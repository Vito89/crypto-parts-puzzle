package com.vito.main;

import java.util.Scanner;

public class Runner {

    public static String blockProcess(Scanner sc) {
        var rowCount = Integer.parseInt(sc.nextLine());
        final String[] wordsA = new String[rowCount];
        final String[] wordsB = new String[rowCount];
        for (int idx = 0; idx < rowCount; idx++) {
            if (!sc.hasNext()) {
                throw new IllegalStateException("number k different than rowCount");
            }
            var pair = sc.nextLine().split(" ");
            wordsA[idx] = pair[0];
            wordsB[idx] = pair[1];
        }
        return PuzzleProcessing.getSequence(wordsA, wordsB);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            System.out.println(blockProcess(sc));
        }
    }
}
