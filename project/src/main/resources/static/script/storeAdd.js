function loadImg () {
    const file = $("#logoimg")[0].files[0];
    const maxSize = 1024*1024;
    if(file.size>maxSize) {
		alert("파일을 사용 할 수 없습니다.");
		$("#logoimg").val("");
		$("#showLogo").removeAttr("src");
		return false;
	}
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = ()=>{
        $("#showLogo").attr("src",reader.result);
    }    
    return true;
}


function storeAdd() {
    const formData = new FormData($("#add_form")[0]);
    console.log(formData);
    $.ajax({
        url : "/store/new",
        method : "post",
        data : formData,
        processData : false,
        contentType : false
    }).done(()=>{
        alert('success!!');
    }).fail(()=>{
        alert('fail!!');
    })
};

$(document).ready(()=>{
    $("#logoimg").on("change", loadImg);
    $("#storeAdd").click(()=>{
        storeAdd();
    })
})