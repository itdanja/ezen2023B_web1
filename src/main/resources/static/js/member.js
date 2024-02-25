console.log( 'member.js' );

// 2. 로그인
function login(){
    console.log( "login()");
    // 1. HTML으로부터 입력값 가져오기
    let id = document.querySelector('#id').value;   console.log( id );
    let pw = document.querySelector('#pw').value;   console.log( pw );

    // 2. 객체화
    let info = { id : id , pw : pw };               console.log( info );

    // 3. 서버와 통신
    $.ajax({
       url : `/member/login`,           // 어디에
       method : 'POST',                 // 어떻게
       success : function ( result ){   // 무엇을 받을지. 통신후 응답받은 값
            console.log( result );
            // 4. 결과
            if( result ){ alert('로그인성공'); location.href="/"; }
            else{ alert('로그인실패'); }
       } // success end
    }) // ajax end
} // method end


// 1. 회원가입
function signup(){
    console.log( "signup() ");
    // 1. HTML 입력값 호출[ document.querySelector() ]
    let id  = document.querySelector('#id').value;      console.log( id );
    let pw  = document.querySelector('#pw').value;      console.log( pw );
    let name = document.querySelector('#name').value;   console.log( name );
    let phone = document.querySelector('#phone').value; console.log( phone );
    let email = document.querySelector('#email').value; console.log( email );
    let img = document.querySelector('#img').value;     console.log( img );
    // --- 유효성검사
    // 2. 객체화 [ let info = { }  ]
    let info = {
        id : id , pw : pw , name : name , phone : phone , email : email , img : img
    }; console.log( info );
    // 3. [1개월차] 객체를 배열에 저장 --> [3개월차] SPRING CONTROLLER 서버 와 통신[ JQUERY AJAX ]
    $.ajax({
            url : '/member/signup',         // controller 매핑 주소
            method : 'POST',                // controller 매핑 방법
            data :  info ,                  // controller 요청 보낼 매개변수
            success : function ( result ){  // controller 응답 받은 매개변수
                console.log( result);
                // 4. 결과
                if( result ) {
                    alert('회원가입 성공');
                    location.href = '/member/login';
                }else{
                    alert('회원가입 실패');
                }
            }
        });
}