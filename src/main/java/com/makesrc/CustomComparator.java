package com.makesrc;

import java.util.Comparator;

/** Created by Brent Burbidge on 4/25/2016. **/
class String1Comparator implements Comparator<String> {
    public int compare(String s1, String s2) {
        String[] st1 = s1.split(",");
        String[] st2 = s2.split(",");

        String stringInput1 = st1[0].toUpperCase();
        String stringInput2 = st1[1].toUpperCase();
        String stringInput3 = st2[0].toUpperCase();
        String stringInput4 = st2[1].toUpperCase();
        if (!(stringInput1.equals(stringInput3)))
            return stringInput1.compareTo(stringInput3);
        else
            return stringInput2.compareTo(stringInput4);
    }


}

class String2Comparator implements Comparator<String> {
    public int compare(String s1, String s2) {
        String[] st1 = s1.split(",");
        String[] st2 = s2.split(",");

        String stringInput1 = st1[0].toUpperCase();
        String stringInput2 = st1[1].toUpperCase();
        String stringInput3 = st2[0].toUpperCase();
        String stringInput4 = st2[1].toUpperCase();
        if (!(stringInput2.equals(stringInput4)))
            return stringInput2.compareTo(stringInput4);
        else
            return stringInput1.compareTo(stringInput3);
    }


}
