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
       data : info,                     // 보낼 데이터
       success : (r)=>{   // 무엇을 받을지. 통신후 응답받은 값
            console.log( r );
            // 4. 결과
            if( r ){
                alert('로그인성공');
                //  JS 페이지 전환
                location.href="/"; // 로그인 성공시 메인페이지로
            }
            else{ alert('로그인실패'); }
       } // success end
    }) // ajax end
} // method end


// 1. 회원가입
function signup( ){
    console.log( "signup() ");
    // 1. HTML 입력값 호출[ document.querySelector() ]
        // 1. 데이터 하나씩 가져오기
        //    let id  = document.querySelector('#id').value;      console.log( id );
        //    let pw  = document.querySelector('#pw').value;      console.log( pw );
        //    let name = document.querySelector('#name').value;   console.log( name );
        //    let phone = document.querySelector('#phone').value; console.log( phone );
        //    let email = document.querySelector('#email').value; console.log( email );
        //    let img = document.querySelector('#img').value;     console.log( img );
// --- 유효성검사
        // 2. 객체화 [ let info = { }  ]
//        let info = {
//            id : id , pw : pw , name : name , phone : phone , email : email , img : img
//        }; console.log( info );

        // 2. 폼 가져오기
    let signUpForm = document.querySelector('.signUpForm');
        console.log( signUpForm ); //
    let signUpFormData = new FormData( signUpForm );
        console.log( signUpFormData );  // new FormData : 문자데이터가 아닌 바이트 데이터로 변환. ( 첨부파일 필수 )

    // 3. [1개월차] 객체를 배열에 저장 --> [3개월차] SPRING CONTROLLER 서버 와 통신[ JQUERY AJAX ]
    $.ajax({
            url : '/member/signup',         // controller 매핑 주소
            method : 'POST',                // controller 매핑 방법
//            data :  info ,                  // controller 요청 보낼 매개변수
            data : signUpFormData ,
            contentType  : false ,
            processData  : false ,
            success : (r) => {  // controller 응답 받은 매개변수
                console.log( r);
                // 4. 결과
                if( r ) {
                    alert('회원가입 성공');
                    location.href = '/member/login';
                }else{
                    alert('회원가입 실패');
                }
            }
        });
} //

/*
    onclick
    onchange
*/
function onChangeImg( event ){
    console.log('preimg');
    console.log( event );   // 현재 함수를 실행한 input
    console.log( event.files ); // 현재 input의 첨부파일들
    console.log( event.files[0] ); // 첨부파일들 중에서 첫번째 파일
    // - input에 업로드 된 파일을 바이트로 가져오기
        //  new FileReader() : 파일 읽기 관련 메소드 제공
    // 1. 파일 읽기 객체 생성
    let fileReader = new FileReader();
    // 2. 파일 읽기 메소드
    fileReader.readAsDataURL( event.files[0] );
    console.log( fileReader );
    console.log( fileReader.result );
    // 3. 파일 onload 필드 정의
    fileReader.onload = e =>{
        console.log( e );           // ProgressEvent
        console.log( e.target );
        console.log( e.target.result ); // 여기에 읽어온 첨부파일 바이트
        document.querySelector('#preimg').src = e.target.result
    }
} // end
/*
    배열타입,함수타입 == 객체 타입
    함수 정의 방법
        1. function 함수명( 매개변수 ) { }
        2. function(매개변수){ }
            let 변수명 = function(매개변수){ }
            let 변수명 = {
                e : function(매개변수){ }
            }
        3. (매개변수) => { }
            let 변수명 = (매개변수) => { }
            let 변수명 = {
                e : (매개변수) => { }
            }

*/















