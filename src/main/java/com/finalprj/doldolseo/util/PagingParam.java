package com.finalprj.doldolseo.util;

import org.springframework.data.domain.Page;

/*
 * 페이징 처리 유틸
 * 페이지블록 (한번에 출력할 페이지 갯수)과 Page 객체를 입력 받아, 페이징처리에 필요한 변수 계산
 *
 * @Author 김경일
 * @Date 2021/08/06
 */
public class PagingParam {
    private int START_BLOCK_PAGE; //시작 페이지 번호
    private int END_BLOCK_PAGE; //마지막 페이지 번호
    private int PAGE_NUMBER;
    private int TOTAL_PAGES;

    public PagingParam(int pageBlock, Page page) {
        this.PAGE_NUMBER = page.getPageable().getPageNumber();  //현재 페이지번호
        this.TOTAL_PAGES = page.getTotalPages(); //총 페이지 수
        this.START_BLOCK_PAGE = ((PAGE_NUMBER) / pageBlock) * pageBlock + 1;
        this.END_BLOCK_PAGE = (PAGE_NUMBER / pageBlock == TOTAL_PAGES / pageBlock) ? TOTAL_PAGES : START_BLOCK_PAGE + pageBlock - 1; //현재페이지가 마지막 블록이면 마지막페이지 = 전체 페이지 수
    }

    public int getStartBlockPage() {
        return START_BLOCK_PAGE;
    }

    public int getEndBlockPage() {
        return END_BLOCK_PAGE;
    }

    public int getPageNumber() {
        return PAGE_NUMBER;
    }

    public int getTotalPages() {
        return TOTAL_PAGES;
    }
}

