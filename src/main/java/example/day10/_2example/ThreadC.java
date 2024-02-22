package example.day10._2example;

public class ThreadC extends  Thread {
    private WorkObject workObject;
    public ThreadC(WorkObject workObject ){
        setName("ThreadC");
        this.workObject = workObject;
    }
    @Override
    public void run() {
        for( int i = 0 ; i<100 ; i++ ){
            workObject.methodA();
        }
    }
}
