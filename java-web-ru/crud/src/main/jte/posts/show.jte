@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes
@param PostPage page

@template.layout.page(content = @`
    <p>${page.getPost().getName()}</p>
    <p>${page.getPost().getBody()}</p>
    <a href="${NamedRoutes.postsPath()}">Return</a>
`)