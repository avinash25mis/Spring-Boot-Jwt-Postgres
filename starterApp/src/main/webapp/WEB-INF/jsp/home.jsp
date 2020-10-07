<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<body>


<br>

<form action="${pageContext.request.contextPath}/Common/getAllUser">
<button>All User</button><br>
</form>


<br>

<form action="${pageContext.request.contextPath}/Common/getAllProduct">
<input type="text" name="userId" placeholder="User Id"/>
<button>All Product</button><br>
</form>
</body>
</html>

<br>

<form action="${pageContext.request.contextPath}/Common/getAllBasket">
<input type="text" name="userId" placeholder="User Id"/>
<button>All Basket</button><br>
</form>

<form action="${pageContext.request.contextPath}/Common/addProduct">
<input type="text" name="name" placeholder="name" label="name"/>
<input type="text" name="type" placeholder="type" label="type"/>
<button>Add Product</button><br>
</form>

<form action="${pageContext.request.contextPath}/Common/addProductToUser">
<input type="text" name="productId" placeholder="Product Id"/>
<input type="text" name="userId" placeholder="User Id"/>
<button>Add Product To User</button><br>
</form>