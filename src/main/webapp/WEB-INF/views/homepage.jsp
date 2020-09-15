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
                Witaj ${userFirstName} na stronie z ogłoszeniami, gdzie możesz dodawać, przeglądać, usuwać ogłoszenia i ich autorów.
            </h2>
        </div>
    </div>
</section>

<section class="section">
    <sec:authorize access="isAuthenticated()">
        <div class="containter">
            <h1>Formularz dodawania ogłoszenia:</h1>
            <jsp:include page="/advertisement-form"/>
        </div>
    </sec:authorize>



    <div class="container">
        <h1>Lista ogłoszeń</h1>
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
            <c:forEach items="${advertisements}" var="advert" varStatus="counter">
                <tr>
                    <td>${counter.count}</td>
                    <td>${advert.title}</td>
                    <td>${advert.description}</td>
                    <td>${advert.user.fullName}</td>
                    <td>${advert.posted}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>


    </div>

</section>


</body>
</html>
