function makeBookmarkTableHeader(bookmarkFeatures, elementName){
    const bookmarkTableHeader = document.createElement("thead");
    const tableHeaderRow = document.createElement("tr");
    tableHeaderRow.id = elementName+"-table-header";
    bookmarkTableHeader.style.border = "solid 1px";
    for (let key in Object.keys(bookmarkFeatures)){
        const tableColumn = document.createElement("th");
        tableColumn.setAttribute("height", "60px");
        tableColumn.style.border = "solid 1px";
        tableColumn.style.textAlign= "center";
        tableColumn.style.fontSize = "14px";
        tableColumn.textContent = bookmarkFeatures[key];
        tableHeaderRow.appendChild(tableColumn);
    }
    bookmarkTableHeader.appendChild(tableHeaderRow);
    return bookmarkTableHeader;
}
function makeBookmarkTableContent(bookmarkFeature, jsonData){

    const contentBody = document.createElement("tbody");
    const jsonDataKey = Object.keys(jsonData);
    for (let i=0; i<jsonDataKey.length; i++){

        const contentRow = document.createElement("tr");
        contentRow.id = "row-" + i;
        for (let j=0; j<bookmarkFeature.length; j++){
            const contentColumn = document.createElement("td");
            contentColumn.id = "row-"+i+"-column-"+j;
            contentColumn.className = "column-"+j;
            contentColumn.style.border = "solid 1px #000";
            contentColumn.style.textAlign= "center";
            contentColumn.style.fontSize = "14px";
            contentColumn.setAttribute("border","solid 1px #000");
            contentColumn.setAttribute("height", "40px");
            if (j===2||j===4){
                let button = document.createElement("button");
                button.id = "link-"+i+"-column-"+j;
                button.type = "button";
                if(j===4){
                    button.textContent = "삭제";

                    button.className = "bookmark-deletion-button"
                }
                else {
                    button.textContent = jsonData[jsonDataKey[i]][[bookmarkFeature[j]]]
                }
                contentColumn.appendChild(button);
            }
            else {
                contentColumn.textContent = jsonData[jsonDataKey[i]][[bookmarkFeature[j]]];
            }

            contentRow.appendChild(contentColumn);
        }
        contentBody.appendChild(contentRow);
    }
    return contentBody;
}

function renderBookmarkTable(bookmarkJson, bookmarkFeature, elementName){
    if(bookmarkFeature !== null && bookmarkJson === null){
        const bookmarkTable = document.createElement("table");
        bookmarkTable.style.borderCollapse = 'collapse';
        bookmarkTable.style.width = "100%";
        bookmarkTable.id = "bookmarkTable";
        const tableHeader = makeBookmarkTableHeader(bookmarkFeature, elementName);
        bookmarkTable.appendChild(tableHeader);
        document.getElementById(elementName).appendChild(bookmarkTable);
    }
    if(bookmarkJson!==null && bookmarkFeature !== null){
        console.log(bookmarkFeature);
        console.log(bookmarkJson);
        const bookmarkTable = document.createElement("table");
        bookmarkTable.id = "bookmarkTable";
        bookmarkTable.style.borderCollapse = 'collapse';
        bookmarkTable.style.width = "100%";
        const tableHeader = makeBookmarkTableHeader(bookmarkFeature, elementName);
        const tableContent = makeBookmarkTableContent(bookmarkFeature, bookmarkJson);
        bookmarkTable.appendChild(tableHeader);
        bookmarkTable.appendChild(tableContent);
        document.getElementById(elementName).appendChild(bookmarkTable);
    }
}