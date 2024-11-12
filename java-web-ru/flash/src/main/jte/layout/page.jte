<!-- BEGIN -->
@import exercise.dto.BasePage
@import gg.jte.Content
@param Content content
@param BasePage page = null

<html lang="ru">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hexlet</title>
</head>
<body>
@if(page != null && page.getFlash() != null)
    <p>${page.getFlash()}</p>
@endif
${content}
</body>
</html>
<!-- END -->
