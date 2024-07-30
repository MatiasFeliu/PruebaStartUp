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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
<form class="container">
    <div class="mb-3">
        <label for="nombre" class="form-label">nombre</label>
        <input type="text" class="form-control" id="nombre" name="nombre" required >
    </div>
    <div class="mb-3">
        <label for="clave" class="form-label">clave</label>
        <input type="password" class="form-control" id="clave" name="clave" required>
    </div>
    <div class="mb-3">
        <label for="apodo" class="form-label">apodo</label>
        <input type="text" class="form-control" id="apodo" name="apodo" required>
    </div>
    <div class="mb-3">
        <label for="peso" class="form-label">peso</label>
        <input type="number" class="form-control" id="peso" name="peso" required>
    </div>

    <button type="submit" class="btn btn-primary">Submit</button>
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
