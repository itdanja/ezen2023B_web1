package example.day10._1example;

public class User2Thread extends Thread {

    private Calculator calculator;

    public User2Thread(){
        setName("User2Thread");
    }

    public void setCalculator( Calculator calculator ){
        this.calculator = calculator;
    }

    @Override
    public void run() {
        calculator.setMemory( 50 );
    }
}
