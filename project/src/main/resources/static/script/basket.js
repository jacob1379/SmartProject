function printBasketList(result) {
	if (result.length == 0) {
		return
	}

	const $list = $("<ul>").attr("id", "blist").appendTo('#BasketList');

	for (const list of result) {
		const $li = $('<li>').appendTo($list);
		$('<span>').text(" 메뉴 : " + list.smenuName).attr("name", "sMenuName").appendTo($li);
		$('<span>').text(" 가격 : " + list.smenuPrice).attr("name", "sMenuPrice").appendTo($li);
		$('<button>').text("-").attr("class", "countminus").attr("type", "submit").attr("value1", list.smenuCode).attr("value2", false).appendTo($li);
		$('<span>').text(" 수량 : " + list.cmenuCount).attr("name", "cMenuCount").attr("id", "cMenuCount").attr("value", list.cmenuCount).appendTo($li);
		$('<button>').text("+").attr("class", "countplus").attr("type", "submit").attr("value1", list.smenuCode).attr("value2", true).appendTo($li);
		$('<button>').text("삭제").attr("class", "deletebutton").attr("type", "submit").attr("value", list.cbasketNum).appendTo($li);
	}

}

function printBasketTotalPrice(result) {

	let totalPrice = 0;
	const $view = $("<div>").attr("id", "totalview").appendTo("#totalPrice");

	for (let i = 0; i < result.length; i++) {
		totalPrice = totalPrice + (result[i].cmenuCount * result[i].smenuPrice);
	}

	$('<span>').text("총 가격 : ").appendTo($view);
	$('<span>').text(totalPrice).attr("id", "orderPrice").attr("value", totalPrice).appendTo($view);
}

function printStoreName(result) {
	if (result.length == 0) {
		$("#BasketStoreName").remove()
		return $('<span>').attr("id", "BasketStoreName").attr("value",result[0].sstoreNum).appendTo('#BasketStore');
	}
	$("#BasketStoreName").remove()
	$('<span>').text(result[0].sstoreName).attr("id", "BasketStoreName").attr("value",result[0].sstoreNum).appendTo('#BasketStore');

}