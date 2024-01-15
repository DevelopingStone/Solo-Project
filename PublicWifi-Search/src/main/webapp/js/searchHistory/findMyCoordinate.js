function CoordinateFinder () {

   this.MoveToSaveSearchHistory = function () {
      const searchHistoryForm = document.createElement("form");
      searchHistoryForm.setAttribute("charset", "UTF-8");

      searchHistoryForm.setAttribute("method", "Post");  //Post 방식

      searchHistoryForm.setAttribute("action", "SaveSearchHistoryServlet"); //요청 보낼 주소

      let xInput = document.createElement("input");

      xInput.setAttribute("type", "hidden");

      xInput.setAttribute("name", "coordinateX");

      xInput.setAttribute("value", document.getElementById("coordinateX").value);

      searchHistoryForm.appendChild(xInput);

      let yInput = document.createElement("input");

      yInput.setAttribute("type", "hidden");

      yInput.setAttribute("name", "coordinateY");

      yInput.setAttribute("value", document.getElementById("coordinateY").value);

      searchHistoryForm.appendChild(yInput);

      let timeInput = document.createElement("input");
      timeInput = document.createElement("input");

      timeInput.setAttribute("type", "hidden");

      timeInput.setAttribute("name", "searchTime");

      timeInput.setAttribute("value", document.getElementById("searchTime").getAttribute("searchTime"));

      searchHistoryForm.appendChild(timeInput);


      document.body.appendChild(searchHistoryForm);

      searchHistoryForm.submit();
   }


   this.findMyLocation = function  () {
      let dateObject = new Date();
      const time = document.getElementById('searchTime');
      const searchDate = MakeDateFormat(dateObject);
      const searchTime = MakeTimeFormat(dateObject);
      const searchDateAndTime = searchDate.concat(searchTime);
      time.setAttribute('searchTime', searchDateAndTime);
      try {
         navigator.geolocation.getCurrentPosition(successToFindMyLocation);
      }
      catch (e){
         console.log(e);
      }
      return searchTime;
   }

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

   function successToFindMyLocation(params) {
      const coordinate_x = document.getElementById("coordinateX");
      const coordinate_y = document.getElementById("coordinateY");
      const x = coordinate_x.value;
      const y = coordinate_y.value;
      const userCoordinateX = params.coords.latitude;
      const userCoordinateY = params.coords.longitude;
      if (x !== userCoordinateX && y !== userCoordinateY) {
         coordinate_x.value = userCoordinateX;
         coordinate_y.value = userCoordinateY;
         coordinate_x.setAttribute("value", userCoordinateX);
         coordinate_y.setAttribute("value", userCoordinateY);

      }
   }
}
let finder = new CoordinateFinder();

document.getElementById('findMyCoordinateButton').addEventListener("click", finder.findMyLocation);

let CoordinateObserver = new MutationObserver(mutations => {
   finder.MoveToSaveSearchHistory();
});
let CoordinateObserverOption = {
   attributes: true,
   childList: true,
   characterData: true
};
let CoordinateObserverTarget = document.getElementById('coordinateX');
CoordinateObserver.observe(CoordinateObserverTarget, CoordinateObserverOption);