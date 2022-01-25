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
        var intersection = fullPartInB ? fWordB.substring(fWordA.length()) : fWordA.substring(fWordB.length()); // e
        var union = fullPartInB ? fWordB.substring(0, fWordB.length() - intersection.length()) :
            fWordA.substring(0, fWordA.length() - intersection.length());
        result.append(union);
        do {
            if (intersection.isEmpty()) return result.toString();
            final String[] curWordsColumn = fullPartInB ? wordsA : wordsB;
            var idxCandidates = findIndexByPrefix(intersection, curWordsColumn);
            if (idxCandidates.length == 0) return IMPOSSIBLE;

            var curIdx = getShorterWordPairIdx(idxCandidates, wordsA, wordsB); // 4 (oyc y)
            var curWord = curWordsColumn[curIdx]; // enj oyc
            var currentPrefix = curWord.substring(intersection.length()); // nj      //o yc
            var curOppositeWord = !fullPartInB ? wordsA[curIdx] : wordsB[curIdx]; // enj oyc

            if (curOppositeWord.length() > currentPrefix.length()) {
                fullPartInB = !fullPartInB; // opposite column contains next intersect
                result.append(intersection);
                result.append(currentPrefix);
                intersection = curOppositeWord.substring(currentPrefix.length());
            } else {
                result.append(intersection);
                result.append(curOppositeWord);
                int interLength = intersection.length();
                intersection = curWord.substring(interLength + curOppositeWord.length());
            }
        } while (intersection.isEmpty());

        return "ERROR";
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

    private static int getShorterWordPairIdx(int[] indexes, String[] wordsA, String[] wordsB) {
        return indexes[0]; // TODO
    }
}
