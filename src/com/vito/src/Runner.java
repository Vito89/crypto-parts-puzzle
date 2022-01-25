package com.vito.src;

import java.util.Scanner;

public class Runner {

    public static String blockProcess(Scanner sc) {
        var rowCount = Integer.parseInt(sc.nextLine());
        final String[] stringsA = new String[rowCount];
        final String[] stringsB = new String[rowCount];
        for (int idx = 0; idx < rowCount; idx++) {
            if (!sc.hasNext()) {
                throw new IllegalStateException("number k different than rowCount");
            }
            var pair = sc.nextLine().split(" ");
            stringsA[idx] = pair[0];
            stringsB[idx] = pair[1];
        }
        return PuzzleProcessing.getSequence(stringsA, stringsB);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            System.out.println(blockProcess(sc));
        }
    }
}
