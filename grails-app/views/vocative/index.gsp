<html>
<head>
<title>Salutr</title>
<meta name="layout" content="main">
</head>
<body>
	<h1>Skloňování jména</h1>

	<form action=".">
		<p>
			Jméno (1. pád):
			<g:textField name="nominative" value="${nominative}" />
			<g:submitButton name="decline" value="Skloňuj!" />
		</p>
	</form>

	<p>
		Oslovení (5. pád):
		${vocative}
	</p>

	<g:if test="${flash.message}">
		<div class="flash">
			${flash.message}
		</div>
	</g:if>
<g:javascript>
$('input#nominative').focus();
</g:javascript>

</body>
</html>
