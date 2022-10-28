const sno = location.search.substr(11);
console.log("================")
async function getResult () {
    const order =  await $.ajax ( {
        url : "/store/order",
        method : "get",
        data : {sStoreNum : sno}
    })

    return order.result;
}

function printresult (order) {
	console.log(order);
    order.forEach((a,b) => {
		if(a.aorderStatus === "접수대기") {
            const $tr = $(`<tr id="tr${b}">`).appendTo("#tbody");
    $("<th>").text(a.aorderNum).appendTo($tr);
    $("<td>").text(a.adeleveryAddress + a.adetailAddress).appendTo($tr);
    $("<td>").text(a.atotalPrice + "원").appendTo($tr);
    $("<td>").text(a.cid).appendTo($tr);
    $(`<button class="btn btn-outline-warning" id="orderbtn${b}">`).text("주문 접수").appendTo("<td id='btnTable'>").appendTo($tr);
    }
    
    	if(a.aorderStatus === "접수완료") {
            const $tr = $(`<tr id="tr${b}">`).appendTo("#tbody");
    $("<th>").text(a.aorderNum).appendTo($tr);
    $("<td>").text(a.adeleveryAddress + a.adetailAddress).appendTo($tr);
    $("<td>").text(a.atotalPrice + "원").appendTo($tr);
    $("<td>").text(a.cid).appendTo($tr);
    $(`<button class="btn btn-outline-success" id="orderbtn${b}">`).text("배달 시작").appendTo("<td id='btnTable'>").appendTo($tr);
    }
    
    	if(a.aorderStatus === "배달중") {
            const $tr = $(`<tr id="tr${b}">`).appendTo("#tbody");
    $("<th>").text(a.aorderNum).appendTo($tr);
    $("<td>").text(a.adeleveryAddress + a.adetailAddress).appendTo($tr);
    $("<td>").text(a.atotalPrice + "원").appendTo($tr);
    $("<td>").text(a.cid).appendTo($tr);
    $(`<button class="btn btn-outline-danger" id="orderbtn${b}">`).text("배달 완료").appendTo("<td id='btnTable'>").appendTo($tr);
    }
    
    });
	}
async function orderAgree (idnum, orderNum) {
     $(`#orderbtn${idnum}`).attr('class','btn btn-outline-success').text("배달 시작");
     await $.ajax({
		 url : "/store/order",
		 method : "put",
		 data : {
			 aOrderNum :   orderNum,
			aOrderStatus : "접수완료"
		 }
	 })
}

async function orderDelevery (idnum, orderNum) {
	$(`#orderbtn${idnum}`).attr('class','btn btn-outline-danger').text("배달 완료");
	await $.ajax({
		 url : "/store/order",
		 method : "put",
		 data : {
			 aOrderNum :   orderNum,
			aOrderStatus : "배달중"
		 }
	 })
	
}

async function orderFinish (idnum, orderNum) {
	
			$(`#tr${idnum}`).remove();
			await $.ajax({
		 url : "/store/order",
		 method : "put",
		 data : {
			 aOrderNum :   orderNum,
			aOrderStatus : "배달완료"
		 }
	 })
}

$(document).ready(async ()=>{
   const order =  await getResult();	
     printresult(order);
    $('.btn').click(async function (i){
		const btn = $(this);
		const tr = btn.parent();
		const td = tr.children();
		const orderNum = td.eq(0).text();
	  const id = i.currentTarget.id
	  const idnum = id.substr(8);
	  if($(`#${id}`).hasClass("btn-outline-warning") === true) {
			await orderAgree(idnum, orderNum);
		} else if ($(`#${id}`).hasClass("btn-outline-success") === true) {
			await orderDelevery(idnum, orderNum);
		} else if ($(`#${id}`).hasClass("btn-outline-danger") === true) {
			await orderFinish(idnum, orderNum);
		}
  })
    
    })