<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
    <h2>Profile page</h2>
        ${username}

<a href="${pageContext.request.contextPath}/profile/edit"> Edit page</a>
</body>
</html>
