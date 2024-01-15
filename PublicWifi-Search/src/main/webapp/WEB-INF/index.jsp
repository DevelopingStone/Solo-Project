<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@include file="Header.jsp"%>--%>
<%!
    private static final String coordinateX = "coordinateX";
    private static final String coordinateY = "coordinateY";
    private String currentX;
    private String currentY;
    private String nearestPublicWifi;
    private String nearestPublicWifiFeature;
    public void mappingRequestParameter(HttpServletRequest request){
        if(request.getParameter(coordinateX)!=null && request.getParameter(coordinateY)!=null){
            currentX = request.getParameter(coordinateX);
            currentY = request.getParameter(coordinateY);
        }
        if (currentX==null && currentY==null) {
            currentX = "0.0";
            currentY = "0.0";
        }
    }


%>
<!DOCTYPE html>
<html>
<head>
    <title>서울에서 내 주변 와이파이 찾기 서비스</title>
    <link href="../css/home/navigator.css?after" rel="stylesheet" >
    <link href="../css/home/locator.css?after" rel="stylesheet" >

</head>
<body>
<h1><%= "내 주변 와이파이 탐색" %></h1>
<br/>
<jsp:include page="Header.jsp"></jsp:include>

<%
    mappingRequestParameter(request);
    if (request.getAttribute("NearestTwentyPublicWifiFeatures")!= null && nearestPublicWifiFeature==null){
        nearestPublicWifiFeature = request.getAttribute("NearestTwentyPublicWifiFeatures").toString();
    }
    if(request.getAttribute("NearestTwentyPublicWifi")!=null){
        nearestPublicWifi = request.getAttribute("NearestTwentyPublicWifi").toString();

        System.out.println(nearestPublicWifi);
    }
%>


<div id="coordinateViewer">
    <form id="coordinateForm">
        <label for="coordinateX">x 좌표: </label>
        <input id="coordinateX"
               type="text"
               name="coordinateX"
               value="<%= currentX %>"
        />
        <label for="coordinateY">y 좌표:</label>
        <input id="coordinateY"
               type="text"
               name="coordinateY"
               value="<%= currentY %>"
        />
        <label for="searchTime"></label>
        <input id="searchTime"
               type="hidden"
               name="searchTime"
        />
        <button type="button"  id="findMyCoordinateButton">
            <%="내 위치 찾기"%>
        </button>

        <button type="button" id="findNearByWifiButton">
            <%="근처 와이파이 찾기"%>
        </button>
    </form>
</div>

<div id="nearestPublicWifiTable"></div>
</body>
<script type="text/javascript" src="../js/searchHistory/findMyCoordinate.js"></script>
<script type="text/javascript" src="../js/publicWifi/findNearestPublicWifi.js"></script>
<script type="text/javascript" src="../js/publicWifi/ShowPublicWifiDetail.js"></script>
<script type="text/javascript" src="../js/tableMaker.js"></script>
<script>
    renderPublicWifiTable(<%= nearestPublicWifi %>, <%= nearestPublicWifiFeature%>, "nearestPublicWifiTable");
</script>
</html>