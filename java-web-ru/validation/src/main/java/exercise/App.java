package exercise;

import io.javalin.Javalin;
import io.javalin.validation.ValidationException;

import java.util.List;

import exercise.model.Article;
import exercise.dto.articles.ArticlesPage;
import exercise.dto.articles.BuildArticlePage;

import static io.javalin.rendering.template.TemplateUtil.model;

import io.javalin.rendering.template.JavalinJte;

import exercise.repository.ArticleRepository;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/articles", ctx -> {
            List<Article> articles = ArticleRepository.getEntities();
            var page = new ArticlesPage(articles);
            ctx.render("articles/index.jte", model("page", page));
        });

        // BEGIN

        app.get("/articles/build", context -> context.render("articles/build.jte",
                model("page", new BuildArticlePage())));


        app.post("/articles", context -> {
            var title = context.formParam("title");
            var content = context.formParam("content");
            try {
                var allArticles = ArticleRepository.getEntities();

                title = context.formParamAsClass("title", String.class)
                        .check(value -> value.length() >= 2, "Название не должно быть короче двух символов")
                        .check(value -> checkTitle(value, allArticles), "Статья с таким названием уже существует")
                        .get();
                content = context.formParamAsClass("content", String.class)
                        .check(value -> value.length() >= 10, "Статья должна быть не короче 10 символов")
                        .get();
                var article = new Article(title, content);
                ArticleRepository.save(article);
                context.redirect("/articles");
            } catch (ValidationException e) {
                context.status(422);
                var page = new BuildArticlePage(title, content, e.getErrors());
                context.render("articles/build.jte", model("page", page));
            }
        });
        // END

        return app;
    }

    private static Boolean checkTitle(String title, List<Article> allArticles) {
        for (var article : allArticles) {
            if (title.equals(article.getTitle())) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Javalin app = App.getApp();
        app.start(7070);
    }
}
