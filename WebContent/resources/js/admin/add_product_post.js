function createPost() {
    $.ajax({
        type: "POST",
        data: {
            action: 'create_post'
        },
        success: function (result) {
            window.location.href = contextPath + "/admin/post/add/index.jsp";
        }
    });
}

function addProduct(param) {
    $.ajax({
        type: "POST",
        url: contextPath + "/admin/post/ajax/add-product.jsp?" + param,
        data: {
            action: 'addProduct'
        },
        success: function (result) {
            rendTable();
        }
    });
}

function removeProduct(videoId) {
    $.ajax({
        type: "POST",
        url: contextPath + "/admin/post/ajax/add-product.jsp",
        data: {
            action: 'removeProduct',
            videoId: videoId
        },
        success: function (result) {
            $('#tr' + videoId).remove();
        }
    });
}

function editPost(idPost, i) {
    $.ajax({
        type: "POST",
        data: {
            action: 'edit_post',
            post_id: idPost
        },
        success: function (result) {
            $('#editForm' + i).submit();
        }
    });
}
function editPostShare(idPostShare, i) {
    $.ajax({
        type: "POST",
        data: {
            action: 'edit_post_share',
            post_share_id: idPostShare
        },
        success: function (result) {
            $('#editForm' + i).submit();
        }
    });
}