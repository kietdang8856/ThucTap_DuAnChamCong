$(document).ready(function() {
    $('input').each(function() {
        if ($(this).val() !== '') {
            $(this).next('label').css('top', '0').css('font-size', '12px').css('color', '#333');
        }
    });

    $('input').focus(function() {
        $(this).next('label').css('top', '0').css('font-size', '12px').css('color', '#333');
        $(this).css('border-color', '#007bff');
    });

    $('input').blur(function() {
        if ($(this).val() === '') {
            $(this).next('label').css('top', '50%').css('font-size', '').css('color', '#999');
            $(this).css('border-color', '#ccc');
        }
    });
});