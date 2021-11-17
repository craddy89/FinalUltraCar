<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="title" var="bundle"/>
<html lang="${language}" class="h-100">
<head>
    <meta charset="utf-8">
    <title><fmt:message key="admin.users.title" bundle="${bundle}"/></title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/cover/">

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
                <select class="form-select" id="language" name="language" onchange="submit()">
                    <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                    <option value="uk" ${language == 'uk' ? 'selected' : ''}>Українська</option>
                </select>
            </form>
            <nav class="float-md-end text-dark">
                <a class="p-2 text-dark" aria-current="page" href="admin"><fmt:message key="admin.header.main" bundle="${bundle}"/></a>
                <a class="p-2 text-dark" aria-current="page" href="admin_users"><fmt:message key="admin.header.users" bundle="${bundle}"/></a>
                <a class="p-2 text-dark margin-left" aria-current="page" href="admin_cars"><fmt:message key="admin.header.cars" bundle="${bundle}"/></a>
                <a class="btn btn-outline-primary margin-left" href="logout"><fmt:message key="general.header.logout" bundle="${bundle}"/></a>
            </nav>
        </div>
    </header>
    <main class="px-3 mt-auto">
        <h1><fmt:message key="admin.users.main" bundle="${bundle}"/></h1>
        <form method="post" action="add_user" class="wrapper">
            <h1 class="h3 mb-3 fw-normal margin"><fmt:message key="admin.users.addModerator.title" bundle="${bundle}"/></h1><br><br>
            <fmt:message key="admin.users.input.phone" bundle="${bundle}" var="inputPhone"/>
            <label for="inputPhone" class="visually-hidden">${inputPhone}</label>
            <input type="tel" pattern="[0-9_-]{9}" title="(99) 999 99 99" id="inputPhone" class="form-control margin" placeholder="${inputPhone}" name="phone" required="" autofocus="">
            <fmt:message key="admin.users.input.name" bundle="${bundle}" var="inputName"/>
            <label for="inputName" class="visually-hidden">${inputName}</label>
            <input type="text" id="inputName" class="form-control margin" placeholder="${inputName}" name="name" required="" autofocus="">
            <fmt:message key="admin.users.input.surname" bundle="${bundle}" var="inputSurname"/>
            <label for="inputSurname" class="visually-hidden">${inputSurname}</label>
            <input type="text" id="inputSurname" class="form-control margin" placeholder="${inputSurname}" name="surname" required="" autofocus="">
            <fmt:message key="admin.users.input.password" bundle="${bundle}" var="inputPass"/>
            <label for="inputPassword" class="visually-hidden">${inputPass}</label>
            <input type="password" id="inputPassword" class="form-control margin" placeholder="${inputPass}" name="password" required="">
            <fmt:message key="admin.users.input.rePassword" bundle="${bundle}" var="inputRePass"/>
            <label for="inputPassword2" class="visually-hidden">${inputRePass}</label>
            <input type="password" id="inputPassword2" class="form-control margin" placeholder="${inputRePass}" name="password2" required="">
            <div><label class="margin" type="text" name="message"> ${message}</label></div>
            <button class="btn btn-outline-secondary text-dark margin" type="submit"><fmt:message key="admin.users.input.button" bundle="${bundle}"/></button>
        </form>
        <h2><fmt:message key="admin.users.allUsers" bundle="${bundle}"/></h2>
        <div class="table-responsive">
            <table class="table table-striped table-sm">
                <tr>
                    <th><fmt:message key="admin.users.table.phone" bundle="${bundle}"/></th>
                    <th><fmt:message key="admin.users.table.name" bundle="${bundle}"/></th>
                    <th><fmt:message key="admin.users.table.surname" bundle="${bundle}"/></th>
                    <th><fmt:message key="admin.users.table.role" bundle="${bundle}"/></th>
                    <th><fmt:message key="admin.users.table.regDate" bundle="${bundle}"/></th>
                    <th><fmt:message key="admin.users.table.active" bundle="${bundle}"/></th>
                    <th><fmt:message key="admin.users.table.button" bundle="${bundle}"/></th>
                </tr>
                <c:forEach var="user" items="${requestScope.users}">
                    <tr>
                        <td>${user.phoneNumber}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.role}</td>
                        <td>${user.regDate}</td>
                        <td>${user.active}</td>
                        <td>
                            <form method="post" action="blockUser">
                                <fmt:message key="admin.users.table.button.name" bundle="${bundle}" var="changeB"/>
                                <label>
                                    <input type="text" hidden name="phone" value="${user.phoneNumber}" />
                                </label>
                                <label>
                                    <input type="text" hidden name="active" value="${user.active}" />
                                </label>
                                <input type="submit" name="block" value="${changeB}"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </main>
</div>
</body>
</html>

<style>

    .wrapper{
        width: 300px;
        margin: auto;
    }

    .margin{
        margin-bottom: 20px;
    }

    .margin-left{
        margin-left: 10px;
    }

</style>