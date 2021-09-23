package com.finalprj.doldolseo;

import com.fasterxml.jackson.databind.ObjectMapper;
import oracle.sql.CHAR;
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

        String ss = "y";
        Character ss2 = 'y';

        System.out.println(ss2.toString() == ss);


    }
}
