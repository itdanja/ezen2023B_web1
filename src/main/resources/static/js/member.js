console.log( 'member.js' );


/* onchange : 포커스(커서) 변경될때 값이 다르면 */
/* onkeyup : 키보드에서 키를 누르고 떼었을때 */
/* onkeydown : 키보드에서 키를 눌렀을때 */

/*
	정규표현식 : 문자열에 특정 규칙/패턴 집합 표현할때 사용되는 언어
		패턴 문법
			/^		: 정규표현식 시작 알림.
			$/		: 정규표현식 끝 알림.
			[a-z] 	: 소문자 a-z 패턴
			[A-Z]	: 대문자 A-Z 패턴
			[0-9] 	: 숫자 0~9 패턴
			\d		:
			[가-힣]	: 한글 패턴
			{ 최소길이 , 최대길이 } : 문자열 길이 패턴
			+ : 앞 에 있는 패턴 1개 이상 반복
			? : 앞 에 있는 패턴 0개 혹은 1개 이상 반복
			* : 앞 에 있는 패턴 0개 반복

			예시]
				1. [a-z]			: 소문자A-Z 패턴
				2. [a-zA-Z]			: 영문(대,소) 패턴
				3. [a-zA-Z0-9] 		: 영문 + 숫자 조합 패턴
				4. [a-zA-Z0-9가-힣] 	: 영문 + 숫자 + 한글 조합 패턴
				5. [ac]				: a와c 패턴
				6. (?=.*[패턴문자])	: 해당 패턴문자가 한개 이상 포함 패턴
					(?=.*[a-z])		: 소문자 한개 이상 필수
					(?=.*[A-Z])		: 대문자 한개 이상 필수
					(?=.*[0-9])		: 숫자 한개 이상 필수
					(?=.*\d)		: 숫자 한개 이상 필수
					(?=.*[!@#$%^&*]): 패턴문자내 특수문자 한개 이상 필수

				/^[A-Za-z0-9]{5,20}$/
				영대소문자 + 숫자 조합의 5~20글자 사이

				/^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]{5,20}$/
				영대문자1개 + 영소문자1개 + 숫자 1개 이상 필수로 갖는 5~20글자 사이

				/^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{5,20}$/
				영대문자1개 + 영소문자1개 + 숫자 1개 + 특수문자 1개 이상 필수로 갖는 5~20글자 사이

			1. 	/^[a-z0-9]{5,30}$/								: 영문(소) + 숫자 조합 5~30글자 패턴
			2. 	/^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{5,20}$/	: 영대소문자(1개이상필수) + 숫자(1개이상필수) 조합 5~20글자 패턴
				/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{5,20}$/
			3.  /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+\.[a-zA-Z]+$/	: 패턴@패턴.패턴
					// itdanja@kakao.com
					// 1. itdanja  	: 이메일 아이디부분 은 영대소문자 , 숫자 , _- 패턴
					// 2. @ 		: +@   @ 앞에 패턴이 1개이상 필수
					// 3. 도메인
						// 회사명		: @ 뒤에 그리고 . 앞에 패턴은 a-zA-Z0-9_-
						// .		: +\. : . 앞에 패턴이 1개이상 필수
						// 도메인		: . 뒤에 패턴은 a-zA-Z

		패턴 검사
			"패턴".test( 검사할데이터 )	: 해당 데이터가 패턴에 일치하면 true / 아니면 false
*/
// 1. 아이디 유효성검사.
function idcheck(){ /* 실행조건 : 아이디 입력창에 입력할때마다 */
	// 1. 값 호출
	let mid = document.querySelector('#id').value;
	let idcheckbox = document.querySelector('#idcheckbox');
	// 2. 유효성검사
		// 제어문 이용한 검사 if( mid.length < 5 && mid.length >= 30  ) { }
		// 1. 아이디는 영문(소문자)+숫자 조합의 5~30글자 사이 이면
			// 1. 정규표현식 작성.
		let midj = /^[a-z0-9]{5,30}$/
			// 2. 정규표현식 검사.
			console.log( midj.test( mid ) )
			console.log( mid )
		if( midj.test(mid) ){ // 입력한 값이 패턴과 일치하면
			// -- [ 아이디중복검사 ]입력한 아이디가 패턴과 일치하면
			$.ajax({
				url : "/jspweb/MemberFindController" ,
				method : "get" ,
				data : { type : "mid" , data : mid },
				success : r => {
					if( r ){  idcheckbox.innerHTML = '사용중인 아이디 입니다.'; checkList[0] = false; }
					else { idcheckbox.innerHTML = '사용가능한 아이디 입니다.'; checkList[0] = true; }
				} ,
				error : e => { }
			})
		}else{ // 입력한 값이 패턴과 일치하지 않으면
			idcheckbox.innerHTML ='영문(소문자)+숫자 조합의 5~30글자 가능합니다.'; checkList[0] = false;
		}
	// 3. 결과 출력
}

