package com.vito.test;

import com.vito.main.Runner;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RunnerTest {

    @Test
    void processPrimeFactorsEqualsExContentTest() {
        var input = """
            5
            are yo
            you u
            how nhoware
            alan arala
            dear de
            8
            i ie
            ing ding
            resp orres
            ond pon
            oyc y
            hello hi
            enj njo
            or c
            3
            efgh efgh
            d cd
            abc ab
            3
            a ab
            b bb
            c cc""";
        var expected = """
            Case 1: dearalanhowareyou
            Case 2: ienjoycorresponding
            Case 3: abcd
            Case 4: IMPOSSIBLE""";
        InputStream sysInBackup = System.in; // backup to restore
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        assertEquals(expected, Runner.blockProcess(new Scanner(System.in)));
        System.setIn(sysInBackup);
    }

    @Test
    void blockProcessEmptyInputTest() {
        InputStream sysInBackup = System.in;
        System.setIn(new ByteArrayInputStream("".getBytes()));

        assertThrows(NoSuchElementException.class, () -> Runner.blockProcess(new Scanner(System.in)));
        System.setIn(sysInBackup);
    }

    @Test
    void blockProcessDifferentContentLengthTest() {
        InputStream sysInBackup = System.in;
        System.setIn(new ByteArrayInputStream("1234567\n".getBytes()));

        assertThrows(IllegalStateException.class, () -> Runner.blockProcess(new Scanner(System.in)));
        System.setIn(sysInBackup);
    }
}
