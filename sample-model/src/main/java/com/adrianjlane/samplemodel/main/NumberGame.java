package com.adrianjlane.samplemodel.main;

import java.io.InvalidClassException;
import java.nio.file.InvalidPathException;
import java.util.Arrays;
import java.util.List;

public class NumberGame {

    private int intProperty;
    private boolean boolProperty;
    private List<Integer> listProperty;
    private String stringsProperty[];

    public int getIntProperty() {
        return intProperty;
    }

    public void setIntProperty(int intProperty) {
        this.intProperty = intProperty;
    }

    public boolean isBoolProperty() {
        return boolProperty;
    }

    public void setBoolProperty(boolean boolProperty) {
        this.boolProperty = boolProperty;
    }



    public List<Integer> getListProperty() {
        return listProperty;
    }

    public void setListProperty(List<Integer> listProperty) {
        this.listProperty = listProperty;
    }

    public String[] getStringsProperty() {
        return stringsProperty;
    }

    public void setStringsProperty(String[] stringsProperty) {
        this.stringsProperty = stringsProperty;
    }

    public int returnNumber(int number) throws IllegalArgumentException {
        if (number <= 10 && number >= 1) {
            return number;
        } else {
            throw new IllegalArgumentException("Only 1-10 allowed");
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NumberGame numberGame = (NumberGame) o;
        return intProperty == numberGame.intProperty &&
                boolProperty == numberGame.boolProperty &&
                listProperty.equals(numberGame.listProperty) &&
                Arrays.equals(stringsProperty, numberGame.stringsProperty);
    }
}
