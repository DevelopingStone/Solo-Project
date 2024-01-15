


function bookmarkUpdate(wantToDeleteBookmarkId){
    const bookmarkUpdateRequestForm = document.createElement("form");
    bookmarkUpdateRequestForm.setAttribute("charset", "UTF-8");

    bookmarkUpdateRequestForm.setAttribute("method", "POST");

    bookmarkUpdateRequestForm.setAttribute("action", "BookmarkPageGroupMoveServlet");
    let bookmarkIdInput = document.createElement("input");
    bookmarkIdInput.setAttribute("type", "hidden");
    bookmarkIdInput.setAttribute("name", "bookmarkId");
    bookmarkIdInput.setAttribute("value", wantToDeleteBookmarkId);
    bookmarkUpdateRequestForm.appendChild(bookmarkIdInput);

    let requestTypeInput = document.createElement("input");
    requestTypeInput.setAttribute("type", "hidden");
    requestTypeInput.setAttribute("name", "method");
    requestTypeInput.setAttribute("value", "UPDATE");
    bookmarkUpdateRequestForm.appendChild(requestTypeInput);

    document.body.append(bookmarkUpdateRequestForm);
    bookmarkUpdateRequestForm.submit();
}


window.addEventListener('load', ()=>{
  document.getElementById("bookmarkGroupTable").addEventListener("click", (event) =>{
        if (event.target.classList.contains("bookmarkGroup-update-button")) {

            const row = event.target.closest("tr");
            console.log(row.rowIndex);
            const cells = row.getElementsByTagName("td");

            bookmarkUpdate(cells.item(0).textContent);
            deleteRow(row.rowIndex);
        }
    })
});