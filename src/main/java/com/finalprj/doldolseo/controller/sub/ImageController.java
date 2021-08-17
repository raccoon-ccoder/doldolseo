package com.finalprj.doldolseo.controller.sub;

import com.finalprj.doldolseo.service.sub.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {

    @Autowired
    ImageService service;


    @PostMapping("/image")
    public ResponseEntity<?> imageUpload(@RequestParam("file") MultipartFile file) {
        try {
            String filePath = service.store(file);
            return ResponseEntity.ok().body(filePath);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/image/crew")
    public ResponseEntity<?> imageUploadCrew(@RequestParam("file") MultipartFile file) {
        try {
            String filePath = service.store_crew(file);
            return ResponseEntity.ok().body(filePath);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
