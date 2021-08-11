package com.finalprj.doldolseo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestMain {
    public static void main(String[] args) {
        LocalDateTime time = LocalDateTime.now();

        SimpleDateFormat sDate2 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        System.out.println(time.format( DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(time.format( DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm ")));
    }
}
