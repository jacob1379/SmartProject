const sno = location.search.substr(11);

async function getResult () {
    const order =  await $.ajax ( {
        url : "/store/order",
        method : "get",
        data : {sStoreNum : sno}
    })

    return order.result;
}

function printresult (order) {

    order.forEach((a,b) => {
		console.log(a)
            const $tr = $(`<tr id="tr${b}">`).appendTo("#tbody");
    $("<th>").text(a.aorderNum).appendTo($tr);
    $("<td>").text(a.adeleveryAddress + a.adetailAddress).appendTo($tr);
    $("<td>").text(a.atotalPrice + "원").appendTo($tr);
    $("<td>").text(a.cid).appendTo($tr);
    $(`<button class="btn btn-outline-warning" id="orderbtn${b}">`).text("주문 접수").appendTo("<td id='btnTable'>").appendTo($tr);
    });

}
async function orderAgree (idnum) {
     $(`#orderbtn${idnum}`).attr('class','btn btn-outline-success').text("배달 완료");
     await $.ajax({
		 url : "/"
	 })
}
function orderFinish (idnum) {
	$(`#orderbtn${idnum}`).text("");
	$(`<i class="fa-sharp fa-solid fa-check"></i>`).appendTo(`#orderbtn${idnum}`);
	$(`#orderbtn${idnum}`).attr('class','btn btn-outline-danger');
	
}


$(document).ready(async ()=>{
   const order =  await getResult();
    printresult(order);
    $('.btn').click((i)=>{
	  const id = i.currentTarget.id
	  const idnum = id.substr(8);
	  if($(`#${id}`).hasClass("btn-outline-warning") === true) {
		orderAgree(idnum);
		} else if ($(`#${id}`).hasClass("btn-outline-success") === true) {
			orderFinish(idnum);
		} else if ($(`#${id}`).hasClass("btn-outline-danger") === true) {
			$(`#tr${idnum}`).remove();
		}
  })
    
    })