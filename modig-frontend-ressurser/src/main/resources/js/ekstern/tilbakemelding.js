$(document).ready(function () {
	setupJqueryHandlers();
});

function removeReadOnly() {
	$('#tilbakemeldingsfelt').removeAttr('readonly');
}

function slideDown() {
	$('.expanded').slideUp(500);
}

function hideFormShowError() {
	$('#tilbakemeldingsform').hide();
	$('#tilbakemeldingsystemfeil').show();
}

function showFormHideError() {
	$('#tilbakemeldingsform').show();
	$('#tilbakemeldingsystemfeil').hide();
}

setupJqueryHandlers = function () {
	$('.collapsed').click(function () {
		$('.expanded').slideDown(200);
	});
	$('.hide-expanded').click(function () {
		$('.expanded').slideUp(200);
		showFormHideError();
	});
	$('.btn').click(function () {
		$('#tilbakemeldingsfelt').attr('readonly', 'readonly');
	});
	$('#systemfeillukk').click(function () {
		showFormHideError();
	});
};
