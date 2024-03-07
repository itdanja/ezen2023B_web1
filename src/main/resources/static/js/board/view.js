// * JS에서 경로(URL)상의 쿼리스트링(매개변수) 호출하기.
    // 1. new URL( location.href ) : 현재 페이지의 경로객체 호출
console.log( new URL( location.href ) );
    // 2. [.searchParams]경로상의 (쿼리스트링)매개변수들
console.log( new URL( location.href ).searchParams );
    // 3. [.get('queryStringKey') ](쿼리스트링)매개변수들 에서 특정 매개변수 호출
console.log( new URL( location.href ).searchParams.get('bno') );

let bno = new URL( location.href ).searchParams.get('bno');

// 1. 게시물 개별 조회
onView();
function onView(){
    console.log( "onView()");
    $.ajax({
        url : "/board/view.do" ,
        method : "get" ,
        data : { "bno" : bno },   // 쿼리스트링.
        success : (r)=>{
            console.log(r);
            document.querySelector('.btitle').innerHTML = r.btitle
            document.querySelector('.bcontent').innerHTML = r.bcontent;

            document.querySelector('.bcno').innerHTML = r.bcno;
            document.querySelector('.mno').innerHTML = r.mno;
            document.querySelector('.bdate').innerHTML = r.bdate;
            document.querySelector('.bview').innerHTML = r.bview;
            //* 다운로드 링크
            document.querySelector('.bfile').innerHTML = `<a href="/board/file/download?bfile=${ r.bfile }"> ${ r.bfile } </a>`;
        }
    }) // ajax end
} // f end

// 2. 게시물 삭제 함수
function onDelete( ){
    $.ajax({
        url : "/board/delete.do" , method : "delete" , data : { 'bno' : bno } , success : (r)=>{
            if( r ){ alert('삭제성공'); location.href="/board"; }
            else{ alert('삭제실패'); }
        }
    });
}










