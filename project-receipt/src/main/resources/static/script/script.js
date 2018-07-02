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
  var $clone = $TABLE.find('tr.hide').clone(true).removeClass('hide').addClass('control');
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

$('#taxVal').on('focusout', function() {
	
	var editedTax = $('#taxVal').val();
	
	if (editedTax == "") {
		return;
	}
	
	tax = parseFloat($('#taxVal').val(), 10);
	
	$('#taxVal').val(tax);
	
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
	
	$('#taxVal').val(tax);
	
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
	
	var $hiddenRow = $TABLE.find('tr.hide');
	$hiddenRow.remove();
	
	var $headerForm = $('#headerForm');
	
	if(! $headerForm[0].checkValidity()) {
		
		$TABLE.find('table').append($hiddenRow);
		return;
	}

	$('#success-foreground').css("zIndex", 1);
	$('#success-load').removeClass('hide').addClass('animated bounceInLeft');
	setTimeout(function() {
		$('#success-load').removeClass('fadeInLeft').addClass('fadeOut');
		
		setTimeout(function() {
			$('#success-check').removeClass('hide').addClass('animated fadeIn');
			setTimeout(function() {
				$('#success-check').removeClass('fadeIn').addClass('bounceOutRight');
				$('#success-foreground').css("zIndex", -1);
			}, 500);
		}, 500);
		
	}, 2000);
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

$('#addAddress').click(function(e) {
	if ($('#addAddress').is('[disabled=disabled]')) {
		return;
	}
	
	resetModal();
	toggleModalClasses(e);
});

$('#closeAddress').click(toggleModalClasses);

$('.modal-background').click(toggleModalClasses);

function toggleModalClasses(event) {
    var modalId = event.currentTarget.dataset.modalId;
    var modal = $(modalId);
    modal.toggleClass('is-active');
    $('html').toggleClass('is-clipped');
};

$('#storeName').on('focusout', function() {
	
	var storeName = $('[name=storeName]').val();
	
	if (storeName == null || storeName == "") {
		$('#addAddress').attr('disabled', 'disabled');
		return;
	}
	
	retrieveAddress(storeName);
	
	$('#addAddress').removeAttr('disabled');
});

function retrieveAddress(storeName) {
	$('[name=addressDummyText]').find('option').remove();
	
	$.get("getAddress",
	    {
	      storeName: storeName
	    },
	    function(data){
	    	var add = data.addressList;
	    	$.each(add, function(key, value) {
	    		$('[name=addressDummyText]')
	    	     	.append($('<option>', { value : add[key].addressId })
	    	        .text( data.addressDummyText[key] ));
	    	});
	    });
}

$('#addressCreateButton').click(function(e) {
	 
	var $myForm = $('#addressForm');
	
	if(! $myForm[0].checkValidity()) {
		  // If the form is invalid, submit it. The form won't actually submit;
		  // this will just cause the browser to display the native HTML5 error messages.
		$('<input type="submit">').hide().appendTo($myForm).click().remove();
		return;
	}
	
	$('#addressCreateButton').addClass('is-loading');
	
	e.preventDefault();
	
	var req = {
			"street": $('#address1').val(),
			"city": $('#city').val(),
			"zip": $('#postalCode').val(),
			"state": $('#state').val(),
			"country": $('#country').val(),
			"storeName": $('#storeName').val()
	}

	var tmp = serverPost(req, "addAddress");
	
	tmp.done(function(data) {
		retrieveAddress($('#storeName').val());
		
		$('#addressCreateButton').removeClass('is-loading').addClass('is-outlined').text('Done').attr('disabled', 'disabled');
	});
});

function serverPost(req, url) {

	return $.ajax({
	    url: url,
	    dataType: 'json',
	    type: 'post',
	    contentType: 'application/json',
	    data: JSON.stringify( req ),
	    processData: false,
	    success: function( data ){
	    	if (data.status === "success") {
//	    		alert(data.id);
	    	}
	    	else if (data.status === "empty field(s)") {
	    		alert("All fields must not be empty");
	    	}
	    	else if (data.status === "address fail") {
	    		alert("Address creation fail :(");
	    	}
	    },
	    error: function( jqXhr, textStatus, errorThrown ){
	    	var msg = '';
	        if (jqXhr.status === 0) {
	            msg = 'Not connect.\n Verify Network.';
	        } else if (jqXhr.status == 404) {
	            msg = 'Requested page not found. [404]';
	        } else if (jqXhr.status == 500) {
	            msg = 'Internal Server Error [500].';
	        } else if (errorThrown === 'parsererror') {
	            msg = 'Requested JSON parse failed.';
	        } else if (errorThrown === 'timeout') {
	            msg = 'Time out error.';
	        } else if (errorThrown === 'abort') {
	            msg = 'Ajax request aborted.';
	        } else {
	            msg = 'Uncaught Error.\n' + jqXhr.responseText;
	        }
	        alert(msg);
	    }
	});
}

function resetModal() {
	$('#address1').val("");
	$('#city').val("");
	$('#postalCode').val("");
	$('#state').val("Texas");
	$('#country').val("US");
	
	$('#addressCreateButton').removeClass('is-loading is-outlined').text('Create').removeAttr('disabled');
}