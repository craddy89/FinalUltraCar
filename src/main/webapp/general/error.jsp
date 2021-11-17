<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="title" var="bundle"/>
<html lang="${language}" class="h-100">
<head>
    <meta charset="utf-8">
    <title><fmt:message key="error.page.title" bundle="${bundle}"/></title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/cover/">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <link rel="shortcut icon" href="images/icon.png" type="image/png">

</head>
<body>
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column d-flex h-100 text-center text-dark">
    <main class="px-3 mt-auto">
        <img src="images/error.png" height="300px" width="300px" alt="main">
        <h1><fmt:message key="error.page.main" bundle="${bundle}"/></h1>
        <form class="mb-auto form" method="post" action="error">
            <button class="btn btn-outline-secondary text-dark" type="submit"><fmt:message key="error.page.button" bundle="${bundle}"/></button>
        </form>
    </main>
    <footer class="mt-auto text-black-50">
        <p><fmt:message key="general.footer" bundle="${bundle}"/></p>
    </footer>
</div>
</body>
</html>
