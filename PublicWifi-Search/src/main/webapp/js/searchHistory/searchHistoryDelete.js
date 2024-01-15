
function searchHistoryDelete(wantToDeleteHistoryId){
    const deleteSearchHistoryRequestForm = document.createElement("form");
    deleteSearchHistoryRequestForm.id = "deleteRequestForSearchHistory";

    deleteSearchHistoryRequestForm.setAttribute("charset", "UTF-8");
    deleteSearchHistoryRequestForm.setAttribute("method", "POST");
    deleteSearchHistoryRequestForm.setAttribute("action", "searchHistoryDelete");

    let historyId = document.createElement("input");
    historyId.setAttribute("type", "hidden");
    historyId.setAttribute("name", "historyId");
    historyId.setAttribute("value", wantToDeleteHistoryId);

    deleteSearchHistoryRequestForm.appendChild(historyId);

    document.body.append(deleteSearchHistoryRequestForm);

    deleteSearchHistoryRequestForm.submit();
}
function deleteRow(index) {
    const table = document.getElementById("searchHistoryTable"); // 테이블을 선택합니다.
    table.deleteRow(index); // 테이블에서 행을 삭제합니다.
}

const table = document.getElementById("searchHistoryTable");
table.addEventListener("click", function(event) {
    if (event.target.classList.contains("deleteButton")) {

        const row = event.target.closest("tr");
        console.log(row.rowIndex);
        const cells = row.getElementsByTagName("td");

        searchHistoryDelete(cells.item(0).textContent);
        deleteRow(row.rowIndex);
    }
});
