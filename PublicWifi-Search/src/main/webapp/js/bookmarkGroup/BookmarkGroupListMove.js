window.addEventListener('load', ()=>{
    document.getElementById('bookmark-group-return-button').addEventListener('click', ()=>{
        const moveToBookmarkListPage = document.createElement("form");
        moveToBookmarkListPage.setAttribute("charset", "UTF-8");

        moveToBookmarkListPage.setAttribute("method", "POST");

        moveToBookmarkListPage.setAttribute("action", "BookmarkPageGroupMoveServlet");

        let requestType = document.createElement("input");
        requestType.setAttribute("type", "hidden");
        requestType.setAttribute("name", "method");
        requestType.setAttribute("value", "MOVE");
        moveToBookmarkListPage.appendChild(requestType);

        document.body.append(moveToBookmarkListPage);
        moveToBookmarkListPage.submit();
    })
})