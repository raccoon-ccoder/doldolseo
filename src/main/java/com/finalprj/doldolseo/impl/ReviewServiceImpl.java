package com.finalprj.doldolseo.impl;

import com.finalprj.doldolseo.dto.ReviewDTO;
import com.finalprj.doldolseo.repository.ReviewRepository;
import com.finalprj.doldolseo.entity.Review;
import com.finalprj.doldolseo.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Page<ReviewDTO> getReviewList(Pageable pageable) {

        Page<Review> entityPage = repository.findAll(pageable);
        Page<ReviewDTO> reviewList = modelMapper.map(entityPage, new TypeToken<Page<ReviewDTO>>() {
        }.getType());

        return reviewList;
    }

    @Override
    public Page<ReviewDTO> getReviewListByArea(Integer areaNo, Pageable pageable) {

        Page<Review> entityPage = repository.findAllByAreaNo(areaNo, pageable);
        Page<ReviewDTO> reviewList = modelMapper.map(entityPage, new TypeToken<Page<ReviewDTO>>() {
        }.getType());

        return reviewList;
    }

}
