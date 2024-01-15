

function requestSearchHistory() {
    const searchHistoryPostForm = document.createElement("form");
    searchHistoryPostForm.setAttribute("charset", "UTF-8");

    searchHistoryPostForm.setAttribute("method", "POST");

    searchHistoryPostForm.setAttribute("action", "requestSearchHistoryServlet");
    document.body.append(searchHistoryPostForm);
    searchHistoryPostForm.submit();

}
// // 요청을 초기화 합니다.
//     xhr.open(method, url);
//
// // onreadystatechange 이벤트를 이용해 요청에 대한 응답 결과를 처리합니다.
//     xhr.onreadystatechange = function (event) {
//         const { target } = event;
//
//         if (target.readyState === XMLHttpRequest.DONE) {
//             const { status } = target;
//
//             if (status === 0 || (status >= 200 && status < 400)) {
//                 // 요청이 정상적으로 처리 된 경우
//             } else {
//                 // 에러가 발생한 경우
//             }
//         }
//     };
//
// // 서버에 요청을 보냅니다.
//     xhr.send();
// }

document.getElementById('history-button').addEventListener("click", requestSearchHistory);