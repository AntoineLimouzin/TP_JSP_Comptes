<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" import="java.io.PrintWriter"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajout Client</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="styles/bootstrap.min.css">
<link rel="stylesheet" href="styles/styleperso.css">
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#" style="color:black;font-size:x-large">Ajout de Client</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="accueil.jsp">Accueil <span class="sr-only">(current)</span></a>
      </li>
      
    </ul>
  </div>
</nav>

	<div class="formulaireAjout">

		<form action='AjoutClientServlet' method='Post'>

			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="inputLastName">Prénom</label> <input type="text"
						class="form-control" id="inputLastName" name="lastName">
				</div>
				<div class="form-group col-md-6">
					<label for="inputFirstName">Nom</label> <input type="text"
						class="form-control" id="inputFirstName" name="firstName">
				</div>
			</div>
			<div class="form-group">
				<label for="inputAddress">Adresse</label> <input type="text"
					class="form-control" id="inputAddress" name="address">
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="inputCity">Ville</label> <input type="text"
						class="form-control" id="inputCity" name ="city">
				</div>
				<div class="form-group col-md-2">
					<label for="inputZip">Code Postal</label> <input type="text"
						class="form-control" id="inputZip" name="zip">
				</div>
				<div class="form-group col-md-4">
					<label for="inputPhone">Téléphone</label> <input type="text"
						class="form-control" id="inputPhone" name="phone">
				</div>
			</div>
			
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="inputPwd">Mot de passe</label> <input type="password"
						class="form-control" id="inputPwd" name="pwd">
				</div>
				<div class="form-group col-md-6">
					<label for="inputPwd_bis">Confirmer le mot de passe</label> <input type="password"
						class="form-control" id="inputPwd_bis" name="pwd_bis">
				</div>
			</div>

			Type de compte :
			<div class="form-check">
				<input class="form-check-input" type="radio" name="choixComptes"
					id="cn" value="normal" checked> <label
					class="form-check-label" for="cn"> Compte Normal </label>
			</div>
			<div class="form-check">
				<input class="form-check-input" type="radio" name="choixComptes"
					id="cc" value="courant"> <label class="form-check-label"
					for="cc"> Compte Courant </label>
			</div>
			<div class="form-check">
				<input class="form-check-input" type="radio" name="choixComptes"
					id="ce" value="epargne"> <label class="form-check-label"
					for="ce"> Compte Epargne </label>
			</div>

			<br>

			<button type="submit" class="btn btn-primary">Inscription</button>

		</form>
		
		

	</div>
	

</body>
</html>