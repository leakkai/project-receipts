flatpickr(".date", {
    enableTime: true,
});

var $TABLE = $('#table');
var isTabOut = false;

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

$('.uPrice').on('focusout', function() {
	if (isTabOut) {
		var $row = $(this).parent().parent();
		
		var qty = $row.find(".qty").val();
		var unitPrice = $row.find(".uPrice").val();
		
		var priceSum = qty * unitPrice;
		
		$row.find(".price").text(priceSum);
	}
});

$(window).keyup(function (e) {
    var code = (e.keyCode ? e.keyCode : e.which);
    if (code == 9) {        	
    	isTabOut = true;
    }
});