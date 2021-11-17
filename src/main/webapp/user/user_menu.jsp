<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="title" var="bundle"/>
<html lang="${language}" class="h-100">
<head>
    <meta charset="utf-8">
    <title><fmt:message key="user.title" bundle="${bundle}"/></title>

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
        <h1 class="mb-5"><fmt:message key="user.main" bundle="${bundle}"/></h1>
        <h3 class="mb-3"><fmt:message key="user.main.navigation" bundle="${bundle}"/>:</h3>
        <div class="container">
            <div class="row">
                <div class="col-lg-4">
                    <form class="mb-3 wrapper" method="get" action="user">
                        <label for="state"></label><select class="form-select" id="state" name="orderBy" onchange="submit()">
                        <option value=""><fmt:message key="user.main.sort" bundle="${bundle}"/></option>
                        <option value="brand"><fmt:message key="user.main.sort.brand" bundle="${bundle}"/></option>
                        <option value="price"><fmt:message key="user.main.sort.price" bundle="${bundle}"/></option>
                        <option value="color"><fmt:message key="user.main.sort.color" bundle="${bundle}"/></option>
                    </select>
                    </form>
                </div>
                <div class="col-lg-4">
                    <form class="mb-3 wrapper" method="get" action="user" onchange="submit()">
                        <label for="brand"></label><select class="form-select" id="brand" name="brand">
                        <option value=""><fmt:message key="user.main.navigation.brand" bundle="${bundle}"/></option>
                        <c:forEach var="brand" items="${requestScope.brands}">
                            <option>${brand}</option>
                        </c:forEach>
                    </select>
                    </form>
                </div>
                <div class="col-lg-4">
                    <form class="mb-3 wrapper" method="get" action="user" onchange="submit()">
                        <label for="carClass"></label><select class="form-select" id="carClass" name="carClass">
                        <option value=""><fmt:message key="user.main.navigation.carClass" bundle="${bundle}"/></option>
                        <c:forEach var="carClass" items="${requestScope.carClasses}">
                            <option>${carClass}</option>
                        </c:forEach>
                    </select>
                    </form>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-lg-4">
                    <form class="mb-auto" method="post" action="user">
                        <fmt:message key="user.main.navigation.sort" bundle="${bundle}"/>: ${requestScope.sort}
                        <c:if test="${requestScope.sort != 'id'}">
                            <label>
                                <input type="text" hidden name="orderByX" value="${requestScope.sort}" />
                            </label>
                        </c:if>
                    </form>
                </div>
                <div class="col-lg-4">
                    <form class="mb-auto" method="post" action="user">
                        <fmt:message key="user.main.navigation.brand" bundle="${bundle}"/>: ${requestScope.brand}
                        <c:if test="${requestScope.brand != ''}">
                            <label>
                                <input type="text" hidden name="brandX" value="${requestScope.brand}" />
                            </label>
                        </c:if>
                    </form>
                </div>
                <div class="col-lg-4">
                    <form class="mb-auto" method="post" action="user">
                        <fmt:message key="user.main.navigation.carClass" bundle="${bundle}"/>: ${requestScope.carClass}
                        <c:if test="${requestScope.carClass != ''}">
                            <label>
                                <input type="text" hidden name="carClassX" value="${requestScope.carClass}" />
                            </label>
                            <button class="btn btn-outline-secondary text-dark margin" type="submit"><fmt:message key="user.main.navigation.button" bundle="${bundle}"/></button>
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <c:forEach var="car" items="${requestScope.cars}">
                    <div class="col-lg-4 g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative block">
                        <div class="col p-4 d-flex flex-column position-static">
                            <div>
                                <img class="images" src="images/${car.photo}" alt="car_photo">
                            </div>
                            <strong class="d-inline-block mb-2 text-success">New</strong>
                            <h3 class="mb-0">${car.brand} ${car.name}</h3>
                            <div class="mb-1 text-muted"><fmt:message key="user.main.class" bundle="${bundle}"/>: ${car.carClass}</div>
                            <p class="mb-0"><fmt:message key="user.main.color" bundle="${bundle}"/>: ${car.color}</p>
                            <p class="mb-0"><fmt:message key="user.main.description" bundle="${bundle}"/>: ${car.description}</p>
                            <p class="mb-1"><fmt:message key="user.main.price" bundle="${bundle}"/>: ${car.price}$</p>
                            <form class="mb-auto" method="get" action="rent_car">
                                <label>
                                    <input type="text" hidden name="carId" value="${car.id}" />
                                </label>
                                <button class="btn btn-outline-secondary text-dark margin" type="submit"><fmt:message key="user.main.button.rent" bundle="${bundle}"/></button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <util:navigation path="user" maxPage="${requestScope.maxPage}" page="${requestScope.page}" pageSize="${requestScope.pageSize}" />
    </main>
    <footer class="mt-auto text-black-50">
        <p><fmt:message key="general.footer" bundle="${bundle}"/></p>
    </footer>
</div>
</body>
</html>

<style>

    .block{
        width: 400px;
        height: 470px;
        margin: auto;
        display: flex;
        flex-direction: row;
    }
    .images{
        width: 100%;
        object-fit: contain;
        max-height: 200px;
    }
    .margin-left{
        margin-left: 10px;
    }
    .wrapper{
        width: 300px;
        margin: auto;
    }

</style>
