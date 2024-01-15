function PublicWifiResponse(featureNames, features){
    console.log("To")
    const publicWifiResponseForm = document.createElement("form");

    publicWifiResponseForm.setAttribute("charset", "UTF-8");
    publicWifiResponseForm.setAttribute("method", "POST");
    publicWifiResponseForm.setAttribute("action", "BookmarkGroupNameResponseServlet");

    let publicWifiFeatures = document.createElement("input");
    publicWifiFeatures.setAttribute("type", "hidden");
    publicWifiFeatures.setAttribute("name", "publicWifiFeatureName");
    publicWifiFeatures.setAttribute("value", createJson(featureNames));
    publicWifiResponseForm.appendChild(publicWifiFeatures);

    let publicWifiRowData = document.createElement("input");
    publicWifiRowData.setAttribute("type", "hidden");
    publicWifiRowData.setAttribute("name", "publicWifiFeature");
    publicWifiRowData.setAttribute("value", createJson(features));
    publicWifiResponseForm.appendChild(publicWifiRowData);

    document.body.append(publicWifiResponseForm);
    publicWifiResponseForm.submit();
}

function createJson(wantTransformed){
    const json = {};
    for (let i = 0; i < wantTransformed.length; i++) {
        json[i] = wantTransformed[i];
    }
    return JSON.stringify(json);
}

// function convertRowToJSON(event) {
//     const link = event.target; // 클릭된 <a> 태그
//     const row = link.closest("tr"); // 부모 요소인 행을 찾음
//
//     const rowData = {};
//
//     // 특정 셀의 값을 추출하여 객체에 저장
//     const cells = row.getElementsByTagName("td");
//     const publicWifiFeatures = "<%=nearestPublicWifiFeature%>";
//     const features = JSON.parse(publicWifiFeatures);
//     for (let i = 0; i < cells.length; i++) {
//         const cell = cells[i];
//         const columnName = features[i]; // 컬럼명을 설정하거나 실제 데이터의 컬럼명을 사용할 수 있습니다.
//         rowData[columnName] = cell.textContent;
//     }
//
//     // JavaScript 객체를 JSON 형식으로 변환하여 반환
//     const json = JSON.stringify(rowData);
//     console.log(json);
// }
// function handleClick(rowId) {
//
//     // 클릭된 열의 데이터 가져오기
//     const clickedData = document.getElementById('row-' + rowId + '-column-0').textContent;
//
//     // 해당 행의 모든 컬럼 값 가져오기
//     const columnData = [];
//     const columns = document.getElementsByClassName('column-' + rowId);
//     for (let i = 0; i < columns.length; i++) {
//         columnData.push(columns[i].textContent);
//     }
//     console.log(columnData);
// }
// // 사용 예시:
// const publicWifiTable = document.getElementById("nearestPublicWifiTable");
// publicWifiTable.addEventListener("click", function(event) {
//     if (event.target.classList.contains("wifiDetailLinkButton")) {
//
//         const row = event.target.closest("tr");
//         console.log(row.rowIndex);
//         const cells = row.getElementsByTagName("td");
//
//         PublicWifiResponse(cells.item(1).textContent);
//     }
// });
// function MoveToPublicWifiDetail(){
//
//     const moveToPublicWifiDetailForm = document.createElement("form");
//     moveToPublicWifiDetailForm.setAttribute("charset", "UTF-8");
//     moveToPublicWifiDetailForm.setAttribute("method", "Post");
//     moveToPublicWifiDetailForm.setAttribute("action", "PublicWifiResponseServlet");
//     let publicWifiIdInput = document.createElement("input");
//
//     publicWifiIdInput.setAttribute("type", "hidden");
//
//     publicWifiIdInput.setAttribute("name", "publicWifiId");
//
//     publicWifiIdInput.setAttribute("value", publicWifiId);
//     moveToPublicWifiDetailForm.appendChild(publicWifiIdInput);
//     document.body.appendChild(moveToPublicWifiDetailForm);
//
//     moveToPublicWifiDetailForm.submit();
// }
window.addEventListener('DOMContentLoaded', function() {
    const links = document.querySelectorAll('[id^="link-"]');
    links.forEach(function(link) {
        link.addEventListener('click', function(event) {
            event.preventDefault();

            const rowId = parseInt(link.id.split('-')[1]);
            const tableHeader = document.getElementById("nearestPublicWifiTable-table-header");
            const columnNames = tableHeader.getElementsByTagName("th");

            const columnFeatures = [];
            for (let i = 0; i < columnNames.length; i++) {
                columnFeatures.push(columnNames.item(i).textContent);
            }
            const clickedData = document.getElementById('row-' + rowId);

            const clickedRow = clickedData.getElementsByTagName("td");
            const rowData = []
            for (let i = 0; i < clickedRow.length; i++) {
                rowData.push(clickedRow[i].textContent);
            }
            PublicWifiResponse(columnFeatures, rowData);
        });
    });
});