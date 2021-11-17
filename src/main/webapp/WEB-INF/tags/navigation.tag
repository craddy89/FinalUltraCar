<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="title" var="bundle"/>
<%@ attribute name="path" required="true" %>
<%@ attribute name="page" type="java.lang.Integer" required="true"%>
<%@ attribute name="pageSize" type="java.lang.Integer" required="true"%>
<%@ attribute name="maxPage" type="java.lang.Integer" required="true"%>
<fmt:message key="navigation.previous" bundle="${bundle}" var="previous"/>
<fmt:message key="navigation.next" bundle="${bundle}" var="next"/>

<c:choose>
    <c:when test="${page == 1}">${previous}</c:when>
    <c:otherwise>
        <a href="${path}?pageSize=${pageSize}&page=${page-1}">${previous}</a>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${page == maxPage}">${next}</c:when>
    <c:otherwise>
        <a href="${path}?pageSize=${pageSize}&page=${page+1}">${next}</a>
    </c:otherwise>
</c:choose>