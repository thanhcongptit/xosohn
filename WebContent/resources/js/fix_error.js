	var currentUrl = document.URL;
	currentUrl = currentUrl.toLowerCase();
	var indexDudoan = currentUrl.indexOf("/du-doan-");
	var isReplace = false;
	
	if(currentUrl.includes("/xosohn/")) {
		currentUrl = currentUrl.replace("xosohn/", "");
		isReplace = true;
	}
	
	if(indexDudoan> -1 && !currentUrl.includes("du-doan-xs")) {
		isReplace = true;
		currentUrl = currentUrl.toLowerCase().replace(" ", "-");
		currentUrl = currentUrl.toLowerCase().replace("%20", "-");
		currentUrl = currentUrl.toLowerCase().replace("phu-yen", "xspy");
		currentUrl = currentUrl.toLowerCase().replace("binh-dinh", "xsbdh");
		currentUrl = currentUrl.toLowerCase().replace("daklak", "xsdlk");
		currentUrl = currentUrl.toLowerCase().replace("quang-nam", "xsqnm");
		currentUrl = currentUrl.toLowerCase().replace("ho-chi-minh", "xshcm");
		currentUrl = currentUrl.toLowerCase().replace("dong-nai", "xsdn");
		
		currentUrl = currentUrl.toLowerCase().replace("an-giang", "xsag");
		currentUrl = currentUrl.toLowerCase().replace("tien-giang", "xstg");
		currentUrl = currentUrl.toLowerCase().replace("kien-giang", "xskg");
		currentUrl = currentUrl.toLowerCase().replace("tay-ninh", "xstn");
		currentUrl = currentUrl.toLowerCase().replace("soc-trang", "xsst");
		currentUrl = currentUrl.toLowerCase().replace("vinh-long", "xsvl");
		
		currentUrl = currentUrl.toLowerCase().replace("ben-tre", "xsbtr");
		currentUrl = currentUrl.toLowerCase().replace("binh-thuan", "xsbth");
		currentUrl = currentUrl.toLowerCase().replace("dong-thap", "xsdt");
		currentUrl = currentUrl.toLowerCase().replace("long-an", "xsla");
		currentUrl = currentUrl.toLowerCase().replace("khanh-hoa", "xskh");
		currentUrl = currentUrl.toLowerCase().replace("da-nang", "xsdng");
		
		currentUrl = currentUrl.toLowerCase().replace("quang-ngai", "xsqni");
		currentUrl = currentUrl.toLowerCase().replace("da-lat", "xsdl");
		currentUrl = currentUrl.toLowerCase().replace("kon-tum", "xskt");
		currentUrl = currentUrl.toLowerCase().replace("binh-duong", "xsbd");
		currentUrl = currentUrl.toLowerCase().replace("quang-binh", "xsqb");
		currentUrl = currentUrl.toLowerCase().replace("gia-lai", "xsgl");
		
		currentUrl = currentUrl.toLowerCase().replace("ninh-thuan", "xsnt");
		currentUrl = currentUrl.toLowerCase().replace("tra-vinh", "xstv");
		currentUrl = currentUrl.toLowerCase().replace("can-tho", "xsct");
		currentUrl = currentUrl.toLowerCase().replace("vung-tau", "xsvt");
		currentUrl = currentUrl.toLowerCase().replace("bac-lieu", "xsbl");
		currentUrl = currentUrl.toLowerCase().replace("ca-mau", "xscm");
		
		currentUrl = currentUrl.toLowerCase().replace("binh-phuoc", "xsbp");
		currentUrl = currentUrl.toLowerCase().replace("thua-thien-hue", "xstth");
		currentUrl = currentUrl.toLowerCase().replace("hau-giang", "xshg");
		currentUrl = currentUrl.toLowerCase().replace("dac-nong", "xsdno");
		currentUrl = currentUrl.toLowerCase().replace("quang-tri", "xsqt");
		
		currentUrl = currentUrl.toLowerCase().replace("mien-bac", "xsmb");
		currentUrl = currentUrl.toLowerCase().replace("mien-trung", "xsmt");
		currentUrl = currentUrl.toLowerCase().replace("mien-nam", "xsmn");
		
		
	} else if(currentUrl.includes("xsbtr-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsbtr-ket-qua-xo-so-ben-tre.html"
	}  else if(currentUrl.includes("xspy-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xspy-ket-qua-xo-so-phu-yen.html"
	}  else if(currentUrl.includes("xsbdh-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsbdh-ket-qua-xo-so-binh-dinh.html"
	} else if(currentUrl.includes("xsdlk-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsdlk-ket-qua-xo-so-daklak.html"
	}  else if(currentUrl.includes("xsqnm-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsqnm-ket-qua-xo-so-quang-nam.html"
	}  else if(currentUrl.includes("xshcm-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xshcm-ket-qua-xo-so-tphcm.html"
	} else if(currentUrl.includes("xsdn-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsdn-ket-qua-xo-so-dong-nai.html"
	}  else if(currentUrl.includes("xsag-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsag-ket-qua-xo-so-an-giang.html"
	}  else if(currentUrl.includes("xstg-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xstg-ket-qua-xo-so-tien-giang.html"
	} else if(currentUrl.includes("xskg-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xskg-ket-qua-xo-so-kien-giang.html"
	}  else if(currentUrl.includes("xstn-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xstn-ket-qua-xo-so-tay-ninh.html"
	}  else if(currentUrl.includes("xsst-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsst-ket-qua-xo-so-soc-trang.html"
	} else if(currentUrl.includes("xsvl-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsvl-ket-qua-xo-so-vinh-long.html"
	}  else if(currentUrl.includes("xsbth-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsbth-ket-qua-xo-so-binh-thuan.html"
	}  else if(currentUrl.includes("xsdt-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsdt-ket-qua-xo-so-dong-thap.html"
	} else if(currentUrl.includes("xsla-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsla-ket-qua-xo-so-long-an.html"
	}  else if(currentUrl.includes("xskh-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xskh-ket-qua-xo-so-khanh-hoa.html"
	}  else if(currentUrl.includes("xsdng-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsdng-ket-qua-xo-so-da-nang.html"
	} else if(currentUrl.includes("xsqni-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsqni-ket-qua-xo-so-quang-ngai.html"
	}  else if(currentUrl.includes("xsdl-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsdl-ket-qua-xo-so-da-lat.html"
	}  else if(currentUrl.includes("xskt-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xskt-ket-qua-xo-so-kon-tum.html"
	} else if(currentUrl.includes("xsbd-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsbd-ket-qua-xo-so-binh-duong.html"
	}  else if(currentUrl.includes("xsqb-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsqb-ket-qua-xo-so-quang-binh.html"
	}  else if(currentUrl.includes("xsgl-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsgl-ket-qua-xo-so-gia-lai.html"
	}  else if(currentUrl.includes("xsnt-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsnt-ket-qua-xo-so-ninh-thuan.html"
	}  else if(currentUrl.includes("xstv-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xstv-ket-qua-xo-so-tra-vinh.html"
	}  else if(currentUrl.includes("xsct-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsct-ket-qua-xo-so-can-tho.html"
	} else if(currentUrl.includes("xsvt-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsvt-ket-qua-xo-so-vung-tau.html"
	}  else if(currentUrl.includes("xsbl-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsbl-ket-qua-xo-so-bac-lieu.html"
	}  else if(currentUrl.includes("xscm-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xscm-ket-qua-xo-so-ca-mau.html"
	} else if(currentUrl.includes("xsbp-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsbp-ket-qua-xo-so-binh-phuoc.html"
	}  else if(currentUrl.includes("xstth-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xstth-ket-qua-xo-so-thua-thien-hue.html"
	}  else if(currentUrl.includes("xshg-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xshg-ket-qua-xo-so-hau-giang.html"
	} else if(currentUrl.includes("xsdno-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsdno-ket-qua-xo-so-dac-nong.html"
	}  else if(currentUrl.includes("xsqt-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsqt-ket-qua-xo-so-quang-tri.html"
	} else if(currentUrl.includes("xsmt-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsmt-ket-qua-xo-so-mien-trung.html"
	}  else if(currentUrl.includes("xsmn-.html")) {
		isReplace = true;
		currentUrl = "https://xosohn.com/xsmn-ket-qua-xo-so-mien-nam.html"
	}
	
	if(isReplace) {
		location.replace(currentUrl);
	}