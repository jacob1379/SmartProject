
// 아이디 패턴 체크 함수
function sIdCheck() {
	$("#sId_msg").text("");
	const pattern = /^[0-9A-Z]{8,10}$/;
	const $sId = $("#sId").val().toUpperCase();
	$("#sId").val($sId);
	const result = pattern.test($sId);
	if(result==false)
		$("#sId_msg").text("아이디는 대문자와 숫자 8~10자입니다").attr("class", "fail")
	return result;
}


// 이메일 패턴 체크 함수
function sEmailCheck() {
	$("#sEmail_msg").text("");
	const pattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	const $sEmail = $("#sEmail").val();
	const result = pattern.test($sEmail);
	if(result==false)
		$("#sEmail_msg").text("이메일을 정확하게 입력하세요").attr("class", "fail");
	return result;
}



// 이름 패턴 체크 함수
function sNameCheck() {
	$("#sName_msg").text("");
	const pattern = /^[가-힣]{2,10}$/; 
	const $sName = $("#sName").val();
	const result = pattern.test($sName);
	if(result==false)
		$("#sName_msg").text("이름은 한글 2~10자입니다");
	return result;
}



// 비밀번호 패턴 체크 함수
function sPasswordCheck() {
	$("#sPassword_msg").text("");
	// (?=.*[!@#$%^&*])는 독립된 조건
	// ?=는 앞에서 부터
	// .은 임의의 글자
	// *는 0개 이상
	const pattern = /^(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$/;
	const $sPassword = $("#sPassword").val();
	const result = pattern.test($sPassword);
	if(result==false)
		$("#sPassword_msg").text("비밀번호는 특수문자를 하나이상 포함하는 영숫자와 특수문자 8~10자입니다");
	return result;
}



// 비밀번호 확인은 필수입력이고 비밀번호와 일치해야 한다
function sPasswordDosCheck() {
	$("#sPasswordDos_msg").text("");
	const $sPasswordDos = $("#sPasswordDos").val();
	if($sPasswordDos=="") {
		$("#sPasswordDos_msg").text("필수입력입니다");
		return false;
	} 
	if($sPasswordDos!==$("#sPassword").val()) {
		$("#sPasswordDos_msg").text("비밀번호가 일치하지 않습니다");
		return false;
	}
	return true;
}



// 생일 패턴 체크 함수
function sBirthCheck() {
	$("#sBirth_msg").text("");
	const pattern = /^[0-9]{4}-[0-9]{2}-[0-9]{2}$/;
	const $sBirth = $("#sBirth").val();
	const result = pattern.test($sBirth);
	if(result==false)
		$("#sBirth_msg").text("정확한 날짜를 입력하세요");
	return result;
}



// 프로필 사진을 출력하는 함수
function loadProfile() {
	const file = $("#sProfile")[0].files[0];
	const maxSize = 1024*1024;			
	if(file.size>maxSize) {
		Swal.fire('프로필 크기 오류', '프로필 사진은 1MB를 넘을 수 없습니다','error');
		$("#sProfile").val("");
		$("#show_sProfile").removeAttr("src");
		return false;
	}
	const reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onload = function() {
		$("#show_sProfile").attr("src", reader.result);
	}
	return true;
}


// formData를 출력하는 함수
function printFormData(formData) {
	for(const key of formData.keys())
		console.log(key);
	for(const value of formData.values()) 
		console.log(value);
}

// 실제 회원가입하는 함수 
function join() {
	// multipart/form-data를 JS에서 다룰 때는 FormData 내장 객체를 사용
	// 1. 폼을 읽어와서 FormData 생성 : new FormData(폼)
	// 2. 비어있는 FormData를 생성한 다음 값을 하나씩 채워넣는 방식
	//	  const formData = new FormData();
	//	  formData.append("username", "spring");
	const formData = new FormData($("#sJoinForm")[0]);
	
	$.ajax({
		url: "/seller/new",
		method: "post",
		data: formData,
		processData: false,			// multipart/form-data 형식일 때 필수 설정
		contentType: false			// multipart/form-data 형식일 때 필수 설정
	})
	.done(()=>{
		// 가입 성공 메시지 출력하고 사용자가 확인하면 로그인 창으로 이동 
		Swal.fire("가입신청완료","이메일을 확인하세요","success").then((choice)=>{ if(choice.isConfirmed) location.href="/seller/login"; })
	}).fail((response)=>{
		// 가입 신청 메시지 출력
		Swal.fire("가입신청실패", response.responseJSON.result, "error");
	});
}


$(document).ready(function() {
	// 프로필 사진을 변경하면 출력하기
	$("#sProfile").on("change", loadProfile);
	
	// 아이디에 대한 패턴 체크 -> 성공하면 사용가능 여부를 ajax로 확인
	$("#sId").on("blur", function() {
		if(sIdCheck()==false)
			return;
		const $sId = $('#sId').val();
		$.ajax("/seller/check/sId?sId="+$sId)
		.done(response=>console.log(response.result))
		.fail(response=>$("#sId_msg").text(response.result).attr("class","fail"));
		
	});
	
	// 이메일에 대한 패턴 체크 -> 성공하면 사용가능 여부를 ajax로 확인
	$("#sEmail").on("blur", function() {
		if(sEmailCheck()==false)
			return;
		const $sEmail = $('#sEmail').val();
		$.ajax("/seller/check/sEmail?sEmail="+$sEmail)
		.done(()=>$("#sEmail_msg")
		.text("사용가능합니다").attr("class", "success"))
		.fail(()=>$("#sEmail_msg")
		.text("사용중입니다").attr("class","fail"));
		
	});
	
	$("#sEmail").on("blur", sEmailCheck);
	$("#sPassword").on("blur", sPasswordCheck);
	$("#sPasswordDos").on("blur", sPasswordDosCheck);
	$("#sName").on("blur", sNameCheck);
	$("#sBirth").on("blur", sBirthCheck);
	
	// 가입 버튼 누르면 6개에 대해 검증을 수행
	$("#sJoin").on("click", function() {
		// 6개 검증을 수행
		const r1 = sIdCheck();
		const r2 = sPasswordCheck();
		const r3 = sPasswordDosCheck();
		const r4 = sNameCheck();
		const r5 = sEmailCheck();
		const r6 = sBirthCheck();
		if((r1 && r2 && r3 && r4 && r5 && r6) == false)
			return;
					
		// ajax로 사용여부 확인 다시
		$.when("/seller/check/sId?sId="+$('#sId').val(),
			"/seller/check/sEmail?sEmail="+$('#sEmail').val())
		.done(()=>join())
		.fail(()=>Swal.fire("실패", "아이디나 이메일이 사용중입니다", "error"));	
	})
	
})











