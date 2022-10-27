function asidePrint () {
    const lists = [
	{ result : "판매자관리", link : "http://localhost:8087/store/storeRead"},
    { result : "메뉴 등록", link : "#"},
    { result : "대표 메뉴", link : "#"},
    { result : "리뷰 관리", link : "#"},
    { result : "주문 관리", link : "http://localhost:8087/store/storeOrder"}]
    lists.forEach((a)=>{
        const $li = $("<li>").appendTo("#asideUl");
        $(`<button type='button' class="btn btn-outline-secondary">`).click(()=>{
            location.href = a.link;
        }).text(a.result).appendTo($li);
    })
}
$(document).ready(()=>{
    asidePrint();
});