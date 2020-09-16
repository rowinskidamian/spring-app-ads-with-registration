<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Navbar</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.0/css/bulma.min.css">
</head>
<body>

<nav class="navbar" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
        <a class="navbar-item" href="/">
            <img src="/images/adverts_logo.png" height="160" width="160">
        </a>

        <a role="button" class="navbar-burger burger" aria-label="menu" aria-expanded="false"
           data-target="navbarBasicExample">
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
        </a>
    </div>

    <div id="navbarBasicExample" class="navbar-menu">
        <div class="navbar-start">
            <a class="navbar-item" href="/">
                Strona główna
            </a>

            <a class="navbar-item">
                Dodaj ogłoszenie
            </a>

            <sec:authorize access="isAuthenticated()">

            <div class="navbar-item has-dropdown is-hoverable">
                <a class="navbar-link">
                    Menu
                </a>

                <div class="navbar-dropdown">
                    <a class="navbar-item" href="/user-adverts">
                        Lista moich ogłoszeń
                    </a>
                    <hr class="navbar-divider">
                    <a class="navbar-item" href="/ads-all">
                        Lista wszystkich ogłoszeń
                    </a>
                    <hr class="navbar-divider">
                    <a class="navbar-item" href="/adverts/favourite/all">
                        Ulubione ogłoszenia
                    </a>
                </div>
            </div>
            </sec:authorize>
        </div>

        <div class="navbar-end">
        <sec:authorize access="!isAuthenticated()">

                <div class="navbar-item">
                    <div class="buttons">
                        <a class="button is-success" href="/register">
                            <strong>Zarejestruj się</strong>
                        </a>
                        <a class="button is-light" href="/login">
                            Zaloguj się
                        </a>
                    </div>
                </div>

        </sec:authorize>

        <sec:authorize access="isAuthenticated()">
                <div class="navbar-item">
                    <div class="buttons">
                            <a class="button is-success" href="/logout-confirm">
                                <strong>Wyloguj</strong>
                            </a>
                    </div>
                </div>
        </sec:authorize>
        </div>
    </div>
</nav>

</body>
</html>
