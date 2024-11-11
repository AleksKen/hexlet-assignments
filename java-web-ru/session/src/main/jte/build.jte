@import exercise.util.NamedRoutes
@import exercise.dto.LoginPage

@param LoginPage page

@template.layout.page(content = @`
    @if(page.getError() != null)
        <p>${page.getError()}</p>
    @endif
    <form action="${NamedRoutes.loginPath()}" method="post">
        <label>
            <input type="text" placeholder="Name" name="name" value="${page.getName()}"/>
        </label>
        <label>
            <input type="password" placeholder="Password" name="password" />
        </label>
        <input type="submit" />
    </form>
`)