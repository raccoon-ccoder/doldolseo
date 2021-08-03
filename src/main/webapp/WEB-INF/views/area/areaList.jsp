<%--
  Created by IntelliJ IDEA.
  User: young
  Date: 2021-07-23
  Time: 오전 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>AREA LIST</title>
    <%-- 메인 스타일 시트 --%>
    <link href="_css/mainStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
    <!--헤더-->
    <header>
        <jsp:include page="../header.jsp"/>
    </header>

    <section id="areaList-container">
        <!--지역별 배경사진-->
        <div id="areaList-img">
            <img src="_image/area/ghm.png" width="1500" height="550px">
        </div>

        <!--네비(카테고리/검색창)-->
        <div id="areaList-nav">
            <!--네비_카테고리-->
            <div id="areaList-nav__cat">
                <ul>
                    <li><a href="#">전체</a></li>
                    <li><a href="#">축제&행사</a></li>
                    <li><a href="#">음식</a></li>
                    <li><a href="#">쇼핑</a></li>
                    <li><a href="#">엔터테인먼트</a></li>
                </ul>
            </div>

            <!--네비_검색창-->
            <div id="areaList-nav__search">
                <input id="areaList-nav__search__input" type="text"/>
                <button id="areaList-nav__search__btn">
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="18" fill="white" class="bi bi-search"
                         viewBox="0 0 16 16">
                        <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"></path>
                    </svg>
                </button>
            </div>
        </div>

        <!-- 지역 게시글 목록 -->
        <div id="areaList-dataContainer">
            <div id="areaList-dataBox">
                <%
                    for (int j = 0; j < 3; j++) {
                        for (int i = 0; i < 4; i++) {
                %>
                <!-- 게시글 1개  -->
                <div class="areaList-data">
                    <div id="areaList-dataImg" style="display: inline-block;margin: 20px;">
                        <a href="/areaDetail">
                            <img src="_image/areaListData/jongro_3an.png" width="250" height="250">
                        </a>
                    </div>
                    <span id="areaList-dataName" style="margin: 0 auto;">
                    <b>갤러리카페 3안</b>
                </span>
                </div>

                <%
                        }
                    }
                %>
            </div>
            <!--페이지-->
            <div id="areaList-dataPage">
                <table class="pagination">
                    <tr>
                        <td><<</td>
                        <td><</td>
                        <td>1</td>
                        <td>2</td>
                        <td>3</td>
                        <td>4</td>
                        <td>5</td>
                        <td>></td>
                        <td>>></td>
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