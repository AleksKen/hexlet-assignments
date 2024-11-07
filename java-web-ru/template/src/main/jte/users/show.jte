@import exercise.dto.users.UserPage
@param UserPage page

<html lang="">
<head>
    <title>Хекслет</title>
</head>
<body>
<h1>${page.getUser().getFirstName() + page.getUser().getLastName()}</h1>
</body>
</html>