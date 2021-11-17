<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="title" var="bundle"/>
<html lang="${language}" class="h-100">
<head>
    <meta charset="utf-8">
    <title><fmt:message key="admin.cars.title" bundle="${bundle}"/></title>

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
                <a class="p-2 text-dark" aria-current="page" href="admin"><fmt:message key="admin.header.main" bundle="${bundle}"/></a>
                <a class="p-2 text-dark" aria-current="page" href="admin_users"><fmt:message key="admin.header.users" bundle="${bundle}"/></a>
                <a class="p-2 text-dark margin-left" aria-current="page" href="admin_cars"><fmt:message key="admin.header.cars" bundle="${bundle}"/></a>
                <a class="btn btn-outline-primary margin-left" href="logout"><fmt:message key="general.header.logout" bundle="${bundle}"/></a>
            </nav>
        </div>
    </header>
    <main class="px-3 mt-auto">
        <h1><fmt:message key="admin.cars.main" bundle="${bundle}"/></h1>
        <form method="post" action="add_car" class="wrapper">
            <h1 class="h3 mb-3 fw-normal margin"><fmt:message key="admin.cars.addModerator.title" bundle="${bundle}"/></h1><br><br>
            <fmt:message key="admin.cars.input.brand" bundle="${bundle}" var="inputBrand"/>
            <label for="inputBrand" class="visually-hidden">${inputBrand}</label>
            <input type="text" id="inputBrand" class="form-control margin" placeholder="${inputBrand}" name="brand" required="" autofocus="">
            <fmt:message key="admin.cars.input.name" bundle="${bundle}" var="inputName"/>
            <label for="inputName" class="visually-hidden">${inputName}</label>
            <input type="text" id="inputName" class="form-control margin" placeholder="${inputName}" name="name" required="" autofocus="">
            <fmt:message key="admin.cars.input.class" bundle="${bundle}" var="inputClass"/>
            <label for="inputClass" class="visually-hidden">${inputClass}</label>
            <input type="text" id="inputClass" class="form-control margin" placeholder="${inputClass}" name="carClass" required="" autofocus="">
            <fmt:message key="admin.cars.input.color" bundle="${bundle}" var="inputColor"/>
            <label for="inputColor" class="visually-hidden">${inputColor}</label>
            <input type="text" id="inputColor" class="form-control margin" placeholder="${inputColor}" name="color" required="">
            <fmt:message key="admin.cars.input.description" bundle="${bundle}" var="inputDescription"/>
            <label for="inputDescription" class="visually-hidden">${inputDescription}</label>
            <input type="text" id="inputDescription" class="form-control margin" placeholder="${inputDescription}" name="description" required="">
            <fmt:message key="admin.cars.input.photo" bundle="${bundle}" var="inputPhoto"/>
            <label for="inputPhoto" class="visually-hidden">${inputPhoto}</label>
            <input type="text" id="inputPhoto" class="form-control margin" placeholder="${inputPhoto}" name="photo" required="" autofocus="">
            <fmt:message key="admin.cars.input.price" bundle="${bundle}" var="inputPrice"/>
            <label for="inputPrice" class="visually-hidden">${inputPrice}</label>
            <input type="number" id="inputPrice" class="form-control margin" placeholder="${inputPrice}" name="price" required="" autofocus="">
            <div><label class="margin" type="text" name="message"> ${message}</label></div>
            <button class="btn btn-outline-secondary text-dark margin" type="submit"><fmt:message key="admin.cars.input.button" bundle="${bundle}"/></button>
        </form>
        <h2><fmt:message key="admin.cars.allCars" bundle="${bundle}"/></h2>
        <div class="table-responsive">
            <table class="table table-striped table-sm">
                <tr>
                    <th><fmt:message key="admin.cars.table.brand" bundle="${bundle}"/></th>
                    <th><fmt:message key="admin.cars.table.name" bundle="${bundle}"/></th>
                    <th><fmt:message key="admin.cars.table.carClass" bundle="${bundle}"/></th>
                    <th><fmt:message key="admin.cars.table.color" bundle="${bundle}"/></th>
                    <th><fmt:message key="admin.cars.table.description" bundle="${bundle}"/></th>
                    <th><fmt:message key="admin.cars.table.photo" bundle="${bundle}"/></th>
                    <th><fmt:message key="admin.cars.table.price" bundle="${bundle}"/></th>
                    <th><fmt:message key="admin.cars.table.active" bundle="${bundle}"/></th>
                    <th><fmt:message key="admin.cars.table.button" bundle="${bundle}"/></th>
                    <th><fmt:message key="admin.cars.table.button2" bundle="${bundle}"/></th>
                </tr>
                <c:forEach var="car" items="${requestScope.cars}">
                    <tr>
                        <td>${car.brand}</td>
                        <td>${car.name}</td>
                        <td>${car.carClass}</td>
                        <td>${car.color}</td>
                        <td>${car.description}</td>
                        <td>${car.photo}</td>
                        <td>${car.price}</td>
                        <td>${car.active}</td>
                        <td>
                            <form method="get" action="admin_update_car">
                                <fmt:message key="admin.cars.table.button.update" bundle="${bundle}" var="buttonUpdate"/>
                                <label>
                                    <input type="text" hidden name="id" value="${car.id}" />
                                </label>
                                <input type="submit" name="update" value="${buttonUpdate}"/>
                            </form>
                        </td>
                        <td>
                            <form method="post" action="deleteCar">
                                <fmt:message key="admin.cars.table.button.delete" bundle="${bundle}" var="buttonDelete"/>
                                <label>
                                    <input type="text" hidden name="id" value="${car.id}" />
                                </label>
                                <input type="submit" name="delete" value="${buttonDelete}"/>
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
