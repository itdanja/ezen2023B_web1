
function logout(){
    $.ajax({
        type: "get",
        url: "/member/logout",
        success: function (response) {

            if( response ){
                alert('로그아웃 되었습니다.');
                location.href="/";
            }else{
                
            }
        }
    });
}

$.ajax({
    type: "get",
    url: "/member/login/check",
    success: function (response) {

        let login_menu = document.querySelector('#login_menu')

        let html = ``;

        if( response == 0 ){
            html +=`                <li class="nav-item">
                                        <a class="nav-link" href="/member/login">로그인</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="/member/signup">회원가입</a>
                                    </li>`;
        }else{
            html +=`                <li class="nav-item">
                                        <a class="nav-link" onclick="logout()">로그아웃</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#">내정보</a>
                                    </li>
                                    <li class="nav-item">
                                        <img src="/img/프로필.jfif" class="login_pimg" />
                                        김현수님
                                    </li>
                                    `;
        }


        login_menu.innerHTML = html;

    }
});