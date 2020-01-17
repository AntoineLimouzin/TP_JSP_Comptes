<%@page import="com.intiformation.tpcomptes.modele.Compte_Courant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List,
	com.intiformation.tpcomptes.modele.Client,
    com.intiformation.tpcomptes.service.ClientService,
    com.intiformation.tpcomptes.modele.Compte,
    com.intiformation.tpcomptes.modele.Compte_Courant,
    com.intiformation.tpcomptes.modele.Compte_Epargne,
    com.intiformation.tpcomptes.service.CompteService"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Opérations</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="styles/jquery-ui.css">
<link rel="stylesheet" href="styles/bootstrap.min.css">
<link rel="stylesheet" href="styles/styleperso.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#personnes").selectmenu();
		$("#tabs").tabs();
	});
</script>
</head>
<body>



	<%
		ClientService cs = new ClientService();
		int idc = (int)request.getSession().getAttribute("id_client");
		Client c = cs.findById(idc);

		CompteService cms = new CompteService();
		Compte cm = cms.findByClientId(idc);
	%>


	<nav class="navbar navbar-expand-lg navbar-light bg-light"> <a
		class="navbar-brand" href="#" style="color: black; font-size: x-large">Gestion
		du Compte</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active">
			<a class="nav-link" href="pageClientServlet?logout=index">Déconnexion<span class="sr-only">(current)</span></a>
			</li>
			<li class="nav-item active"><a class="nav-link"
				href="pageClientServlet?logout=new">Nouveau Client <span class="sr-only">(current)</span></a>
 			</li>
		</ul>
	</div>
	</nav>



	<div class="operations">
		<div id="tabs">

			<ul>
				<li><a href="#tabs-1">Informations</a></li>
				<li><a href="#tabs-2">Dépôt</a></li>
				<li><a href="#tabs-3">Retrait</a></li>
				<li><a href="#tabs-4">Transfert</a></li>
			</ul>

			<div id="tabs-1">

				<%=c.getPrenom() + " " + c.getNom() + " possède " + cm.getSolde() + " €"%>

				<br> <br>

				<form class='form-group'
					action='pageClientServlet?idClient=<%=idc%>' method='post'>

					<div class="form-group row">
						<div class="form-group mb-2">
							<label for="adresse" class="sr-only">Adresse</label> <input
								type="text" readonly class="form-control-plaintext" id="adresse"
								value="Adresse">
						</div>
						<div class="form-group mx-sm-3 mb-2">
							<label for="chAdresse" class="sr-only">Changer Adresse</label> <input
								type="text" class="form-control" id="chAdresse" name="chAdresse"
								value="<%=c.getAdresse()%>">
						</div>
						<button type="submit" class="btn btn-primary mb-2"
							name="operation" value="chAdresse">Changer</button>
						&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
						<div class="form-group mb-2">
							<label for="codepostal" class="sr-only">Code Postal</label> <input
								type="text" readonly class="form-control-plaintext"
								id="codepostal" value="Code Postal">
						</div>
						<div class="form-group mx-sm-3 mb-2">
							<label for="chCodePostal" class="sr-only">Changer Code
								Postal</label> <input type="text" class="form-control" id="chCodePostal"
								value="<%=c.getCode_postal()%>" name="chCodePostal">
						</div>
						<button type="submit" class="btn btn-primary mb-2"
							name="operation" value="chCodePostal">Changer</button>
					</div>

					<div class="form-group row">
						<div class="form-group mb-2">
							<label for="ville" class="sr-only">Ville</label> <input
								type="text" readonly class="form-control-plaintext" id="ville"
								value="Ville">
						</div>
						<div class="form-group mx-sm-3 mb-2">
							<label for="chVille" class="sr-only">Changer Ville</label> <input
								type="text" class="form-control" id="chVille" name="chVille"
								value="<%=c.getVille()%>">
						</div>
						<button type="submit" class="btn btn-primary mb-2"
							name="operation" value="chVille">Changer</button>
						&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;

						<div class="form-group mb-2">
							<label for="telephone" class="sr-only">Ville</label> <input
								type="text" readonly class="form-control-plaintext"
								id="telephone" value="Téléphone">
						</div>
						<div class="form-group mx-sm-3 mb-2">
							<label for="chTel" class="sr-only">Changer Téléphone</label> <input
								type="text" class="form-control" id="chTel" name="chTel"
								value="<%=c.getTelephone()%>">
						</div>
						<button type="submit" class="btn btn-primary mb-2"
							name="operation" value="chTel">Changer</button>
					</div>
					
					<div class="form-group row">
						<div class="form-group mb-2">
							<label for="oldPwd" class="sr-only">Ancien mot de passe</label> <input
								type="text" readonly class="form-control-plaintext" id="oldPwd"
								value="Ancien mot de passe">
						</div>
						<div class="form-group mx-sm-3 mb-2">
							<label for="oldPwdIn" class="sr-only">Ancien mot de passe</label> <input
								type="password" class="form-control" id="oldPwdIn" name="oldPwdIn" 
								placeholder="Ancien mot de passe">
						</div>
						
						&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
						&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
						&nbsp; &nbsp; &nbsp; &nbsp;

						<div class="form-group mb-2">
							<label for="newPwd" class="sr-only">Nouveau mot de passe</label> <input
								type="text" readonly class="form-control-plaintext" id="newPwd"
								value="Nouveau mot de passe">
						</div>
						<div class="form-group mx-sm-3 mb-2">
							<label for="chPwd" class="sr-only">Nouveau mot de passe</label> <input
								type="password" class="form-control" id="chPwd" name="chPwd"
								placeholder="Nouveau mot de passe">
						</div>
						<button type="submit" class="btn btn-primary mb-2"
							name="operation" value="chPwd">Changer</button>
					</div>

					<c:set var="typeCompte" value="<%=cm.getClass().getName()%>" />

					<div class="form-group row">

						<c:choose>
							<c:when
								test="${typeCompte.equals('com.intiformation.tpcomptes.modele.Compte_Courant')}">
								<div class="form-group mb-2">
									<label for="dm" class="sr-only">Découvert max</label> <input
										type="text" readonly class="form-control-plaintext" id="dm"
										value="Découvert Max">
								</div>
								<div class="form-group mx-sm-3 mb-2">
									<label for='chDm' class='sr-only'>Changer Découvert max</label>
									<input type="text" class="form-control" id="chDm" name="chDm"
										value="<%=((Compte_Courant) cm).getDecouvert_autorise()%>">
								</div>
								<button type="submit" class="btn btn-primary mb-2"
									name="operation" value="chDm">Changer</button>
								&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
						</c:when>
							<c:when
								test="${typeCompte.equals('com.intiformation.tpcomptes.modele.Compte_Epargne')}">
								<div class="form-group mb-2">
									<label for="taux" class="sr-only">Taux d'épargne</label> <input
										type="text" readonly class="form-control-plaintext" id="taux"
										value="Taux d'épargne">
								</div>
								<div class="form-group mx-sm-3 mb-2">
									<label for='chTaux' class='sr-only'>Changer Taux</label> <input
										type="text" class="form-control" id="chTaux" name="chTaux"
										value="<%=((Compte_Epargne) cm).getTaux()%>">
								</div>
								<button type="submit" class="btn btn-primary mb-2"
									name="operation" value="chTaux">Changer</button>
								&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
						</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>



						<button type="submit" class="btn btn-danger mb-2" name="operation"
							value="suppr">Supprimer le compte</button>
					</div>

				</form>

			</div>

			<div id="tabs-2">

				<form class='form-inline'
					action='pageClientServlet?idClient=<%=idc%>' method='post'>

					<div class="form-group mb-2">
						<label for="depot" class="sr-only">Somme à déposer : </label> <input
							type="text" readonly class="form-control-plaintext" id="depot"
							value="Somme à déposer :"> <input type="hidden"
							name="id_client" value="<%=idc%>" />
					</div>
					<div class="form-group mx-sm-3 mb-2">
						<label for="inputDepot" class="sr-only">Somme</label> <input
							type="text" class="form-control" id="inputDepot"
							name="inputDepot">
					</div>
					<button type="submit" class="btn btn-primary mb-2" name="operation"
						value="depot" id="btn-depot">Déposer</button>
				</form>

			</div>

			<div id="tabs-3">


				<form class='form-inline'
					action='pageClientServlet?idClient=<%=idc%>' method='post'>
					<div class="form-group mb-2">
						<label for="retrait" class="sr-only">Somme à retirer : </label> <input
							type="text" readonly class="form-control-plaintext" id="retrait"
							value="Somme à retirer :">
					</div>
					<div class="form-group mx-sm-3 mb-2">
						<label for="inputRetrait" class="sr-only">Somme</label> <input
							type="text" class="form-control" id="inputRetrait"
							name="inputRetrait">
					</div>
					<button type="submit" class="btn btn-primary mb-2" name="operation"
						value="retrait" id="btn-retrait">Retirer</button>
				</form>

			</div>

			<div id="tabs-4">

				<%
					List<Client> clients = cs.findAllExcept(c);
					String listeClientsHtml = "";
					for (Client client : clients) {
						listeClientsHtml += "<option value='" + client.getId_client() + "'>" + client.getPrenom() + " "
								+ client.getNom() + "</option>\n";
					}
				%>

				<form class='form-group'
					action='pageClientServlet?idClient=<%=idc%>' method='post'>

					<div class="form-group row">
						<div class="form-group mb-2">
							<label for="transfert" class="sr-only">Destinataire : </label> <input
								type="text" readonly class="form-control-plaintext" id="dest"
								value="Destinataire :">
						</div>
						<div class="form-group mb-2">
							<fieldset>
								<select name="personnes" id="personnes">
									<%=listeClientsHtml%>
								</select>
							</fieldset>
						</div>
					</div>

					<div class="form-group row">
						<div class="form-group mb-2">
							<label for="transfert" class="sr-only">Somme à transférer
								: </label> <input type="text" readonly class="form-control-plaintext"
								id="transfert" value="Somme à transférer :">
						</div>
						<div class="form-group mb-2">
							<label for="inputTransfert" class="sr-only">Somme</label> <input
								type="text" class="form-control" id="inputTransfert"
								name="inputTransfert">
						</div>
						&nbsp;&nbsp;&nbsp;
						<button type="submit" class="btn btn-primary mb-2"
							name="operation" value="transfert" id="btn-transfert">Transférer</button>
					</div>
				</form>

			</div>


		</div>


	</div>

</body>
</html>