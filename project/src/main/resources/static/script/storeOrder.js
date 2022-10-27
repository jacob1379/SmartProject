const sno = 1 ;

async function getResult () {
    const order =  await $.ajax ( {
        url : "/store/order",
        method : "get",
        data : {sStoreNum : sno}
    })

    return order.result;
}

function printresult (order) {

    order.forEach((a) => {
            const $tr = $("<tr id='tra'>").appendTo("#tbody");
    $("<th>").text(a.aorderNum).appendTo($tr);
    $("<td>").text(a.adeleveryAddress + a.adetailAddress).appendTo($tr);
    $("<td>").text(a.atotalPrice + "원").appendTo($tr);
    $("<td>").text(a.cid).appendTo($tr);
    $(`<button class="btn btn-outline-warning" id="orderbtn">`).text("주문 접수").appendTo("<td id='btnTable'>").appendTo($tr);
    });

}
function orderAgree () {
     $('#orderbtn').attr('class','btn btn-outline-success').text("배달 완료");
}
function orderFinish () {
	$("#orderbtn").text("");
	$(`<i class="fa-sharp fa-solid fa-check"></i>`).appendTo('#orderbtn');
	$('#orderbtn').attr('class','btn btn-outline-danger');
	
}


$(document).ready(async ()=>{
   const order =  await getResult();
    console.log(order);
    console.log(order[0])
    printresult(order);
    $("#orderbtn").click(()=>{
		if($("#orderbtn").hasClass("btn-outline-warning") === true) {
		orderAgree();	
		} else if ($("#orderbtn").hasClass("btn-outline-success") === true) {
			orderFinish();
		} else if ($("#orderbtn").hasClass("btn-outline-danger") == true) {
			$("#tra").remove();
		}
        
    })
})