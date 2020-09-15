<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ads List</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.0/css/bulma.min.css">
</head>
<body>

<section class="navbar">
    <jsp:include page="navbar.jsp"/>
</section>

<div class="container">
    <div class="notification is-success">
        <div class="column is-full">
            <h4 class="title is-4 has-text-centered"> Lista ogłoszeń wszystkich użytkowników</h4>
        </div>
    </div>
    <table class="table is-striped is-hoverable">
        <thead>
        <tr>
            <th>Lp</th>
            <th>Tytuł</th>
            <th>Treść ogłoszenia</th>
            <th>Autor ogłoszenia</th>
            <th>Data i czas dodania</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allAds}" var="advert" varStatus="counter">
            <tr>
                <td>${counter.count}</td>
                <td>${advert.title}</td>
                <td>${advert.description}</td>
                <td>
                        <a href="/user-adverts/${advert.user.id}"> ${advert.user.fullName}</a>
                </td>
                <td>${advert.formattedPosted()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
