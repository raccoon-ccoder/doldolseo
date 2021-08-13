//전역 변수
let doNameCheck = false; //크루명 중복 체크 유무
var btnCount = 0; //질문 갯수 버튼 카운트

function addQuestion() {
    $(function () {
        $('#crewI-btn--addQuestion').click(function () {
            var num = btnCount + 2
            if (btnCount >= 2) {
                alert("질문은 3개이상 추가하실 수 없습니다.");
            } else {
                $('#crewI-insertTbl__recuit').append("<tr class='common-tbl__item'>" +
                    "<td style='width: 170px;'>" +
                    "가입 질문 (추가)" +
                    "</td>" +
                    "<td>" +
                    "<input name='question" + num + "\'" + " type='text' style='width: 850px'/>" +
                    "</td></tr>");
            }
            btnCount += 1;
        });
    });
}

function setImg(event) {
    var reader = new FileReader();

    reader.onload = function (event) {
        document.getElementById('crew_img').setAttribute("src", event.target.result);
        /*
        * document.getElementById('my_img').src = event.target.result;
        */
    }
    reader.readAsDataURL(event.target.files[0]);
}



function checkCrewName(contextPath) {
    $(function () {
        $('#crewI-btn--checkName').click(function () {
            var crewName = $('#crewI-input--name').val()
            var klen = 0;
            var elen = 0;
            var msg = "";

            //1. null 체크
            if (crewName == null || crewName == '') {
                msg = "* 크루명을 입력 하세요.";
                document.getElementById('crewI-nameCheckMsg').style.color = 'red'
                document.getElementById('crewI-nameCheckMsg').innerText = msg;
                return false;
            }

            //2. 공백 체크
            var blank_pattern = /[\s]/g;
            if (blank_pattern.test(crewName) == true) {
                msg = "* 크루명에 공백은 사용할 수 없습니다.";
                document.getElementById('crewI-nameCheckMsg').style.color = 'red'
                document.getElementById('crewI-nameCheckMsg').innerText = msg;
                return false;
            }

            //3. 일부 문자 제외
            var special_character_pattern = /[.]/;
            if (special_character_pattern.test(crewName) == true) {
                msg = "* 크루명에 사용할수 없는 문자가 포함되어 있습니다.";
                document.getElementById('crewI-nameCheckMsg').style.color = 'red'
                document.getElementById('crewI-nameCheckMsg').innerText = msg;
                return false;
            }

            //4. 한글 자음모음 체크
            var kr_notword_pattern = /([^가-힣\x20^\d^\w])/i;
            if (kr_notword_pattern.test(crewName) == true) {
                msg = "* 한글은 글자 단위로만 입력 가능 합니다";
                document.getElementById('crewI-nameCheckMsg').style.color = 'red'
                document.getElementById('crewI-nameCheckMsg').innerText = msg;
                return false;
            }

            //5. 글자수 제한
            for (var i = 0; i < crewName.length; i++) {
                if (escape(crewName.charAt(i)).length === 6) {
                    klen++;
                } else {
                    elen++;
                }
            }
            if (elen > 12 || klen > 8 || elen + klen > 9) {
                msg = "* 크루명은 한글 8자 이하, 영문,숫자 12자 이내로 작성 하셔야 합니다.";
                document.getElementById('crewI-nameCheckMsg').style.color = 'red'
                document.getElementById('crewI-nameCheckMsg').innerText = msg;
                return false;
            }

            //6. 크루명 중복 체크
            let crewNameForm = $('#crewI-input--name').serialize();

            $j1124.ajax({
                async: false,
                url: contextPath + '/crewI/check',
                type: 'GET',
                data: crewNameForm,
                dataType: 'json',
                contentType: "application/json; charset=UTF-8",
                success: function (data) {
                    if (data) {
                        msg = "* 이미 존재하는 크루명 입니다."
                        document.getElementById('crewI-nameCheckMsg').style.color = 'red'
                        document.getElementById('crewI-nameCheckMsg').innerText = msg;
                        return false;
                    } else {
                        msg = "* 사용가능한 크루명 입니다."
                        document.getElementById('crewI-nameCheckMsg').style.color = 'green'
                        document.getElementById('crewI-nameCheckMsg').innerText = msg;
                        doNameCheck = true;
                        return false;
                    }
                }
            });
        });
    });
}


function limitAreaList() {
    $(function () {
        $("input[name=areaList]").on("click", function () {
            let count = $("input:checked[name=areaList]").length;

            if (count > 3) {
                $(this).prop("checked", false);
                alert("관심 지역은 최대 3개까지 선택 가능 합니다.");
            }
        });
    });
}

function checkCrewParam() {
    $(function () {
        let intro = $('#crewI-intro')
        let introDetail = $('#crewI-introDetail')
        let recruit = $('#crewI-recuit')
        let question1 = $('#question1')


        if(!doNameCheck){
            alert("크루명 중복 체크는 필수 입니다.")
            $('#crewI-btn--checkName').focus();
            return false;
        }

        if(intro.val() == null || intro.val() === ''){
            alert("크루 소개(간략)는 필수 항목 입니다.")
            intro.focus();
            return false;
        }

        if(introDetail.val() == null || introDetail.val() === ''){
            alert("크루 소개(상세)는 필수 항목 입니다.")
            introDetail.focus();
            return false;
        }

        if(recruit.val() == null || recruit.val() === ''){
            alert("모집 공고는 필수 항목 입니다.")
            recruit.focus();
            return false;
        }

        if(question1.val() == null || question1.val() === ''){
            alert("질문은 최소 1개이상 입력 하셔야 합니다.")
            question1.focus();
            return false;
        }

        $('#crewI-form').submit();
    });
}
