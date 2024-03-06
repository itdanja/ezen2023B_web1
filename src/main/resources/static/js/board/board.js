// ======== 페이지 정보 관련 객체 = 여러개 변수 묶음 ======== //
let pageObject = {          // 기본값
    page : 1 ,              // 현재 페이지
    pageBoardSize : 5 ,      // 현재 페이지당 표시할 게시물 수
    bcno : 0 ,               // 현재 카테고리
    key : 'b.btitle',       // 현재 검색 key
    keyword : ''           // 현재 검색 keyword
}

// 1. 전체 출력용 : 함수 - 매개변수 = page , 반환 x , 언제 실행할껀지 : 페이지 열릴때(JS)
doViewList( 1 ); // 첫페이지 실행
function doViewList( page ){   console.log( "doViewList()");

    pageObject.page = page; // 매개변수로 들어온 페이지를 현재페이지에 대입

    $.ajax({
        url : "/board/do" ,
        method : "get" ,
        data : pageObject ,
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

            // == 3. 부가 출력  ======================================================

            document.querySelector('.totalPage').innerHTML = r.totalPage;
            document.querySelector('.totalBoardSize').innerHTML = r.totalBoardSize;

        } // success end
    }); // ajax end
    return;
} // end

// 2. 페이지당 게시물 수
function onPageBoardSize( object ){     console.log( object );console.log( object.value );
    pageObject.pageBoardSize = object.value;
    doViewList( 1 );
}
// 3. 카테고리 변경 함수
function onBcno( bcno ){
    // bcno : 카테고리 식별번호 [ 0 : 전체 , 1~ : 식별번호pk
    pageObject.bcno = bcno;
    // 검색 제거 ( 검색이 없다는 기준 데이터 )
    pageObject.key = 'b.title';
    pageObject.keyword = '';
    document.querySelector('.key').value = 'b.btitle'
    document.querySelector('.keyword').value = '';
    //
    doViewList( 1 );
}
// 4. 검색 함수
function onSearch(){
    let key =  document.querySelector('.key').value; // 1. 입력받은 값 가져오기
    let keyword = document.querySelector('.keyword').value;
    pageObject.key = key;  // 2. 서버에 전송할 객체에 담아주고
    pageObject.keyword = keyword;
    doViewList( 1 ); // 3. 출력 함수 호출
}
















