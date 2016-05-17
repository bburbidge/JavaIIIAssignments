package com.makesrc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Created by Brent Burbidge on 5/1/2016. **/
public class RemoveDup {

    ///From class
    private static final String TEXT_FILE_REGEX_STR = "[^*?\"<>]+\\.txt$";
    private static final Pattern TEXT_FILE_MATCH_PATTERN = Pattern.compile(TEXT_FILE_REGEX_STR);
    private static final Pattern DUPLICATE_PATTERN = Pattern.compile("((\\b[a-zA-Z0-9]+\\b)(\\S?)" +
            "(\\s+))((\\2\\S?\\s+)| (\\2\\S?$))+", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
    private static final Matcher DUPLICATE_MATCHER = DUPLICATE_PATTERN.matcher("");

    ///Main method
    public static void main(String[] args) {
        RemoveDup(args);
    }

    ///From class
    public static void RemoveDup(String[] args) {
        try {
            List<File> fileList = getFileList(args);
            int totalFilesProcessed = processFiles(fileList);
            System.out.println("Total Text Files Processed: " + totalFilesProcessed);
        } catch (Throwable e) {
            System.err.println("Usage: java RemoveDup <file.txt | startingDir>");
        }
    }

    ///From class
    private static int processFiles(List<File> fileList) {
        int i = 0;
        for (File f : fileList) {
            try {
                System.out.println("Processing: " + f);

                String s = TextUtil.readTextFileToString(f);

                TextUtil.writeStringToTextFile(DUPLICATE_MATCHER.reset(s).replaceAll("$2$3$4"), f);
                i++;
            } catch (IOException x) {
                System.err.println("Failed to process: " + f + " due to " + x);
            }
        }
        return i;
    }

    ///From class
    private static List<File> getFileList(String[] args) {
        if (args.length == 0)
            throw new IllegalArgumentException("Invalid command line argument!");

        File f = new File(args[0]);
        List<File> fileList = null;

        if (f.isDirectory()) {
            fileList = TextUtil.generateFileList(f.getPath(), TEXT_FILE_MATCH_PATTERN);
        } else {
            if (f.getName().matches(TEXT_FILE_REGEX_STR)) {
                fileList = new ArrayList<File>();
                fileList.add(f);
            }
        }

        return fileList;

    }

}
