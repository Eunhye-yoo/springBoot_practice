package com.example.ch2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BoardRepository boardRepository;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count() > 1) return;

        User aaa = new User();
        aaa.setFirstName("에이");
        userRepository.save(aaa);

        User bbb = new User();
        bbb.setFirstName("비");
        userRepository.save(bbb);

        for(int i = 1; i <= 36; i++){
            Board board = new Board();
            board.setTitle("title"+i);
            board.setContent("content"+i);
            board.setUser(i % 3 == 0 ? aaa : bbb);
            board.setViewCnt(0L);
            boardRepository.save(board);
        }
        System.out.println(">>> 초기 데이터 생성 완료");
    }
}
