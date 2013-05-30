$(document).ready(function () {
	setupJqueryHandlers();
});

function removeReadOnly() {
	$('.tilbakemelding #tilbakemeldingsfelt').removeAttr('readonly');
}

function skjul() {
	$('.tilbakemelding .ekspandert').slideUp(500).hide();
}

function hideFormShowError() {
	$('.tilbakemelding #tilbakemeldingsform').hide();
	$('.tilbakemelding #tilbakemeldingsystemfeil').show();
}

function showFormHideError() {
	$('.tilbakemelding #tilbakemeldingsform').show();
	$('.tilbakemelding #tilbakemeldingsystemfeil').hide();
}

setupJqueryHandlers = function () {
	$('.tilbakemelding .kollapset').click(function () {
		$('.tilbakemelding .ekspandert').slideDown(200);
	});
	$('.tilbakemelding .skjul-ekspandert').click(function () {
		$('.tilbakemelding .ekspandert').slideUp(200);
		showFormHideError();
	});
	$('.tilbakemelding .knapp-hoved').click(function () {
		$('.tilbakemelding #tilbakemeldingsfelt').attr('readonly', 'readonly');
	});
	$('.tilbakemelding #systemfeillukk').click(function () {
		showFormHideError();
	});
};
