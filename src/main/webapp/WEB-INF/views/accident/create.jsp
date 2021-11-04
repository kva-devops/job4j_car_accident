<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html land="ru">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
          crossorigin="anonymous">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Accident Create</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-6">
            <h2>Create Accident</h2>
            <form action="<c:url value='/save'/>" method='POST'>
                <div class="mb-3">
                    <label for="nameIdForm" class="form-label">Название</label>
                    <input type="text" class="form-control" id="nameIdForm" name="name">
                    <div id="nameHelp" class="form-text">Придумайте название инцидента</div>
                </div>
                <div class="mb-3">
                    <label for="textIdForm" class="form-label">Описание</label>
                    <textarea class="form-control" id="textIdForm" rows="3" name="text"></textarea>
                    <div id="textHelp" class="form-text">Подробно опишите нарушение</div>
                </div>
                <div class="mb-3">
                    <label for="addressIdForm" class="form-label">Адрес нарушения</label>
                    <input type="text" class="form-control" id="addressIdForm" name="address">
                    <div id="addressHelp" class="form-text">Адрес, где зафиксировано нарушение</div>
                </div>
                <button type="submit" class="btn btn-primary">Сохранить</button>
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