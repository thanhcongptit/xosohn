
<%@page import="inet.model.NewsSCMBChinhXacDAO"%>
<%@page import="inet.model.NewsSCMBDAO"%>
<%@page import="inet.model.News888DAO"%>
<%@page import="inet.model.News99DAO"%>
<%@page import="inet.model.NewsDBDAO"%>
<%@page import="inet.constant.Constants"%>
<%@page import="inet.model.DreamsDAO"%>
<%@page import="inet.bean.Dream"%>
<%@page import="inet.model.LotteryCompanyDAO"%>
<%@page import="inet.bean.LotteryCompany"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.soicaupro.thongkebacnho.CommonUtil"%>
<%@page import="inet.bean.MetaGuide"%>
<%@page import="inet.model.MetaGuideDao"%>
<%@page import="inet.model.NewsDAO"%>
<%@page import="inet.bean.News"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="inet.util.DateUtil"%>
         

<head>
<meta name="msvalidate.01" content="5C6E91E38296079233B81B810D61922C" />
<meta http-equiv="Content-Language" content="vi">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="icon" rel="Shortcut icon" type="image/x-icon" href="${pageContext.servletContext.contextPath}/resources/img/xs.png"/>
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" />
	
 <script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/resources/js/fix_error.js"></script>	
<script type="text/javascript">
	var currentUrl = document.URL;
	currentUrl = currentUrl.toLowerCase();
	
	if(currentUrl.includes("/xosohn/")) {
		currentUrl = currentUrl.replace("xosohn/", "");
		location.replace(currentUrl);
	}
</script>

