package example.day04._1리스트컬렉션;

import java.util.ArrayList;
import java.util.List;

public class Example1 {

    public static void main(String[] args) {

        // 컬렉션 프레임워크
            // 1. 컬렉션(수집) , 프레임워크( 미리 만들어진 클래스/인터페이스/함수)
            // 2. 널리 사용되는 자료구조( 데이터 저장/관리 하는 방법론 ) 기반으로 제공한다.
            // 3. 대표 List , Set , Map 인터페이스

        // 1. List
            // - 객체를 저장하면 인덱스가 부여되고 , 중복 가능
            // - ArrayList , Vector , LinkedList 클래스
            // - List 선언하는 방법
                // E : 리스트저장하고싶은객체타입
                // 1. List< E > list = new ArrayList<>();
                // 2. List< E  > list = new ArrayList< E >();
                // 3. List list = new ArrayList();
                // 4. ArrayList< E > list = new ArrayList<>();
            // 제공하는 함수
                // 1. 리스트객체명.add( 객체 )            : 주어진 객체를 리스트내 맨 끝에 추가
                // 2. 리스트객체명.add( 인덱스 , 객체 )    : 주어진 객체를 리스트내 주어진 인덱스에 추가 [ 기존 인덱스 객체 밀려남 ]
                // 3. 리스트객체명.set( 인덱스 , 객체 )    : 주어진 객체를 리스트내 주어진 인덱스에 바꿈 [ 기존 인덱스 객체 사라짐 ]
                // 4. 리스트객체명.size()                 : 리스트내 저장된 전체 객체 수를 반환
                // 5. 리스트객체명.get( 인덱스 )           : 주어진 인덱스에 저장된 객체를 반환
                // 6. 리스트객체명.contains( 객체 )        : 주어진 객체가 리스트내 저장되어 있는지 여부[T/F] 반환
                // 7. 리스트객체명.remove( 인덱스 )        : 주어진 인덱스에 저장된 객체를 삭제
                // 8. 리스트객체명.remove( 객체 )          : 주어진 객체를 삭제
                // 9. 리스트객체명.clear()                : 리스트내 저장된 모든 객체 삭제
                // 10. 리스트객체명.isEmpty()             : 리스트가 비어 있는지 확인[T/F] 반환

        // 1. 리스트 컬렉션 생성
        List<Board> list = new ArrayList<>();

        // 2. 리스트에 객체 추가
        list.add( new Board("제목1","내용1","글쓴이1") );
        list.add( new Board("제목2","내용2","글쓴이2") );
        Board board = new Board("제목3","내용3","글쓴이3");
        list.add( board );
        list.add( new Board("제목4","내용4","글쓴이4") );
            // * add( 인덱스 , 객체 ) : 특정 인덱스에 객체 추가 가능.
        list.add( 0 , new Board("제목5","내용5","글쓴이5") );
        System.out.println( list );
        list.set( 0 , board );
        System.out.println( list );
        
        // 3. 리스트에 저장된 총 객체 수 호출하기
        int size = list.size();
        System.out.println("size = " + size);

        // 4. 리스트내 저장된 특정 인덱스의 객체 호출 하기
        Board board1 = list.get( 2 );
        System.out.println("2번 인덱스(세번째 위치한) 의 객체 : board1 = " + board1);
        
        // 5. 리스트내 저장된 특정 객체의 존재 여부 확인
        boolean result1 = list.contains( board1 );
        System.out.println("result1 = " + result1);

        // 6. 리스트내 객체 삭제
        list.remove( 2 );
        System.out.println( list );

        boolean empty1 = list.isEmpty();
        System.out.println("empty1 = " + empty1);

        // 7. 리스트내 모든 객체 삭제
        list.clear(  );
        System.out.println( list );

        // 8. 리스트내 비어 있는지 확인
        boolean empty2 = list.isEmpty();
        System.out.println("empty2 = " + empty2);

    }
}










