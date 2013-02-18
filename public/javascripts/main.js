$(document).ready(function() {
	$('#declineForm input[name=name]').focus();

	if (!$("#vocative").text()) {
		$("#result-container").hide();
	}
	$("#declineForm").submit(function() {
		var name = $("#declineForm input[name=name]").val();
		if (name) {
			$.ajax({
				url : "vocative.json",
				type : "GET",
				data : {
					name : name
				},
				success : function(data) {
					if ($("#result-container").is(":hidden")) {
						$("#result-container").slideDown("slow", function() {
							$("#vocative").hide().text(data.vocative).fadeIn();
						});
					} else {
						$("#vocative").fadeOut(function() {
							$(this).text(data.vocative).fadeIn()
						});
					}
				}
			});
		} else {
			$("#vocative").fadeOut(function() {
				$("#result-container").slideUp("slow", function() {
					$(this).hide();
				});
			});
		}
		return false;
	});
});