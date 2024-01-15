
function deleteRow(index) {
    const table = document.getElementById("bookmarkTable"); // 테이블을 선택합니다.
    table.deleteRow(index); // 테이블에서 행을 삭제합니다.
}




function bookmarkDeletion(wantToDeleteBookmarkId){
    const bookmarkDeleteRequestForm = document.createElement("form");
    bookmarkDeleteRequestForm.setAttribute("charset", "UTF-8");

    bookmarkDeleteRequestForm.setAttribute("method", "POST");

    bookmarkDeleteRequestForm.setAttribute("action", "BookmarkDeleteServlet");
    let bookmarkIdInput = document.createElement("input");
    bookmarkIdInput.setAttribute("type", "hidden");
    bookmarkIdInput.setAttribute("name", "bookmarkId");
    bookmarkIdInput.setAttribute("value", wantToDeleteBookmarkId);
    bookmarkDeleteRequestForm.appendChild(bookmarkIdInput);

    document.body.append(bookmarkDeleteRequestForm);
    bookmarkDeleteRequestForm.submit();
}


window.addEventListener('load', ()=>{
    document.getElementById("bookmarkTable").addEventListener("click", (event) =>{
    if (event.target.classList.contains("bookmark-deletion-button")) {

        const row = event.target.closest("tr");
        console.log(row.rowIndex);
        const cells = row.getElementsByTagName("td");

        bookmarkDeletion(cells.item(0).textContent);
        deleteRow(row.rowIndex);
    }
})});