function MoveToBookmarkPage(){
    const moveBookmarkPageRequestForm = document.createElement("form");
    moveBookmarkPageRequestForm.setAttribute("charset", "UTF-8");

    moveBookmarkPageRequestForm.setAttribute("method", "POST");

    moveBookmarkPageRequestForm.setAttribute("action", "BookmarkResponseServlet");
    document.body.append(moveBookmarkPageRequestForm);
    moveBookmarkPageRequestForm.submit();
}
document.getElementById('bookmark-button').addEventListener('click', MoveToBookmarkPage);
