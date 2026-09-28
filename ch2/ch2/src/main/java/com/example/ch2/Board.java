package com.example.ch2;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
public class Board {
    // 관계 정하기 전 코드
//    @Id
//    // IDENTITY : DB에서 AutoIncreament와 동일하게 자동 번호 증가
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long bno;
//    private String title;
//    private String writer;
//    private String content;
//    private Long viewCnt;
//    @CreationTimestamp
//    private Date inDate;
//    @UpdateTimestamp
//    private Date upDate;

    @Id
    // IDENTITY : DB에서 AutoIncreament와 동일하게 자동 번호 증가
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    private String title;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String content;
    private Long viewCnt;
    @CreationTimestamp
    private Date inDate;
    @UpdateTimestamp
    private Date upDate;


    // ★ @Data -> toString 자동 생성
    // 양방향 상태에서 System.out.println(boards) -> StackOverflowError 발생
    // (Board.toString -> User.toString -> boards의 Board.toString -> 무한 반복)

    @Override
    public String toString() {
        return "Board{" +
                "bno=" + bno +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", viewCnt=" + viewCnt +
                ", inDate=" + inDate +
                ", upDate=" + upDate +
                '}';
    }
}
