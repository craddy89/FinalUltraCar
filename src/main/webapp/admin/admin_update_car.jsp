<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="title" var="bundle"/>
<html lang="${language}" class="h-100">
<head>
    <meta charset="utf-8">
    <title><fmt:message key="admin.car.edit.title" bundle="${bundle}" var="inputPrice"/></title>

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
    <main>
        <fmt:message key="admin.car.edit.input.brand" bundle="${bundle}" var="inputBrand"/>
        <fmt:message key="admin.car.edit.input.name" bundle="${bundle}" var="inputName"/>
        <fmt:message key="admin.car.edit.input.class" bundle="${bundle}" var="inputClass"/>
        <fmt:message key="admin.car.edit.input.color" bundle="${bundle}" var="inputColor"/>
        <fmt:message key="admin.car.edit.input.description" bundle="${bundle}" var="inputDescription"/>
        <fmt:message key="admin.car.edit.input.photo" bundle="${bundle}" var="inputPhoto"/>
        <fmt:message key="admin.car.edit.input.price" bundle="${bundle}" var="inputPrice"/>
        <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative block">
            <div class="col p-4 d-flex flex-column position-static">
                <strong class="d-inline-block mb-2 text-primary"><fmt:message key="admin.car.edit" bundle="${bundle}"/></strong>
                <h3 class="mb-0">${requestScope.car.brand} ${requestScope.car.name}</h3>
                <div class="mb-1 text-muted">${inputClass}: ${requestScope.car.carClass}</div>
                <p class="card-text mb-auto">${inputColor}: ${requestScope.car.color}</p>
                <p class="card-text mb-auto">${inputDescription}: ${requestScope.car.description}</p>
                <p class="card-text mb-auto">${inputPhoto}: ${requestScope.car.photo}</p>
                <p class="card-text mb-auto">${inputPrice}: ${requestScope.car.price}</p>
            </div>
        </div>

        <form method="post" action="admin_update_car" class="wrapper">
            <h1 class="h3 mb-3 fw-normal margin"><fmt:message key="admin.car.edit.update" bundle="${bundle}"/></h1><br><br>
            <input type="text" hidden name="id" value="${requestScope.car.id}" />
            <label for="inputBrand" class="visually-hidden">${inputBrand}</label>
            <input type="text" id="inputBrand" class="form-control margin" placeholder="${inputBrand}" value="${requestScope.car.brand}" name="brand" required="" autofocus="">
            <label for="inputName" class="visually-hidden">${inputName}</label>
            <input type="text" id="inputName" class="form-control margin" placeholder="${inputName}" value="${requestScope.car.name}" name="name" required="" autofocus="">
            <label for="inputClass" class="visually-hidden">${inputClass}</label>
            <input type="text" id="inputClass" class="form-control margin" placeholder="${inputClass}" value="${requestScope.car.carClass}" name="carClass" required="" autofocus="">
            <label for="inputColor" class="visually-hidden">${inputColor}</label>
            <input type="text" id="inputColor" class="form-control margin" placeholder="${inputColor}" value="${requestScope.car.color}" name="color" required="">
            <label for="inputDescription" class="visually-hidden">${inputDescription}</label>
            <input type="text" id="inputDescription" class="form-control margin" placeholder="${inputDescription}" value="${requestScope.car.description}" name="description" required="">
            <label for="inputPhoto" class="visually-hidden">${inputPhoto}</label>
            <input type="text" id="inputPhoto" class="form-control margin" placeholder="${inputPhoto}" value="${requestScope.car.photo}" name="photo" required="" autofocus="">
            <label for="inputPrice" class="visually-hidden">${inputPrice}</label>
            <input type="number" id="inputPrice" class="form-control margin" placeholder="${inputPrice}" value="${requestScope.car.price}" name="price" required="" autofocus="">
            <div><label class="margin" type="text" name="message"> ${message}</label></div>
            <button class="btn btn-outline-secondary text-dark margin" type="submit"><fmt:message key="admin.car.edit.button" bundle="${bundle}"/></button>
        </form>
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

    .block{
        width: 400px;
        margin: auto;
    }

</style>
