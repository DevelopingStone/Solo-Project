function PadTimeFormat (timeString){
    const maxLength = 2;
    const padWord = 0;
    return timeString.padStart(maxLength,padWord);
}

function MakeDateFormat (timeObject) {
    const searchYear = timeObject.getFullYear().toString();
    const searchMonth = PadTimeFormat(timeObject.getMonth().toString())
    const searchDay = PadTimeFormat(timeObject.getDay().toString());
    const searchDate = [searchYear, searchMonth, searchDay];
    return searchDate.join("-");
}
function MakeTimeFormat (timeObject) {
    const delimiterForTimeExpression = "T";
    const searchHour = PadTimeFormat(timeObject.getHours().toString());
    const searchMinute = PadTimeFormat(timeObject.getMinutes().toString());
    const searchSecond = PadTimeFormat(timeObject.getSeconds().toString());
    const searchDate = [searchHour, searchMinute, searchSecond];
    return delimiterForTimeExpression.concat(searchDate.join(":"));
}

document.getElementById('add-bookmark-button').addEventListener('click', ()=>{
    const selector = document.getElementById('bookmark-selector');
    const wifiName = document.getElementById('row-3-column-1').textContent;
    console.log(wifiName);

    const selectedOption = selector.options[selector.selectedIndex];
    console.log(selector.options.length);
    const bookmarkGroupId = selectedOption.value;
    if(selector.options.length > 1){
        let dateObject = new Date();
        const searchDate = MakeDateFormat(dateObject);
        const searchTime = MakeTimeFormat(dateObject);
        const enrollmentTime = searchDate.concat(searchTime);

        console.log(bookmarkGroupId);

        const bookmarkSaveForm = document.createElement("form");

        bookmarkSaveForm.setAttribute("charset", "UTF-8");
        bookmarkSaveForm.setAttribute("method", "POST");
        bookmarkSaveForm.setAttribute("action", "BookmarkSaveServlet");

        let wifiNameInput= document.createElement("input");
        wifiNameInput.setAttribute("type", "hidden");
        wifiNameInput.setAttribute("name", "wifiName");
        wifiNameInput.setAttribute("value", wifiName);
        bookmarkSaveForm.appendChild(wifiNameInput);

        let bookmarkGroupOrderInput= document.createElement("input");
        bookmarkGroupOrderInput.setAttribute("type", "hidden");
        bookmarkGroupOrderInput.setAttribute("name", "bookmarkGroupId");
        bookmarkGroupOrderInput.setAttribute("value", bookmarkGroupId);
        bookmarkSaveForm.appendChild(bookmarkGroupOrderInput);

        let enrollmentTimeInput = document.createElement("input");
        enrollmentTimeInput.setAttribute("type", "hidden");
        enrollmentTimeInput.setAttribute("name", "enrollmentTime");
        enrollmentTimeInput.setAttribute("value", enrollmentTime);
        bookmarkSaveForm.appendChild(enrollmentTimeInput);

        document.body.appendChild(bookmarkSaveForm);

        bookmarkSaveForm.submit();
    }


});