// 2. 비밀번호 유효성검사 [ 1.정규표현식 검사 2. 비밀번호 와 비밀번호 확인 일치여부 ]
function pwcheck(){	console.log('패스워드 입력중');
	let pwcheckbox = document.querySelector('.pwcheckbox')
	// 1. 입력 값 호출
	let mpwd = document.querySelector('.mpwd').value; 					console.log('mpwd : ' + mpwd);
	let mpwdconfirm = document.querySelector('.mpwdconfirm').value;		console.log('mpwdconfirm : ' + mpwdconfirm);

	// 2. 유효성검사
		// 1. 정규표현식 만들기 [ 영대소문자(1개필수) + 숫자(1개필수) 조합 5~20글자 사이 ]
		// let mpwdj = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{5,20}$/
		let mpwdj = /^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{5,20}$/

		if( mpwdj.test( mpwd ) ){  // 1.비밀번호 정규표현식 검사
			// 2.비밀번호 확인 정규표현식 검사
			if( mpwdj.test( mpwdconfirm) ){
				// 3. 비밀번호 와 비밀번호 확인 일치여부
				if( mpwd == mpwdconfirm ){
					pwcheckbox.innerHTML = `사용가능한 비밀번호`; checkList[1] = true;
				}else{
					pwcheckbox.innerHTML = `비밀번호가 일치하지 않습니다.`; checkList[1] = false;
				}
			}else{
				pwcheckbox.innerHTML = `영대소문자1개이상+숫자1개이상 조합 5~20글자 사이로 입력해주세요.`; checkList[1] = false;
			}
		}else{
			pwcheckbox.innerHTML = `영대소문자1개이상+숫자1개이상 조합 5~20글자 사이로 입력해주세요.`;checkList[1] = false;
		}
} // f end

// 3. 이메일 유효성검사 [ 1. 정규표현식 2. 중복검사 ]
function emailcheck(){
	let emailcheckbox = document.querySelector('.emailcheckbox');
	let authReqBtn = document.querySelector('.authReqBtn');
	// 1. 입력된 값 호출
	let memail = document.querySelector('.memail').value
	// 2. 이메일 정규표현식 [  영대소문자,숫자,_-  @  ]
	let memailj = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+\.[a-zA-Z]+$/
	// 3. 유효성검사
	if( memailj.test( memail) ) {
		$.ajax({
			url : "/jspweb/MemberFindController" ,
			method : "get" ,
			// *type 사용하는 이유 : 서로 다른 ajax가 동일한 서블릿과 동일한 get메소드 사용할때.
			data :  { type : "memail" , data : memail }, // : 이메일 중복검사
			// data :  { type : "mid" , data : mid }, : 아이디 중복검사
			success : r => {
				if( r ){
					emailcheckbox.innerHTML =`사용중인 이메일입니다.`;
					authReqBtn.disabled = true; // 해당 버튼의 disabled 속성 적용
					checkList[2] = false;
				}else{
					emailcheckbox.innerHTML =`사용가능한 이메일입니다.`;
					authReqBtn.disabled = false; // 해당 버튼의 disabled 속성 해제
					checkList[2] = false;
				}
			} ,
			error : r => { console.log(r); }
		})
	}else{
		emailcheckbox.innerHTML = `이메일형식에 맞게 입력해주세요.`;
		authReqBtn.disabled = true; // 해당 버튼의 disabled 속성 적용
		checkList[2] = false;
	}
} // f end

