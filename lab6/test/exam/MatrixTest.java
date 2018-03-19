package exam;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class MatrixTest {
    @Test
    public void getTransposition() throws Exception {
        Random randomInt = new Random();
        Matrix mat;
        try {
            mat = Matrix.random(randomInt.nextInt(100) + 1, randomInt.nextInt(100) + 1); // 1 i 2
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }

        assertEquals(mat.shape()[0], mat.getTransposition().shape()[1]); //3  a
        assertEquals(mat.shape()[1], mat.getTransposition().shape()[0]); // 3 b
        assertEquals(mat.frobenius(), mat.getTransposition().frobenius(), 0.00001); // 4
        assertEquals(mat.toString(), mat.getTransposition().getTransposition().toString()); // 5
    }
}