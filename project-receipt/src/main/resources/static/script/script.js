flatpickr(".date", {
    enableTime: true,
});

var $TABLE = $('#table');

/*$(window).keyup(function (e) {

	var code = (e.keyCode ? e.keyCode : e.which);
    if (code == 9 && $('.uPrice:focusout').length) {        	
    	return true;
    }
});*/

var total = 0.00;
var tax = 0.00;
var tips = 0.00;

var count = 1;

//	Clone the first detail row, reset all data then add to the most bottom
function addRow() {
	var $clone = $TABLE.find('tr:eq(1)').clone(true);
	count++;
	resetData($clone);
	$TABLE.find('table').append($clone);
	
	
	$clone.find('[name="name[]"]').focus();
}

//	Delete that row
function delRow($e) {
	var $row = $e.parents('tr');
	
	if ($e.parents('tbody').find('tr').length === 1) {
		return;
	}
	
	$e.parents('tr').detach();
}

//	Reset all data to empty in a row
function resetData($clone) {
	$clone.children('.sr-no').text(count);
	
	$clone.find('[name="name[]"]').val('');
	$clone.find('[name="quantity[]"]').val('');
	$clone.find('[name="unitPrice[]"]').val('');
	$clone.find('[name="price[]"]').val('');
	$clone.find('.price').text('');
	$clone.find('[name="tax[]"]').val('');
	$clone.find('.taxBox').addClass('is-inverted');
}

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

//	Calculate the overall price. This method is specifically made for after unit price
function calculatePrice($e) {
	var $row = $e.parent().parent();
	
	var qty = $row.find('[name="quantity[]"]').val();
	var unitPrice = $row.find('[name="unitPrice[]"]').val();
	
	var priceSum = qty * unitPrice;
	
	$row.find(".price").text(priceSum);
	$row.find('[name="price[]"]').val(priceSum);
	
	total += priceSum;
}

//	Calculate grand total after tax been modified manually
function calculateAfterTax() {
	var editedTax = $('#taxVal').val();
	
	if (null === editedTax || editedTax === "" || editedTax === 0) {
		return;
	}
	
	tax = parseFloat(editedTax, 10);
	
	$('#taxVal').val(tax);
}

//	Calculate a tax for each detail and add to total tax
function taxBoxClicked($e) {
	var tempPrice = $e.parent().prev().children('.price').text();
	
	var indiTax = 0;
	
	if ($e.prev().val() === '' || $e.prev().val() <= 0) {
		indiTax = setTax(parseFloat(tempPrice, 10), true);
		$e.prev().val(indiTax);
		$e.removeClass('is-inverted');
	}
	else {
		indiTax = setTax(parseFloat(tempPrice, 10), false);
		$e.prev().val(0);
		$e.addClass('is-inverted');
	}
}

//	Set global var -- tips
function setTips($e) {
	tips = parseFloat($e.val(), 10);
}

function getTotal() {
	
	var tmptotal = 0;
	var $details = $TABLE.find('tbody').children('.control');
	$details.each(function() {
		tmptotal += parseFloat($(this).find('[name="price[]"]').val(), 10);
	});
	tmptotal = round(tmptotal, 2);
	total = tmptotal;
	
	$('#dummyTotal').text(tmptotal);
	$('#total').val(tmptotal);
	
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

//	Smooth scrolling after clicking some elements, temporally disable
/*$('#toTable').click(function(e) {
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
});*/

function toggleModalClasses(event) {
    var modalId = event.currentTarget.dataset.modalId;
    var modal = $(modalId);
    modal.toggleClass('is-active');
    $('html').toggleClass('is-clipped');
};

function enableAddressButton(storeName) {
	
	if (storeName === null || storeName === "") {
		$('#addAddress').attr('disabled', 'true');
		return;
	}
	
	$('#addAddress').removeAttr('disabled');
}

function retrieveAddress(storeName) {
	$('[name=addressId]').find('option').remove();
	
	if (storeName !== null && storeName !== "") {
		var data = serverGet("", "getAddress/"+storeName);
	
		return data;
	}
}

function createAddress() {	
	var req = {
			"street": $('#address1').val(),
			"city": $('#city').val(),
			"zip": $('#postalCode').val(),
			"state": $('#state').val(),
			"country": $('#country').val(),
			"storeId": $('#storeId').val()
	}

	var tmp = serverPost(req, "addAddress");
	
	return tmp;
}

function setAddressList(addList) {
	$.each(addList, function(key, value) {
		$('[name=addressId]')
			.append($('<option>', { value : addList[key].id })
					.text(addList[key].address));
	});
	
	$('#addressId').val(addList[0].id);
	
	//must get storeid from address return
	$('#storeId').val(addList[0].storeId);
}

function saveTransaction($req) {
//	var data = $req.serializeArray();
//	var data = $req.serialize();
	var data = $req.serializeObject();
	
	return serverPost(data, "process");
}

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
	    	else {
//	    		alert(data.status);
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

function serverGet(req, url) {
	return $.ajax({
	    url: url,
	    dataType: 'json',
	    type: 'get',
	    contentType: 'application/json',
//	    data: JSON.stringify( req ),
	    processData: false,
	    success: function( data ){
	    	if (data.status === "success") {
//	    		alert(data.id);
	    	}
	    	else {
	    		alert(data.status);
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

function setDetailCategory($e) {
	var value = $e.attr('id');
	
	var $toBePop = $e.parent().next().next();
	
	$toBePop.find('a').remove();
	
	$e.parent().find('input').val(value[value.length-1]);
}





















(function($){
    $.fn.serializeObject = function(){

        var self = this,
            json = {},
            push_counters = {},
            patterns = {
                "validate": /^[a-zA-Z][a-zA-Z0-9_]*(?:\[(?:\d*|[a-zA-Z0-9_]+)\])*$/,
                "key":      /[a-zA-Z0-9_]+|(?=\[\])/g,
                "push":     /^$/,
                "fixed":    /^\d+$/,
                "named":    /^[a-zA-Z0-9_]+$/
            };


        this.build = function(base, key, value){
            base[key] = value;
            return base;
        };

        this.push_counter = function(key){
            if(push_counters[key] === undefined){
                push_counters[key] = 0;
            }
            return push_counters[key]++;
        };

        $.each($(this).serializeArray(), function(){

            // skip invalid keys
            if(!patterns.validate.test(this.name)){
                return;
            }

            var k,
                keys = this.name.match(patterns.key),
                merge = this.value,
                reverse_key = this.name;

            while((k = keys.pop()) !== undefined){

                // adjust reverse_key
                reverse_key = reverse_key.replace(new RegExp("\\[" + k + "\\]$"), '');

                // push
                if(k.match(patterns.push)){
                    merge = self.build([], self.push_counter(reverse_key), merge);
                }

                // fixed
                else if(k.match(patterns.fixed)){
                    merge = self.build([], k, merge);
                }

                // named
                else if(k.match(patterns.named)){
                    merge = self.build({}, k, merge);
                }
            }

            json = $.extend(true, json, merge);
        });

        return json;
    };
})(jQuery);



function sleep(milliseconds) {
  var start = new Date().getTime();
  for (var i = 0; i < 1e7; i++) {
    if ((new Date().getTime() - start) > milliseconds){
      break;
    }
  }
}