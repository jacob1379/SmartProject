const sno = 21;
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
function placeholder(a) {
	$("#sStoreName").attr('placeholder',a.result.sstoreName);
$("#sStoreAddress").attr('placeholder',a.result.sstoreAddress);
$("#sMinDeleVery").attr('placeholder',a.result.sminDeleVery);
$("#sStoreTime").attr('placeholder',a.result.sstoreTime);
$("#sStoreIntro").attr('placeholder',a.result.sstoreIntro);
$("#sStoreNum").val(a.result.sstoreNum);
}

function storeUpdate() {
    const formData = new FormData($("#update_form")[0]);
    $.ajax({
        url : "/store/update",
        method : "put",
        data : formData ,
        processData : false,
        contentType : false
    }).done(()=>{
        alert('변경완료!');
    }).fail(()=>{
        alert('변경실패!');
    })
}
$(document).ready(async ()=>{
	$("#logoimg").on("change", loadImg);
	const a = await $.ajax({
        url : "/store",
        method : "get",
        data : {sStoreNum : sno}
    });
    console.log(a.result);
    
    placeholder(a);
    $("#storeUpdate").click(()=>{
        storeUpdate();
    })
})