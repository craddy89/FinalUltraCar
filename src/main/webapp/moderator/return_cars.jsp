<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="title" var="bundle"/>
<html lang="${language}" class="h-100">
<head>
    <meta charset="utf-8">
    <title><fmt:message key="moderator.returns.title" bundle="${bundle}"/></title>

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
                <label for="language"></label><select class="form-select" id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="uk" ${language == 'uk' ? 'selected' : ''}>Українська</option>
            </select>
            </form>
            <nav class="float-md-end text-dark">
                <a class="p-2 text-dark" aria-current="page" href="moderator"><fmt:message key="moderator.header.main" bundle="${bundle}"/></a>
                <a class="p-2 text-dark" aria-current="page" href="moderation"><fmt:message key="moderator.header.moderation" bundle="${bundle}"/></a>
                <a class="p-2 text-dark margin-left" aria-current="page" href="return_cars"><fmt:message key="moderator.header.return" bundle="${bundle}"/></a>
                <a class="p-2 text-dark margin-left" aria-current="page" href="search_info"><fmt:message key="moderator.header.dataSearch" bundle="${bundle}"/></a>
                <a class="btn btn-outline-primary margin-left" href="logout"><fmt:message key="general.header.logout" bundle="${bundle}"/></a>
            </nav>
        </div>
    </header>
    <main class="px-3">
        <h1><fmt:message key="moderator.returns.main" bundle="${bundle}"/></h1>
        <h2><fmt:message key="moderator.returns.returns" bundle="${bundle}"/></h2>
        <div class="table-responsive">
            <table class="table table-striped table-sm">
                <tr>
                    <th><fmt:message key="moderator.returns.rentId" bundle="${bundle}"/></th>
                    <th><fmt:message key="moderator.returns.user" bundle="${bundle}"/></th>
                    <th><fmt:message key="moderator.returns.car" bundle="${bundle}"/></th>
                    <th><fmt:message key="moderator.returns.driver" bundle="${bundle}"/></th>
                    <th><fmt:message key="moderator.returns.days" bundle="${bundle}"/></th>
                    <th><fmt:message key="moderator.returns.price" bundle="${bundle}"/></th>
                    <th><fmt:message key="moderator.returns.status" bundle="${bundle}"/></th>
                    <th><fmt:message key="moderator.returns.dateStart" bundle="${bundle}"/></th>
                    <th><fmt:message key="moderator.returns.dateFinish" bundle="${bundle}"/></th>
                    <th><fmt:message key="moderator.returns.damageLevel" bundle="${bundle}"/></th>
                    <th><fmt:message key="moderator.returns.description" bundle="${bundle}"/></th>
                    <th><fmt:message key="moderator.returns.pickUp" bundle="${bundle}"/></th>
                </tr>
                <c:forEach var="history" items="${requestScope.moderList}">
                    <form class="mb-auto" method="post" action="pick_up">
                        <tr>
                            <td>${history.id}</td>
                            <td>${history.userId}</td>
                            <td>${history.carId}</td>
                            <td>${history.driver}</td>
                            <td>${history.days}</td>
                            <td>${history.price}</td>
                            <td>${history.rentStatus}</td>
                            <td>${history.dateStart}</td>
                            <td>${history.dateFinish}</td>
                            <td>
                                <label for="state"></label><select class="form-select" id="state" name="damage" required="">
                                <option value=""><fmt:message key="moderator.returns.select.choose" bundle="${bundle}"/></option>
                                <option value="No damage"><fmt:message key="moderator.returns.select.noDamage" bundle="${bundle}"/></option>
                                <option value="Low"><fmt:message key="moderator.returns.select.low" bundle="${bundle}"/></option>
                                <option value="Average"><fmt:message key="moderator.returns.select.average" bundle="${bundle}"/></option>
                                <option value="High"><fmt:message key="moderator.returns.select.high" bundle="${bundle}"/></option>
                                <option value="Critical"><fmt:message key="moderator.returns.select.critical" bundle="${bundle}"/></option>
                            </select>
                            </td>
                            <td>
                                <fmt:message key="moderator.returns.input.description" bundle="${bundle}" var="inputDescription"/>
                                <label for="description"></label><input type="text" class="form-control" id="description" name="description" placeholder="${inputDescription}" value="" required="">
                            </td>
                            <td>
                                <label>
                                    <input type="text" hidden name="id" value="${history.id}" />
                                </label>
                                <label>
                                    <input type="text" hidden name="userId" value="${history.userId}" />
                                </label>
                                <label>
                                    <input type="text" hidden name="carId" value="${history.carId}" />
                                </label>
                                <button class="btn btn-outline-secondary text-dark margin" type="submit"><fmt:message key="moderator.returns.button" bundle="${bundle}"/></button>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </table>
        </div>
    </main>
</div>
</body>
</html>

<style>

    .margin-left{
        margin-left: 10px;
    }

</style>