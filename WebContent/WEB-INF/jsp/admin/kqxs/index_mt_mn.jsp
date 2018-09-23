<%-- 
    Document   : index
    Created on : Nov 29, 2014, 9:08:31 AM
    Author     : Designer Nguyễn
--%>

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
        <title>Quản lý tin tức</title>
        <%@include file="../include/css-lib.jsp" %>
        <%@include file="../include/js-lib.jsp" %>
		<script type="text/javascript">
       $(function() {
               $("#date").datepicker({ dateFormat: "dd/mm/yy" }).val()
       });
   </script>
    </head>
    <style>
        input{
            border:  1px red double; 
            padding-top: 5px; 
            margin-top: 10px;
            padding-left: 10px;
        }

        #content {
            margin-left: 50px;
        }

        .btn-primary {
            text-align: center;
            color: #fff;
            height: 25px;
            width: 120px;
            /* padding: 10px; */
            background-color: #337ab7;
            border-color: #2e6da4;
        }
        button {
            margin-top: 20px !important;
        }
    </style>
    <body>
        <div class="container">
            <%@include file="../include/header.jsp" %>
            <script>

                function submitForm() {
                    var res = true;
                    $("input[type='text']", this).each(function () {
                        if ($(this).val().trim() == "") {
                            res = false;
                        }
                    })

                    if (res) {
                        var c = confirm('Bạn có chắc chắn muốn lưu lại tin này?');
                        if (c) {

                            $("#updateForm").submit();
                        }
                    } else {
                        alert('Thiếu dữ liệu');
                    }
                }
            </script>

            <%
                String date = DateUtil.date2String(new Date(), "dd/MM/yyyy");
            %>

            <div id="content">
                
                
                <h1 style="margin-top: 50px;">Nhập kết quả xổ số MT, MN</h1> 

                <table>
                    <form id ="updateForm" method="POST" action="${pageContext.servletContext.contextPath}/admin/kqxs/save_mt_mn.htm" >
                        
                        <tr>

                            <td> <label for="date" class="">Tỉnh : </label> </td>
                            <td> 
                                <select name="tinh">
                                    <option value="HCM"> TP.HCM</option>
                                    <option value="CM"> Cà Mau</option>
                                    <option value="DT"> Đồng Tháp</option>
                                    <option value="BL"> Bạc Liêu</option>
                                    <option value="BT"> Bến Tre</option>
                                    <option value="VT"> Vũng Tàu</option>
                                    <option value="CT"> Cần Thơ</option>
                                    <option value="DN"> Đồng nai</option>
                                    <option value="ST"> Sóc Trăng</option>
                                    <option value="AG"> An Giang</option>
                                    <option value="BTH"> Bình Thuận</option>
                                    <option value="TN"> Tây Ninh</option>
                                    <option value="BD"> Bình Dương</option>
                                    <option value="TV"> Trà Vinh</option>
                                    <option value="VL"> Vĩnh Long</option>
                                    <option value="BP"> Bình Phước</option>
                                    <option value="HG"> Hậu Giang</option>
                                    <option value="LA"> Long An</option>
                                    <option value="KG"> Kiên Giang</option>
                                    <option value="LD"> Lâm Đồng (Đà Lạt)</option>
                                    <option value="TG"> Tiền Giang</option>
                                    <option value="PY"> Phú Yên</option>
                                    <option value="TTH"> Thừa Thiên Huế</option>
                                    <option value="DLK"> Đắc Lắc </option>
                                    <option value="QNM"> Quảng Nam</option>
                                    <option value="DNG"> Đà Nẵng</option>
                                    <option value="KH"> Khánh Hòa</option>
                                    <option value="BDI"> Bình Định</option>
                                    <option value="QB"> Quảng Bình</option>
                                    <option value="QT"> Quảng Trị</option>
                                    <option value="GL"> Gia Lai</option>
                                    <option value="NT"> Ninh Thuận</option>
                                    <option value="DNO"> Đắc Nông</option>
                                    <option value="QNG"> Quảng Ngãi</option>
                                    <option value="KT"> Kon Tum</option>
                                    
                                    
                                </select>
                            </td>
                        </tr>
                        
                        <tr>

                            <td> <label for="date" class="">Ngày : </label> </td>
                            <td> <input class="input" type="text" name="date" id="date" value="<%=date%> "
                                        style="width: 70px"> </td>
                        </tr>

                        <tr>
                            <td> <label for="date" class="">GĐB : </label> </td>
                            <td> <input class="input" type="number" style="width: 70px;" name="special" required> </td>
                        </tr>
                        <tr>
                            <td><label for="date" class="">Giải nhất : </label></td>
                            <td> <input class="input" type="number" style="width: 70px" name="first" required > </td>
                        </tr>

                        <tr>
                            <td><label for="date" class="">Giải nhì : </label></td>
                            <td><input class="input" type="number" style="width: 70px" name="second" required ></td>
                        </tr>

                        <tr>
                            <td><label for="date" class="">Giải ba : </label></td>
                            <td> <input class="input" type="number" style="width: 70px" name="third" required >
                                - <input class="input" type="number" style="width: 70px" name="third" required>
                                </td>

                        </tr>

                        <tr>
                            <td><label for="date" class="">Giải tư :  </label></td>
                            <td><input class="input" type="number" style="width: 70px" name="fourth" required> 
                                - <input class="input" type="number" style="width: 70px" name="fourth" required>
                                - <input class="input" type="number" style="width: 70px" name="fourth" required>
                                - <input class="input" type="number" style="width: 70px" name="fourth" required>
                                - <input class="input" type="number" style="width: 70px" name="fourth" required>
                                - <input class="input" type="number" style="width: 70px" name="fourth" required>
                                - <input class="input" type="number" style="width: 70px" name="fourth" required></td>
                        </tr>
                        <tr>
                            <td><label for="date" class="">Giải năm : </label></td>
                            <td><input class="input" type="number" style="width: 70px" name="fifth" required>
                               </td>
                        </tr>
                        <tr>
                            <td><label for="date" class="">Giải sáu : </label> </td>
                            <td><input class="input" type="number" style="width: 70px" name="sixth" required>
                                - <input class="input" type="number" style="width: 70px" name="sixth" required>
                                - <input class="input" type="number" style="width: 70px" name="sixth" required> </td>
                        <br/>
                        </tr>
                        <tr>
                            <td> <label for="date" class="">Giải bảy : </label> </td>
                            <td> <input class="input" type="number" style="width: 70px" name="seventh" required>
                                </td>
                        </tr>
                        
                        <tr>
                            <td> <label for="date" class="">Giải tám : </label> </td>
                            <td> <input class="input" type="number" style="width: 70px" name="eighth" required>
                                </td>
                        </tr>
                        <p></p>
                        <tr >
                            <td></td>

                            <td> <button type="submit"  class="btn-primary">Save</button> </td></tr>
                    </form>
                </table>

            </div>
        </div><!--end content-->
        <%@include file="../include/footer.jsp" %>
    </body>
    
</html>