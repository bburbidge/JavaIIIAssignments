package com.makesrc;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

/** Created by Brent Burbidge on 5/1/2016. **/
public class RemoveDupTest {

    @Test
    public void RemoveDupTest()
    {
        try {
            String input = "This is is a test test of the dup 44 44 Test\n" + "This is another line line for test";
            String[] fileName = new String[1];
            fileName[0] = "UnitTestRemoveDup.txt";
            TextUtil.writeStringToTextFile(input, new File(fileName[0]));
            RemoveDup.RemoveDup(fileName);

            List<String> results = TextUtil.readTextFile(fileName[0]);
            int match =input.compareToIgnoreCase(results.get(0));
            Assert.assertFalse(match==1);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
