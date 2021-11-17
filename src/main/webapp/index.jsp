<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="title" var="bundle"/>
<html lang="${language}" class="h-100">
<head>
    <meta charset="utf-8">
    <title><fmt:message key="home.title" bundle="${bundle}"/></title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/cover/">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <link rel="shortcut icon" href="images/icon.png" type="image/png">

</head>

<body>
<div class="cover-container d-flex w-100 h-100 mx-auto flex-column d-flex text-center text-dark">
    <header class="p-3" >
        <div>
            <img class="float-md-start mb-auto" src="images/logo.png" width="34" height="32" alt="logo">
            <h3 class="float-md-start margin-left">ULTRACAR</h3>
            <form class="float-md-start margin-left">
                <label for="language"></label><select class="form-select" id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="uk" ${language == 'uk' ? 'selected' : ''}>Українська</option>
            </select>
            </form>
            <nav class="float-md-end text-dark">
                <a class="p-2 text-dark" aria-current="page" href="home"><fmt:message key="general.header.home" bundle="${bundle}"/></a>
                <a class="btn btn-outline-primary margin-left" href="registration"><fmt:message key="general.header.registration" bundle="${bundle}"/></a>
                <a class="btn btn-outline-primary margin-left" href="login"><fmt:message key="general.header.login" bundle="${bundle}"/></a>
            </nav>
        </div>
    </header>

    <main class="mt-auto">
        <h2><fmt:message key="home.tagline" bundle="${bundle}"/></h2>
        <img src="images/113.png" width="1366" height="401" alt="main" >
        <h5 class="lead mt-2"><fmt:message key="home.hint" bundle="${bundle}"/></h5>
    </main>

    <footer class="mt-auto text-black-50">
        <p><fmt:message key="general.footer" bundle="${bundle}"/></p>
    </footer>
</div>
</body>
</html>

<style>

    .margin-left{
        margin-left: 10px;
    }

</style>
