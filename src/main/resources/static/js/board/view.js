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
            document.querySelector('.mid').innerHTML = r.mid;
            document.querySelector('.bdate').innerHTML = r.bdate;
            document.querySelector('.bview').innerHTML = r.bview;

            //* 다운로드 링크
                // 유효성검사
            if( r.bfile != null){
                document.querySelector('.bfile').innerHTML = `<a href="/board/file/download?bfile=${ r.bfile }"> ${ r.bfile } </a>`;
            }
            //* 삭제 / 수정 버튼 활성화 ( 해당 보고 있는 클라이언트가 게시물 작성자의 아이디와 동일하면 )
                // 유효성검사.
                // 현재 로그인된 아이디 또는 번호 ( 1.헤더 HTML 가져온다 . 2.서버에게 요청 )
            $.ajax({
                url : "/member/login/check",
                method : 'get',
                success : (loginId)=>{
                    if( loginId == r.mid ){
                        let btnHTML = `<button class="boardBtn" type="button" onclick="onDelete( )"> 삭제 </button>`
                        btnHTML +=  `<button class="boardBtn" type="button" onclick="location.href='/board/update?bno=${ r.bno }'"> 수정 </button>`
                        document.querySelector('.btnBox').innerHTML += btnHTML
                    }
                } // success end
            }) // ajax2 end
            onRelyList(); // 댓글출력함수 호출
        } // success end
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

// 3. 댓글쓰기
function onRelyWrite( brindex ){
    $.ajax({
        url : "/board/reply/write.do" ,  method : "post",
        data : {
            'bno' : bno , // -- 현재 보고 있는 게시물번호
            'brcontent' : document.querySelector(`.brcontent${ brindex }`).value,  // 작성된 댓글 내용
            'brindex' : brindex // 댓글 위치 번호[ 0:상위, 1~:하위]
        },
        success : (r)=>{
            console.log(r);
            if( r ){  alert('댓글 작성 성공'); onRelyList();}
            else{ alert('댓글 작성 실패');}
        }
    }); // ajax end
} // f end
// 4. 댓글출력 [ 1. 게시물 출력된후 2.댓글작성시 3.댓글삭제 4.댓글수정 ]
function onRelyList(){
    $.ajax({
        url : "/board/reply/do", method : "get", data : { "bno" : bno },
        success : (r)=>{ console.log( r );
            let replyListBox = document.querySelector('.replyListBox');
            let html = ``;
                r.forEach( (reply)=>{
                    html += `<div>
                                <span>${ reply.brcontent}</span>
                                <span>${ reply.mno}</span>
                                <span>${ reply.brdate}</span>
                                <button type="button" onclick="subReplyView( ${ reply.brno } )"> 답변작성하기 </button>
                                <div class="subReplyBox${ reply.brno }"> </div>
                                ${ onSubRelyList(  reply.subReply  ) }
                            </div>` // class 명 뒤에 식별키(pk) 연결
                });
            replyListBox.innerHTML = html;
        }
    })
} // f end
// 5. 대댓글 작성 칸 생성 함수
function subReplyView( brno ){
    let html = ` <textarea class="brcontent${ brno }"> </textarea>
                <button onclick="onRelyWrite( ${ brno })" type="button"> 답변작성 </button> `
    document.querySelector(`.subReplyBox${ brno }`).innerHTML = html
} // f end
// 6. 대댓글 함수 출력 함수
function onSubRelyList( index , subReply ){
    let subHTML = ``;
    subReply.forEach( (reply)=>{
        subHTML += `<div style="margin-left : 50px;">
                    <span>${ reply.brcontent}</span>
                    <span>${ reply.mno}</span>
                    <span>${ reply.brdate}</span>
                </div>`
    });
    return subHTML;
}










