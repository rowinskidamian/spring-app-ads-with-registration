<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Formularz rejestracyjny</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.0/css/bulma.min.css">
</head>
<body>
<section class="section">
    <div class="container">
        <h1 class="title">
            Formularz Rejestracyjny
        </h1>
        <p class="subtitle">
            Wpisz swoje dane, aby się zarejestrować.
        </p>
    </div>
    <div class="container">
        <form method="post" action="/register">

            <div class="field">
                <label class="label">Nazwa użytkownika</label>
                <div class="control">
                    <input class="input" type="text" name="username" placeholder="Podaj nazwę użytkownika">
                </div>
            </div>

            <div class="field">
                <label class="label">Imię</label>
                <div class="control has-icons-left has-icons-right">
                    <input class="input" type="text" name="firstName" placeholder="Imię">
                    <span class="icon is-small is-left">
                        <i class="fas fa-user"></i>
                    </span>
                </div>
            </div>

            <div class="field">
                <label class="label">Nazwisko</label>
                <div class="control has-icons-left has-icons-right">
                    <input class="input" type="text" name="lastName" placeholder="Nazwisko">
                    <span class="icon is-small is-left">
                        <i class="fas fa-user"></i>
                    </span>
                </div>
            </div>

            <div class="field">
                <label class="label">Hasło</label>
                <div class="control has-icons-left">
                    <input class="input" type="password" name="password" placeholder="Hasło">
                    <span class="icon is-small is-left">
                        <i class="fas fa-lock"></i>
                    </span>
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
<%--            poniższe dodajemy, aby wykorzystać wbudowany mechanizm Springa zabezpieczający przed atakami CSRF--%>
            <sec:csrfInput/>
        </form>
    </div>
</section>
</body>
</html>
