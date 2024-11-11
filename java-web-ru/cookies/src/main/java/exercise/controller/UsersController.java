package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void create(Context ctx) {
        var firstName = ctx.formParam("firstName");
        var lastName =  ctx.formParam("lastName");
        var email =  ctx.formParam("email");
        var password =  ctx.formParam("password");
        var token = Security.generateToken();
        ctx.cookie("registered", token);
        var user = new User(firstName, lastName, email, password, token);
        UserRepository.save(user);
        var users = UserRepository.getEntities();
        var id = -1L;
        for (var el : users) {
            if (el.getToken().equals(token)) {
                id = el.getId();
            }
        }
        ctx.redirect(NamedRoutes.userPath(id));
    }

    public static void show(Context ctx) {
        var token = ctx.cookie("registered");
        var id = ctx.pathParamAsClass("id", Long.class).getOrDefault(-1L);
        var user = UserRepository.find(id).orElseThrow(() -> new NotFoundResponse("Пользователь не найден!"));
        if (token != null && token.equals(user.getToken())) {
            ctx.render("users/show.jte", model("page", new UserPage(user)));
        }
        else {
            ctx.redirect(NamedRoutes.buildUserPath());
        }
    }
    // END
}
