
$(document).ready(function () {
    var b = true;
	$('.categories .container #btnToggle').click(function(){
		if (b) {
			$('.categories .container .navHidden').stop().slideDown();
			b = false;
			}
			else {
				$('.categories .container .navHidden').stop().slideUp(150);
				b = true;
				}
		});
		
	$('#homepage, .foodter, .header').click(function(){
		$('.categories .container .navHidden').stop().slideUp(150);
		b = true;
		});
});