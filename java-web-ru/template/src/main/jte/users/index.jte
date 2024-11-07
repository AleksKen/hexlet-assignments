@import exercise.dto.users.UsersPage
@param UsersPage page

<html lang="">
<head>
    <title>Хекслет</title>
</head>
<body>
<h1>Список пользователей:</h1>
@if(page.getUsers().isEmpty())
    <p>Пока не добавлено ни одного курса</p>
@else
    @for(var user : page.getUsers())
        <div>
            <h2><a href="/users/${user.getId()}">${user.getFirstName() + user.getLastName()}</a></h2>
            <p>${user.getEmail()}</p>
        </div>
    @endfor
@endif
</body>
</html>