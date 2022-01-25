package com.vito.main;

public class PuzzleProcessing {

    public static final String IMPOSSIBLE = "IMPOSSIBLE";

    public static String getSequence(String[] wordsA, String[] wordsB) {
        var result = new StringBuffer(50);

        var firstIdx = determineFirstIndex(wordsA, wordsB);
        if (firstIdx == -1) return IMPOSSIBLE;

        var fWordA = wordsA[firstIdx];
        var fWordB = wordsB[firstIdx];
        var fullPartInB = fWordA.length() < fWordB.length();
//        var fullWord = fullPartInB ? fWordB : fWordA;
        var intersection = fullPartInB ? fWordB.substring(fWordA.length()) : fWordA.substring(fWordB.length()); // e
        result.append(fullPartInB ? fWordB.substring(0, intersection.length()) : fWordA.substring(0, intersection.length())); // ie
        do {
            final String[] currentColumn = fullPartInB ? wordsA : wordsB;
            var indexes = findIndexByPrefix(intersection, currentColumn);
            if (indexes.length == 0) return IMPOSSIBLE;
            var curIdx = getShorterPairOfWords(indexes, wordsA, wordsB); // 4 (oyc y)

            String currentWord = fullPartInB ? wordsA[curIdx] : wordsB[curIdx]; // enj oyc
            intersection = currentWord.substring(intersection.length()); // o yc
            var union = findUnion(currentWord, !fullPartInB ? wordsA[curIdx] : wordsB[curIdx]);
            result.append();

            fullPartInB = !fullPartInB; // TODO depends on including (oyc y)
        } while (intersection.isEmpty());


        return IMPOSSIBLE;
    }

    private static int determineFirstIndex(String[] wordsA, String[] wordsB) {
        for (int idx = 0; idx < wordsA.length; idx++) {
            if (wordsB[idx].startsWith(wordsA[idx]) || wordsA[idx].startsWith(wordsB[idx])) {
                if (wordsA[idx].length() != wordsB[idx].length()) {
                    return idx;
                }
            }
        }
        return -1;
    }

    // may be improved by set unavailable indexes that already used in the past (array of bool, size the same as words)
    private static int[] findIndexByPrefix(String intersection, String[] words) {
        int[] result = new int[words.length / 2]; // admit that no more than half part of words will be found
        int resIdx = 0;
        for (int idx = 0; idx < words.length; idx++) {
            if (words[idx].startsWith(intersection)) {
                result[resIdx] = idx;
                resIdx++;
            }
        }
        return result;
    }

    private static int getShorterPairOfWords(int[] indexes, String[] wordsA, String[] wordsB) {
        return indexes[0]; // TODO
    }

    private static String findUnion(String currentWord, String s) {

    }
}
