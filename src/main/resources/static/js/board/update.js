
// ( $ : jquery 문법  : )
    // JS : window.onload = function(){}            VS  JQUERY : $(document).ready(function(){}  : 문서(HTML)이 모두 렌더링 되었을때
    // JS : document.querySelector('#summernote')   VS  JQUERY : $('#summernote')
    // JS :    X
let bno = new URL( location.href ).searchParams.get('bno');
// 1. 게시물 개별 조회
onView();
function onView(){
    $.ajax({
        url : "/board/view.do" ,  method : "get" , data : { "bno" : bno },
        success : (r)=>{
            document.querySelector('.btitle').value = r.btitle
            document.querySelector('.bcontent').innerHTML = r.bcontent;
            document.querySelector('.bcno').value = r.bcno;
            document.querySelector('.bfile').innerHTML = r.bfile;
              let option = { // 썸머노트 옵션객체
                lang : 'ko-KR' , // 한글패치
                height : 500  // 에디터 세로 크기
              }$('#summernote').summernote( option ); // 썸머노트 실행
        }
    }) // ajax end
} // f end
// 2. 게시물 수정
function onUpdate(){
    // 1. 폼 가져온다.
    let boardUpdateForm = document.querySelector('.boardUpdateForm');
    // 2. 폼 객체화 ( 첨부파일 바이트화 )
    let boardUpdateFormData = new FormData( boardUpdateForm );
        // + 폼 객체에 데이터 추가. [ HTML 입력 폼 외 데이터 삽입 가능 ]
        // 폼데이터객체명.set( 속성명(name) , 데이터(value) );
        boardUpdateFormData.set( 'bno' , bno );
    $.ajax({
        url : "/board/update.do", method:'put',
        data : boardUpdateFormData ,
        contentType : false , processData : false , // 멀티파트 폼 전송
        success : (r)=>{
            if( r ){ alert('수정성공'); location.href="/board/view?bno="+bno; }
            else{ alert('수정실패'); }
        }
    });
}











