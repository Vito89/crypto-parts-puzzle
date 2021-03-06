package com.vito.main;

import java.util.Arrays;

public class PuzzleProcessing {

    public static final String IMPOSSIBLE = "IMPOSSIBLE";

    public static String getSequence(String[] wordsA, String[] wordsB) {
        var result = new StringBuilder(50);

        var firstIdx = determineFirstIndex(wordsA, wordsB);
        if (firstIdx == -1) return IMPOSSIBLE;

        var firstWordA = wordsA[firstIdx];
        var firstWordB = wordsB[firstIdx];

        var analyseInA = firstWordA.length() < firstWordB.length();
        var intersection = analyseInA ?
            firstWordB.substring(firstWordA.length()) :
            firstWordA.substring(firstWordB.length());
        var union = analyseInA ?
            firstWordB.substring(0, firstWordB.length() - intersection.length()) :
            firstWordA.substring(0, firstWordA.length() - intersection.length());
        result.append(union);

        var alreadyProcessedRows = new boolean[wordsA.length];
        Arrays.fill(alreadyProcessedRows, false);
        alreadyProcessedRows[firstIdx] = true;
        do {
            if (intersection.isEmpty()) return result.toString();
            final String[] curWordsColumn = analyseInA ? wordsA : wordsB;
            var idxCandidates = findIndexByPrefix(intersection, curWordsColumn, alreadyProcessedRows);
            if (idxCandidates == null) return IMPOSSIBLE;

            var curIdx = idxCandidates.length == 1 ? idxCandidates[0] :
                getShorterWordPairIdx(idxCandidates, wordsA, wordsB);
            alreadyProcessedRows[curIdx] = true;
            var curWord = curWordsColumn[curIdx];
            var currentPrefix = curWord.substring(intersection.length());
            var curOppositeWord = !analyseInA ? wordsA[curIdx] : wordsB[curIdx];

            result.append(intersection);
            if (curOppositeWord.length() > currentPrefix.length()) {
                result.append(currentPrefix);
                intersection = curOppositeWord.substring(currentPrefix.length());
            } else {
                analyseInA = !analyseInA; // opposite column contains next intersect
                result.append(curOppositeWord);
                int interLength = intersection.length();
                intersection = curWord.substring(interLength + curOppositeWord.length());
            }
        } while (!intersection.isBlank());

        return result.toString();
    }

    private static int determineFirstIndex(String[] wordsA, String[] wordsB) {
        for (int idx = 0; idx < wordsA.length; idx++) {
            if (wordsB[idx].startsWith(wordsA[idx]) || wordsA[idx].startsWith(wordsB[idx])) {
                return idx;
            }
        }
        return -1;
    }

    private static int[] findIndexByPrefix(String intersection, String[] words, boolean[] alreadyProcessedRows) {
        int[] result = new int[words.length / 2]; // admit that no more than half part of words will be found
        Arrays.fill(result, -1);
        int resIdx = -1;
        for (int idx = 0; idx < words.length; idx++) {
            if (alreadyProcessedRows[idx]) continue;
            if (words[idx].startsWith(intersection)) {
                resIdx++;
                result[resIdx] = idx;
            }
        }
        return resIdx == -1 ? null : Arrays.stream(result).filter(p -> p != -1).toArray();
    }

    private static int getShorterWordPairIdx(int[] indexes, String[] wordsA, String[] wordsB) {
        int minLength = 101;
        int resIdx = -1;
        for (int idx : indexes) {
            var wrdA = wordsA[idx];
            var wrdB = wordsB[idx];
            int length = wrdA.length() + wrdB.length();
            if (minLength > length && (wrdA.contains(wrdB) || wrdB.contains(wrdA))) {
                minLength = length;
                resIdx = idx;
            }
        }
        return resIdx;
    }
}
