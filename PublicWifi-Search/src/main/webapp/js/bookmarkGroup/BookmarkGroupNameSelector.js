function checkBookmarkGroupName(bookmarkGroupNameBundle){
    if(bookmarkGroupNameBundle===null){
        alert("서버에서 북마크 이름을 가져오지 못했습니다");
    }
}
function getExistBookmarkGroupName(selector){

    const optionCount = selector.options.length;
    const selectItems = [];

    for (let i = 0; i < optionCount; i++) {
        const option = selector.options[i];
        selectItems.push(option.text);
    }
    return selectItems;
}

function renderBookmarkGroupSelector(bookmarkGroupNameBundle){
    console.log("bookmarkGroupNameBundle: "+bookmarkGroupNameBundle);
    if(bookmarkGroupNameBundle.length > 0){
        console.log(bookmarkGroupNameBundle);
        checkBookmarkGroupName(bookmarkGroupNameBundle);
        const selector = document.getElementById("bookmark-selector");
        const existBookmarkGroupName = getExistBookmarkGroupName(selector);
        console.log(Object.keys(bookmarkGroupNameBundle));
        // console.log(Object.keys(bookmarkGroupNameBundle[0]));
        const bookmarkGroupNameKey = Object.keys(bookmarkGroupNameBundle[0]);
        console.log(bookmarkGroupNameKey);
        for (let pos in bookmarkGroupNameBundle){
            console.log(bookmarkGroupNameBundle[pos]);
            console.log(Object.keys(bookmarkGroupNameBundle[pos]))

            if(!existBookmarkGroupName.includes(bookmarkGroupNameBundle[pos][bookmarkGroupNameKey[pos]])){
                const bookmarkGroupOptions = document.createElement("option");
                bookmarkGroupOptions.value = bookmarkGroupNameBundle[pos][bookmarkGroupNameKey[1]];
                bookmarkGroupOptions.text = bookmarkGroupNameBundle[pos][bookmarkGroupNameKey[0]];
                selector.appendChild(bookmarkGroupOptions);
            }
        }
    }

}