<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="title" var="bundle"/>
<html lang="${language}" class="h-100">
<head>
    <meta charset="utf-8">
    <title><fmt:message key="moderator.search.title" bundle="${bundle}"/></title>

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
                <a class="p-2 text-dark" aria-current="page" href="moderator"><fmt:message key="moderator.header.main" bundle="${bundle}"/></a>
                <a class="p-2 text-dark" aria-current="page" href="moderation"><fmt:message key="moderator.header.moderation" bundle="${bundle}"/></a>
                <a class="p-2 text-dark margin-left" aria-current="page" href="return_cars"><fmt:message key="moderator.header.return" bundle="${bundle}"/></a>
                <a class="p-2 text-dark margin-left" aria-current="page" href="search_info"><fmt:message key="moderator.header.dataSearch" bundle="${bundle}"/></a>
                <a class="btn btn-outline-primary margin-left" href="logout"><fmt:message key="general.header.logout" bundle="${bundle}"/></a>
            </nav>
        </div>
    </header>
    <main>
        <h1><fmt:message key="moderator.search.main" bundle="${bundle}"/></h1>
        <h2>${requestScope.message}</h2>
        <div class="container">
            <div class="row" style="display: flex; justify-content: space-around">
                <div class="col-lg-3 p-4 g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                    <div class="col d-flex flex-column position-static">
                        <h2 class="mb-2"><fmt:message key="moderator.search.user" bundle="${bundle}"/></h2>
                        <h3 class="mb-0">${requestScope.user.firstName} ${requestScope.user.lastName}</h3>
                        <div class="mb-1 text-muted">${requestScope.user.phoneNumber}</div>
                        <strong class="d-inline-block mb-2 text-success"><fmt:message key="moderator.search.money" bundle="${bundle}"/>: ${requestScope.user.money}$</strong>
                        <p class="mb-2"><fmt:message key="moderator.search.dateReg" bundle="${bundle}"/>: ${requestScope.user.regDate}</p>
                    </div>
                    <div class="col d-flex flex-column position-static">
                        <h2 class="mb-2"><fmt:message key="moderator.search.passport" bundle="${bundle}"/></h2>
                        <h3 class="mb-0">${requestScope.passport.series} ${requestScope.passport.number}</h3>
                        <div class="mb-1 text-muted">${requestScope.passport.by}</div>
                        <strong class="d-inline-block mb-2 text-success">${requestScope.passport.createDate}</strong>
                    </div>
                    <form class="mb-auto form" method="post" action="search_info">
                        <fmt:message key="moderator.search.input.user" bundle="${bundle}" var="textUserID"/>
                        <label for="inputUserId" class="visually-hidden">${textUserID}</label>
                        <input type="number" id="inputUserId" class="form-control margin w-30 mb-3" placeholder="${textUserID}" name="userId" required="" autofocus="">
                        <button class="btn btn-outline-secondary text-dark" type="submit"><fmt:message key="moderator.search.button.user" bundle="${bundle}"/></button>
                    </form>
                </div>
                <div class="col-lg-3 g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                    <div class="col p-4 d-flex flex-column position-static">
                        <h2 class="mb-2"><fmt:message key="moderator.search.car" bundle="${bundle}"/></h2>
                        <img class="images" src="images/${requestScope.car.photo}" width="200px" height="120px" alt="car_photo">
                        <strong class="d-inline-block mb-2 text-success"><fmt:message key="moderator.search.new" bundle="${bundle}"/></strong>
                        <h3 class="mb-0">${requestScope.car.brand} ${requestScope.car.name}</h3>
                        <div class="mb-1 text-muted"><fmt:message key="moderator.search.class" bundle="${bundle}"/>: ${requestScope.car.carClass}</div>
                        <p class="mb-0"><fmt:message key="moderator.search.color" bundle="${bundle}"/>: ${requestScope.car.color}</p>
                        <p class="mb-0"><fmt:message key="moderator.search.description" bundle="${bundle}"/>: ${requestScope.car.description}</p>
                        <p class="d-inline-block text-success mb-0"><fmt:message key="moderator.search.price" bundle="${bundle}"/>: ${requestScope.car.price}$</p>
                        <p class="d-inline-block mb-2 text-success"><fmt:message key="moderator.search.active" bundle="${bundle}"/>: ${requestScope.car.active}</p>
                        <form class="mb-auto form" method="post" action="search_info">
                            <fmt:message key="moderator.search.input.car" bundle="${bundle}" var="textCarId"/>
                            <label for="inputCarId" class="visually-hidden">${textCarId}</label>
                            <input type="number" id="inputCarId" class="form-control margin w-30 mb-3" placeholder="${textCarId}" name="carId" required="" autofocus="">
                            <button class="btn btn-outline-secondary text-dark" type="submit"><fmt:message key="moderator.search.button.car" bundle="${bundle}"/></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>
</body>
</html>

<style>

    .images{
        width: 100%;
        object-fit: contain;
        max-height: 150px;
    }

    .margin-left{
        margin-left: 10px;
    }

</style>