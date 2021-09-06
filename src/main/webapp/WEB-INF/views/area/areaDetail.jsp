<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:useBean id="mapFactory" class="com.finalprj.doldolseo.util.CodeMapFactory"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>지역 게시판</title>
    <%-- 메인 스타일 시트 --%>
    <link href="${pageContext.request.contextPath}/_css/mainStyle.css" rel="stylesheet" type="text/css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <%-- contentId 통한 overview(내용) 추출 --%>
    <script>
        $(function () {
            var key = "P3TbC5uJmBCIyJ5XyNE96Iggnml%2FE7YpEPLGKNQAG6P1Pg36WbbyZPeOkl%2BjZa9JsjLoIwO0saCVPxy48P5nMQ%3D%3D";
            $.ajax({
                url: "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=" + key + "&contentTypeId=&contentId=" + "${area.contentId}" +
                    "&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y",
                dataType: "json",
                type: "GET",
                async: "false",
                success: function (resp) {
                    var overview = resp.response.body.items.item.overview;
                    document.getElementById("areaD-overview").innerHTML= overview;
                },
                error: function () {
                    alert("로딩실패");
                }
            });
        });
    </script>

</head>

<body>
    <!--헤더-->
    <header>
        <jsp:include page="../header.jsp"/>
    </header>

    <section id="areaDetail-container">

        <%-- 게시판 제목 및 드릴다운 --%>
        <div id="areaD-topContainer">
            <%-- 제목 --%>
            <div class="common-top__title" style="color: #0080c0;">
                지역게시판
            </div>
            <%-- 게시판 드릴다운 --%>
            <span class="common-top__drilldownbox">
                    <a href="#" style="color: #495c75;">지역게시판</a>
                    <span> > </span>
                    <a href="#">${mapFactory.areaMap.get(area.sigungu)}</a>
            </span>
        </div>

        <%-- 게시판 본문 --%>
        <div id="areaDetail-content">
            <!--지역/이름-->
            <div id="areaDetail-name">
                <h3><b>${mapFactory.areaMap.get(area.sigungu)}</b></h3>
                <p>${area.name}</p>
            </div>

            <hr style="width: 1200px; color: #495c75; "/>

            <!--사진-->
            <div id="areaDetail-img">
                <c:choose>
                    <c:when test="${area.image1 ne null}">
                        <img src="${area.image1}" width="550" height="500" alt="area_image">
                    </c:when>
                    <c:otherwise>
                            <img src="${pageContext.request.contextPath}/_image/area/areaListData/default.png" width="550" height="500" alt="area_image"/>
                    </c:otherwise>
                </c:choose>
            </div>

            <!--정보 : append -->
            <div id="areaDetail-info">
                <p id="areaD-overview">
                </p>
            </div>

            <hr style="width: 1200px; color: #495c75; "/>

            <!--지도-->
            <div id="areaDetail-map">
                <div id="map"></div>
                <!--카카오맵 API-->
                <script type="text/javascript"
                        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=cb0b3988eb15f5d9ee7b535c89c89b5c"></script>
                <script>
                    var container = document.getElementById('map');
                    var options = {
                        center: new kakao.maps.LatLng(${area.y}, ${area.x}),
                        level: 3
                    };
                    var map = new kakao.maps.Map(container, options);
                </script>
            </div>

            <!-- 주소 -->
            <div id="areaDetail-info__table">
                <table style="color: white;">
                    <tr style="text-align: left">
                        <th style="width: 140px">주소</th>
                        <td>${area.address}</td>
                    </tr>
                    <tr style="text-align: left">
                        <th>전화번호</th>
                        <td>${area.tel}</td>
                    </tr>
                </table>
            </div>

        </div>
    </section>

    <!--푸터-->
    <footer>
        <jsp:include page="../footer.jsp"/>
    </footer>


</body>
</html>
