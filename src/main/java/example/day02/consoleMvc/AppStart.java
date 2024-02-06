package example.day02.consoleMvc;

public class AppStart {
    public static void main(String[] args) {
        // 1.
        MainView mainView = new MainView();
        mainView.home();

        // 2.
        // new MainView().home();

        // 3.싱글톤
        // MainView.getInstance().home();

        // 4. 메소드가 정적메소드 일때.
        // MainView.home();

    }
}
/*

 */
