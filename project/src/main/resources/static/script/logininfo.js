async function getLogininfo () {
	const loginId = await $.ajax({
		url : "/consumer/getloginuserinfo"		
	})

	const info = loginId.result;

	if(info != "비회원"){
		$("#headerLogin").remove();
		const $header = "#header"
		
		$("<span>").text(info).attr("value","mypage").appendTo($header);
		$("<button>").text("로그아웃").attr("id","logout").appendTo($header);
		
	}
}

$(document).on("click", "#logout", function(){
	$("<form>").attr("id", "logoutform").attr("action","/consumer/logout").attr("method","post").appendTo("#header");
	$("#logoutform").submit();
})