<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="title" var="bundle"/>
<html lang="${language}" class="h-100">
<head>
  <meta charset="utf-8">
  <title><fmt:message key="moderator.moderation.title" bundle="${bundle}"/></title>

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
    <h1><fmt:message key="moderator.moderation.main" bundle="${bundle}"/></h1>
    <h2><fmt:message key="moderator.moderation.orders" bundle="${bundle}"/></h2>
    <h3>${requestScope.message}</h3>
    <div class="table-responsive">
      <table class="table table-striped table-sm">
        <tr style="vertical-align: middle">
          <th><fmt:message key="moderator.moderation.rentId" bundle="${bundle}"/></th>
          <th><fmt:message key="moderator.moderation.user" bundle="${bundle}"/></th>
          <th><fmt:message key="moderator.moderation.car" bundle="${bundle}"/></th>
          <th><fmt:message key="moderator.moderation.driver" bundle="${bundle}"/></th>
          <th><fmt:message key="moderator.moderation.days" bundle="${bundle}"/></th>
          <th><fmt:message key="moderator.moderation.price" bundle="${bundle}"/></th>
          <th><fmt:message key="moderator.moderation.status" bundle="${bundle}"/></th>
          <th><fmt:message key="moderator.moderation.allow" bundle="${bundle}"/></th>
          <th><fmt:message key="moderator.moderation.ban" bundle="${bundle}"/></th>
          <th><fmt:message key="moderator.moderation.search" bundle="${bundle}"/></th>
        </tr>
        <c:forEach var="history" items="${requestScope.moderList}">
          <tr style="vertical-align: middle">
            <td>${history.id}</td>
            <td>${history.userId}</td>
            <td>${history.carId}</td>
            <td>${history.driver}</td>
            <td>${history.days}</td>
            <td>${history.price}</td>
            <td>${history.rentStatus}</td>
            <td>
              <form style="display: flex; flex-direction: column" method="post" action="allow_rent">
                <fmt:message key="moderator.moderation.dateStart" bundle="${bundle}" var="dateStart"/>
                <label for="inputDateStart" class="visually-hidden">${dateStart}</label>
                <input type="date" id="inputDateStart" class="form-control margin w-30 mb-3" placeholder="Date Start" name="dateStart" required="" autofocus="">
                <fmt:message key="moderator.moderation.dateFinish" bundle="${bundle}" var="dateFinish"/>
                <label for="inputDateFinish" class="visually-hidden">${dateFinish}</label>
                <input type="date" id="inputDateFinish" class="form-control margin w-30 mb-3" placeholder="Date Finish" name="dateFinish" required="" autofocus="">
                <label>
                  <input type="text" hidden name="id" value="${history.id}" />
                </label>
                <label>
                  <input type="text" hidden name="userId" value="${history.userId}" />
                </label>
                <label>
                  <input type="text" hidden name="price" value="${history.price}" />
                </label>
                <label>
                  <input type="text" hidden name="carId" value="${history.carId}" />
                </label>
                <button class="btn btn-outline-secondary text-dark margin" type="submit"><fmt:message key="moderator.moderation.allow.button" bundle="${bundle}"/></button>
              </form>
            </td>
            <td>
              <form style="display: flex; flex-direction: column" method="post" action="ban_rent">
                <fmt:message key="moderator.moderation.ban.description" bundle="${bundle}" var="banDesc"/>
                <label for="inputDescription" class="visually-hidden">${banDesc}</label>
                <input type="text" id="inputDescription" class="form-control margin mb-3" placeholder="${banDesc}" name="description" required="" autofocus="">
                <label>
                  <input type="text" hidden name="id" value="${history.id}" />
                </label>
                <button class="btn btn-outline-secondary text-dark margin" type="submit"><fmt:message key="moderator.moderation.ban.button" bundle="${bundle}"/></button>
              </form>
            </td>
            <td>
              <form style="display: flex; justify-content: center" method="post" action="search_info">
                <label>
                  <input type="text" hidden name="userId" value="${history.userId}" />
                </label>
                <label>
                  <input type="text" hidden name="carId" value="${history.carId}" />
                </label>
                <button class="btn btn-outline-secondary text-dark margin" type="submit"><fmt:message key="moderator.moderation.search.button" bundle="${bundle}"/></button>
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

  .margin-left{
    margin-left: 10px;
  }

</style>