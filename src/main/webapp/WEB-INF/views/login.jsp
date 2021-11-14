<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
          crossorigin="anonymous">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Авторизация</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-4">
            <h2>Авторизация</h2>
        <c:if test="${not empty errorMessage}">
            <div style="color:red; font-weight: bold; margin: 30px 0px;">
                    ${errorMessage}
            </div>
        </c:if>
        <form name='login' action="<c:url value='/login'/>" method='POST'>
            <div class="mb-3">
                <label for="usernameIdForm" class="form-label">Имя пользователя</label>
                <input type="text" class="form-control" id="usernameIdForm" name="username">
                <div id="usernameHelp" class="form-text">Введите имя</div>
            </div>
            <div class="mb-3">
                <label for="passwordIdForm" class="form-label">Пароль</label>
                <input type="password" class="form-control" id="passwordIdForm" name="password">
                <div id="passwordHelp" class="form-text">Введите пароль</div>
            </div>
            <button type="submit" class="btn btn-primary">Войти</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"
        integrity="sha384-W8fXfP3gkOKtndU4JGtKDvXbO53Wy8SZCQHczT5FMiiqmQfUpWbYdTil/SxwZgAN"
        crossorigin="anonymous">

</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js"
        integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/"
        crossorigin="anonymous">

</script>
</body>
</html>