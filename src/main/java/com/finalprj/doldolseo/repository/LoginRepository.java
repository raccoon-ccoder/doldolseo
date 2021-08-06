package com.finalprj.doldolseo.repository;

import com.finalprj.doldolseo.vo.MemberVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * 로그인,로그아웃 관련 Repository
 *
 * @Author 백정연
 * @Date 2021/08/03
 */

@Repository
public interface LoginRepository extends JpaRepository<MemberVO, String> {
    Optional<MemberVO> findByIdAndPassword(String id,String password);
}
