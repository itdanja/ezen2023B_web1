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
                async : false,
                success : (loginId)=>{
                    if( loginId == r.mid ){
                        let btnHTML = `<button class="boardBtn" type="button" onclick="onDelete( )"> 삭제 </button>`
                        btnHTML +=  `<button class="boardBtn" type="button" onclick="location.href='/board/update?bno=${ r.bno }'"> 수정 </button>`
                        document.querySelector('.btnBox').innerHTML += btnHTML


                    }
                } // success end
            }) // ajax2 end

            getReplyList();

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


// 6. 댓글 쓰기
function rwrite( brindex ){
	$.ajax({
		url : "/board/reply/write.do" ,
		method : "post" ,
		data : {
			"brindex" : brindex ,
			"bno" : bno ,
			"brcontent" : document.querySelector(`.rcontent${brindex}`).value
			} ,
		success : (r)=>{
			console.log(r);
			if( r == true ){
				alert('댓글작성성공');
				document.querySelector(`.rcontent${brindex}`).value = ''
				if( brindex >= 1 ){
				    document.querySelector(`.subReplyBox${ brindex }`).innerHTML = '';
				}
				getReplyList();
			 }
			else{ alert('댓글작성실패');}
		}
	});
}
// 7. 댓글 출력
function getReplyList(){
	$.ajax({
		url : "/board/reply/do" ,
		method : "get" ,
		data : { "type" : 1 ,"bno" : bno },
		success : (r) => {
			console.log(r);

			let html = ''
			r.forEach( (o) => {
				html +=`
					<div>
						<span>${ o.mno} </span>
						<span>${ o.brdate} </span>
						<span>${ o.brcontent} </span>
						<span> <button type="button" onclick="subReplyView(${ o.brno })"> 답글 </button> </span>
						<div class="subReplyBox${ o.brno }"></div>
						<div>`
						    o.subReply.forEach( ( o2 )=>{
						        html +=` <div class="subReply">
                                                <span>${ o2.mno} </span>
                                                <span>${ o2.brdate} </span>
                                                <span>${ o2.brcontent} </span>
                                        </div>`
						    })
                html +=`
						</div>
					</div>
					`
			})
			document.querySelector('.replylistbox').innerHTML = html;
		}
	})
}//end

function subReplyView( brno ){

    document.querySelector(`.subReplyBox${ brno }`).innerHTML = `
        <textarea class="rcontent${brno}" rows="" cols=""></textarea>
        <button class="rwritebtn boardBtn" onclick="rwrite( ${ brno })" type="button">댓글작성</button>
    `

}









