package com.finalprj.doldolseo.repository;

import com.finalprj.doldolseo.entity.MemberVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/*
 * 멤버 관련 Repository
 *
 * @Author 백정연
 * @Date 2021/08/03
 */

@Repository
public interface MemberRepository extends JpaRepository<MemberVO, String> {
    Optional<MemberVO> findByNickname(String nickname);
    Optional<MemberVO> findById(String id);
}
