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
    void getSequenceAbcdContentTest() {
        var expected = "abcd";
        var sequence = PuzzleProcessing.getSequence(
            new String[]{"efgh", "d", "abc"},
            new String[]{"efgh", "cd", "ab"}
        );
        assertEquals(expected, sequence);
    }

    @Test
    void getSequenceAlanContentTest() {
        var expected = "dearalanhowareyou";
        var sequence = PuzzleProcessing.getSequence(
            new String[]{"are", "you", "how", "alan", "dear"},
            new String[]{"yo", "u", "nhoware", "arala", "de"}
        );
        assertEquals(expected, sequence);
    }

    @Test
    void shouldReturnImpossibleGetSequenceTest() {
        var sequence = PuzzleProcessing.getSequence(
            new String[]{"a", "b", "c"},
            new String[]{"ab", "bb", "cc"}
        );
        assertEquals(IMPOSSIBLE, sequence);
    }
}
