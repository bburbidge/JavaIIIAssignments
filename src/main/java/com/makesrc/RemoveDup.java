package com.makesrc;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Created by Brent Burbidge on 5/1/2016. **/
public class RemoveDup {

    public static void main(String[] args) {
        RemoveDup(args[1]);
    }

    public static void RemoveDup(String input) {
        File f = new File(input);
        try {
            if (f.exists()) {
                RemoveDupWordsFromText(f);
            } else {
                System.out.println("File or Path doesn't exist");
            }
        } catch (IOException ex) {
            System.err.print(ex.getMessage());
        }

    }

    private static String RemoveDupWordFromString(String input) {
        String output = "";
        Pattern p = Pattern.compile("\\b(\\w+)\\b\\s+\\b\\1\\b", Pattern.MULTILINE + Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(input);
        while (m.find()) {
            if (output == "") {
                output = input.replaceFirst(m.group(), m.group(1));
            } else {
                output = output.replaceAll(m.group(), m.group(1));
            }
        }
        input = output;
        m = p.matcher(input);
        while (m.find()) {
            output = "";
            if (output == "") {
                output = input.replaceAll(m.group(), m.group(1));
            } else {
                output = output.replaceAll(m.group(), m.group(1));
            }
        }

        return output;
    }

    private static void RemoveDupWordsFromText(File dir) throws IOException {

        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; children != null && i < children.length; i++) {
                RemoveDupWordsFromText(new File(dir, children[i]));
            }
        }
        if (dir.isFile()) {
            if (dir.getName().trim().endsWith(".txt")) {
                List<String> contents;
                contents = TextUtil.readTextFile(dir.getAbsolutePath());
                if (!contents.isEmpty()) {
                    for (int i = 0; i < contents.size(); i++) {
                        contents.set(i, RemoveDupWordFromString(contents.get(i)));
                    }

                    TextUtil.writeTextFile(contents, dir.getAbsolutePath());
                }
            } else {
                System.out.println(dir + " Not a text file");
            }
        }
    }
}
