<!--
작성자: 백정연
페이지이름: mypageDetail.jsp
페이지설명: 개인 정보 조회 및 수정과 회원이 작성한 글, 댓글을 보여주는 페이지
페이지 생성일: 210723
-->

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>내프로필</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/_css/mainStyle.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/_js/mainJs.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>
        $(document).ready(function (){
            var gender = '<c:out value="${member.gender}" />';

            if(gender == "M"){
                $('#gender').val('M').prop("selected", true);
            }else if(gender == "F"){
                $('#gender').val('F').prop("selected", true);
            }else if(gender == "U"){
                $('#gender').val('U').prop("selected", true);
            }

            var img = '${member.member_img}';
            if(img == null){
                $('#my_img').attr("src","${pageContext.request.contextPath}/_image/profile/sample.png");
            }

            $(".mypageD-buttonbox__button--gray").click(function (){
                var leader = '<c:out value="${member.crleader}" />';

                if(leader == "y"){
                    alert("크루장인 경우, 탈퇴가 불가합니다. 크루장을 위임해주시길 바랍니다.");
                }else{
                    if(!confirm("탈퇴하시면 복구할 수 없습니다. 정말로 탈퇴하시겠습니까?")){
                        return false;
                    }else{
                        window.location.href='${pageContext.request.contextPath}/removeMember?id=${member.id}';
                    }
                }
            });

        });
    </script>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Jua&display=swap');
        @import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap');
    </style>
</head>
<body>
<!-- header -->
<div class="planD-header">
    <jsp:include page="../header.jsp"/>
