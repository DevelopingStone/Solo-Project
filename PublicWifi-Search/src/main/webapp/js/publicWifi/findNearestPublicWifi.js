function send (){
    this.sendCoordinate = function (){
        const findNearestWifiRequest = document.getElementById('coordinateForm');
        if(findNearestWifiRequest===null){
            alert("갑이 존재하지 않습니다");
        }
            findNearestWifiRequest.setAttribute("charset", "UTF-8");
            findNearestWifiRequest.setAttribute("method", "Post");
            findNearestWifiRequest.setAttribute("action", "FindNearestPublicWifi");
            findNearestWifiRequest.submit();
    }
}
let s = new send();



document.getElementById('findNearByWifiButton').addEventListener('click', s.sendCoordinate);