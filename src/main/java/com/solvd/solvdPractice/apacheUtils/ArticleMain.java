package com.solvd.solvdPractice.apacheUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ArticleMain {
    private static final Logger LOGGER = LogManager.getLogger(ArticleMain.class);
    /*TODO:
    - create a FIleManage class and split this code in 2 methods
    - configure the log4j2.xml with the different status and appenders (https://howtodoinjava.com/log4j2/multiple-appenders/)
    - use environment variables to paths.
     */
    public static void main(String[] args) {
        final File TEXT = new File("C:\\Users\\Usuario\\OneDrive\\Desktop\\solvdPractice\\src\\main\\resources\\article.txt");

        HashMap<String, Integer> wordOccurrences = new HashMap<>();
        String[] words = null;
        String text = null;
        LOGGER.info("Info");
        LOGGER.fatal("fatal");
        LOGGER.debug("debug");
        LOGGER.trace("trace");
        LOGGER.warn("warn");
        LOGGER.error("error");
        try {
            text = FileUtils.readFileToString(TEXT, StandardCharsets.UTF_8);

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        words = StringUtils.split(text, " ");

        for (String word : words) {
            if (wordOccurrences.containsKey(word)) {
                wordOccurrences.put(word, wordOccurrences.get(word) + 1);
            } else {
                wordOccurrences.put(word, 1);
            }
        }
        try {
            FileWriter answer = new FileWriter("C:\\Users\\Usuario\\OneDrive\\Desktop\\solvdPractice\\src\\main\\java\\com\\solvd\\solvdPractice\\apacheUtils\\answer.txt");
            for (String word : wordOccurrences.keySet()) {
                answer.write(word + " - " + wordOccurrences.get(word) + "\n");
            }
            answer.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
