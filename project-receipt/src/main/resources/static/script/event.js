//Close date UI and focus on store name after tab out from am/pm
$('.flatpickr-am-pm').on('focusout', function(e) {
	$('.flatpickr-calendar').removeClass('open');
	
	$('#storeName').focus();
});

//	Tab-out from unit price 
$('[name="unitPrice[]"]').on('focusout', function(e) {
	//calculate price
	calculatePrice($(this));
	
	getTotal();
	
	getGrandTotal();
	
	//add dummy row
    addRow();
});

//	Click delete detail icon, simply delete
$('.table-remove').click(function () {
	delRow($(this));
});

//	Tab-out from tax
$('#taxVal').on('focusout', function() {
	calculateAfterTax();
	getGrandTotal();
});

//	Tax box clicked for each line
$('.taxBox').click(function () {
	taxBoxClicked($(this));
	
	getGrandTotal();
});

//	Tab-out from tips
$('#tips').on('focusout', function() {
	setTips($(this));
	getGrandTotal();
});

//	Add address clicked, open modal
$('#addAddress').click(function(e) {
	if ($('#addAddress').is('[disabled]')) {
		return;
	}
	
	resetModal();
	toggleModalClasses(e);
});

//	Close modal
$('#closeAddress').click(toggleModalClasses);

//	Close modal when click on dark background
$('.modal-background').click(toggleModalClasses);

//	Enable/disable add address after tabbing out from store name
$('#storeName').on('focusout', function() {
	
	var storeName = $(this).val();
	
	enableAddressButton(storeName);
	
	var result = retrieveAddress(storeName);
	
	result.done(function(data) {
		if (data.status === "success") {
	
			var addList = data.object;
			
			$.each(addList, function(key, value) {
				$('[name=addressId]')
					.append($('<option>', { value : addList[key].id })
							.text(addList[key].address));
			});
			
			$('#addressId').val(addList[0].id);
		}
	});
});

//	Tab out from address list, set value
$('#addressId').on('focusout', function() {
//	$('#addressId').val($("#addressId option:selected").val());
});

//	Create address clicked
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
	
	var result = createAddress();
	
	result.done(function(data) {
		retrieveAddress($('#storeName').val());
		
		$('#addressCreateButton').removeClass('is-loading').addClass('is-outlined').text('Done').attr('disabled', 'disabled');
	});
});

//	Show dropdown for each detail category when hover
$('.category-dropdown').mouseenter(function() {
	$(this).children('.dropdown-menu').children('.dropdown-content').removeClass('hide');
});

//	Hide dropdown for each detail category when mouse leave
$('.category-dropdown').mouseleave(function() {
	$(this).children('.dropdown-menu').children('.dropdown-content').addClass('hide');
});

//	Set the rest of the detail category when being set on top
$('#categorySelection').on('focusout', function() {
	var cat = $('#categorySelection').val();
	
	$('.ct-parent').find('#cat'+cat).click();
});

//	Set detail category
$('.categoryTag').click(function () {
	$(this).parent().find('.tag').height('2em');
	$(this).height('3em');

	setDetailCategory($(this));
});

//	Save transaction
$('#saveTransaction').click(function (e) {
	
	var $headerForm = $('#headerForm');
	
	if(! $headerForm[0].checkValidity()) {
		return;
	}
	
	e.preventDefault();

	$('#success-foreground').css("zIndex", 1);
	$('#success-load').removeClass('hide').addClass('animated bounceInLeft');
	
	var result = saveTransaction($headerForm);
	
	result.done(function(data) {
		$('#success-load').removeClass('fadeInLeft').addClass('fadeOut');
		
		if (data.status === "success") {
			$('#success-check').removeClass('hide').addClass('animated fadeIn');
			$('#success-check').removeClass('fadeIn').addClass('bounceOutRight');
			alert("YESSS");
		}
		else {
			//load "X" animation
			alert("sumting wong");
		}
	});
	
	$('#success-foreground').css("zIndex", -1);
});