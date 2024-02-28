/*
    onclick     : 클릭 할때 마다 
    onchange    : 값이 변경될때 마다 
    onkeyup     : 키보드 키를 떼었을때.
    
    ------ 정규표현식 ------
    정규표현식란 : 특정한 규칙을 가진 문자열의 집합을 표현할때 사용하는 형식 언어
        - 주로 문자열 데이터 검사할때 사용 - 유효성검사.
        - 메소드 
            정규표현식.test( 검사할대상 );
        - 형식 규칙 
            /^      : 정규표현식 시작 알림.
            $/      : 정규표현식 끝 알림 
            { 최소길이 , 최대길이 }    : 허용 문자 길이 규칙
            [ 허용할 문자 ]     : 허용 문자 규칙 규칙  
                [a-z]           : 소문자 a ~ z 허용
                [a-zA-Z]        : 영 대소문자 a ~ z 허용 
                [a-zA-Z0-9]     : 영 대소문자 , 숫자 허용 
                [a-zA-Z0-9가-힣] : 영 대소문자 , 숫자 , 한글 허용 
*/

// ******* 현재 유효성검사 체크 현황 
let checkArray = [ false,false,false,false,false ]; // 아이디 , 비밀번호 ,이름 , 전화번호 , 이메일 

// 4. 아이디 유효성검사. ( 아이디 입력할때 마다. )
function idcheck(){   console.log('idcheck()');
    // 1. 입력된 데이터 가져오기 
    let id = document.querySelector('#id').value;    console.log( id );
    // 2. 정규표현식 : 영소문자+숫자 조합의 5~30 글자 사이 규칙
    let 아이디규칙 = /^[a-z0-9]{5,30}$/
    // 3. 정규표현식 에 따른 검사.
    console.log( 아이디규칙.test( id ) );
    if( 아이디규칙.test(id) ){
        // * 아이디 중복체크 ( ajax )
        $.ajax({ // 비동기 vs 동기 
            url : `/member/find/idcheck` , 
            method : "get" ,        // HTTP BODY -> 없다. -> 쿼리스트링
            data : { 'id' : id } ,  // `/member/find/idcheck?id=${id}`
            success : (r)=>{
                if( r ){  // true : 중복있다 , false : 중복없다.
                    document.querySelector('.idcheckbox').innerHTML = `사용중인 아이디`;
                    checkArray[0] = false; // 체크 현황 변경
                }else{
                    document.querySelector('.idcheckbox').innerHTML = `통과`;
                    checkArray[0] = true; // 체크 현황 변경
                }
            } // success end 
        }) // ajax end 
    }else{
        // 유효성 검사 결과 출력 
        document.querySelector('.idcheckbox').innerHTML = `영소문자+숫자 조합의 5~30글자 사이로 입력해주세요.`;
        checkArray[0] = false; // 체크 현황 변경
    }
} // f end 
















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

    // * 유효성검사 체크 현황중에 하나라도 false이면 회원가입 금지.
    // 1. 
    for( let i = 0 ; i<checkArray.length ; i++ ){
        if( !checkArray[i] ){
            alert('입력사항들을 모두 정확히 입력해주세요.');
            return;
        }
    }

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
        console.log( signUpForm );      // 문자형식 HTML
    let signUpFormData = new FormData( signUpForm );
        console.log( signUpFormData );  // new FormData : 문자데이터가 아닌 바이트 데이터로 변환 된 객체. ( 첨부파일 필수 )

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


// 3. 
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















