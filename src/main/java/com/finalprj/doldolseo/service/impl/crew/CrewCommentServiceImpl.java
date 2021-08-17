package com.finalprj.doldolseo.service.impl.crew;

import com.finalprj.doldolseo.domain.crew.CrewComment;
import com.finalprj.doldolseo.dto.crew.CrewCommentDTO;
import com.finalprj.doldolseo.repository.crew.CrewCommentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CrewCommentServiceImpl {
    @Autowired
    CrewCommentRepository repository;

    @Autowired
    ModelMapper modelMapper;

    public List<CrewCommentDTO> getComments(Long postNo) {
        List<CrewComment> commentEntity = repository.findAllByCrewPost_PostNo(postNo);
        List<CrewCommentDTO> comments = modelMapper.map(commentEntity, new TypeToken<List<CrewCommentDTO>>() {
        }.getType());

        return comments;
    }

    public CrewComment insertComment(CrewCommentDTO dto) {
        dto.setWDate(LocalDateTime.now());
        CrewComment comment = modelMapper.map(dto, CrewComment.class);

        return repository.save(comment);
    }

    @Transactional
    public void updateComment(Long commentNo, CrewCommentDTO dto){
        CrewComment comment = repository.findByCommentNo(commentNo);
        comment.setWDate(LocalDateTime.now());
        comment.setContent(dto.getContent());
    }

    public void deleteComment(Long commentNo) {
        repository.deleteById(commentNo);
    }

}
