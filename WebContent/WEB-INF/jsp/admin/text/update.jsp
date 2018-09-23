<%-- 
    Document   : index
    Created on : Nov 29, 2014, 9:08:31 AM
    Author     : Designer Nguyễn
--%>

<%@page import="inet.model.DescriptionDao"%>
<%@page import="inet.bean.Description"%>
<%@page import="com.soicaupro.thongkebacnho.CommonUtil"%>
<%@page import="inet.util.EntityDecoder"%>
<%@page import="java.util.Date"%>
<%@page import="inet.util.DateUtil"%>
<%@page import="inet.bean.News"%>
<%@page import="inet.model.NewsDAO"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="inet.bean.Member"%>
<%@page import="inet.util.RequestUtil"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý text</title>
        <%@include file="../include/css-lib.jsp" %>
        <%@include file="../include/js-lib.jsp" %>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/tinymce/tinymce.min.js"></script>
    </head>
    <body>
        <div class="container">
            <%@include file="../include/header.jsp" %>
            <style>
                .error{color:red}
                .ok{color:green}
            </style>
            <script>
                tinymce.init({
                    selector: "textarea#content",
                    plugins: [
                        "advlist autolink autosave link image lists charmap print preview hr anchor pagebreak spellchecker",
                        "searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking",
                        "table contextmenu directionality emoticons template textcolor paste fullpage textcolor colorpicker textpattern"
                    ],
                    toolbar1: "code | bold italic underline strikethrough | alignleft aligncenter alignright alignjustify | formatselect fontselect fontsizeselect ",
                    toolbar2: "bullist numlist | outdent indent | link unlink image media | table | removeformat | emoticons | forecolor backcolor | preview fullscreen ",
                    menubar: false,
                    toolbar_items_size: 'small',
                    style_formats: [
                        {title: 'Bold text', inline: 'b'},
                        {title: 'Red text', inline: 'span', styles: {color: '#ff0000'}},
                        {title: 'Red header', block: 'h1', styles: {color: '#ff0000'}},
                        {title: 'Example 1', inline: 'span', classes: 'example1'},
                        {title: 'Example 2', inline: 'span', classes: 'example2'},
                        {title: 'Table styles'},
                        {title: 'Table row 1', selector: 'tr', classes: 'tablerow1'}
                    ],
                    templates: [
                        {title: 'Test template 1', content: 'Test 1'},
                        {title: 'Test template 2', content: 'Test 2'}
                    ]
                });
                $(document).ready(function () {
                setTimeout(function () {
                    $(".mce-i-code").html("HTML");
                    $(".mce-i-code").css("font-size", "10px");
                    $(".mce-i-code").css("font-weight", "bold");
                    $(".mce-i-code").parent().css("padding-right", "16px");
                }, 500);
            });
                function updateStatus(status,id){                    
                    $("#updateForm input[name=action]").val("updateStatus");
                    $("#updateForm input[name=id]").val(id);
                    $("#updateForm input[name=status]").val(status);
                    $("#updateForm").submit();
                }
                function deleteTKV(id){
                    var c = confirm('Bạn có chắc chắn muốn xóa dự đoán này?');
                    if(c){
                        $("#updateForm input[name=action]").val("delete");
                        $("#updateForm input[name=id]").val(id);
                        $("#updateForm").submit();
                    }
                }
                $(function() {
                    $( "input[name=genDate]" ).datepicker();
                 });
            </script>
            <%
                String msg = "";
                String action = RequestUtil.getString(request, "action", "");
                BigDecimal id = RequestUtil.getBigDecimal(request, "id", null);
                
                DescriptionDao dao = new DescriptionDao();
                Description des = dao.findById(id);
                if("update".equals(action) && id != null){
                 
                    //String content = RequestUtil.getString(request, "content", "");
                    String content = request.getParameter("content");
                    
                    if(CommonUtil.isEmptyString(content)) {
                        content = "";
                    }
                    
                    des.setCode(EntityDecoder.convertHtmlEntityToChar(content));
                    if(dao.update(des)){
                        msg = "<span style='color:green'>Cập nhật tin bài thành công!</span>";
                    }else{
                        msg = "Cập nhật tin bài thất bại!";
                    }
                }
                Description desUpdate = null;
                if(id != null){
                    desUpdate = dao.findById(id);
                }
            %>
            <form style="display: none" id="updateForm" method="post">
                <input type="hidden" name="action"/>
                <input type="hidden" name="id"/>
                <input type="hidden" name="status"/>
            </form>
            <div class="content">
                <div class="formWrapper">
                    <h1>Cập nhật tin XS</h1>
                    <div class="form1">
                        <div style="color: red"><%= msg %></div>
                        <%
                            if(desUpdate != null){
                        %>
                        <form method="post">
                            <table class="table-auto">
                                <tr>
                                    <td>Vị trí/td>
                                    <td>
                                       <%=desUpdate.getPositionName() %>
                                    </td>
                                </tr>
                                
                                <tr>
                                    <td>Nội dung</td>
                                    <td>
                                        <textarea id="content" name="content"><%= desUpdate.getCode() %></textarea>
                                    </td>
                                </tr>
                                <tr>                                    
                                    <td>
                                        <input type="hidden" name="action" value="update"/>
                                        <button type="submit" class="btnCMS">Cập nhật</button>
                                    </td>
                                    <td><a href="<%= request.getContextPath() %>/admin/quan-ly-text.htm" class="btnCMS">Trở về</a></td>
                                </tr>
                            </table>
                        </form>
                        <%
                            }else out.print("Tin bài này không tồn tại!");
                        %>
                    </div>
                </div><!--end form-->                
            </div><!--end ndung-->
        </div><!--end content-->
        <%@include file="../include/footer.jsp" %>
    </body>
</html>