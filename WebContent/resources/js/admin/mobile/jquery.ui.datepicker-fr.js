/* French initialisation for the jQuery UI date picker plugin. */

jQuery(function($){
	$.datepicker.regional['fr'] = {
		closeText: '��ng',
		prevText: 'Truoc',
		nextText: 'Sau',
		//currentText: 'Aujourd\'hui',
		monthNames: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6',
			'Tháng 6', 'Tháng 7', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
		
		dayNames: ['dimanche', 'lundi', 'mardi', 'mercredi', 'jeudi', 'vendredi', 'samedi'],
		dayNamesShort: ['dim.', 'lun.', 'mar.', 'mer.', 'jeu.', 'ven.', 'sam.'],
		dayNamesMin: ['D','L','M','M','J','V','S'],
		weekHeader: 'Sem.',
		dateFormat: 'dd/mm/yy',
		firstDay: 1,
		isRTL: false,
		showMonthAfterYear: false,
		yearSuffix: ''};
	$.datepicker.setDefaults($.datepicker.regional['fr']);        
});
