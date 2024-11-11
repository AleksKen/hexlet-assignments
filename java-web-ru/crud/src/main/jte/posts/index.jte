@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(content = @`
    @for(var post : page.getPosts())
        <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
    @endfor
    <footer>
        @if(page.getPageNumber() != 1)
        <a href="${NamedRoutes.postsPath() + "?page=" + (page.getPageNumber() - 1)}">${page.getPageNumber() - 1}</a>
        @endif
        <a href="${NamedRoutes.postsPath() + "?page=" + (page.getPageNumber() + 1)}">${page.getPageNumber() + 1}</a>
    </footer>
`)