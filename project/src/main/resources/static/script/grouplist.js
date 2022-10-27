// 서버에서 가게번호 가져오기
function getstoreNum() {
	const storeNum = location.search.substring(11)
	if (isNaN(storeNum)) {
		alert("가게를 찾을 수 없습니다");
		location.reload();
	}
	return storeNum;
}

// 메뉴 그룹 출력 (메뉴 추가 버튼 출력)
async function printgroup(group) {
	const grouplist = await $.ajax({
		url: "/seller/menu/group/all",
		data: { sStoreNum: group }
	})
	for (const mgl of grouplist.result) {
		const $li = $("<li>").text(mgl.sgroupName).attr("value1", mgl.sgroupNum).attr("value2", "show").attr("class", "menugroup").appendTo("#menegroup");
    	$("<button>").text("그룹 수정").attr("class", "modal_group_update_in").attr("value", mgl.sgroupNum).appendTo($li);
    	$("<button>").text("그룹 삭제").attr("class", "modal_group_delete").attr("value", mgl.sgroupNum).appendTo($li);
		$("<button>").text("메뉴 추가").attr("class", "modal_menu_in").attr("value", mgl.sgroupNum).appendTo($li);
		$("<ul>").attr("class", "menulist").appendTo($li);
	}
	return grouplist;
}

