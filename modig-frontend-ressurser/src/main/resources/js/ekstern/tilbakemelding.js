$(document).ready(function () {
	setupJqueryHandlers();
});

function removeReadOnly() {
	$('.tilbakemelding #tilbakemeldingsfelt').removeAttr('readonly');
}

function skjul() {
	$('.tilbakemelding .ekspandert').slideUp(500);
    var $kollapset = $('.tilbakemelding .kollapset');
    $kollapset.removeClass("reversert");
    $('html,body').animate({scrollTop: $kollapset.offset().top},'slow');
    return false;
}

function hideFormShowError() {
    $('.tilbakemelding .kollapset').addClass("reversert");
	$('.tilbakemelding #tilbakemeldingsform').hide();
	$('.tilbakemelding .tilbakemeldingsfeil').show();
}

function showFormHideError() {
    $('.tilbakemelding .kollapset').addClass("reversert");
	$('.tilbakemelding #tilbakemeldingsform').show();
	$('.tilbakemelding .tilbakemeldingsfeil').hide();
	$('.tilbakemelding .feedbackPanel').hide();
}

setupJqueryHandlers = function () {
	$('.tilbakemelding .kollapset').click(function () {
        $(this).addClass("reversert");
        $('.tilbakemelding .ekspandert').slideDown(200);
        $('html,body').animate({scrollTop: $(this).offset().top},'slow');
		return false;
	});
	$('.tilbakemelding .skjul-ekspandert a').click(function () {
		showFormHideError();
		skjul();
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
