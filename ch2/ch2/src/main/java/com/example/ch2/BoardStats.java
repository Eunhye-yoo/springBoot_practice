package com.example.ch2;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
// @Entity 아님! 테이블과 매핑 X , 조회 결과 담는 객체
@AllArgsConstructor // QueryDSL이 사용할 수 있도록 생성자 추가
public class BoardStats {
    private Long writer; // 작성자 id
    private Long count; // 글 수
    private Long totalView; // 총 조회수
    private Double avgView; // 평균 조회수
}
