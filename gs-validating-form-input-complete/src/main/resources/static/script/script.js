flatpickr(".date", {
    enableTime: true,
});

var $TABLE = $('#table');

$('.table-add').click(function () {
  var $clone = $TABLE.find('tr.hide').clone(true).removeClass('hide table-line');
  $TABLE.find('table').append($clone);
  
  var a = parseInt($('#srnos').text(), 10) + 1;
  $('#srnos').text(a);
});

$('.table-remove').click(function () {
  $(this).parents('tr').detach();
});

$('.table-up').click(function () {
  var $row = $(this).parents('tr');
  if ($row.index() === 1) return; // Don't go above the header
  $row.prev().before($row.get(0));
});

$('.table-down').click(function () {
  var $row = $(this).parents('tr');
  $row.next().after($row.get(0));
});

if($('#qty').val()) {
	$('#price').text($('#qty').val());
}

$('#uPrice').on('focus', function(e) {
    $(window).keyup(function (e) {
        var code = (e.keyCode ? e.keyCode : e.which);
        if (code == 9) {
        	$('#price').text($('#qty').val());
        }
    });
});