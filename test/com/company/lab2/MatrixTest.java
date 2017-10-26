package com.company.lab2;

import static org.junit.Assert.*;

public class MatrixTest {
    @org.junit.Test
    public void getRows() throws Exception {
        Matrix m = new Matrix(2,3);
        assertEquals(m.getRows(),2);
        assertNotEquals(m.getRows(),3);
    }

    @org.junit.Test
    public void getCols() throws Exception {
        Matrix m = new Matrix(2,3);
        assertEquals(m.getCols(),3);
        assertNotEquals(m.getCols(),2);
    }

    @org.junit.Test
    public void getData() throws Exception {
    }

    @org.junit.Test
    public void setRows() throws Exception {
    }

    @org.junit.Test
    public void setCols() throws Exception {
    }

    @org.junit.Test
    public void setData() throws Exception {
    }

    @org.junit.Test
    public void asArray() throws Exception {
    }

    @org.junit.Test
    public void get() throws Exception {
    }

    @org.junit.Test
    public void set() throws Exception {
    }

    @org.junit.Test
    public void testToString() throws Exception {
    }

    @org.junit.Test
    public void reshape() throws Exception {
    }

    @org.junit.Test
    public void shape() throws Exception {
    }

    @org.junit.Test
    public void add() throws Exception {
    }

    @org.junit.Test
    public void sub() throws Exception {
    }

    @org.junit.Test
    public void mul() throws Exception {
    }

    @org.junit.Test
    public void div() throws Exception {
    }

    @org.junit.Test
    public void dot() throws Exception {
    }

    @org.junit.Test
    public void frobenius() throws Exception {
    }

    @org.junit.Test
    public void add1() throws Exception {
    }

    @org.junit.Test
    public void sub1() throws Exception {
    }

    @org.junit.Test
    public void mul1() throws Exception {
    }

    @org.junit.Test
    public void div1() throws Exception {
    }

    @org.junit.Test
    public void eye() throws Exception {
    }

    @org.junit.Test
    public void random() throws Exception {
    }

    @org.junit.Test
    public void main() throws Exception {
    }

}