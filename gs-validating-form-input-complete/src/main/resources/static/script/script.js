flatpickr(".date", {
    enableTime: true,
});

var $TABLE = $('#table');

var isTabOut = false;
$(window).keyup(function (e) {
    var code = (e.keyCode ? e.keyCode : e.which);
    if (code == 9) {        	
    	isTabOut = true;
    }
});

$('.table-add').click(function () {
  var $clone = $TABLE.find('tr.hide').clone(true).removeClass('hide table-line').addClass('control');
  $TABLE.find('table').append($clone);
  
//  $clone.toggleClass('change_me newClass');
  
  var a = parseInt($('#srnos').text(), 10) + 1;
  $('#srnos').text(a);
});

$('.table-remove').click(function () {
  $(this).parents('tr').detach();
});

//$('.table-up').click(function () {
//  var $row = $(this).parents('tr');
//  if ($row.index() === 1) return; // Don't go above the header
//  $row.prev().before($row.get(0));
//});
//
//$('.table-down').click(function () {
//  var $row = $(this).parents('tr');
//  $row.next().after($row.get(0));
//});

$('.uPrice').on('focusout', function() {
	var $row = $(this).parent().parent();
	
	var qty = $row.find(".qty").val();
	var unitPrice = $row.find(".uPrice").val();
	
	var priceSum = qty * unitPrice;
	
	$row.find(".price").text(priceSum);
	$row.find(".price").children('input').val(priceSum);
	
	getTotal();
});

$('.control .checkbox').click(function () {
	
	var tax = 0.00;
	
	if ($(this).prop('checked')) {
		tax = $(this).parent().prev.text();
	}
});

function getTotal() {
	var total = 0;
	
	$('.control .price').each(function() {
		var tax = $(this).next().children('.checkbox').prop('checked');
		
		total += parseFloat($(this).text(), 10);
	});

	$('#dummyTotal').text(total);
	$('#total').val(total);
	
	return;
}