$(document).ready(async function() {

	const imgurl = "https://s3.ap-northeast-2.amazonaws.com/iciasmartframe2/upload/seller/menuimg/"

	const sid = 'test1';

	const storeNum = getstoreNum();
	const a = await printgroup(storeNum);


	// 메뉴 리스트 출력 값 넣기
	$(document).on("click", ".menugroup", async function() {
		if ($(this).attr("value2") == "show") {

			const groupnum = $(this).attr("value1")
			const result = await $.ajax({
				url: "/seller/menu/all",
				data: { sGroupNum: groupnum }
			})

			const menuresult = result.result;
			console.log(menuresult);

			const $group = $(this).children('.menulist');

			for (const ml of menuresult) {
				const $li = $("<li>").attr("value1", ml.sgroupNum).attr("value2", ml.smenuCode).attr("class", "listli").appendTo($group);
				$("<span>").text(ml.smenuImg).appendTo($li);
				$("<span>").text("메뉴 : " + ml.smenuName).appendTo($li);
				$("<span>").text("가격 : " + ml.smenuPrice).appendTo($li);
				$("<button>").text("수정").attr("class", "modal_menu_update_in").attr("value", ml.smenuCode).appendTo($li);
				$("<button>").text("삭제").attr("class", "modal_menu_delete").attr("value", ml.smenuCode).appendTo($li);
			}

			$(this).attr("value2", "hide");
		} else {
			$(this).children().children().remove();

			$(this).attr("value2", "show");
		}
	})

	// 벨류 값 가져오기
	$(document).on("click", ".listli", async function(e) {
		e.stopPropagation();

		const groupnum = $(this).attr("value1");
		const menucode = $(this).attr("value2");
	})

	// 모달 출력 시 가게번호 가져가기
	$(document).on("click", ".modal_group_in", async function() {
		$("#sStoreNum").attr("value", storeNum);
		$(".modal_group").fadeIn();
	});

	// 모달 닫기
	$(document).on("click", ".modal_group_Out", function() {
		$(".modal_group").fadeOut();
	});

	// 모달 메뉴 그룹 추가
	$(document).on("click", "#write_group", async function() {
		const param = {
			sId: 'test1',
			sStoreNum: $("#sStoreNum").val(),
			sGroupName: $("#sGroupName").val()
		}

		const result = await $.ajax({
			url: "/seller/menu/group/new",
			method: "post",
			data: param,
			success: function() {
				location.href = location.href
			}
		})
	})

	// 모달 출력 시 메뉴그룹번호 가져가기
	$(document).on("click", ".modal_menu_in", function(e) {
		e.stopPropagation();

		const groupnum = $(this).attr("value");

		$("#sGroupNum").attr("value", groupnum);

		$(".modal_menu").fadeIn();
	})

	// 메뉴 그룹에 메뉴 추가
	$(document).on("click", "#write_menu", function() {

		const formData = new FormData($("#write_menu_form")[0]);

		const result = $.ajax({
			url: "/seller/menu/new",
			method: "post",
			data: formData,
			processData: false,
			contentType: false,
			success: function() {
				location.href = location.href
			}
		});
	})

	// 모달 취소
	$(document).on("click", ".modal_menu_Out", function() {
		$(".modal_menu").fadeOut();
	})

	// 이미지 선택 시 미리보기
	const fileTagNew = document.querySelector("input[id=sMenuImg]");
	fileTagNew.onchange = function() {
		const imgTagNew = document.querySelector("#profileImg_new");
		if (fileTagNew.files.length > 0) {
			let reader = new FileReader();
			reader.onload = function(data) {
				console.log(data);
				imgTagNew.src = data.target.result;
			}

			reader.readAsDataURL(fileTagNew.files[0]);
		} else {

			imgTagNew.src = "";
		}
	}
	
	// 이미지 선택 시 미리보기
	const fileTagUpdate = document.querySelector("input[id=sMenuImgUpdate]");
	fileTagUpdate.onchange = function() {
		const imgTagUpdate = document.querySelector("#profileImg_update");
		if (fileTagUpdate.files.length > 0) {
			let reader = new FileReader();
			reader.onload = function(data) {
				console.log(data);
				imgTagUpdate.src = data.target.result;
			}
			reader.readAsDataURL(fileTagUpdate.files[0]);
		} else {
			imgTagUpdate.src = "";
		}
	}
	
	// 메뉴 그룹 업데이트 모달 출력
	$(document).on("click", ".modal_group_update_in", async function(e) {
		e.stopPropagation();

		const groupnum = $(this).attr("value");

		$("#sGroupNumUpdate").attr("value", groupnum);
		
		const groupprint = await $.ajax({
			url : "/seller/menu/group/read",
			data : {sGroupNum : groupnum}
		})
		
		const groupread = groupprint.result;

		$("#modalGroupName").text("그룹명 : "+groupread.sgroupName);
		
		$(".modal_group_update").fadeIn();
	})
	
	// 메뉴 그룹 업데이트 완료 변경
	$(document).on("click", "#write_group_update", async function() {
		const paramsa = {
			sId : $("#sIdUpdate").val(),
			sGroupNum: $("#sGroupNumUpdate").val(),
			sGroupName: $("#sGroupNameUpdate").val()
		}

		await $.ajax({
			url: "/seller/menu/group/update",
			method: "put",
			data: paramsa,
			success: function() {
				location.href = location.href
			}
		})
	})
	
	// 메뉴 그룹 업데이트 모달 끄기
	$(document).on("click", ".modal_group_update_Out", function() {
		$(".modal_group_update").fadeOut();
	})
	
	
	$(document).on("click", ".modal_group_delete", async function(e) {
	    e.stopPropagation();
	    
	    const groupdelete = $(this).attr("value");
	    
	    const ps = {
	    	sId : "test1",
	    	sGroupNum : groupdelete
	    }
	    
	    if(confirm("정말 삭제하시겠습니까?")) {
  			
			await $.ajax({ 
		      	url : "/seller/menu/group/delete",
		      	method : "delete",
		      	data : ps,
		      	success: function() {
		      		location.href = location.href
		      	}
		    })
	    }
	})
	
	// 엔터 이벤트
	$("#sGroupName").keydown(function(e){
		if(e.keyCode==13){
			$("#write_group").click();
		}
	})
	
	// 메뉴 업데이트 모달 출력
	$(document).on("click", ".modal_menu_update_in", async function() {
		
		const menucodes = $(this).attr("value");

		$("#sMenuCodeUpdate").attr("value",menucodes);
		
		const menuread = await $.ajax({
			url: "/seller/menu/read",
			data: { sMenuCode : menucodes }
		})

		const menuresult = menuread.result;

 		$("#profileImg_update").attr("src", imgurl+menuresult.smenuImg);
 		$("#modalname").text("메뉴명 : "+menuresult.smenuName);
 		$("#modalinfo").text("메뉴 설명 : "+menuresult.smenuInfo);
 		$("#modalprice").text("메뉴 가격 : "+menuresult.smenuPrice);
		
		
		$(".modal_menu_update").fadeIn();
	})
	
	// 메뉴 업데이트 취소
	$(document).on("click", ".modal_menu_update_Out", function() {
		$(".modal_menu_update").fadeOut();
	})
	
	// 메뉴 업데이트
	$(document).on("click", "#write_menu_update", function() {

		const formDatas = new FormData($("#write_menu_update_form")[0]);

		const result = $.ajax({
			url: "/seller/menu/update",
			method: "put",
			data: formDatas,
			processData: false,
			contentType: false,
	      	success: function() {
	      		location.href = location.href
	      	}
		});
	})
	
	$(document).on("click", ".modal_menu_delete", async function(e) {
	    e.stopPropagation();
	    
	    const menudelete = $(this).attr("value");
	    
	    const ps = {
	    	sId : "test1",
	    	sMenuCode : menudelete
	    }

	    if(confirm("정말 삭제하시겠습니까?")) {
  			
			await $.ajax({ 
		      	url : "/seller/menu/delete",
		      	method : "delete",
		      	data : ps,
		      	success: function() {
		      		location.href = location.href
		      	}
		    })
	    }
	})
})