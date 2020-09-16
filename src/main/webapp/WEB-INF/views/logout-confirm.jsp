<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirm Logout</title>
</head>
<body>

<section class="navbar">
    <jsp:include page="navbar.jsp"/>
</section>

<section class="section">
    <div class="container is-fluid">
        <div class="notification">
            <h5 class="title is-5 has-text-centered">Potwierdź wylogowanie</h5>
            <form method="post" action="/logout">
                <div class="field is-grouped is-grouped-centered">
                    <p class="control">
                        <div class="buttons">
                            <button class="button is-success" type="submit">
                                <strong>Potwierdź</strong>
                            </button>
                            <a class="button is-light" href="/">
                                Anuluj
                            </a>
                        </div>
                    </p>
                </div>
                <sec:csrfInput/>
            </form>
        </div>
    </div>
</section>


</body>
</html>
