package com.example.sycompany.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiplierTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void multiply() {
        Multiplier multiplier = new Multiplier();
        assertEquals(multiplier.Multiply(8,9),72);
    }

    @Test
    void testMultiply() {
        Multiplier multiplier = new Multiplier();
        assertEquals(multiplier.Multiply(2,3,4),30);
    }
}