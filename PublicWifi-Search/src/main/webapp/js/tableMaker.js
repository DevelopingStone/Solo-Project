function validateJson(jsonData){
    if(jsonData===null){
        alert("데이터가 없습니다");
    }
}
function validateFeature(jsonFeature){
    if(jsonFeature===null){
        alert("데이터가 없습니다");
    }
}
function makeTableHeader(features, elementName){
    const tableHeader = document.createElement("thead");
    const tableHeaderRow = document.createElement("tr");
    tableHeaderRow.id = elementName+"-table-header";
    tableHeader.style.border = "solid 1px";
    for (let key in Object.keys(features)){
        const tableColumn = document.createElement("th");
        tableColumn.setAttribute("height", "60px");
        tableColumn.style.border = "solid 1px";
        tableColumn.style.textAlign= "center";
        tableColumn.style.fontSize = "14px";
        tableColumn.textContent = features[key];
        tableHeaderRow.appendChild(tableColumn);
    }
    tableHeader.appendChild(tableHeaderRow);
    return tableHeader;
}
function makePublicWifiTableContent(features, jsonData){

    const contentBody = document.createElement("tbody");
    const jsonDataKey = Object.keys(jsonData);
    for (let i=0; i<jsonDataKey.length; i++){
        // const publicWifi = JSON.parse(jsonData[key]);
        const contentRow = document.createElement("tr");
        contentRow.id = "row-" + i;
        for (let j=0; j<features.length; j++){
            const contentColumn = document.createElement("td");
            contentColumn.id = "row-"+i+"-column-"+j;
            contentColumn.className = "column-"+j;
            contentColumn.style.border = "solid 1px #000";
            contentColumn.style.textAlign= "center";
            contentColumn.style.fontSize = "14px";
            contentColumn.setAttribute("border","solid 1px #000");
            contentColumn.setAttribute("height", "40px");
            if (j===3){
                let link = document.createElement("a");
                link.id = "link-"+i;
                link.text = jsonData[jsonDataKey[i]][[features[j]]]
                link.href = "#";
                contentColumn.appendChild(link);
            }
            else {
                contentColumn.textContent = jsonData[jsonDataKey[i]][[features[j]]];
            }

            contentRow.appendChild(contentColumn);
        }
        contentBody.appendChild(contentRow);
    }
    return contentBody;
}

function renderPublicWifiTable(jsonData, jsonFeatures, elementName){

    if(jsonFeatures !== null && jsonData === null){
        const features = JSON.parse(jsonFeatures);
        const nearestPublicWifiTable = document.createElement("table");

        nearestPublicWifiTable.style.borderCollapse = 'collapse';
        nearestPublicWifiTable.style.width = "100%";
        nearestPublicWifiTable.id = "publicWifiTable";
        const tableHeader = makeTableHeader(features, elementName);
        nearestPublicWifiTable.appendChild(tableHeader);
        document.getElementById(elementName).appendChild(nearestPublicWifiTable);
    }
    if(jsonData!==null && jsonFeatures !== null){
        console.log(jsonFeatures);
        console.log(jsonData);
        const features = JSON.parse(jsonFeatures);
        const nearestPublicWifiTable = document.createElement("table");
        nearestPublicWifiTable.id = "publicWifiTable";
        nearestPublicWifiTable.style.borderCollapse = 'collapse';
        nearestPublicWifiTable.style.width = "100%";
        const tableHeader = makeTableHeader(features, elementName);
        const tableContent = makePublicWifiTableContent(features, jsonData);
        nearestPublicWifiTable.appendChild(tableHeader);
        nearestPublicWifiTable.appendChild(tableContent);
        document.getElementById(elementName).appendChild(nearestPublicWifiTable);
    }
}

