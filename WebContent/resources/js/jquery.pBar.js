/**
 * pBar 1.0 https://github.com/lastdates/pBar
 */
!function(i){var r,t=";min-height:14px;border-radius:",a=function(i){return r[parseInt(i*r.length/101)]};i.fn.pBar=function(){return this.each(function(){var t=i(this),n=t.data("from"),o=t.data("to");r=(t.data("color")||"#b32,#d31,#ea6,#fa6,#fd8,#ad6,#9c6,#5b6,#6a7").split(","),t.html('<div style="width:'+n+"%;background-color:"+a(n)+'"></div>').prop("title",o+"%").attr("onclick","$(this).pBar();"),t.children("div").width(o+"%").css("background-color",a(o))})},i("<style>.pBar{display:inline-block;min-width:150px"+t+"2px;border:1px solid #ccc;padding:1px; margin:5px auto;}.pBar div{height:100%"+t+"1px;transition:all 1s linear 0s;box-sizing:border-box}</style>").appendTo("head"),i(document).ready(function(){i(".pBar").pBar()})}(jQuery);
