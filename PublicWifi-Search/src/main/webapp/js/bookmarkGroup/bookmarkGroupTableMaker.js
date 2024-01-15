function makeBookmarkGroupTableHeader(features, elementName){
    console.log(features)
    const bookmarkGroupTableHeader = document.createElement("thead");
    const bookmarkGroupTableHeaderRow = document.createElement("tr");
    bookmarkGroupTableHeaderRow.id = elementName+"-table-header";
    bookmarkGroupTableHeader.style.border = "solid 1px";
    for (let bookmarkGroupKey in Object.keys(features)){
        const tableColumn = document.createElement("th");
        tableColumn.setAttribute("height", "60px");
        tableColumn.style.border = "solid 1px";
        tableColumn.style.textAlign= "center";
        tableColumn.style.fontSize = "14px";
        tableColumn.textContent = features[bookmarkGroupKey];
        bookmarkGroupTableHeaderRow.appendChild(tableColumn);
    }
    bookmarkGroupTableHeader.appendChild(bookmarkGroupTableHeaderRow);
    return bookmarkGroupTableHeader;
}
function makeBookmarkGroupTableContent(bookmarkGroupFeature, jsonData){

    const contentBody = document.createElement("tbody");
    const jsonDataKey = Object.keys(jsonData);

    for (let i=0; i<jsonDataKey.length; i++){

        const contentRow = document.createElement("tr");
        contentRow.id = "row-" + i;
        for (let j=0; j<bookmarkGroupFeature.length; j++){
            const contentColumn = document.createElement("td");
            contentColumn.id = "row-"+i+"-column-"+j;
            contentColumn.className = "column-"+j;
            contentColumn.style.border = "solid 1px #000";
            contentColumn.style.textAlign= "center";
            contentColumn.style.fontSize = "14px";
            contentColumn.style.textAlign = "center";
            contentColumn.setAttribute("border","solid 1px #000");
            contentColumn.setAttribute("height", "40px");
            if (j===5){
                let updateButton = document.createElement("button");
                updateButton.id = "updateButton-"+i;
                updateButton.type = "button";
                updateButton.textContent = "수정";
                updateButton.style.textAlign = "center";
                updateButton.style.marginRight = "5px";
                updateButton.className = "bookmarkGroup-update-button"

                let deleteButton = document.createElement("button");
                deleteButton.id = "deleteButton-"+i;
                deleteButton.type = "button";
                deleteButton.textContent = "삭제";
                deleteButton.style.marginLeft = "5px";
                deleteButton.className = "bookmarkGroup-deletion-button"
                contentColumn.appendChild(updateButton);
                contentColumn.appendChild(deleteButton);
            }
            else {
                contentColumn.textContent = jsonData[jsonDataKey[i]][[bookmarkGroupFeature[j]]];
            }

            contentRow.appendChild(contentColumn);
        }
        contentBody.appendChild(contentRow);
    }
    return contentBody;
}

function renderBookmarkGroupTable(bookmarkGroupJson, bookmarkGroupFeature, elementName){
    console.log(bookmarkGroupFeature)
    if(bookmarkGroupFeature !== null && bookmarkGroupJson === null){
        console.log(bookmarkGroupFeature)
        const bookmarkGroupTable = document.createElement("table");
        bookmarkGroupTable.style.borderCollapse = 'collapse';
        bookmarkGroupTable.style.width = "100%";
        bookmarkGroupTable.id = "bookmarkGroupTable";
        const tableHeader = makeBookmarkGroupTableHeader(bookmarkGroupFeature, elementName);
        bookmarkGroupTable.appendChild(tableHeader);
        document.getElementById(elementName).appendChild(bookmarkGroupTable);
    }
    if(bookmarkGroupJson!==null && bookmarkGroupFeature !== null){
        console.log(bookmarkGroupFeature);
        console.log(bookmarkGroupJson);
        const bookmarkGroupTable = document.createElement("table");
        bookmarkGroupTable.id = "bookmarkGroupTable";
        bookmarkGroupTable.style.borderCollapse = 'collapse';
        bookmarkGroupTable.style.width = "100%";
        const tableHeader = makeBookmarkGroupTableHeader(bookmarkGroupFeature, elementName);
        const tableContent = makeBookmarkGroupTableContent(bookmarkGroupFeature, bookmarkGroupJson);
        bookmarkGroupTable.appendChild(tableHeader);
        bookmarkGroupTable.appendChild(tableContent);
        document.getElementById(elementName).appendChild(bookmarkGroupTable);
    }
}