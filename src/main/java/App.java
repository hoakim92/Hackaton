public class App {
    public static void main(String[] args) {
        try {
            cucumber.api.cli.Main.main(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
