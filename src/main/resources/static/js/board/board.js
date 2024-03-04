let listSize = 5;
let bcno = 0;

function onCategoryChange( object ){
	bcno = object.value ;
	doBoards( 1 );
} // f end

function onListSizeChange( object ){
    console.log( object );   console.log( object.value );
    listSize = object.value ;
    doBoards( 1 );
}

doBoards( 1 );
function doBoards( page ){

    console.log('doBoards()'  + page );

    $.ajax({
        url : "/board/do",
        method : 'get',
        data : { page : page , listSize : listSize , bcno : bcno  },
        success : (r)=>{ console.log(r);
            let boardBody = document.querySelector('#boardBody')

            let html = ``;

            r.list.forEach( board =>{
                console.log(board);

                html += `<tr>
                           <th scope="row">${ board.bno }</th>
                           <td> <a href="/board/view?bno=${ board.bno }"> ${ board.btitle } </a> </td>
                           <td><img src="/img/${ board.mimg }" style=" width: 20px;border-radius: 50%;"/>${ board.mid }</td>
                           <td>${ board.bdate }</td>
                           <td>${ board.bview }</td>
                         </tr>`;
            })
            boardBody.innerHTML = html;
            // ========================================================= //
            // 전체 페이지 개수 , 페이징 시작번호 , 페이징 끝번호
            let pageHtml = ``;

            pageHtml += `<li class="page-item"> <a class="page-link" onclick="doBoards( ${ page-1 < 1 ? page : page-1 } )" >이전</a></li>`

            for( let i = r.startBtn ; i<=r.endBtn ; i++ ){
               pageHtml += `<li class="page-item ${ i == page ? 'active' : '' }"><a class="page-link" onclick="doBoards( ${ i } )"> ${ i }</a></li>`
            }
            pageHtml += ` <li class="page-item"> <a class="page-link" onclick="doBoards( ${page+1 > r.totalPage ? page : page+1} )">다음</a></li>`

            document.querySelector('.pagination').innerHTML =  pageHtml;

        }
    });
}