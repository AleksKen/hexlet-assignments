@template.layout.page(
content = @`
    <form action="/users" method="post">
        <div>
            <label>
                Имя
                <input type="text" name="firstName">
            </label>
        </div>
        <div>
            <label>
                Фамилия
                <input type="text" name="lastName">
            </label>
        </div>
        <div>
            <label>
                Почта
                <input type="email" name="email">
            </label>
        </div>
        <div>
            <label>
                Пароль
                <input type="password" name="password">
            </label>
        </div>
        <div>
            <input type="submit" value="Зарегистрировать">
        </div>
    </form>
`
)
