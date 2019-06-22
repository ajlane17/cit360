package com.adrianjlane.samplemodel.main;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.fail;

public class NumberGameTest {
    @Test
    public void validateReturnNumber() {
        NumberGame numberGame = new NumberGame();

        for (int i = 1; i < 10; i++) {
            int value = numberGame.returnNumber(i);
            Assert.assertEquals(value, i);
        }
    }

    @Test
    public void validateReturnNumberError() {

        try {
            NumberGame numberGame = new NumberGame();
            numberGame.returnNumber(11);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Only 1-10 allowed"));
        }

    }

    @Test
    public void validateNumgerGameObjectsEqual() {
        NumberGame numberGame1 = new NumberGame();
        NumberGame numberGame2 = new NumberGame();

        numberGame1.setIntProperty(1);
        numberGame2.setIntProperty(1);

        numberGame1.setBoolProperty(true);
        numberGame2.setBoolProperty(true);

        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5, 6);

        numberGame1.setListProperty(list1);
        numberGame2.setListProperty(list2);

        Assert.assertEquals(numberGame1, numberGame2);
    }

    @Test
    public void validateNumgerGameObjectsNotEqual() {
        NumberGame numberGame1 = new NumberGame();
        NumberGame numberGame2 = new NumberGame();

        numberGame1.setIntProperty(1);
        numberGame2.setIntProperty(1);

        numberGame1.setBoolProperty(true);
        numberGame2.setBoolProperty(true);

        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5, 7);

        numberGame1.setListProperty(list1);
        numberGame2.setListProperty(list2);

        Assert.assertNotEquals(numberGame1, numberGame2);
    }

    @Test
    public void validateNumberGameNotSame() {
        NumberGame numberGame1 = new NumberGame();
        NumberGame numberGame2 = new NumberGame();

        numberGame1.setIntProperty(1);
        numberGame2.setIntProperty(1);

        numberGame1.setBoolProperty(true);
        numberGame2.setBoolProperty(true);

        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5, 6);

        numberGame1.setListProperty(list1);
        numberGame2.setListProperty(list2);

        String strings1[] = {"A", "B", "C"};
        String strings2[] = {"A", "B", "C"};

        numberGame1.setStringsProperty(strings1);
        numberGame2.setStringsProperty(strings2);

        Assert.assertNotSame(numberGame1, numberGame2);
    }

    @Test
    public void validateNumberGameSame() {
        NumberGame numberGame1 = new NumberGame();
        NumberGame numberGame2 = numberGame1;

        numberGame1.setIntProperty(1);

        numberGame1.setBoolProperty(true);

        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6);

        numberGame1.setListProperty(list1);

        Assert.assertSame(numberGame1, numberGame2);
    }

    @Test
    public void validateStringArraysEqual() {
        NumberGame numberGame1 = new NumberGame();
        NumberGame numberGame2 = new NumberGame();

        String strings1[] = {"A", "B", "C"};
        String strings2[] = {"A", "B", "C"};

        numberGame1.setStringsProperty(strings1);
        numberGame2.setStringsProperty(strings2);

        Assert.assertArrayEquals(numberGame1.getStringsProperty(), numberGame2.getStringsProperty());
    }

    @Test
    public void validateListPropertyIsNull() {
        NumberGame numberGame = new NumberGame();

        Assert.assertNull(numberGame.getListProperty());
    }

    @Test
    public void validateListPropertyNotNull() {
        NumberGame numberGame = new NumberGame();

        numberGame.setListProperty(new ArrayList<>());

        Assert.assertNotNull(numberGame.getListProperty());
    }

    @Test
    public void validateBoolPropertyTrue() {
        NumberGame numberGame = new NumberGame();

        numberGame.setBoolProperty(true);

        Assert.assertTrue(numberGame.isBoolProperty());
    }

    @Test
    public void validateBoolPropertyFalse() {
        NumberGame numberGame = new NumberGame();

        numberGame.setBoolProperty(false);

        Assert.assertFalse(numberGame.isBoolProperty());
    }
}
