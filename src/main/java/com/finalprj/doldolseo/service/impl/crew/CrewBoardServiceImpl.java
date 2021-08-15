package com.finalprj.doldolseo.service.impl.crew;

import com.finalprj.doldolseo.repository.crew.CrewBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrewBoardServiceImpl {

    @Autowired
    CrewBoardRepository repository;
}
