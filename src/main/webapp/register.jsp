<%--
  Created by IntelliJ IDEA.
  User: matia
  Date: 16-07-2024
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form>
    <div class="mb-3">
        <label for="nombre" class="form-label">nombre</label>
        <input type="text" class="form-control" id="nombre" name="nombre"  >
    </div>
    <div class="mb-3">
        <label for="clave" class="form-label">clave</label>
        <input type="password" class="form-control" id="clave" name="clave">
    </div>
    <div class="mb-3">
        <label for="apodo" class="form-label">apodo</label>
        <input type="text" class="form-control" id="apodo" name="apodo"  >
    </div>
    <div class="mb-3">
        <label for="peso" class="form-label">peso</label>
        <input type="number" class="form-control" id="peso" name="peso"  >
    </div>

    <button type="submit" class="btn btn-primary">Submit</button>
</form>

</body>
</html>
