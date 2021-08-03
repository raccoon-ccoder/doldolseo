<%--
  Created by IntelliJ IDEA.
  User: young
  Date: 2021-07-23
  Time: 오후 5:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>지역 게시판</title>
    <%-- 메인 스타일 시트 --%>
    <link href="_css/mainStyle.css" rel="stylesheet" type="text/css">

    <%-- 카카오 맵 API --%>

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
                    <a href="#">광화문</a>
            </span>
        </div>

        <%-- 게시판 본문 --%>
        <div id="areaDetail-content">
            <!--지역/이름-->
            <div id="areaDetail-name">
                <h3><b>광화문</b></h3>
                <p>경복궁 별빛야행</p>
            </div>

            <hr style="width: 1200px; color: #495c75; "/>

            <!--사진-->
            <div id="areaDetail-img">
                <img src="_image/areaListData/y1.png" width="550" height="500">
            </div>

            <!--정보-->
            <div id="areaDetail-info">
                <p>
                    경복궁 소주방에서 전통국악공연을 즐기며 임금님의 수라상을 맛보고,
                    전문가의 해설을 들으며 경복궁 후원으로의 아름다운 야행을 시작한다.
                    경복궁 소주방에서 전통국악공연을 즐기며 임금님의 수라상을 맛보고,
                    전문가의 해설을 들으며 경복궁 후원으로의 아름다운 야행을 시작한다.
                    경복궁 소주방에서 전통국악공연을 즐기며 임금님의 수라상을 맛보고,
                    전문가의 해설을 들으며 경복궁 후원으로의 아름다운 야행을 시작한다.

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
                        center: new kakao.maps.LatLng(37.5776087901, 126.9938137563),
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
                        <td>서울특별시 종로구 사직로 161</td>
                    </tr>
                    <tr style="text-align: left">
                        <th>전화번호</th>
                        <td>02-3210-4806</td>
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
