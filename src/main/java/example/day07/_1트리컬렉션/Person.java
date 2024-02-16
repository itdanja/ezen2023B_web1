package example.day07._1트리컬렉션;

public class Person implements Comparable<Person>  {
    // implements : 구현하다( 인터페이스의 추상메소드 )
    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person o) {
        // * 같으면 0 , 주어진 객체보다 적으면 -1 , 주어진 객체보다 크면 1
        // 1. age 나이 정렬
        /*
            if( this.age < o.age ) return -1;
            else if( this.age == o.age ) return 0;
            else return 1;
        */
        // 2. name 이름 정렬 ( String 클래스이므로 정렬기준 이미 구현 )
        return this.name.compareTo( o.name) ;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
