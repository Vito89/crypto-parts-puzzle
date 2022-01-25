package com.vito.main;

import java.util.Arrays;

public class PuzzleProcessing {

    public static final String IMPOSSIBLE = "IMPOSSIBLE";

    public static String getSequence(String[] wordsA, String[] wordsB) {
        var result = new StringBuilder(50);

        var firstIdx = determineFirstIndex(wordsA, wordsB);
        if (firstIdx == -1) return IMPOSSIBLE;

        var fWordA = wordsA[firstIdx];
        var fWordB = wordsB[firstIdx];

        var analyseInA = fWordA.length() < fWordB.length();
        var intersection = analyseInA ? fWordB.substring(fWordA.length()) : fWordA.substring(fWordB.length()); // e
        var union = analyseInA ? fWordB.substring(0, fWordB.length() - intersection.length()) :
            fWordA.substring(0, fWordA.length() - intersection.length());
        result.append(union);
        do {
            if (intersection.isEmpty()) return result.toString();
            final String[] curWordsColumn = analyseInA ? wordsA : wordsB;
            var idxCandidates = findIndexByPrefix(intersection, curWordsColumn);
            if (idxCandidates == null) return IMPOSSIBLE;

            var curIdx = idxCandidates.length == 1 ? idxCandidates[0] :
                getShorterWordPairIdx(idxCandidates, wordsA, wordsB); // 4 (oyc y)
            var curWord = curWordsColumn[curIdx]; // enj oyc
            var currentPrefix = curWord.substring(intersection.length()); // nj      //o yc
            var curOppositeWord = !analyseInA ? wordsA[curIdx] : wordsB[curIdx]; // enj oyc

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
        Arrays.fill(result, -1);
        int resIdx = -1;
        for (int idx = 0; idx < words.length; idx++) {
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
            int length = wordsA[idx].length() + wordsB[idx].length();
            if (minLength > length) {
                minLength = length;
                resIdx = idx;
            }
        }
        return resIdx;
    }
}
