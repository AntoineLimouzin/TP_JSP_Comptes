<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List,
	com.intiformation.tpcomptes.modele.Client,
    com.intiformation.tpcomptes.service.ClientService,
    com.intiformation.tpcomptes.modele.Compte,
    com.intiformation.tpcomptes.service.CompteService"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="fr">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Accueil</title>

<!-- Bootstrap core CSS -->
<link href="styles/bootstrap.min.css" rel="stylesheet">


<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
<!-- Custom styles for this template -->



<link href="styles/signin.css" rel="stylesheet">
</head>

<%
	ClientService cs = new ClientService();
	List<Client> clients = cs.findAll();
	String listeClientsHtml = "";
	for (Client client : clients) {
		listeClientsHtml += "<option value='" + client.getId_client() + "'>" + client.getPrenom() + " "
				+ client.getNom() + "</option>\n";
	}
%>

<body class="text-center">
	<form class="form-signin" action="AccueilServlet" method="post">
		<img class="mb-4" src="img/money-bag.png" alt="" width="120"
			height="120">
		<h1 class="h3 mb-3 font-weight-normal">Veuillez vous identifier</h1>

		<br>

		<div class="form-group mx-sm-3 mb-2">
			<fieldset>
				<select name="users" id="users" class="form-control">
					<%=listeClientsHtml%>
				</select> <input type="password" id="inputPassword" name="inputPassword"
					class="form-control" placeholder="Mot de passe">
			</fieldset>
		</div>


		<button class="btn btn-lg btn-primary btn-block" type="submit"
			name="btn-accueil" value="existant">Accès au compte</button>

		<button class="btn btn-lg btn-secondary btn-block" type="submit"
			name="btn-accueil" value="nouveau">Nouveau client?</button>

		<p class="mt-5 mb-3 text-muted">&copy; INTI Formation 2019</p>
	</form>
</body>
</html>
