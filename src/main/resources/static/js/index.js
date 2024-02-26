// 모든페이지에서 적용할 공통 JS

// 1. 로그인 여부 확인 요청 [ JS 열렸을때 마다 ]
$.ajax({
    url : '/member/login/check',
    method : 'get',
    success : (r)=>{  console.log(r);
        // 1. 어디에
        let login_menu = document.querySelector('#login_menu');
        // 2. 무엇을
        let html = ``;
        if( r != '' ){ // 로그인 했을때
            html += `<li class="nav-item">   <a class="nav-link" onclick="logout()">로그아웃</a>   </li>
                      <li class="nav-item">   <a class="nav-link" href="#">내정보</a>   </li>
                      <li class="nav-item"> <img src="#"/> ${ r } 님 </li>`;
        }else{ // 로그인 안했을떄
            html += `<li class="nav-item">   <a class="nav-link" href="/member/login">로그인</a>        </li>
                      <li class="nav-item">   <a class="nav-link" href="/member/signup">회원가입</a>     </li>`;
        }
        // 3. 대입
        login_menu.innerHTML = html;
    } // s end
}); // ajax end

// 2. 로그아웃
function logout(){
    $.ajax({
        url : `/member/logout`,
        method: 'get' ,
        success : (r)=>{
            if( r ){
                alert('로그아웃 했습니다.');
                location.href='/'; // 로그아웃 성공시 메인페이지로
            }else{
                alert('로그아웃 실패[관리자에게 문의');
            }
        }
    })
}