<%
	String positionString = CommonUtil.getPosition(request);
	String temps[] = positionString.split("_");
	
	String guidePage = "";
	String titlePage = "";
	String metaPage = "";
	String textPage = "";
	String keyword = "";
	String publishDate = "";
	String articleSection = "";
	String urlImage = Constants.URL_CAN;
	
	String breadcrumbListId= "";

	if (temps.length > 1) {
		positionString = temps[0];
	}

	int position = Integer.parseInt(positionString);

	if (position > 0) {
		MetaGuide metaGuide = new MetaGuideDao().findByPosition(position);
		if (metaGuide != null) {
			titlePage = metaGuide.getTitle();
			metaPage = metaGuide.getMeta();
			textPage = metaGuide.getFooter();
			keyword = metaGuide.getKeyword();

			if (temps.length > 1) {

				if (position >= 26 && position <= 29) {
					Date date = DateUtil.toDate(temps[1], "dd-MM-yyyy");
					String currentDate = DateUtil.date2String(date, "dd/MM/yyyy");
					titlePage = titlePage.replace("{ngay}", currentDate);
					metaPage = metaPage.replace("{ngay}", currentDate);
					textPage = textPage.replace("{ngay}", currentDate);
				} else if ((position == 30 || position == 35 || position >= 38) && position != 47) {
					String code = temps[1];
					LotteryCompany company = new LotteryCompany();

					if(position != 48) {
						if (code.equalsIgnoreCase("xsmb")) {
							company.setCode("xsmb");
							company.setCompany("miền bắc");
						} else if (code.equalsIgnoreCase("xsmt")) {
							company.setCode("xsmt");
							company.setCompany("miền trung");
						} else if (code.equalsIgnoreCase("xsmn")) {
							company.setCode("xsmn");
							company.setCompany("miền nam");
						} else {
							String name = new LotteryCompanyDAO().findCompanyName(code);
							company.setCode(code.toLowerCase());
							company.setCompany(name.toLowerCase());
						}
					} else {
						if(code.toLowerCase().startsWith("xs")) {
							if (code.equalsIgnoreCase("xsmb")) {
								company.setCode("xsmb");
								company.setCompany("miền bắc");
							} else if (code.equalsIgnoreCase("xsmt")) {
								company.setCode("xsmt");
								company.setCompany("miền trung");
							} else if (code.equalsIgnoreCase("xsmn")) {
								company.setCode("xsmn");
								company.setCompany("miền nam");
							}  else {
								company = new LotteryCompanyDAO().findCompanyCode(code.toUpperCase());
							}
						} else {
							company = new LotteryCompanyDAO().findCompanyByName(code.replace("-", " "));
						}
					}

					if(company != null) {
						titlePage = titlePage.replace("{code}", company.getCode());
						titlePage = titlePage.replace("{company}", company.getCompany());

						metaPage = metaPage.replace("{code}", company.getCode());
						metaPage = metaPage.replace("{company}", company.getCompany());

						textPage = textPage.replace("{code}", company.getCode());
						textPage = textPage.replace("{company}", company.getCompany());
					}

				} else if(position == 47) {
					String code = temps[1];
					LotteryCompany company = new LotteryCompany();
					String name = new LotteryCompanyDAO().findCompanyName(code);
					company.setCode(code.toLowerCase());
					company.setCompany(name.toLowerCase());
					
					titlePage = titlePage.replace("{code}", company.getCode());
					titlePage = titlePage.replace("{company}", company.getCompany());
					metaPage = metaPage.replace("{code}", company.getCode());
					metaPage = metaPage.replace("{company}", company.getCompany());
					textPage = textPage.replace("{code}", company.getCode());
					textPage = textPage.replace("{company}", company.getCompany());
					
					String currentDate = temps[2].replace("-", "/");
					titlePage = titlePage.replace("{ngay}", currentDate);
					metaPage = metaPage.replace("{ngay}", currentDate);
					textPage = textPage.replace("{ngay}", currentDate);

				}

			}
		} else {
			if (position == 45) {
				String id = request.getParameter("id");
				News news = new NewsDAO().getRowTitle(new BigDecimal(id));
				titlePage = news.getTitleSeo();
				metaPage = news.getMetaSeo();
				keyword = news.getKeyword();
				urlImage = urlImage + "/images/news/"+news.getImageUrl();
				publishDate = DateUtil.timestamp2String(news.getPublishDate(), "yyyy-MM-dd'T'hh:mm:ss");
				articleSection = "Dự đoán";
				breadcrumbListId = "/du-doan.html";
			} else if (position == 46) {
				String id = request.getParameter("id");
				Dream drea = new DreamsDAO().getRowTitle(new BigDecimal(id));
				titlePage = drea.getTitleSeo();
				metaPage = drea.getMetaSeo();
				keyword = drea.getKeyword();
				publishDate = DateUtil.timestamp2String(drea.getPublishDate(), "yyyy-MM-ddThh:mm:ss");
				breadcrumbListId = "/so-mo-lo-de.html";
				articleSection = "Sổ mơ lô đề";
			} else if (position == 54) {
				String id = request.getParameter("id");
				Dream drea = new NewsDBDAO().getRowTitle(new BigDecimal(id));
				titlePage = drea.getTitleSeo();
				metaPage = drea.getMetaSeo();
				keyword = drea.getKeyword();
				publishDate = DateUtil.timestamp2String(drea.getPublishDate(), "yyyy-MM-ddThh:mm:ss");
				breadcrumbListId = "/thong-ke-2-so-cuoi-giai-dac-biet.html";
				articleSection = "Thống kê 2 số cuối giải đặc biệt";
			} else if (position == 55) {
				String id = request.getParameter("id");
				Dream drea = new News99DAO().getRowTitle(new BigDecimal(id));
				titlePage = drea.getTitleSeo();
				metaPage = drea.getMetaSeo();
				keyword = drea.getKeyword();
				publishDate = DateUtil.timestamp2String(drea.getPublishDate(), "yyyy-MM-ddThh:mm:ss");
				breadcrumbListId = "/soi-cau-99.html";
				articleSection = "Soi cầu 99";
			} else if (position == 56) {
				String id = request.getParameter("id");
				Dream drea = new News888DAO().getRowTitle(new BigDecimal(id));
				titlePage = drea.getTitleSeo();
				metaPage = drea.getMetaSeo();
				keyword = drea.getKeyword();
				publishDate = DateUtil.timestamp2String(drea.getPublishDate(), "yyyy-MM-ddThh:mm:ss");
				breadcrumbListId = "/soi-cau-888.html";
				articleSection = "Soi cầu 888";
			} else if (position == 57) {
				String id = request.getParameter("id");
				Dream drea = new NewsSCMBDAO().getRowTitle(new BigDecimal(id));
				titlePage = drea.getTitleSeo();
				metaPage = drea.getMetaSeo();
				keyword = drea.getKeyword();
				publishDate = DateUtil.timestamp2String(drea.getPublishDate(), "yyyy-MM-ddThh:mm:ss");
				breadcrumbListId = "/soi-cau-mb.html";
				articleSection = "Soi cầu MB";
			} else if (position == 58) {
				String id = request.getParameter("id");
				Dream drea = new NewsSCMBChinhXacDAO().getRowTitle(new BigDecimal(id));
				titlePage = drea.getTitleSeo();
				metaPage = drea.getMetaSeo();
				keyword = drea.getKeyword();
				publishDate = DateUtil.timestamp2String(drea.getPublishDate(), "yyyy-MM-ddThh:mm:ss");
				breadcrumbListId = "/soi-cau-xsmb-chinh-xac-nhat.html";
				articleSection = "Soi cầu xsmb chính xác nhất";
			}
		}
		
		if(position == 36) {
			articleSection = "Dự đoán";
			breadcrumbListId = "/du-doan.html";
		} else if(position == 37) {
			breadcrumbListId = "/so-mo-lo-de.html";
			articleSection = "Sổ mơ lô đề";
		} else if(position == 49) {
			breadcrumbListId = "/thong-ke-2-so-cuoi-giai-dac-biet.html";
			articleSection = "Thống kê 2 số cuối giải đặc biệt";
		} else if(position == 50) {
			breadcrumbListId = "/soi-cau-99.html";
			articleSection = "Soi cầu 99";
		} else if(position == 51) {
			breadcrumbListId = "/soi-cau-888.html";
			articleSection = "Soi cầu 888";
		} else if(position == 52) {
			breadcrumbListId = "/soi-cau-mb.html";
			articleSection = "Soi cầu MB";
		} else if(position == 53) {
			breadcrumbListId = "/soi-cau-xsmb-chinh-xac-nhat.html";
			articleSection = "Soi cầu xsmb chính xác nhất";
		}
		

	}

	/* if(position == 1 || position == 24 || 
			position == 7 || position == 15 ||  
			position == 11 || position == 61 ) {
		Date date1 = new Date();
		String dayOfWeek = DateUtil.getWeekFromDate(DateUtil.date2String(date1, "dd/MM/yyyy"));
		String currentDateHeader = DateUtil.date2String(date1, "dd-MM-yyyy");
		titlePage = titlePage + " " + dayOfWeek + " ngày " + currentDateHeader;
		metaPage = metaPage + " " + dayOfWeek + " ngày " + currentDateHeader;
	} */
