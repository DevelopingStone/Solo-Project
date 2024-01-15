document.getElementById('homeButton').addEventListener('click', ()=>{
    const returnHomeRequestForm = document.createElement("form");
    returnHomeRequestForm.setAttribute("charset", "UTF-8");

    returnHomeRequestForm.setAttribute("method", "Post");  //Post 방식

    returnHomeRequestForm.setAttribute("action", "/"); //요청 보낼 주소
    document.body.appendChild(returnHomeRequestForm);
    returnHomeRequestForm.submit();
})