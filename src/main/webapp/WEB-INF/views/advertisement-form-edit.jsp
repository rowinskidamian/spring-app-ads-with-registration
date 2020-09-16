<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Advertisement Edit Form</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.0/css/bulma.min.css">
    <script src="https://kit.fontawesome.com/a1834f9866.js" crossorigin="anonymous"></script>
</head>
<body>

<section class="navbar">
    <jsp:include page="navbar.jsp"/>
</section>

<section class="section">
    <div class="container">
        <div class="containter">
            <h5 class="title is-5 has-text-centered">Formularz edycji ogłoszenia</h5>
            <form:form modelAttribute="advertisement" method="post">
                <div class="field">
                    <label class="label">Tytuł: </label>
                    <div class="control">
                        <form:input path="title" cssClass="input"/>
                    </div>
                </div>

                <div class="field">
                    <label class="label">Opis: </label>
                    <div class="control">
                        <form:textarea path="description" cols="20" rows="5" cssClass="textarea"/>
                    </div>
                </div>
                <input hidden name="id" value="${advertisement.id}">
                <input hidden name="posted" value="${advertisement.posted}">

                <div class="field is-grouped">
                    <div class="control">
                        <button class="button is-link" type="submit">Wyślij</button>
                    </div>
                    <div class="control">
                        <button class="button is-link is-light" type="reset">Wyczyść</button>
                    </div>
                </div>
                <sec:csrfInput/>
            </form:form>
        </div>
    </div>
</section>

</body>
</html>
