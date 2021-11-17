<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="title" var="bundle"/>
<html lang="${language}" class="h-100">
<head>
    <meta charset="utf-8">
    <title><fmt:message key="user.profile.title" bundle="${bundle}"/></title>

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
        <h1 class="mb-4"><fmt:message key="user.profile.main" bundle="${bundle}"/></h1>
        <h4 class="mb-2">${requestScope.message}</h4>
        <div class="container">
            <div class="row">
                <div class="col-lg-3 g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative block">
                    <div class="col p-4 d-flex flex-column position-static">
                        <h3 class="mb-0">${requestScope.user.firstName} ${requestScope.user.lastName}</h3>
                        <div class="mb-1 text-muted">${requestScope.user.phoneNumber}</div>
                        <strong class="d-inline-block mb-2 text-success"><fmt:message key="user.profile.money" bundle="${bundle}"/>: ${requestScope.user.money}$</strong>
                        <p class="mb-0"><fmt:message key="user.profile.dateReg" bundle="${bundle}"/>: ${requestScope.user.regDate}</p>
                    </div>
                </div>
                <div class="col-lg-3 g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative block">
                    <div class="col p-4 d-flex flex-column position-static">
                        <h3 class="mb-0">${requestScope.passport.series} ${requestScope.passport.number}</h3>
                        <div class="mb-1 text-muted">${requestScope.passport.by}</div>
                        <strong class="d-inline-block mb-2 text-success">${requestScope.passport.createDate}</strong>
                    </div>
                </div>
                <div class="col-lg-3 g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative block">
                    <div class="col p-4 d-flex flex-column position-static">
                        <h3 class="mb-3"><fmt:message key="user.profile.addMoney" bundle="${bundle}"/></h3>
                        <form class="mb-auto form" method="post" action="add_money">
                            <fmt:message key="user.profile.addMoney.input" bundle="${bundle}" var="textSum"/>
                            <label for="inputMoney" class="visually-hidden">${textSum}</label>
                            <input style="margin: 0 auto" type="number" id="inputMoney" class="form-control w-50 mb-3" placeholder="${textSum}" name="money" required="" autofocus="">
                            <button class="btn btn-outline-secondary text-dark" type="submit"><fmt:message key="user.profile.addMoney.button" bundle="${bundle}"/></button>
                        </form>
                    </div>
                </div>
                <div class="col-lg-3 g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative block">
                    <div class="col p-4 d-flex flex-column position-static">
                        <h3 class="mb-3"><fmt:message key="user.profile.passport" bundle="${bundle}"/></h3>
                        <form class="mb-auto form" method="post" action="add_passport">
                            <fmt:message key="user.profile.passport.input.series" bundle="${bundle}" var="textSeries"/>
                            <label for="inputSeries" class="visually-hidden">${textSeries}</label>
                            <input style="margin: 0 auto" type="text" id="inputSeries" class="form-control w-70 mb-3" placeholder="${textSeries}" name="series" required="" autofocus="">
                            <fmt:message key="user.profile.passport.input.number" bundle="${bundle}" var="textNumber"/>
                            <label for="inputNumber" class="visually-hidden">${textNumber}</label>
                            <input style="margin: 0 auto" type="number" id="inputNumber" class="form-control w-70 mb-3" placeholder="${textNumber}" name="number" required="" autofocus="">
                            <fmt:message key="user.profile.passport.input.made" bundle="${bundle}" var="textMade"/>
                            <label for="inputWhoMade" class="visually-hidden">${textMade}</label>
                            <input style="margin: 0 auto" type="text" id="inputWhoMade" class="form-control w-70 mb-3" placeholder="${textMade}" name="whoMade" required="" autofocus="">
                            <fmt:message key="user.profile.passport.input.date" bundle="${bundle}" var="textDateR"/>
                            <label for="inputDateReg" class="visually-hidden">${textDateR}</label>
                            <input style="margin: 0 auto" type="date" id="inputDateReg" class="form-control w-70 mb-3" placeholder="${textDateR}" name="regDate" required="" autofocus="">
                            <button class="btn btn-outline-secondary text-dark" type="submit"><fmt:message key="user.profile.passport.button" bundle="${bundle}"/></button>
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

    .block{
        height: auto;
        margin: auto;
        display: flex;
        flex-direction: row;
    }
    .margin-left{
        margin-left: 10px;
    }
    .form{
        text-align: center;
    }

</style>