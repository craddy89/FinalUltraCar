<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="title" var="bundle"/>
<html lang="${language}" class="h-100">
<head>
    <meta charset="utf-8">
    <title><fmt:message key="user.history.title" bundle="${bundle}"/></title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/cover/">

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <link rel="shortcut icon" href="images/icon.png" type="image/png">

</head>
<body>
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column d-flex h-100 text-center text-dark">
    <header class="" >
        <div>
            <img class="float-md-start mb-auto" src="images/logo.png" width="30px" height="26px" alt="logo">
            <h3 class="float-md-start margin-left">ULTRACAR</h3>
            <form class="float-md-start margin-left">
                <label for="language"></label><select class="form-select" id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="uk" ${language == 'uk' ? 'selected' : ''}>Українська</option>
            </select>
            </form>
            <nav class="float-md-end text-dark">
                <a class="p-2 text-dark" aria-current="page" href="user"><fmt:message key="user.header.main" bundle="${bundle}"/></a>
                <a class="p-2 text-dark" aria-current="page" href="user_profile"><fmt:message key="user.header.profile" bundle="${bundle}"/></a>
                <a class="p-2 text-dark" aria-current="page" href="history"><fmt:message key="user.header.history" bundle="${bundle}"/></a>
                <a class="p-2 text-dark" aria-current="page" href="fines"><fmt:message key="user.header.fines" bundle="${bundle}"/></a>
                <a class="btn btn-outline-primary margin-left" href="logout"><fmt:message key="general.header.logout" bundle="${bundle}"/></a>
            </nav>
        </div>
    </header>
    <main>
        <h1 class="mb-5"><fmt:message key="user.history.main" bundle="${bundle}"/></h1>
        <div><label class="margin" type="text" name="message"> ${message}</label></div>
        <table class="table table-striped table-sm">
            <tr>
                <th><fmt:message key="user.history.car" bundle="${bundle}"/></th>
                <th><fmt:message key="user.history.driver" bundle="${bundle}"/></th>
                <th><fmt:message key="user.history.days" bundle="${bundle}"/></th>
                <th><fmt:message key="user.history.price" bundle="${bundle}"/></th>
                <th><fmt:message key="user.history.status" bundle="${bundle}"/></th>
                <th><fmt:message key="user.history.dateStart" bundle="${bundle}"/></th>
                <th><fmt:message key="user.history.dateFinish" bundle="${bundle}"/></th>
                <th><fmt:message key="user.history.description" bundle="${bundle}"/></th>
                <th><fmt:message key="user.history.return" bundle="${bundle}"/></th>
            </tr>
            <c:forEach var="history" items="${requestScope.stories}">
                <tr>
                    <td>${history.carId}</td>
                    <td>${history.driver}</td>
                    <td>${history.days}</td>
                    <td>${history.price}</td>
                    <td>${history.rentStatus}</td>
                    <td>${history.dateStart}</td>
                    <td>${history.dateFinish}</td>
                    <td>${history.description}</td>
                    <td>
                        <c:if test="${history.rentStatus == 'Active'}">
                            <form class="mb-auto" method="post" action="return_car">
                                <label>
                                    <input type="text" hidden name="id" value="${history.id}" />
                                </label>
                                <button class="btn btn-outline-secondary text-dark margin" type="submit"><fmt:message key="user.history.return.button" bundle="${bundle}"/></button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
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