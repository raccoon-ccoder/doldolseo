package com.finalprj.doldolseo.controller;

import com.finalprj.doldolseo.dto.TestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.security.krb5.internal.Ticket;

@RestController
public class TestRestController {

    @GetMapping(value = "/sample/{cat}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    public TestDTO getText(@PathVariable("cat") String cat) {
        System.out.println("cat : "+cat);
        return new TestDTO("kki7823","김경일","kjd55399@naver.com");
    }

    @GetMapping(value = "/check", params = {"id", "name"})
    public ResponseEntity<TestDTO> check(String id, String  name){

        TestDTO dto = new TestDTO("kjd55399", "김경일2","kki7823@gamail.com");
        ResponseEntity<TestDTO> result = null;

        if(!(id.equals("kjd55399"))){
            result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(dto);
        }else {
            result = ResponseEntity.status(HttpStatus.OK).body(dto);
        }

        return result;
    }

    @PostMapping("/dto")
    public Ticket convert(@RequestBody Ticket ticket){
        return ticket;
    }
}
