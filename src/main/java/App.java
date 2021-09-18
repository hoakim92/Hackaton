public class App {
    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                cucumber.api.cli.Main.main(new String[]{"-g", "", "src/test/features"});
            } else {
                cucumber.api.cli.Main.main(args);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
