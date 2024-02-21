
console.log( 'day08Board');

// 1. 저장 메소드 : 실행조건 : 등록 버튼 클릭시    매개변수x , 리턴x
function doCreate(){ console.log( "doCreate()" );
    // 1. 입력받은 데이터 가져오기
    let bcontent    = document.querySelector('#bcontent').value;    console.log( bcontent );
    let bwriter     = document.querySelector('#bwriter').value;     console.log( bwriter );
    let bpassword   = document.querySelector('#bpassword').value;   console.log( bpassword );
    // * 유효성검사/ 생략
    // 2. 객체화
    let info = {
        bcontent : bcontent ,
        bwriter : bwriter ,
        bpassword : bpassword
    };  console.log( info );
    // 3. controller 전송후 응답
    // ----------- AJAX ------------- //
        // 1. jquery 라이브러리 호출 :  없으면 : $ is not defined : $ 사용하는 문법 라이브러리가 jquery 가 없다는 뜻
        // 2. ajax 함수
    $.ajax({
       url : '/board/create',  method : 'post', data : info ,
       success : function ( result ){ console.log(result);
            // 4. 결과
            if( result ){  alert('글쓰기 성공'); doRead();} // 안내후 게시물목록 새로고침
            else{    alert('글쓰기 실패');    }
       }
    }) // ajax end
    // ------------------------------ //
} // end

// 2. 전체 호출 메소드 : 실행조건 : 페이지 열릴때 , 변화(저장,수정,삭제)가 있을때( 부분 새로고침  )   매개변수 x , 리턴 x
doRead(); // JS 열릴때 최초 실행;
function doRead( ){
    console.log( "doRead()" );
    // ----------- AJAX ------------- //
    $.ajax({
       url : '/board/read',
       method : 'GET',
       success : function ( result ){ console.log(result);
            // 1. 어디에
            let tbody = document.querySelector('table tbody');
            // 2. 무엇을
            let html = "";
            for( let i = 0 ; i<result.length ; i++ ){
                console.log( result[i] );
                // 백틱 ` : 키보드 tab키 위에 있는 키 // ``백틱 문자열 사이에 ${ JS코드 } 대입하는 템플릿
                html += `<tr>
                             <th> ${ result[i].bno }  </th>
                             <th> ${ result[i].bcontent }  </th>
                             <th> ${ result[i].bwriter }  </th>
                             <th>
                                 <button onclick="doDelete( ${ result[i].bno }  )" >삭제</button>
                                 <button onclick="doUpdate( ${ result[i].bno }  )" >수정</button>
                             </th>
                         </tr>`
            };
            // 3. 출력
            tbody.innerHTML = html;
       }
    })
    // ------------------------------ //
}
// 3. 수정 메소드 : 실행조건 : 수정 버튼 클릭시    매개변수 : 수정할식별키bno , 리턴 x
function doUpdate( bno ){
    console.log( "doUpdate()" + bno );
    // ----------- AJAX ------------- //
    $.ajax({
       url : '/board/update',
       method : 'POST',
       data :  { bno: bno , bcontent : '수정할내용' }  ,
       success : function ( result ){ console.log(result); }
    })
    // ------------------------------ //
}
// 4. 삭제 메소드 : 실행조건 : 삭제 버튼 클릭시    매개변수 : 삭제할식별키bno , 리턴 x
function doDelete( bno ){
    console.log( "doDelete()" + bno  );
    // ----------- AJAX ------------- //
    $.ajax({
       url : '/board/delete/'+bno,
       method : 'GET',
       success : function ( result ){ console.log(result); }
    })
    // ------------------------------ //
 }

