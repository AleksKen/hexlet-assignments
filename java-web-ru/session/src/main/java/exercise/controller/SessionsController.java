package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class SessionsController {

    // BEGIN
    public static void build(Context ctx) {
        var page = new LoginPage(null, null);
        ctx.render("build.jte", model("page", page));
    }
    public static void create(Context ctx) {
        var name = ctx.formParam("name");
        System.out.println(name);
        try {
            var password = encrypt(ctx.formParam("password"));
            System.out.println(password);
            var user = UsersRepository
                    .findByName(name)
                    .orElseThrow(() -> new NotFoundResponse("Wrong username or password"));
            if (!user.getPassword().equals(password)) {
                throw new NotFoundResponse("Wrong username or password");
            }
            ctx.sessionAttribute("name", name);
            ctx.redirect(NamedRoutes.rootPath());
        }
        catch (NotFoundResponse e) {
            var page = new LoginPage(name, e.getMessage());
            ctx.render("build.jte", model("page", page));
        }
    }
    public static void destroy(Context ctx) {
        ctx.sessionAttribute("name", null);
        ctx.redirect(NamedRoutes.rootPath());
    }

    public static void index(Context ctx) {
        var page = new MainPage(ctx.sessionAttribute("name"));
        ctx.render("index.jte", model("page", page));
    }
    // END
}