function renderPublicWifiDetailTable(publicWifiDetailJson, publicWifiDetailJSonFeatures, elementName) {
    if(publicWifiDetailJson!==null && publicWifiDetailJSonFeatures !== null){

        const publicWifiDetailTable = document.createElement("table");
        publicWifiDetailTable.id = "publicWifiDetailTable";
        publicWifiDetailTable.style.borderCollapse = 'collapse';
        publicWifiDetailTable.style.width = "100%";
        publicWifiDetailTable.style.height = "80%";

        const featureKey = Object.keys(publicWifiDetailJSonFeatures);

        for (let j = 0; j <featureKey.length; j++) {

            const contentRow = document.createElement("tr");
            if(j%2===1){
                contentRow.style.backgroundColor = "gray";
            }
            const featureColumn = document.createElement("td");
            featureColumn.style.border = "solid 1px #D8D8D8";
            featureColumn.style.textAlign= "center";
            featureColumn.style.fontSize = "14px";
            featureColumn.id = "row-"+j+"-column-0";
            featureColumn.textContent = publicWifiDetailJSonFeatures[j];
            featureColumn.style.backgroundColor = "#00FF00";
            featureColumn.style.textDecorationColor = "#FFFFFF";
            contentRow.appendChild(featureColumn);

            const valueColumn=document.createElement("td");
            valueColumn.style.border = "solid 1px #D8D8D8";
            valueColumn.style.textAlign= "center";
            valueColumn.style.fontSize = "14px";
            valueColumn.id = "row-"+j+"-column-1";
            valueColumn.textContent = publicWifiDetailJson[j];
            contentRow.appendChild(valueColumn);

            publicWifiDetailTable.appendChild(contentRow);
        }
        document.getElementById(elementName).appendChild(publicWifiDetailTable);
    }
}

function makeSearchHistoryTableContent(features, jsonData){
    const searchHistoryContentBody = document.createElement("tbody");

    for (let data in jsonData){
        const contentRow = document.createElement("tr");

        for (let i=0; i<features.length; i++){
            const contentColumn = document.createElement("td");
            contentColumn.style.border = "solid 1px #000";
            contentColumn.style.textAlign= "center";
            contentColumn.style.fontSize = "14px";
            contentColumn.id = features[i];
            contentColumn.setAttribute("border","solid 1px #000");
            contentColumn.setAttribute("height", "40px");
            if(i===4){
                let button = document.createElement("button");
                button.innerText = "삭제";
                button.type = "button";
                button.className = "deleteButton"
                contentColumn.appendChild(button);
            }
            else{
                contentColumn.textContent = jsonData[data][[features[i]]];
            }

            contentRow.appendChild(contentColumn);
        }
        searchHistoryContentBody.appendChild(contentRow);
    }
    return searchHistoryContentBody;
}

function renderSearchHistoryTable(searchHistoryJson, searchHistoryJSonFeatures, elementName){

    if(searchHistoryJSonFeatures !== null && searchHistoryJson === null){

        const features = JSON.parse(searchHistoryJSonFeatures);
        const searchHistoryTable = document.createElement("table");
        searchHistoryTable.style.borderCollapse = 'collapse';
        searchHistoryTable.style.width = "100%";
        searchHistoryTable.id = "searchHistoryTable";
        const tableHeader = makeTableHeader(features, elementName);
        searchHistoryTable.appendChild(tableHeader);
        document.getElementById(elementName).appendChild(searchHistoryTable);
    }
    if(searchHistoryJson!==null && searchHistoryJSonFeatures !== null){

        const features = JSON.parse(searchHistoryJSonFeatures);
        const searchHistoryTable = document.createElement("table");
        searchHistoryTable.id = "searchHistoryTable";
        searchHistoryTable.style.borderCollapse = 'collapse';
        searchHistoryTable.style.width = "100%";
        const tableHeader = makeTableHeader(features, elementName);
        const tableContent = makeSearchHistoryTableContent(features, searchHistoryJson);
        searchHistoryTable.appendChild(tableHeader);
        searchHistoryTable.appendChild(tableContent);
        document.getElementById(elementName).appendChild(searchHistoryTable);
    }
}