package com.finalprj.doldolseo;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestMain {
    public static void main(String[] args) {
        File saveFile = new File("/Users/gimgyeong-il/IdeaProjects/doldolseo/src/main/resources/static/_image/review/90");
        File[] fileLists = saveFile.listFiles();

        File[] fileNameList = saveFile.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return !name.equals("course.png");
            }
        });

        Arrays.stream(fileLists).forEach(file -> System.out.println(file.getName()));
        Arrays.stream(fileNameList).forEach(file -> System.out.println(file.getName()));

    }
}
