
/* 게시물 조회 조건 객체 */
let pageOject = {
	bcno : 0  , pageBoardSize : 5 ,  // * type : 1:전체조회 , 2:개별조회 // * bcno : 조회할 카테고리 번호 [ 기본값은 전체보기 ] // * listsize : 하나의 페이지에 최대표시할 게시물수 [ 기본값은 10개 ]
	page : 1 , key : '' , keyword : ''
	// * page : 조회할 페이지번호  // key : 검색할 기준 필드명 // keyword : 검색할 데이터
}



// 1. 전체 출력용 : 함수 - 매개변수 = page , 반환 x , 언제 실행할껀지 : 페이지 열릴때(JS)
doViewList( 1 ); // 첫페이지 실행
function doViewList( page ){   console.log( "doViewList()");

    pageOject.page = page;

    $.ajax({
        url : "/board/do" ,
        method : "get" ,
        data : pageOject ,
        success : (r)=>{    console.log( r );
            // ==테이블에 레코드 구성======================================================
            // 1. 어디에
            let boardTableBody = document.querySelector("#boardTableBody");
            // 2. 무엇을
            let html = ``;
                // 서버가 보내준 데이터를 출력
                // 1.
                r.list.forEach( board => {
                    console.log( board );
                    html += `<tr>
                                 <th> ${ board.bno }</th>       <td> ${ board.btitle } </td>
                                 <td> <img src="/img/${ board.mimg }"
                                       style="width:20px; border-radius:50%;" /> ${ board.mid }
                                 </td>
                                 <td> ${ board.bdate } </td>    <td> ${ board.bview } </td>
                             </tr>`
                });
            // 3. 출력
            boardTableBody.innerHTML = html;
            // == 페이지네이션(페이지버튼) 구성 ======================================================
            // 1. 어디에
            let  pagination = document.querySelector('.pagination');
            // 2. 무엇을
            let pagehtml = ``;
                // 이전 버튼 ( 만약에 현재페이지가 1페이지이면 1페이지 고정 )
                pagehtml += `<li class="page-item"><a class="page-link" onclick="doViewList( ${ page-1 < 1 ? 1 : page-1 } )">이전</a></li>`
                // 페이지번호 버튼 ( 1페이지부터 마지막페이지(totalPage)까지
                for( let i = r.startBtn ; i <= r.endBtn ; i++ ){
                    // + 만약에 i가 현재페이지와 같으면 active 클래스 삽입 아니면 생략 ( *조건부 렌더링 )
                    pagehtml += ` <li class="page-item ${ i == page ? 'active' : '' }"><a class="page-link" onclick="doViewList( ${ i } )"> ${ i } </a></li>`
                }
                // 다음 버튼 ( 만약에 현재페이지가 마지막 페이지 이면 현재 페이지 고정 )
                pagehtml += `<li class="page-item"><a class="page-link" onclick="doViewList( ${ page+1 > r.totalPage ? r.totalPage : page+1 } )">다음</a></li>`
            // 3. 출력
            pagination.innerHTML = pagehtml;



            document.querySelector('.boardcount').innerHTML = `${r.totalBoardSize}`;
        } // success end
    }); // ajax end
    return;
}


// 3. 카테고리 버튼을 클릭했을때.
function onCategory( bcno ){ 	console.log('클릭된 카테고리 : ' + bcno );
	pageOject.bcno = bcno; // 조회 조건객체내 카테고리번호를 선택한 카테고리로 변경
	pageOject.key = ''; pageOject.keyword = '' // 검색해제
	doViewList(1); // 조건이  변경되었기 때문에 다시 출력[ 재렌더링/새로고침 ]
	console.log( pageOject )


	let buttons = document.querySelectorAll('.boardcategorybox button');
	console.log( buttons );
	for( let i = 0 ; i<buttons.length ; i++ ){
	    buttons[i].classList.remove('categoryActive');
	    if( i == bcno ) buttons[i].classList.add('categoryActive');
	}

} // f end

// 4. 한페이지 최대 표시할 개수를 변경했을때.
function onListSize(){
	pageOject.pageBoardSize=document.querySelector('.pageBoardSize').value; // 선택된 게시물수를 조회조건객체 저장
	doViewList(1); // 조건이  변경되었기 때문에 다시 출력[ 재렌더링/새로고침 ]
	console.log( pageOject )
} // f end


// 5. 검색 버튼을 클릭했을때.
function onSearch(){
	pageOject.key = document.querySelector('.key').value;
	pageOject.keyword = document.querySelector('.keyword').value;
	console.log( pageOject )
	doViewList(1);
} // f end
