<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="title" var="bundle"/>
<html lang="${language}" class="h-100">
<head>
    <meta charset="utf-8">
    <title><fmt:message key="user.rent.title" bundle="${bundle}"/></title>

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
        <h1><fmt:message key="user.rent.main" bundle="${bundle}"/></h1>
        <div class="container">
            <div class="row">
                <div class="col-lg-4 g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative block">
                    <div class="col p-4 d-flex flex-column position-static">
                        <div>
                            <img class="images" src="images/${requestScope.car.photo}" alt="car_photo">
                        </div>
                        <strong class="d-inline-block mb-2 text-success"><fmt:message key="user.rent.new" bundle="${bundle}"/></strong>
                        <h3 class="mb-0">${requestScope.car.brand} ${requestScope.car.name}</h3>
                        <div class="mb-1 text-muted"><fmt:message key="user.rent.class" bundle="${bundle}"/>: ${requestScope.car.carClass}</div>
                        <p class="mb-0"><fmt:message key="user.rent.color" bundle="${bundle}"/>: ${requestScope.car.color}</p>
                        <p class="mb-0"><fmt:message key="user.rent.description" bundle="${bundle}"/>: ${requestScope.car.description}</p>
                        <p class="mb-1"><fmt:message key="user.rent.price" bundle="${bundle}"/>: ${requestScope.car.price}$</p>
                        <h3 class="d-inline-block mb-2 text-success"><fmt:message key="user.rent.total.price" bundle="${bundle}"/>: ${requestScope.sum}$</h3>
                        <div><label class="margin" type="text" name="message"> ${message}</label></div>
                        <form class="mb-auto" method="get" action="rent_car">
                            <h4><fmt:message key="user.rent.customization" bundle="${bundle}"/>:</h4>
                            <p><fmt:message key="user.rent.checkbox" bundle="${bundle}"/> <label for="checkbox"></label><input type="checkbox" name="driver" id="checkbox" value="70" /></p>
                            <fmt:message key="user.rent.input.days" bundle="${bundle}" var="textDays"/>
                            <label for="inputMoney" class="visually-hidden">${textDays}</label>
                            <input style="margin: 0 auto" type="number" id="inputMoney" class="form-control margin w-50 mb-3" placeholder="${textDays}" name="days" required="" autofocus="">
                            <label>
                                <input type="text" hidden name="price" value="${requestScope.car.price}" />
                            </label>
                            <label>
                                <input type="text" hidden name="carId" value="${requestScope.car.id}" />
                            </label>
                            <button class="btn btn-outline-secondary text-dark margin" type="submit"><fmt:message key="user.rent.button.checkPrice" bundle="${bundle}"/></button>
                        </form>
                        <c:if test="${requestScope.sum > 0}">
                            <form class="mb-auto" method="post" action="rent_car">
                                <label>
                                    <input type="text" hidden name="carId" value="${requestScope.car.id}" />
                                </label>
                                <label>
                                    <input type="text" hidden name="sum" value="${requestScope.sum}" />
                                </label>
                                <label>
                                    <input type="text" hidden name="days" value="${requestScope.days}" />
                                </label>
                                <label>
                                    <input type="text" hidden name="price" value="${requestScope.car.price}" />
                                </label>
                                <label>
                                    <input type="text" hidden name="driver" value="${requestScope.driver}" />
                                </label>
                                <button class="btn btn-outline-secondary text-dark margin" type="submit"><fmt:message key="user.rent.button.rent" bundle="${bundle}"/></button>
                            </form>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>
</body>
</html>

<style>

    .block{
        width: 400px;
        height: 650px;
        margin: auto;
        display: flex;
        flex-direction: row;
    }
    .images{
        width: 100%;
        object-fit: contain;
        max-height: 150px;
    }
    .margin-left{
        margin-left: 10px;
    }

</style>

