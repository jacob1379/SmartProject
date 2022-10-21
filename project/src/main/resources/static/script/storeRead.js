const sno = 20;
async function getResult() {
    const result = await $.ajax({
        url : "/store",
        method : "get",
        data : {sStoreNum : sno}
    });
    return result ;
}
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
	$("#sStoreName").attr('value',a.result.sstoreName);
$("#sStoreAddress").attr('value',a.result.sstoreAddress);
$("#sMinDeleVery").attr('value',a.result.sminDeleVery);
$("#sStoreTime").attr('value',a.result.sstoreTime);
$("#sStoreIntro").attr('value',a.result.sstoreIntro);
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
	const a  = getResult();
    $("#showLogo").attr("src",a.result.sstoreLogo);
    console.log(a.result);
    placeholder(a);
    $("#storeUpdate").click(()=>{
        storeUpdate();
    })
})