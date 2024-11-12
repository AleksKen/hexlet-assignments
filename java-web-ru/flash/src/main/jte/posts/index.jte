@import exercise.dto.posts.PostsPage
@param PostsPage page


@template.layout.page(
    page = page,
    content = @`
    @if(page.getPosts().isEmpty())
        <p>Пока не добавлено ни одного поста</p>
    @else
        @for(var page : page.getPosts())
            <h1>${page.getName()}</h1>
            <p>${page.getBody()}</p>
        @endfor
    @endif
`)