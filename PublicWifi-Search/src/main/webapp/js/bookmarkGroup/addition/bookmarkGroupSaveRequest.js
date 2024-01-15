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

function saveBookmarkGroupRequest(){
    const bookmarkGroupPostForm = document.createElement("form");
    bookmarkGroupPostForm.setAttribute("charset", "UTF-8");
    bookmarkGroupPostForm.setAttribute("method", "POST");
    bookmarkGroupPostForm.setAttribute("action", "BookmarkGroupSaveServlet");

    let dateObject = new Date();
    const searchDate = MakeDateFormat(dateObject);
    const searchTime = MakeTimeFormat(dateObject);
    const enrollmentTime = searchDate.concat(searchTime);
    const bookmarkGroupName = document.getElementById("bookmark-group-name-input").value;
    const bookmarkGroupOrder = document.getElementById('bookmark-group-order-input').value;
    console.log(bookmarkGroupName);
    let bookmarkGroupNameInput = document.createElement("input");
    bookmarkGroupNameInput.setAttribute("type", "hidden");
    bookmarkGroupNameInput.setAttribute("name", "bookmarkGroupName");
    bookmarkGroupNameInput.setAttribute("value", bookmarkGroupName);
    bookmarkGroupPostForm.appendChild(bookmarkGroupNameInput);

    let bookmarkGroupOrderInput = document.createElement("input");
    bookmarkGroupOrderInput.setAttribute("type", "hidden");
    bookmarkGroupOrderInput.setAttribute("name", "bookmarkGroupOrder");
    bookmarkGroupOrderInput.setAttribute("value", bookmarkGroupOrder);
    bookmarkGroupPostForm.appendChild(bookmarkGroupOrderInput);

    let enrollmentTimeInput = document.createElement("input");
    enrollmentTimeInput.setAttribute("type", "hidden");
    enrollmentTimeInput.setAttribute("name", "enrollmentTime");
    enrollmentTimeInput.setAttribute("value", enrollmentTime);
    bookmarkGroupPostForm.appendChild(enrollmentTimeInput);

    document.body.append(bookmarkGroupPostForm);
    bookmarkGroupPostForm.submit();
}


document.getElementById('bookmark-group-addition-button').addEventListener('click', ()=>{

    saveBookmarkGroupRequest();
});