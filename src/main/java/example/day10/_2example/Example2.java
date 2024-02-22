package example.day10._2example;

public class Example2 {

    public static void main(String[] args) {

        // 1. 공유객체 1개 생성
        WorkObject workObject = new WorkObject();

        // 2. 각 스레드 2개 생성
        ThreadA threadA = new ThreadA(workObject);
        ThreadB threadB = new ThreadB(workObject);
        ThreadC threadC = new ThreadC(workObject);

        threadA.start();
        threadB.start();
        threadC.start();

    }
}
