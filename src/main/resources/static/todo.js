console.log('todo.js실행');
// JS 함수 정의 : function 함수명( 매개변수 ) { 실행문; }
// JS 익명함수  : function( 매개변수 ) { 실행문; }
// { key : value , key : value } : 객체   vs  [ ] : 배열
// 1. 할일등록 함수
function doPost(){
    console.log( 'doPost()' );
    // 1. HTML입력받은 값 가져오기
    let content = document.querySelector('#content').value;
    let deadline = document.querySelector('#deadline').value;
        console.log( content );
        console.log( deadline );
    // 2. 객체화(선택)
    let info = {
        content : content ,
        deadline : deadline
    }; console.log( info );
    // 3. 컨트롤에게 요청 / 응답
        // HTTP통신 : 어디에(action/url) 어떻게(method/method) 보낼데이터(name/data) 응답데이터(x/success)
       $.ajax({
            url : '/todo/post.do',
            method : 'post',
            data : info ,
            success : function( result ){
                console.log( result ); // 성공시 true / 실패면 false
                if( result == true ){
                    // 화면갱신// 4. 출력
                    doGet();
                }
            }
       }) // ajax end
} // m e

// 2. 할일목록출력 함수
doGet(); // JS 실행시 최초로 1번 실행.
function doGet(){
    $.ajax({
        url : '/todo/get.do' ,
        method : 'get' ,
        success : function result( resultValue ){
            console.log( resultValue );
            // 통신 응답 결과를 HTML형식으로 출력해주기.
            // 1. 어디에
            let tbody = document.querySelector('table tbody')
            // 2. 무엇을
            let html = ``; // ` 백틱
                for( let i = 0 ; i < resultValue.length ; i++ ){
                    html += `<tr>
                                 <th> ${ resultValue[i].id } </th>
                                 <th> ${ resultValue[i].content} </th>
                                 <th> ${ resultValue[i].deadline}</th>
                                 <th> ${ resultValue[i].state}</th>
                             </tr>`
                } // for end
            // 3. 대입
            tbody.innerHTML = html;
        } // success end
    }) // ajax end
} // m end

// 3. 할일 상태 수정 함수
function doPut(){

}
// 4. 할일 삭제 함수
function doDelete(){

}
    // - 스프링(자바) 와 통신( 주고 받고 )
    // JQUERY AJAX 함수
        //  $.ajax( JSON형식의 통신정보 )
        /*
            HTTP method : post , get , put , delete 등등

            $.ajax({})
            $.ajax({
                url : spring controller url / 통신 대상 식별 ,
                method : 'HTTP method / 통신 방법 ' ,
                data : 'HTTP request value / 통신 요청으로 보낼 데이터'
                success : HTTP response function / 통신 응답 함수
            })
        */
