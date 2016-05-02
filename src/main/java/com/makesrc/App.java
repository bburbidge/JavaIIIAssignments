package com.makesrc;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Hello world!
 */
public class App {
    private static final String1Comparator STRING_1_COMPARATOR = new String1Comparator();

    public static void main(String[] args) {
        System.out.print("Hello World");
    }

    //region Test Person write & read functions provided from teacher
    private static void personObjectExample() {
        try {
            writePersonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            readPersonObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void writePersonObject() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream("data.zip")))) {
            Person p = new Person();
            p.setFirstName("Brent");
            p.setLastName("Burbidge");
            p.setEmail("Brent.M.Burbidge@gmail.com");
            p.setPhone("619-876-9097");

            List<Person> lp = new ArrayList<>();
            lp.add(p);
            lp.add(p);
            lp.add(p);

            oos.writeObject(lp);
        }

    }

    private static void readPersonObject() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new GZIPInputStream(new FileInputStream("data.zip")))) {
            List<Person> lp = (List<Person>) ois.readObject();
            System.out.println(lp);
        }

    }
    //endregion

    //region Examples
    private static void findDirectoriesNoRecursion(String[] args) {
        if (args.length == 0)
            args = new String[]{".."};

        List<String> nextDir = new ArrayList<String>();
        nextDir.add(args[0]);

        try {

            while (nextDir.size() > 0) {
                File pathName = new File(nextDir.get(0));
                String[] fileNames = pathName.list();

                for (int i = 0; i < fileNames.length; i++) {
                    File f = new File(pathName.getPath(), fileNames[i]);
                    if (f.isDirectory()) {
                        //                        System.out.println(f.getCanonicalPath());
                        nextDir.add(f.getPath());
                    }
                }
                nextDir.remove(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void binaryVsText() {
        try {
            DataOutputStream dos =
                    new DataOutputStream(
                            new FileOutputStream("number.bin"));
            dos.writeInt(51966);
            dos.close();

            PrintWriter pw =
                    new PrintWriter(
                            new BufferedWriter(
                                    new FileWriter("number.txt")));

            pw.print(51966);
            pw.close();

            File file = new File("number.bin");
            System.out.println("Binary file size: " + file.length());
            file = new File("number.txt");
            System.out.println("Text file size: " + file.length());

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private static void sortStrings(String[] args) {
        try { // Step 1: Read Text From File
            List<String> textData = TextUtil.readTextFile(args[0]);

            // Step 2: Process Text Data
            Collections.sort(textData, STRING_1_COMPARATOR);

            // Step 3: Write Text Data toFile
            TextUtil.writeTextFile(textData, args[1]);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    //endregion

    //region Assignment 1 - Generate VCard
    private static void Assignment1() {
        File avatar = new File("daEAZHH.png");
        VCard me = new VCard();
        me.setFirstName("Brent");
        me.setLastName("Burbidge");
        me.setEmail("Brent.M.Burbidge@gmail.com");
        me.setPhone("619-876-9097");
        me.setAvatar(avatar.getAbsolutePath());
        me.VCardGenerate();
    }
    //endregion


}