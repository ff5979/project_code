package com.example.service.qboard;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.dto.qboard.QboardDTO;

@Service
public interface QboardService {
    public long qboardSeq();

    //질문작성
    public int insertQboard(QboardDTO board);
    //질문전체목록
    public List<QboardDTO> selectList();
    //아이템1개 조회
    public QboardDTO selectOne(Long no);
    //답글작성
    public int insertRep(QboardDTO board);
    //검색어 페이지네이션 포함 글개수(제목)
    public Long countBoardListLikePagenation(Map<String, Object> map);
    //페이지네이션포함 글목록
    public List<QboardDTO> qboardList(Map<String, Object> map);
    //조회수증가
    public long updatedHit(long no);
    //게시글수정
    public int updateQboard(QboardDTO board);
    //게시글 삭제+ 답글까지 모두삭제됨
    public int deleteQboard(Long grp);
    //문의 게시판 수정 시 관련 게시글 비공개 전환     
    public int updateQboardByGRP( Map<String, Object> map );
}