// 4. 인증요청 버튼을 눌렀을때.
function authReq(){
	// ------------------------- 테스트용 ---------------- //
	// 1. 'authbox' div 호출
	let authbox = document.querySelector('.authbox')

	// 2. auth html 구성
	let html = `<span class="timebox">02:00</span>
				<input class="ecode" type="text" />
				<button onclick="auth()" type="button">인증</button> `
	// 3. auth html 대입
	authbox.innerHTML = html;
	// 4. 타이머 실행
	authcode = "1234" ; 		 // [ 테스트용 ] 임의로 인증 코드를 '1234'
	timer = 120; 		// [ 테스트용 ] 인증 제한시간 10초
	settimer();			// 타이머 실행
	/*

	// ------------------------- 이메일 인증 보냈을때 ---------------- //
	// -- 인증요청시 서블릿 통신[ 인증코드 생성 , 이메일전송 ]
	$.ajax({
		url : "/jspweb/AuthSendEmailController" ,
		method : "get" ,
		data : { memail : document.querySelector('.memail').value } ,
		success : r => {  console.log( r );

			// 1. 'authbox' div 호출
			let authbox = document.querySelector('.authbox')

			// 2. auth html 구성
			let html = `<span class="timebox">02:00</span>
						<input class="ecode" type="text" />
						<button onclick="auth()" type="button">인증</button> `
			// 3. auth html 대입
			authbox.innerHTML = html;
			// 4. 타이머 실행
			authcode = r ;				 // [ 이메일전송 ] Controller(서블릿) 에게 전달받은 값이 인증코드
			timer = 120; 		// [ 테스트용 ] 인증 제한시간 10초
			settimer();			// 타이머 실행
		} ,
		error : e => { console.log(e); }
	})
	*/

} // f end

// 4번,5번,6번 함수에서 공통적으로 사용할 변수[전역변수]
let authcode = ''; 	// 인증코드
let timer = 0; 		// 인증 시간(초) 변수
let timerInter; 	// setInterval() 함수를 가지고 있는 변수 [ setInterval 종료시 필요. ]

// 5. 타이머 함수 만들기
function settimer(){
	timerInter = setInterval( () => {
		// 시간 형식 만들기
			// 1. 분 만들기 [ 초 / 60 ] => 분  /  [ 초 % 60 ] => 나머지 초
		let m = parseInt( timer / 60 ); // 분 계산 [ 몫 ]
		let s = parseInt( timer % 60 ); ; // 초 계산 [ 나머지 ]
			// 2. 두자리수 맞춤  3 -> 03
		m = m < 10 ? "0"+m : m; // 만약에 분 이 10보다 작으면 한자리수 이므로 0 붙이고 아니면
		s = s < 10 ? "0"+s : s;

		document.querySelector('.timebox').innerHTML = `${m}:${s}`; // 현재 인증 시간(초) HTML 대입
		timer--; // 1씩 차감

		// 만약에 타이머가 0 보다 작으면 [ 시간 끝 ]
		if( timer < 0 ){
			// 1. setInterval 종료 [ 누구를 종료할건지 식별자.. 변수 선언 = timerInter ]
			clearInterval( timerInter )
			// 2. 인증실패 알림
			document.querySelector('.emailcheckbox').innerHTML =`인증실패`;
			// 3. authbox 구역 HTML 초기화
			document.querySelector('.authbox').innerHTML=``;
			checkList[2] = false;
		}
	} , 1000 ); // 1초에 한번씩 실행되는 함수
} // f end

// 6. 인증요청후 인증코드를 입력하고 인증하는 함수
function auth(){ console.log('auth() open')
	// 1. 입력받은 인증코드
	let ecode = document.querySelector('.ecode').value;
	// 2. 비교[ 인증코드 와 입력받은 인증코드 ]
	if( authcode == ecode ){
		clearInterval( timerInter ); // 1. setInterval 종료
		document.querySelector('.emailcheckbox').innerHTML =`인증성공`; // 2. 인증성공 알림
		document.querySelector('.authbox').innerHTML=``; // 3. authbox 구역 HTML 초기화
		checkList[2] = true;
	}else{
		// 1. 인증코드 불일치 알림
		document.querySelector('.emailcheckbox').innerHTML =`인증코드 불일치`; checkList[2] = false;
	}
} // f end