</div>
<!-- // header -->

    <!-- 마이페이지 container -->
    <div class="mypageD-mypagecontainer">

        <!-- 마이페이지 네비바 -->
        <div class="mypageD-listcontainer">
            <ul class="mypageD-listcontainer__ul--blue" >
                <li class="mypageD-listcontainer__li--big">마이 페이지</li>
                <li class="mypageD-listcontainer__li--small"><a href="${pageContext.request.contextPath}/mypageD?id=${member.id}" class="mypageD-listcontainer__a--blue"><img class="mypageD-listcontainer__img--small" src="_image/mypage/person.png">&nbsp;  개인 정보</a></li>
                <li class="mypageD-listcontainer__li--small"><a href="${pageContext.request.contextPath}/planL?id=${member.id}" class="mypageD-listcontainer__a--blue"><img class="mypageD-listcontainer__img--small" src="_image/mypage/planner.png">&nbsp;&nbsp;내 플래너</a></li>
            </ul>
        </div>
        <!-- // 마이페이지 네비바 -->

    <!-- 마이페이지 상세 -->
    <div class="mypageD-container">
        <div class="mypageD-titlecontainer">
            <span class="mypageD-titlecontainer__span--big">내 정보</span>
        </div>

        <!-- 개인 정보 수정-->
        <form action="${pageContext.request.contextPath}/updateMember" method="post" onsubmit="return myinfoCheck()" name="myinfoFrm" enctype="multipart/form-data">
            <div class="mypageD-infocontainer">
                <div class="mypageD-userbox">

                    <div class="mypageD-imgbox">
                        <span class="mypageD-imgbox__span--move">사진 (선택)</span>
                        <img id="my_img" class="mypageD-imgbox__img--small" src="${pageContext.request.contextPath}/_image/profile/${member.member_img}">
                        <label for="img" class="mypageD-imgbox__label--big">업로드</label>
                        <input type="file" class="mypageD-imgbox__input--disapear" id="img" name="memberimg" onchange="setImg(event);">
                    </div>

                    <div class="mypageD-infobox">
                        <span class="mypageD-infocontainer__span--move">아이디</span>
                        <input type="text" name="id" class="mypageD-infocontainer__input--big" value="${member.id}" readonly>
                    </div>

                    <div class="mypageD-infobox">
                        <span class="mypageD-infocontainer__span--move">이름</span>
                        <input type="text" name="name" class="mypageD-infocontainer__input--big" value="${member.name}">
                    </div>

                    <div class="mypageD-infobox">
                        <span class="mypageD-infocontainer__span--move">닉네임</span>
                        <input type="text" name="nickname" class="mypageD-infocontainer__input--big" value="${member.nickname}" readonly>
                    </div>

                    <div class="mypageD-infobox">
                        <span class="mypageD-infocontainer__span--move">이메일</span>
                        <input type="email" name="email" class="mypageD-infocontainer__input--big" value="${member.email}">
                    </div>

                </div>

               <div class="mypageD-userbox2">

                   <div class="mypageD-infobox">
                       <span class="mypageD-infobox__span--move">비밀번호</span>
                       <input type="password" name="password" class="mypageD-infocontainer__input--big">
                   </div>

                   <div class="mypageD-infobox">
                       <span class="mypageD-infobox__span--move" id="mypageD-infobox__span--move">비밀번호 확인</span>
                       <input type="password" class="mypageD-infocontainer__input--big" name="password2">
                   </div>

                   <div class="mypageD-infobox">
                       <span class="mypageD-infobox__span--down">생년월일</span>
                       <input type="text" name="yy" class="mypageD-infobox__input-big" value="<fmt:formatDate value="${member.birth}" pattern="yyyy" />" readonly>
                       <input type="text" name="mm" class="mypageD-infobox__input-big" value="<fmt:formatDate value="${member.birth}" pattern="MM" />" readonly>
                       <input type="text" name="dd" class="mypageD-infobox__input-big" value="<fmt:formatDate value="${member.birth}" pattern="dd" />" readonly>
                       <input type="hidden" name="birth" value="<fmt:formatDate value="${member.birth}" pattern="yyyy-MM-dd" />">
                   </div>

                   <div class="mypageD-infobox">
                       <span class="mypageD-infobox__span--down">성별</span>

                       <select name="gender" class="mypageD-infocontainer__span--big" id="gender">
                           <option value="">성별</option>
                           <option value="M">남자</option>
                           <option value="F">여자</option>
                           <option value="U">선택 안함</option>
                       </select>
                   </div>

                   <div class="mypageD-infobox">
                       <span class="mypageD-infobox__span--down">전화번호 (선택)</span>
                       <input type="tel" name="phone" class="mypageD-infocontainer__input--big" value="${member.phone}" pattern="[0-9]{11}">
                   </div>

                   <div class="mypageD-buttonbox">
                       <input type="hidden" name="crleader" value="${member.crleader}">
                       <input type="submit" value="수정 완료" class="mypageD-buttonbox__button--blue">
                       <input type="button" value="회원 탈퇴" class="mypageD-buttonbox__button--gray">
                   </div>

               </div>

            </div>
        </form>
        <!-- // 개인 정보 수정-->

        <!-- 내 크루 -->
        <div class="mypageD-crewcontainer">
            <span class="mypageD-crewcontainer__span--big">내 크루</span>

            <div class="mypageD-crewbox">
                <c:forEach begin="1" end="3">
                    <div class="mypageD-crewlistbox">
                        <span class="mypageD-crewlistbox__span--big">㈜코ㄷing zI존</span>
                        <a href="#" class="mypageD-crewlistbox__a--big"><img src="_image/sample.png" class="mypageD-crewlistbox__img--big"></a>
                        <button class="mypageD-crewlistbox__button--blue" onclick="location.href='#'">내 크루 보기</button>
                    </div>
                </c:forEach>

            </div>
        </div>
        <!-- // 내 크루 -->

        <!-- 내가 쓴 글 / 컨트롤러에게 값 받아서 글 부분 c:forEach 사용 -->
        <div class="mypageD-boardcontainer">
            <span class="mypageD-boardcontainer__span--big">내가 쓴 글</span>

            <div class="mypageD-boardbox">
                <table class="mypageD-boardbox__table--big">
                    <tr class="mypageD-boardbox__tr--blue">
                        <th class="mypageD-boardbox__td--num">번호</th>
                        <th class="mypageD-boardbox__td--title">제목</th>
                        <th class="mypageD-boardbox__td--date">날짜</th>
                    </tr>

                    <c:if test="${reviewList.hasContent() == false}">
                        <tr class="mypageD-boardbox__tr--white">
                            <td colspan="3" class="mypageD-boardbox__td--none">작성한 글이 존재하지 않습니다.</td>
                        </tr>
                    </c:if>

                    <c:forEach items="${reviewList.content}" var="reviewList" varStatus="status">
                        <tr class="mypageD-boardbox__tr--white">
                            <td class="mypageD-boardbox__td--white">${reviewList.reviewNo}</td>
                            <td class="mypageD-boardbox__td--white"><a href="${pageContext.request.contextPath}/review/${reviewList.reviewNo}" class="mypageD-boardbox__a--white">${reviewList.title}</a></td>
                            <td class="mypageD-boardbox__td--white">
                                    <fmt:parseDate value="${reviewList.WDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" type="both" />
                                    <fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd" />
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <ul class="mypageD-boardpage">
                    <li class="mypageD-boardpage__li--link">
                            <a href="${pageContext.request.contextPath}/mypageD?id=${member.id}&page=0" class="mypageD-boardpage__a--num">&laquo;</a>
                    </li>

                    <c:if test="${startBlockPage ne 1}">
                        <li class="mypageD-boardpage__li--link">
                            <a href="${pageContext.request.contextPath}/mypageD?id=${member.id}&page=${startBlockPage-2}" class="mypageD-boardpage__a--num">&lt;</a>
                        </li>
                    </c:if>

                    <c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="status">
                        <li class="mypageD-boardpage__li--link">
                            <a href="${pageContext.request.contextPath}/mypageD?id=${member.id}&page=${status-1}" class="mypageD-boardpage__a--num">${status}</a>
                        </li>
                    </c:forEach>

                    <c:if test="${endBlockPage ne reviewList.totalPages}">
                        <li class="mypageD-boardpage__li--link">
                            <a href="${pageContext.request.contextPath}/mypageD?id=${member.id}&page=${endBlockPage}" class="mypageD-boardpage__a--num">&gt;</a>
                        </li>
                    </c:if>

                    <li class="mypageD-boardpage__li--link">
                        <a href="${pageContext.request.contextPath}/mypageD?id=${member.id}&page=${reviewList.totalPages-1}" class="mypageD-boardpage__a--num">&raquo;</a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- / 내가 쓴 글 -->

        <!-- 내가 쓴 댓글 / 컨트롤러에게 값 받아서 댓글 부분 c:forEach 사용 -->
        <div class="mypageD-commentcontainer">
            <span class="mypageD-boardcontainer__span--big">내가 쓴 댓글</span>

            <div class="mypageD-boardbox">
                <table class="mypageD-boardbox__table--big">
                    <tr class="mypageD-boardbox__tr--blue">
                        <th class="mypageD-boardbox__td--num">번호</th>
                        <th class="mypageD-boardbox__td--title">제목</th>
                        <th class="mypageD-boardbox__td--date">날짜</th>
                    </tr>

                    <c:if test="${commentList.hasContent() == false}">
                        <tr class="mypageD-boardbox__tr--white">
                            <td colspan="3" class="mypageD-boardbox__td--none">작성한 댓글이 존재하지 않습니다.</td>
                        </tr>
                    </c:if>

                    <c:forEach items="${commentList.content}" var="commentList" varStatus="status">
                        <tr class="mypageD-boardbox__tr--white">
                            <td class="mypageD-boardbox__td--white">${commentList.commentNo}</td>
                            <td class="mypageD-boardbox__td--white"><a href="${pageContext.request.contextPath}/review/${commentList.reviewNo}" class="mypageD-boardbox__a--white">${commentList.content}</a></td>
                            <td class="mypageD-boardbox__td--white">
                                <fmt:parseDate value="${commentList.WDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" type="both" />
                                <fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd" />
                            </td>
                        </tr>
                    </c:forEach>

                    <!-- 백엔드 작업 후 코드 수정
                    <tr class="mypageD-boardbox__tr--white">
                        <td class="mypageD-boardbox__td--white">1</td>
                        <td class="mypageD-boardbox__td--white"><a href="#" class="mypageD-boardbox__a--white">로또 당첨 후기</a></td>
                        <td class="mypageD-boardbox__td--white">2021-07-25</td>
                    </tr>

                    <tr class="mypageD-boardbox__tr--white">
                        <td class="mypageD-boardbox__td--white">1</td>
                        <td class="mypageD-boardbox__td--white"><a href="#" class="mypageD-boardbox__a--white">로또 당첨 후기</a></td>
                        <td class="mypageD-boardbox__td--white">2021-07-25</td>
                    </tr>

                    <tr class="mypageD-boardbox__tr--white">
                        <td class="mypageD-boardbox__td--white">1</td>
                        <td class="mypageD-boardbox__td--white"><a href="#" class="mypageD-boardbox__a--white">로또 당첨 후기</a></td>
                        <td class="mypageD-boardbox__td--white">2021-07-25</td>
                    </tr>

                    <tr class="mypageD-boardbox__tr--white">
                        <td class="mypageD-boardbox__td--white">1</td>
                        <td class="mypageD-boardbox__td--white"><a href="#" class="mypageD-boardbox__a--white">로또 당첨 후기</a></td>
                        <td class="mypageD-boardbox__td--white">2021-07-25</td>
                    </tr>

                    <tr class="mypageD-boardbox__tr--white">
                        <td class="mypageD-boardbox__td--white">1</td>
                        <td class="mypageD-boardbox__td--white"><a href="#" class="mypageD-boardbox__a--white">로또 당첨 후기</a></td>
                        <td class="mypageD-boardbox__td--white">2021-07-25</td>
                    </tr>
                    -->
                </table>

                <ul class="mypageD-boardpage">
                    <li class="mypageD-boardpage__li--link">
                        <a href="${pageContext.request.contextPath}/mypageD?id=${member.id}&page=0" class="mypageD-boardpage__a--num">&laquo;</a>
                    </li>

                    <c:if test="${c_startBlockPage ne 1}">
                        <li class="mypageD-boardpage__li--link">
                            <a href="${pageContext.request.contextPath}/mypageD?id=${member.id}&page=${c_startBlockPage-2}" class="mypageD-boardpage__a--num">&lt;</a>
                        </li>
                    </c:if>

                    <c:forEach begin="${c_startBlockPage}" end="${c_endBlockPage}" var="status">
                        <li class="mypageD-boardpage__li--link">
                            <a href="${pageContext.request.contextPath}/mypageD?id=${member.id}&page=${status-1}" class="mypageD-boardpage__a--num">${status}</a>
                        </li>
                    </c:forEach>

                    <c:if test="${c_endBlockPage ne commentList.totalPages}">
                        <li class="mypageD-boardpage__li--link">
                            <a href="${pageContext.request.contextPath}/mypageD?id=${member.id}&page=${c_endBlockPage}" class="mypageD-boardpage__a--num">&gt;</a>
                        </li>
                    </c:if>

                    <li class="mypageD-boardpage__li--link">
                        <a href="${pageContext.request.contextPath}/mypageD?id=${member.id}&page=${commentList.totalPages-1}" class="mypageD-boardpage__a--num">&raquo;</a>
                    </li>
                </ul>

                <!--
                <ul class="mypageD-boardpage">
                    <li class="mypageD-boardpage__li--link">
                        <a href="#" class="mypageD-boardpage__a--num">&laquo;</a>
                    </li>
                    <li class="mypageD-boardpage__li--link">
                        <a href="#" class="mypageD-boardpage__a--num">&lt;</a>
                    </li>
                    <li class="mypageD-boardpage__li--link">
                        <a href="#" class="mypageD-boardpage__a--num">1</a>
                    <li class="mypageD-boardpage__li--link">
                        <a href="#" class="mypageD-boardpage__a--num">2</a>
                    </li>
                    <li class="mypageD-boardpage__li--link">
                        <a href="#" class="mypageD-boardpage__a--num">3</a>
                    </li>
                    <li class="mypageD-boardpage__li--link">
                        <a href="#" class="mypageD-boardpage__a--num">4</a>
                    </li>
                    <li class="mypageD-boardpage__li--link">
                        <a href="#" class="mypageD-boardpage__a--num">5</a>
                    </li>
                    <li class="mypageD-boardpage__li--link">
                        <a href="#" class="mypageD-boardpage__a--num">&gt;</a>
                    </li>
                    <li class="mypageD-boardpage__li--link">
                        <a href="#" class="mypageD-boardpage__a--num">&raquo;</a>
                    </li>
                </ul>
                -->
            </div>
        </div>
        <!-- // 내가 쓴 댓글 -->
    </div>
    </div>
    <!-- // 마이페이지 상세 -->

    <!-- -->
    <div class="mypageD-footer">
        <%@ include file="../footer.jsp" %>
    </div>
    <!-- -->
</body>
</html>
