<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Advertisement Form</title>
</head>
<body>

    <form:form modelAttribute="advertisement" method="post" action="/advertisement-form">
        <div class="field">
            <label class="label">Tytuł: </label>
            <div class="control">
                <form:input path="title" cssClass="input" />
            </div>
        </div>

        <div class="field">
            <label class="label">Opis: </label>
            <div class="control">
                <form:textarea path="description" cols="20" rows="5" cssClass="textarea"/>
            </div>
        </div>

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


</body>
</html>
