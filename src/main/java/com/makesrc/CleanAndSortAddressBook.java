package com.makesrc;

import java.io.*;
import java.util.List;

/** Created by Brent Burbidge on 4/23/2016. **/
public class CleanAndSortAddressBook {


    //region Properties
    private List<String> addresses;
    //endregion

    //region Assignment 2 - Clean and Sort Addressbook
    public static void CleanAndSortAddressBook(String oldFile, String newFile) {
        File f = new File(oldFile);
        if (f.exists()) {
            try {
                CleanAndSortAddressBook adr = new CleanAndSortAddressBook();
                adr.LoadAddressesFromText(f);
                adr.PrintAddresses();
                adr.WriteAddressesToText(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            System.err.print("Input file does not exist");
        }
    }

    //region Property Getters and Setters
    private List<String> getAddresses() {
        return this.addresses;
    }

    //endregion

    private void setAddresses(List<String> str) {
        this.addresses = str;
    }

    private void CleanAndSortAddressBook() {
    }

    private void LoadAddressesFromText(File f) throws IOException, ClassNotFoundException {
        List<String> addresses;
        TextUtil tx = new TextUtil();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)))) {
            addresses = tx.CleanFile(br, ",", ",");
            setAddresses(addresses);
        }
    }

    private void PrintAddresses() {
        List<String> addr = getAddresses();
        for (int i = 0; i < addr.size(); i++) {
            System.out.println(addr.get(i));
        }
    }

    private void WriteAddressesToText(String fileName) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)))) {
            List<String> addr = getAddresses();
            for (int i = 0; i < addr.size(); i++) {
                bw.write(addr.get(i) + "\n");
            }
        }
    }
    //endregion

    //    public static void main(String[] args)
    //    {
    //        CleanAndSortAddressBook(args[0],args[1]);
    //    }

}

