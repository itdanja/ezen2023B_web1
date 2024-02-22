package example.day10._1example;

public class Example1 {
    public static void main(String[] args) {

        // 1. 계산기 객체 1개 생성
        Calculator calculator = new Calculator();
            // 두 개이상의 스레드가 하나의 메모리를 공유해서 사용

        // 2.
        User1Thread user1Thread = new User1Thread();
        user1Thread.setCalculator( calculator );
        user1Thread.start(); // 작업스레드run 실행, 계산기 100 저장

        // 3.
        User2Thread user2Thread = new User2Thread();
        user2Thread.setCalculator( calculator );
        user2Thread.start(); // 작업스레드run 실행, 계산기 50 저장
    }
}