// 7. 첨부파일에 등록된 사진을 HTML 표시하기 < 등록 사진을 미리보기 기능 >
function preimg( o ){ console.log('사진 선택 변경');
	console.log( o ); // 이벤트 발생시킨 태그의 DOM객체를 인수로 받음
	// 1. input태그의 속성 [ type,class,onchange,name,value 등등 ] type="file" 이면 추가적인 속성
		// .files : input type="file" 에 선택한 파일 정보를 리스트로 받음
	console.log( o.files );		console.log( o.files[0] ); // 리스트중에서 하나의 파일만 가져오기
	// --- 해당 파일을 바이트코드 변환
	// 2. JS 파일읽기 클래스 선언
	let file = new FileReader(); // 파일 읽기 클래스 이용한 파일읽기객체 선언
	// 3. 파일 읽어오기 함수 제공
	file.readAsDataURL( o.files[0] ); // input 에 등록된 파일리스트(o.files) 중 1개를 파일객체로 읽어오기
		console.log( file );
	// document.querySelector('.preimg').src = file.result; // img src속성에 대입 // 오류
	// 4. 읽어온 파일을 해당 html img태그에 load
	file.onload = e => { // onload() : 읽어온 파일의 바이트코드를 불러오는 함수 구현
		console.log( e ); 				// e : 이벤트 정보
		console.log( e.target );	 	// onload() 실행한 FileReader 객체
		console.log( e.target.result ); // 읽어온 파일의 바이트코드
		document.querySelector('.preimg').src = e.target.result; // img src속성에 대입
	}
} // f end


let checkList = [ false , false , false ] // [0] : 아이디통과여부 , [1] : 패스워드통과여부 , [2] : 이메일통과여부
	// true 통과 , false 비통과
// 8. 회원가입 메소드
function signup(){
	// 1. 아이디/비밀번호/이메일 유효성검사 통과 여부 체크
		console.log( checkList )
	if( checkList[0] && checkList[1] && checkList[2] ){ // checkList 에 저장된 논리가 모두 true 이면
		console.log('회원가입 진행가능');

		// 2. 입력받은 데이터를 한번에 가져오기 form 태그 이용
			// <form> 각종 input/button </from>
			// 1. form 객체 호출  document.querySelectorAll( 폼태그식별자 )
			let signupForm = document.querySelectorAll('.signupForm')[0];
				console.log( signupForm );
			// 2. form 데이터 객체화
				// 일반객체로 첨부파일 전송X -> FormData객체 이용시 첨부파일 전송 가능
			let signupData = new FormData( signupForm ); // 첨부파일 [ 대용량 ] 시 필수..
				console.log( signupData );
			// 3. AJAX 에게 첨부파일[대용량] 전송 하기
				// 2. 첨부파일 있을때. [ 기존 json형식의 전송x form객체 전송 타입으로 변환 ]
				$.ajax({
					url : "/jspweb/MemberInfoController" ,
					method: "post" ,			// form 객체 [ 대용량 ] 전송은 무조건 post 방식
					data : signupData ,			// FormData 객체를 전송
					contentType : false ,		// form 객체 [ 대용량 ]  전송타입
					processData : false ,
					success : r => {
						if( r ){ // 회원가입성공 [ 1.알린다 2.페이지전환]
							alert('회원가입성공');
							location.href = '/jspweb/member/login.jsp';
						}
						else{ // 회원가입실패
							alert('회원가입실패[관리자문의]');
						}
					} ,
					error : e => { console.log(e) } ,
				})
	}else{
		alert('정상적으로 입력 안된 내용이 있습니다.');
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
function signup(){
    console.log( "signup() ");
    // 1. HTML 입력값 호출[ document.querySelector() ]
//    let id  = document.querySelector('#id').value;      console.log( id );
//    let pw  = document.querySelector('#pw').value;      console.log( pw );
//    let name = document.querySelector('#name').value;   console.log( name );
//    let phone = document.querySelector('#phone').value; console.log( phone );
//    let email = document.querySelector('#email').value; console.log( email );
//    let img = document.querySelector('#img').value;     console.log( img );
//    // --- 유효성검사
//    // 2. 객체화 [ let info = { }  ]
//    let info = {
//        id : id , pw : pw , name : name , phone : phone , email : email , img : img
//    }; console.log( info );
    // 3. [1개월차] 객체를 배열에 저장 --> [3개월차] SPRING CONTROLLER 서버 와 통신[ JQUERY AJAX ]

    let signupForm = document.querySelectorAll('.signupForm2')[0];
    let signupData = new FormData( signupForm );

    $.ajax({
            url : '/member/signup',         // controller 매핑 주소
            method : 'POST',                // controller 매핑 방법
//            data :  info ,                  // controller 요청 보낼 매개변수
            data : signupData ,			// FormData 객체를 전송
            contentType : false ,		// form 객체 [ 대용량 ]  전송타입
            processData : false ,
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
}