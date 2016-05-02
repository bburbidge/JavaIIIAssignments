package com.makesrc;


import org.junit.Assert;
import org.junit.Test;

/** Created by Brent Burbidge on 4/25/2016. **/
public class CleanAndSortAddressBookTest {

    @Test
    public void CleanAndSort() {
        try {
            CleanAndSortAddressBook.CleanAndSortAddressBook("addressbooktest.txt", "testcleanbook.txt");
            Assert.assertTrue(true);
        } catch (Exception ex) {
            Assert.assertTrue(false);
        }
    }

}
