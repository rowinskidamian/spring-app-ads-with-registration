<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.0/css/bulma.min.css">
</head>
<body>

<section class="navbar">
    <jsp:include page="navbar.jsp"/>
</section>

<section class="section">
    <div class="container has-text-centered">
        <div class="notification is-danger">
            <h3 class="title is-3">Error</h3>
            ${errorMessage}
        </div>
    </div>
</section>

</body>
</html>
