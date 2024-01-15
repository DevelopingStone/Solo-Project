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

function updateBookmarkGroupRequest(){
    const updateBookmarkGroupForm = document.createElement("form");
    updateBookmarkGroupForm.setAttribute("charset", "UTF-8");
    updateBookmarkGroupForm.setAttribute("method", "POST");
    updateBookmarkGroupForm.setAttribute("action", "BookmarkGroupUpdateServlet");

    let dateObject = new Date();
    const searchDate = MakeDateFormat(dateObject);
    const searchTime = MakeTimeFormat(dateObject);
    const enrollmentTime = searchDate.concat(searchTime);
    const bookmarkGroupName = document.getElementById("bookmark-group-name-input").value;
    const bookmarkGroupOrder = document.getElementById('bookmark-group-order-input').value;

    let bookmarkGroupNameInput = document.createElement("input");
    bookmarkGroupNameInput.setAttribute("type", "hidden");
    bookmarkGroupNameInput.setAttribute("name", "bookmarkGroupName");
    bookmarkGroupNameInput.setAttribute("value", bookmarkGroupName);
    updateBookmarkGroupForm.appendChild(bookmarkGroupNameInput);

    let bookmarkGroupOrderInput = document.createElement("input");
    bookmarkGroupOrderInput.setAttribute("type", "hidden");
    bookmarkGroupOrderInput.setAttribute("name", "bookmarkGroupOrder");
    bookmarkGroupOrderInput.setAttribute("value", bookmarkGroupOrder);
    updateBookmarkGroupForm.appendChild(bookmarkGroupOrderInput);

    let enrollmentTimeInput = document.createElement("input");
    enrollmentTimeInput.setAttribute("type", "hidden");
    enrollmentTimeInput.setAttribute("name", "enrollmentTime");
    enrollmentTimeInput.setAttribute("value", enrollmentTime);
    updateBookmarkGroupForm.appendChild(enrollmentTimeInput);

    document.body.append(updateBookmarkGroupForm);
    updateBookmarkGroupForm.submit();
}


window.addEventListener('load', ()=>{
    document.getElementById('bookmark-group-update-button').addEventListener('click', ()=>{
        updateBookmarkGroupRequest();
    })
})