function MoveToBookmarkGroupPage(){
    const moveBookmarkPageRequestForm = document.createElement("form");
    moveBookmarkPageRequestForm.setAttribute("charset", "UTF-8");

    moveBookmarkPageRequestForm.setAttribute("method", "POST");

    moveBookmarkPageRequestForm.setAttribute("action", "BookmarkPageGroupMoveServlet");

    let requestType = document.createElement("input");
    requestType.setAttribute("type", "hidden");
    requestType.setAttribute("name", "method");
    requestType.setAttribute("value", "MOVE");
    moveBookmarkPageRequestForm.appendChild(requestType);


    document.body.append(moveBookmarkPageRequestForm);
    moveBookmarkPageRequestForm.submit();
}
document.getElementById('bookmarkGroup-button').addEventListener('click', MoveToBookmarkGroupPage);
