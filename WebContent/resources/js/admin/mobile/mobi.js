/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// sidebar menu
$(function(){
	var wide = '200';
	var isExpanded_left = false;

	function openLeft() {
		$('.menu-super').animate({ right: '0' }, { duration: 500, queue: false });
		$('.bg_menu-super').animate({ right: '0' }, { duration: 500, queue: false });
		$('#container').animate({ 'margin-right': wide + 'px' }, { duration: 500, queue: false });
		$('#container').animate({ 'margin-left': '-' + wide + 'px' }, { duration: 500, queue: false });
		isExpanded_left = true;
	}

	function closeLeft() {
		$('.menu-super').animate({ right: '-' + wide }, { duration: 500, queue: false });
		$('.bg_menu-super').animate({ right: '-' + wide }, { duration: 500, queue: false });
		$('#container').animate({ 'margin-right': '0' }, { duration: 500, queue: false });
		$('#container').animate({ 'margin-left': '0' }, { duration: 500, queue: false });
		isExpanded_left = false;
	}

	$(".toggle-menu").click(function () {
		if (!isExpanded_left) {
			openLeft();
		} else {
			closeLeft();
		}
	});
});

