
console.log( 'day08Board');

// 1. 저장 메소드 : 실행조건 : 등록 버튼 클릭시    매개변수x , 리턴x
function doCreate(){
    console.log( "doCreate()" );

    $.ajax({
        url : '/board/create',
        method : 'Post',
        data :  { bcontent : '내용' , bwriter : '작성자' , bpassword : '비밀번호' },
        success : function ( result ){
            console.log( result);
        }
    })

}
// 2. 전체 호출 메소드 : 실행조건 : 페이지 열릴때 , 변화(저장,수정,삭제)가 있을때(새로고침)   매개변수 x , 리턴 x
doRead(); // JS 열릴때 최초 실행;
function doRead( ){
    console.log( "doRead()" );

    $.ajax({
        url : '/board/read',
        method : 'get',
        success : function ( result ){
            console.log( result);
        }
    })
}
// 3. 수정 메소드 : 실행조건 : 수정 버튼 클릭시    매개변수 : 수정할식별키bno , 리턴 x
function doUpdate( bno ){
    console.log( "doUpdate()" + bno );

    $.ajax({
        url : '/board/update',
        method : 'post',
        data :  { bno : bno , bcontent : '수정내용합니다.' },
       success : function ( result ){
           console.log( result);
       }
    })
}
// 4. 삭제 메소드 : 실행조건 : 삭제 버튼 클릭시    매개변수 : 삭제할식별키bno , 리턴 x
function doDelete( bno ){
    console.log( "doDelete()" + bno  );

    $.ajax({
        url : '/board/delete/'+bno,
        method : 'Get',
       success : function ( result ){
           console.log( result);
       }
    })

 }

