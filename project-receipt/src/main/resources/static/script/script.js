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

var total = 0.00;
var tax = 0.00;
var tips = 0.00;

$('.table-add').click(function () {
  var $clone = $TABLE.find('tr.hide').clone(true).removeClass('hide table-line').addClass('control');
  $TABLE.find('table').append($clone);
  
  var a = parseInt($('#srnos').text(), 10) + 1;
  $('#srnos').text(a);
});

$('.table-remove').click(function () {
	var $row = $(this).parents('tr');
	
	if ($row.index() === 1) return; // Don't go above the header
	
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
	$row.find(".priceVal").val(priceSum);
	
	total += priceSum;
	
	getTotal();
	
	getGrandTotal();
});

$('.taxBox').click(function () {
	
	var tempPrice = $(this).parent().prev().children('.price').text();
	
	var indiTax = 0;
	
	if ($(this).prev().val() <= 0 || $(this).prev().val() == '') {
		indiTax = setTax(parseFloat(tempPrice, 10), true);
		$(this).prev().val(indiTax);
		$(this).removeClass('is-inverted');
	}
	else {
		indiTax = setTax(parseFloat(tempPrice, 10), false);
		$(this).prev().val(0);
		$(this).addClass('is-inverted');
	}
	
	getGrandTotal();
});

$('#tips').on('focusout', function() {
	tips = parseFloat($(this).val(), 10);
	
	getGrandTotal();
});

function getTotal() {
	
	$('.control .price').each(function() {
//		var tax = $(this).parent().next().children('.checkbox').prop('checked');
		
//		total += parseFloat($(this).text(), 10);
	});
	total = round(total, 2);
	
	$('#dummyTotal').text(total);
	$('#total').val(total);
	
	return;
}

function setTax(aTax, isAdd) {

	var taxVal = $('#taxVal').val();
	if (taxVal == '') {
		taxVal = 0.00;
	}
	
	var totalTax = parseFloat(taxVal, 10);
	aTax = aTax * 0.0825;
	
	if (isAdd) {
		$('#taxVal').val(round(totalTax + aTax, 2));
	}
	else {
		$('#taxVal').val(round(totalTax - aTax, 2));
	}
	
	tax = parseFloat($('#taxVal').val(), 10);
	
	$('#taxText').text(tax);
	
	return aTax;
}

function round(number, precision) {
	var shift = function (number, precision, reverseShift) {
		if (reverseShift) {
			precision = -precision;
		}  
		
		var numArray = ("" + number).split("e");
		
		return +(numArray[0] + "e" + (numArray[1] ? (+numArray[1] + precision) : precision));
	};
	return shift(Math.round(shift(number, precision, false)), precision, true);
}

function getGrandTotal() {
	var grand = round(total + tax + tips, 2);
	
	$('#grandTotalVal').val(grand);
	$('#grandTotalText').text(grand);
}

$('#saveTransaction').click(function () {
	$TABLE.find('tr.hide').remove();
});


$('.table-add').click(function () {
	  var $clone = $TABLE.find('tr.hide').clone(true).removeClass('hide table-line').addClass('control');
	  $TABLE.find('table').append($clone);
	  
	  var a = parseInt($('#srnos').text(), 10) + 1;
	  $('#srnos').text(a);
	});



$('#toTable').click(function(e) {
    // target element id
    var id = $TABLE;
    
    // target element
    var $id = $(id);
    if ($id.length === 0) {
        return;
    }
    
    // prevent standard hash navigation (avoid blinking in IE)
    e.preventDefault();
    
    // top position relative to the document
    var pos = $id.offset().top;
    
    // animated top scrolling
    $('body, html').animate({scrollTop: pos});
});