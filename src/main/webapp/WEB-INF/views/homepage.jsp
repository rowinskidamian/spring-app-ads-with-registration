<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Website Home Page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.0/css/bulma.min.css">
</head>
<body>

<section class="navbar">
    <jsp:include page="navbar.jsp"/>
</section>

<section class="hero is-success">
    <div class="hero-body">
        <div class="container">
            <h1 class="title">
                Strona z ogłoszeniami
            </h1>
            <h2 class="subtitle">
                Witaj ${userFirstName} na stronie z ogłoszeniami, gdzie możesz dodawać, przeglądać, usuwać ogłoszenia i
                ich autorów.
            </h2>
        </div>
    </div>
</section>

<section class="section">
    <div class="container">
        <sec:authorize access="isAuthenticated()">
            <div class="containter">
                <h5 class="title is-5 has-text-centered">Formularz dodawania ogłoszenia</h5>
                <jsp:include page="/advertisement-form"/>
            </div>
        </sec:authorize>
    </div>

    <div class="container">
        <div class="notification is-success">
            <div class="column is-full">
                <h4 class="title is-4 has-text-centered"> Lista ogłoszeń </h4>
                <h5 class="subtitle is-5 has-text-centered"> Najnowszych 5 ogłoszeń. </h5>
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
                <sec:authorize access="isAuthenticated()">
                    <th>Ulubione</th>
                </sec:authorize>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${advertisements}" var="advert" varStatus="counter">
                <tr>
                    <td>${counter.count}</td>
                    <td>${advert.title}</td>
                    <td>${advert.description}</td>
                    <td>
                        <sec:authorize access="isAuthenticated()">
                            <a href="/user-adverts/${advert.user.id}"> ${advert.user.fullName}</a>
                        </sec:authorize>
                        <sec:authorize access="isAnonymous()">
                            ${advert.user.fullName}
                        </sec:authorize>
                    </td>
                    <td>${advert.formattedPosted()}</td>
                    <sec:authorize access="isAuthenticated()">
                        <td>
                            <c:set var="isInList" scope="session" value="false"/>
                            <c:forEach items="${favouriteAds}" var="favouriteAd">
                                    <c:if test="${favouriteAd.id eq advert.id}">
                                        <a href="/adverts/favourite/delete/${advert.id}">Usuń z ulubionych</a>
                                        <c:set var="isInList" scope="session" value="true"/>
                                    </c:if>
                            </c:forEach>
                            <c:if test="${isInList eq false}">
                                <a href="/adverts/favourite/add/${advert.id}">Dodaj do ulubionych</a>
                            </c:if>
                        </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</section>


</body>
</html>
