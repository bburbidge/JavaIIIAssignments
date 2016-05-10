package com.makesrc;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;


/** Created by Brent Burbidge on 4/23/2016. Sampled from Kent Yang **/
class TextUtil {

    private static final String2Comparator STRING_2_COMPARATOR = new String2Comparator();

    static void writeTextFile(List<String> list, String outputFile)
            throws IOException {
        try (Writer wout =
                     new OutputStreamWriter(
                             new FileOutputStream(outputFile), "UTF-8")) {
            BufferedWriter out = new BufferedWriter(wout);
            for (String str : list) {
                out.write(str);
            }
            out.close();
        }
    }

    static List<String> readTextFile(String inputFile) throws IOException {
        List<String> list;

        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(inputFile), "UTF-8"))) {

            list = new ArrayList<>();
            for (String line; (line = in.readLine()) != null; ) {
                list.add(line);
            }

        }
        return list;
    }

    protected static String encodeFile(File f) throws IOException, NullPointerException {
        BASE64Encoder be = new BASE64Encoder();
        String image = null;
        BufferedImage buffImage = ImageIO.read(f);

        if (buffImage != null) {
            try (ByteArrayOutputStream os = new
                    ByteArrayOutputStream()) {
                ImageIO.write(buffImage, "png", os);
                byte[] data = os.toByteArray();
                image = be.encode(data);
            }


        }
        return new String(image.getBytes(), "UTF-8");
    }

    ///From class
    static List<File> generateFileList(String path, Pattern textFileMatchPattern) {
        List<File> fileList = new ArrayList<>();
        File dir = new File(path);
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; children != null && i < children.length; i++) {
                generateFileList(dir.getAbsolutePath(), textFileMatchPattern);
            }
        }
        if (dir.isFile()) {
            if (textFileMatchPattern.matcher(dir.getName()).find()) {
                fileList.add(dir);
            }

        }


        return fileList;

    }

    ///From class
    static String readTextFileToString(File f) throws IOException {
        String str;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(f), "UTF-8"))) {
            str = in.readLine();
        }
        return str;
    }

    ///From class
    static void writeStringToTextFile(String input, File f) throws IOException {
        try (FileWriter fw = new FileWriter(f)) {
            fw.write(input);
        }
    }

    public List<String> CleanFile(BufferedReader br, String oldDel, String newDel) throws IOException {
        List<String> cleanList = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            String str = DelimitString(Arrays.asList(line.split(oldDel)), newDel);
            if (!cleanList.contains(str))
                cleanList.add(str);
        }
        Collections.sort(cleanList, STRING_2_COMPARATOR);

        return cleanList;
    }

    private String DelimitString(List<String> list, String del) {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                str = str + list.get(i).trim();
            } else
                str = str + list.get(i).trim() + del;
        }
        return str;
    }

    private void TextUtil() {
    }
}
