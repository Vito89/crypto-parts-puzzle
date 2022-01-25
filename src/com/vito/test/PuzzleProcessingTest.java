package com.vito.test;

import com.vito.main.PuzzleProcessing;
import org.junit.jupiter.api.Test;

import static com.vito.main.PuzzleProcessing.IMPOSSIBLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PuzzleProcessingTest {

    @Test
    void getSequenceEmptyContentTest() {
        String sequence = PuzzleProcessing.getSequence(new String[0], new String[0]);
        assertEquals(IMPOSSIBLE, sequence);
    }

    @Test
    void getSequenceShortContentTest() {
        var expected = "abcd";
        var sequence = PuzzleProcessing.getSequence(
            new String[]{"efgh", "d", "abc"},
            new String[]{"efgh", "cd", "ab"}
        );
        assertEquals("abcd", sequence);
    }
}