%>
<title><%=titlePage%></title>
<meta name="description" content="<%=metaPage%>" />
<meta name="keywords" content="<%=keyword %>" />

<meta name="robots" content="index,follow" />

<meta property="og:type" content="article" />
<meta property="og:site_name" content="xosohn" />
<meta property="og:title" content="<%=titlePage%>" />
<meta property="og:description" content="<%=metaPage%>" />
<meta property="og:url" content="${url_ca}" />
<%if(position == 45) { %>
<meta property="og:image" content="<%=urlImage%>" />
<% }%>



<!-- SEO -->
<link rel="canonical" href="${url_ca}" />


<!-- css -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/jquery.mmenu.all.css">
<%-- <link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/dcalendar.picker.css"> --%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/style.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/jquery-ui.css">
<!--js-->
<%-- <script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/resources/js/jquery-2.1.3.min.js"></script>
 --%>

<%-- <link rel="Shortcut Icon" type="image/ico"
	href="${pageContext.servletContext.contextPath}/resources/img/xs.png"> --%>
	
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-119023560-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-119023560-1');
  
</script>	

<!-- du lieu co cau truc -->
<script type="application/ld+json">
{
  "@context" : "http://schema.org",
  "@type" : "Organization",
  "name" : "xosohn",
  "url" : "https://xosohn.com/",
  "logo": "https://xosohn.com/resources/img/xs.png"
}
</script>
<%if(position == 46 || position == 45) {%>

	<script type="application/ld+json">
        {
            "@context": "http://schema.org",
            "@type": "NewsArticle",
            "mainEntityOfPage":{ "@type":"WebPage", "@id":"${url_ca}" },
            "url": "${url_ca}",
            "dateCreated": "<%=publishDate%>+07:00",
            "articleSection": ["<%=articleSection%>"],
            "headline": "<%=titlePage%>",
            "description": "<%=metaPage%>",
            "datePublished": "<%=publishDate%>+07:00",
            "dateModified": "<%=publishDate%>+07:00",
            "author":
            { "@type": "Person", "name": "admin" }
            ,
            "publisher": {
            "@type": "Organization",
            "name": "xosohn",
            "logo":
            { "@type": "ImageObject", "url": "https://xosohn.com/resources/img/xs.png", "width": 120, "height": 60 }
            }<%if(position == 46 || position == 45) {%> ,"image": {
                "@type": "ImageObject",
                "url": "<%=urlImage%>",
                "width": 1200,
                "height": 675
            }
		<%}%>
	}
      </script>  
      
<%}%>
<%if(position == 46 || position == 45 || position == 36 || position == 37) {%>
<script type="application/ld+json">
        {
        "@context": "http://schema.org",
        "@type": "BreadcrumbList",
        "itemListElement": 
        [
            {
                "@type": "ListItem",
                "position": 1,
                "item": 
                {
                  "@id": "https://xosohn.com/",
                  "name": "Trang chủ"
                }
            },
            {
                "@type": "ListItem",
                "position": 2,
                "item": 
                {
                  "@id": "<%=breadcrumbListId%>",
                  "name": "<%=articleSection%>"
                }
            }
        ]
        }
    </script>
      
<%}%>

</head>