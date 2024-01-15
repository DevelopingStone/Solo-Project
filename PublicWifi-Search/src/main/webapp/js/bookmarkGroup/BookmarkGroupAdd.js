
function sendBookmarkSaveRequest(bookmarkGroupName, bookmarkGroupOrder){
    const bookmarkGroupForm = document.createElement("form");
    bookmarkGroupForm.setAttribute("charset", "UTF-8");

    bookmarkGroupForm.setAttribute("method", "POST");

    bookmarkGroupForm.setAttribute("action", "BookmarkPageAdditionMoveServlet");

    let bookmarkGroupNameInput= document.createElement("input");
    bookmarkGroupNameInput.setAttribute("type", "hidden");
    bookmarkGroupNameInput.setAttribute("name", "bookmarkGroupName");
    bookmarkGroupNameInput.setAttribute("value", bookmarkGroupName);
    bookmarkGroupForm.appendChild(bookmarkGroupNameInput);

    let bookmarkGroupOrderInput= document.createElement("input");
    bookmarkGroupOrderInput.setAttribute("type", "hidden");
    bookmarkGroupOrderInput.setAttribute("name", "bookmarkGroupOrder");
    bookmarkGroupOrderInput.setAttribute("value", bookmarkGroupOrder);
    bookmarkGroupForm.appendChild(bookmarkGroupOrderInput);

    document.body.appendChild(bookmarkGroupForm);

    bookmarkGroupForm.submit();
}

document.getElementById('bookmark-group-add-button').addEventListener('click', sendBookmarkSaveRequest);
