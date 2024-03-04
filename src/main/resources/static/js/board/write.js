//1. 글쓰기
function onWrite(){ console.log( "onWrite()");
    // 1. 폼DOM 가져온다.
    let boardWriteForm = document.querySelector('.boardWriteForm');
    // 2. 폼 바이트(바이너리) 객체 변환[ 첨부파일 보낼때는 필수 ]
    let boardWriteFormData = new FormData( boardWriteForm );
    // 3. ajax 첨부파일 폼 전송
    $.ajax({
        url : "/board/write.do" ,
        method : "post" ,
        data : boardWriteFormData ,
        contentType : false,
        processData : false,
        success : (r)=>{
            console.log( r );
        }
    }); // ajax end
}