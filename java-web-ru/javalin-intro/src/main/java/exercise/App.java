package exercise;

// BEGIN
import io.javalin.Javalin;
// END


public final class App {

    public static Javalin getApp() {

        // BEGIN
        return  Javalin.create(/*config*/)
                .get("/", context -> context.result("Welcome to Hexlet!"));
        // END
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
