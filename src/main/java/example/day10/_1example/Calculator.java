package example.day10._1example;

public class Calculator { // 계산기

    private int memory;

    // * getter
    public int getMemory(){
        return memory;
    }

    // * setter : 매개변수를 저장 [ 2초간 뒤에 저장된 값을 출력 ]
    // synchronized : 동기화 : 여러 스레드가 해당 메소드/블록 호출했을때 순서매기기
        // 컬렉션 프레임워크 : List(Vector) , map( hashTable )
    public synchronized void setMemory( int memory ){

        this.memory = memory;

        try{ Thread.sleep(2000); }
        catch (Exception e ){}

        System.out.println(
                Thread.currentThread().getName()+
                        " : "+  this.memory);

    }

}
