<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/resources/js/jquery-2.1.3.min.js"></script>

<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/resources/js/jquery-1.8.2.js"></script>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/resources/js/jquery-ui-1.9.0.custom.min.js"></script>

<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/resources/js/jquery.mmenu.min.all.js"></script>

<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/resources/js/javascript.js"></script>



<script>
	//creates the calendar

	$(window).load(function() {
		
		$(".datepicker").on("change", function() {
			var string = $(this).val();
			console.log("date", string);
			var mm = string.substring(0, 2);
			var dd = parseInt(string.substring(3, 5));
			var ddString = string.substring(3, 5);
			var yyyy = string.substring(6, 10);
			var date = new Date();

			var chosedDate = ddString + "-" + mm + "-" + yyyy;

			var url = '${pageContext.servletContext.contextPath}/';
			url = url + "kqxs-" + chosedDate + ".html";

			if (eval(yyyy) === eval(date.getFullYear())) {
				if (eval(mm - 1) === eval(date.getMonth())) {
					if (eval(dd) <= eval(date.getDate())) {
						window.location.href = url;
					}
				} else if (eval(mm-1) < eval(date.getMonth())) {
					window.location.href = url;
				}
			}

		});
		
	var isHiddenAdv = getCookie("isHiddenAdv");
		
		if ( isHiddenAdv == "true"){
			for(var i=0;i<14;i++) {
				$("#banner_hidden"+i).hide();
			}
			$("#btn_seo").text("Hiện quảng cáo");
		} else {
			$("#btn_seo").text("Ẩn quảng cáo");
		}

	});
	
	function setCookie(cname, cvalue, exdays) {
	    var d = new Date();
	    d.setTime(d.getTime() + (exdays*24*60*60*1000));
	    var expires = "expires="+ d.toUTCString();
	    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
	}
	
	function getCookie(cname) {
	    var name = cname + "=";
	    var decodedCookie = decodeURIComponent(document.cookie);
	    var ca = decodedCookie.split(';');
	    for(var i = 0; i <ca.length; i++) {
	        var c = ca[i];
	        while (c.charAt(0) == ' ') {
	            c = c.substring(1);
	        }
	        if (c.indexOf(name) == 0) {
	            return c.substring(name.length, c.length);
	        }
	    }
	    return "";
	}
	
	function disableAdv() {
			
			var isHiddenAdv = getCookie("isHiddenAdv");
			
			if ( $("#banner_hidden0").css('display') == 'none' ){
			    // element is hidden
				for(var i=0;i<14;i++) {
					$("#banner_hidden"+i).show();
				}
				setCookie("isHiddenAdv", "", 1);
				$("#btn_seo").text("Ẩn quảng cáo");
			} else {
				for(var i=0;i<14;i++) {
					$("#banner_hidden"+i).hide();
				}
				setCookie("isHiddenAdv", "true", 0.1);
				$("#btn_seo").text("Hiện quảng cáo");
			}
			
	}
</script>

<script>
	function handleOptions(id) {
		var url = "";
		if (id == 1) {
			url = "${pageContext.servletContext.contextPath}/"
					+ "quay-thu-ket-qua-xo-so-mien-bac-xsmb.html";
			$("#province_code2").hide();
			$("#province_code1").show();
		} else {
			url = "${pageContext.servletContext.contextPath}/"
					+ "quay-thu-ket-qua-xo-so-binh-dinh-xsbdh.html";
			$("#province_code1").hide();
			$("#province_code2").show();
		}

		window.location.href = url;
	}

	function handelCodeChange(id) {
		var value = $('#province_code' + id).val();
		var fields = value.split('_');
		var url = "${pageContext.servletContext.contextPath}/"
				+ "quay-thu-ket-qua-xo-so-" + fields[1] + "-" + fields[0]
				+ ".html";
		window.location.href = url;
	}

	function reloadUrl() {
		location.reload();
	}

	function changeThongKeDBMonth() {
		var month = $('#select_db_month').val();
		var temp = '${pageContext.servletContext.contextPath}';
		//conglt
		if (!temp) {
			temp = '/xosohn'
		}
		loadAjax_DBThang(temp + "/thongke/db_thang.htm?month=" + month);
	}

	function loadAjax_DBThang(url) {
		$.ajax({
			type : 'POST',
			url : url,
			dataType : 'text',
			cache : false
		}).done(function(sResponse) {
			$("#db_thang").html(sResponse);
		});
	}

	function changeThongKeDBWeek() {
		var week = $('#select_db_week').val();
		var temp = '${pageContext.servletContext.contextPath}';
		//conglt
		if (!temp) {
			temp = '/xosohn'
		}
		loadAjax_DBWeek(temp + "/thongke/db_week.htm?week=" + week);
	}

	function loadAjax_DBWeek(url) {
		$.ajax({
			type : 'POST',
			url : url,
			dataType : 'text',
			cache : false
		}).done(function(sResponse) {
			$("#db_week").html(sResponse);
		});
	}

	function changeThongKeDBYear() {
		var year = $('#select_db_year').val();
		var temp = '${pageContext.servletContext.contextPath}';
		//conglt
		if (!temp) {
			temp = '/xosohn'
		}
		loadAjax_DBYear(temp + "/thongke/db_year.htm?year=" + year);
	}

	function loadAjax_DBYear(url) {
		$.ajax({
			type : 'POST',
			url : url,
			dataType : 'text',
			cache : false
		}).done(function(sResponse) {
			$("#db_year").html(sResponse);
		});
	}
</script>
