/////////////////////////////////////////////
///////THANG DEVELOPER - INPUT UTIL/////////
///////////////////////////////////////////

        $(document).ready(function () {
    $("form[use=inputUtil]").submit(function () {
        return checkRequire(this);
    });
    $("form[use=inputUtil] button[checkChange=true]").click(function () {
        //get url response
        var urlResponse = $(this).attr("urlResponse");
        //		
        var form = $(this).parentsUntil("body", "form[use=inputUtil]");
        var inputArr = form.find("*[oldValue]");
        for (var i = 0; i < inputArr.size(); i++) {
            var inp = inputArr.eq(i);
            var oldValue = inp.attr("oldValue");
            var newValue = inp.val();
            if (oldValue !== newValue) {
                var msg = $(this).attr("msgChangeConfirm");
                var c;
                if (msg == window.undifined || msg == '')
                    c = confirm("Có trường đã được thay đổi bạn có muốn lưu lại?");
                else
                    c = confirm(msg);
                if (c) {
                    var idBtnSubmit = $(this).attr("btnSubmitChange");
                    var btnSubmit = form.find("#" + idBtnSubmit);
                    btnSubmit.click();
                }
                else {
                    window.location.href = urlResponse;
                }
                return;
            }
        }
        window.location.href = urlResponse;
    });
    function checkRequire(form) {
        //kiem tra cac truong co require = true
        var inputArr = $(form).find("*[require=true]");
        for (var i = 0; i < inputArr.size(); i++) {
            var inp = inputArr.eq(i);
            if (inp.val() == '') {
                var msg = inp.attr("msgRequire");
                if (msg == window.undifined || msg == '')
                    alert("Trường này không được để trống!");
                else
                    alert(msg);
                inp.focus();
                return false;
            }
        }
        return true;
    }
    //su dung cho select
    $("select[use=inputUtil]").on("change", function () {
        var idForm = $(this).attr("idFormSubmit");
        $("#" + idForm).submit();
    });
    //su dung cho the a
    $("a[submitForm]").click(function () {
        var msg = $(this).attr("confirm");
        var c = false;
        if (msg != window.undifined && msg != '') {
            c = confirm(msg);
            if (!c)
                return false;
        }
        var id = $(this).attr("submitForm");
        $("form#" + id).submit();
    });
    //su dung cho checkRadio
    $("form").submit(function () {
        if ($(this).attr("use") == "inputUtil") {
            //kiem tra form co muon check radio khong			
            if ($(this).attr("checkRadioName") !== window.undifined) {
                var radioName = $(this).attr("checkRadioName");
                if ($("input[name=" + radioName + "]:checked").size() == 0) {
                    var msg = $(this).attr("msgRadio");
                    if (msg !== window.undifined && msg !== "") {
                        alert(msg);
                    } else {
                        alert("Vui lòng chọn một giá trị!");
                    }
                    return false;
                } else
                    true;
            }
            return true;
        } else
            return true;
    });
    //su dung trong truong hop muon xoa ban ghi
    $("a.link-remove").click(function () {
        var msg = $(this).attr("msgConfirm");
        var c = false;
        if (msg == '')
            c = confirm('Bạn có chắc chắn muốn xóa bản ghi này!');
        else
            c = confirm(msg);
        if (c) {
            var frmId = $(this).attr("id");
            $("form#" + frmId).submit();
        }
    });
    function isDate(txtDate)
    {
        var currVal = txtDate;
        if (currVal == '')
            return false;

        var rxDatePattern = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/;
        var dtArray = currVal.match(rxDatePattern); // is format OK?
        if (dtArray == null)
            return false;
        dtDay = dtArray[1];
        dtMonth = dtArray[3];
        dtYear = dtArray[5];
        if (dtMonth < 1 || dtMonth > 12)
            return false;
        else if (dtDay < 1 || dtDay > 31)
            return false;
        else if ((dtMonth == 4 || dtMonth == 6 || dtMonth == 9 || dtMonth == 11) && dtDay == 31)
            return false;
        else if (dtMonth == 2)
        {
            var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
            if (dtDay > 29 || (dtDay == 29 && !isleap))
                return false;
        }
        return true;
    }
});
