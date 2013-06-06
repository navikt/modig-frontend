$(document).ready(function () {
	setupJqueryHandlers();
});

function removeReadOnly() {
	$('.tilbakemelding #tilbakemeldingsfelt').removeAttr('readonly');
}

function skjul() {
	$('.tilbakemelding .ekspandert').slideUp(500);
    $('.tilbakemelding .kollapset').removeClass("reversert");
}

function hideFormShowError() {
	$('.tilbakemelding #tilbakemeldingsform').hide();
	$('.tilbakemelding .tilbakemeldingsfeil').show();
}

function showFormHideError() {
	$('.tilbakemelding #tilbakemeldingsform').show();
	$('.tilbakemelding .tilbakemeldingsfeil').hide();
	$('.tilbakemelding .feedbackPanel').hide();
}

setupJqueryHandlers = function () {
	$('.tilbakemelding .kollapset').click(function () {
        $(this).addClass("reversert");
		$('.tilbakemelding .ekspandert').slideDown(200);
		return false;
	});
	$('.tilbakemelding .skjul-ekspandert a').click(function () {
		skjul();
		showFormHideError();
		return false;
	});
	$('.tilbakemelding .knapp-hoved').click(function () {
		$('.tilbakemelding #tilbakemeldingsfelt').attr('readonly', 'readonly');
	});
	$('.tilbakemelding #systemfeillukk').click(function () {
		showFormHideError();
		return false;
	});
};
