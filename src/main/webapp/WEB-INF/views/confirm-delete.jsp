<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Potwierdzenie usunięcia ogłoszenia</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.0/css/bulma.min.css">
</head>
<body>

<section class="navbar">
    <jsp:include page="navbar.jsp"/>
</section>

<section class="section">
    <div class="container has-text-centered">
        Potwierdź usunięcie ogłoszenia albo anuluj.
        <form method="post">
            <div class="field is-grouped">
                <div class="control">
                    <button class="button is-link" type="submit">Potwierdź</button>
                </div>
                <div class="control">
                    <a class="button is-link is-light" href="/">Anuluj</a>
                </div>
            </div>
            <sec:csrfInput/>
        </form>
    </div>
</section>

</body>
</html>
