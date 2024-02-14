package example.day05._1SET컬렉션;

public class Member {

    public String name;
    public int age;

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }
    // hashCode , equals => 메소드의 주인은 Object
        // 힙 가상주소를 int 값이 아닌 새로값 정의
    @Override
    public int hashCode() {
        return name.hashCode() + age;
    }
    @Override
    public boolean equals( Object obj){
        if( obj instanceof Member  ){ // 매개변수의 객체가 Member 타입이면
            Member target = (Member) obj; // 왜??? Member 객체 필드 사용할려고
            return target.name.equals( this.name ) && ( target.age == this.age );
        }else{
            return false;
        }
    }
            // instanceof : 타입 확인
    // toString => 메소드의 주인은 Object
        // - 객체의 주소를 반환
        // 오버라이딩 : 주소 반환 대신 필드(정보)로 반환
}
