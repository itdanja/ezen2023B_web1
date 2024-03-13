
// 클라이언트(브라우저) 위치 가져오기
    // 1. navigator.geolocation.getCurrentPosition() : 현재 위치 정보 호출( JS ) 함수
navigator.geolocation.getCurrentPosition( ( myLocation ) =>{

    console.log( myLocation );
    console.log( myLocation.coords );
    console.log( myLocation.coords.latitude );  // 현재 위치 위도
    console.log( myLocation.coords.longitude );  // 현재 위치 경도

    // 카카오 지도 실행
    kakaoMapView( myLocation.coords.latitude  , myLocation.coords.longitude  );

})

function kakaoMapView( latitude , longitude ){

    // 1. 지도 객체
    var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center : new kakao.maps.LatLng( latitude, longitude ), // 지도의 중심좌표
        level : 3 // 지도의 확대 레벨 [ 0 :확대 ~ 14 : 축소 ]
    });
    // =========== 마커 이미지 ============ //
    var imageSrc = '/img/mapicon.png', // 마커이미지의 주소입니다
        imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
        imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

    // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption)
    // ================================== //

    // 2. 클러스터 객체 ( 클러스터란 마커가 여러일때 효과 )
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 5 // 클러스터 할 최소 지도 레벨
    });

    // 3. 마커 생성후 클러스터 넣을 마커들의 데이터
    // 데이터를 가져오기 위해 jQuery를 사용합니다   // 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
    $.get("/product/list.do", ( response ) => { console.log( response );

        let markerList = response.map( (data) => {
            // 1. 마커 생성
            let marker = new kakao.maps.Marker( {
                position  : new kakao.maps.LatLng( data.plat , data.plng ),
                image: markerImage
            } )

            // - 클러스터에 넣기 전에 마커 커스텀
            // 1. 마커에 클릭이벤트를 등록합니다
            kakao.maps.event.addListener( marker, 'click', function() {
                  // 2. 만약에 마커 클릭시 사이드바 열기
                  document.querySelector('.sideBarBtn').click();
                  // 3. 사이드바 내용물
                    // 1. 제품 제목
                  document.querySelector('.offcanvas-title').innerHTML = `제품명 : ${ data.pname }`
                    // 2. 제품 이미지들
                  let carouselHTML = '';

                  let index = 0;
                  data.pimg.forEach( (img)=>{
                    carouselHTML += `<div class="carousel-item ${ index == 0 ? 'active' : '' }">
                                         <img style="height:400px; object-fit: cover;" src="/img/${img}" class="d-block w-100" alt="...">
                                     </div>`
                     index++;
                  } )

                  document.querySelector('.offcanvas-body .carousel-inner').innerHTML = carouselHTML
                    // 3. 제품 가격/내용들
                    // 4. 버튼( 찜하기 , 채팅하기 )
                    plikeView( data.pno );
            });
            return marker; // 2. 클러스터 저장하기 위해 반복문 밖으로 생성된 마커 반환.
        });
        // 3. 클러스터에 마커들(markerList  )
        clusterer.addMarkers(markerList);
    });
}; //
// 2. 주소도 동일하고 매개변수도 동일할때
function plikeWrite( pno , method ){    console.log( pno ); console.log( method );
    let result = false;
    $.ajax({
        url : "/product/plike.do" ,
        method : method ,
        async : false ,
        data: { 'pno' : pno },
        success : function(r) { console.log( r );
            result = r;
        }
    });
    if( method != 'get' ) plikeView( pno ); // 찜하기 변화 가 있을때
    return result;
}
// 3. 찜하기 상태 출력 함수 // 1., 사이드바 열릴때  2. 찜하기 변화가 있을때.
function plikeView( pno ){
       // *현재 로그인 했고 찜하기 상태 여부 따라 css 변화
      let result = plikeWrite(  pno , 'get'  );
      if( result  ){ // 로그인 했고 이미 찜하기 상태.
           document.querySelector('.offcanvas-body .sideBarBtnBox').innerHTML = `
                <button onclick="plikeWrite( ${ pno } ,  'delete' )" type="button"> 찜하기 ♥</button>
                <button type="button"> 채팅하기 </button> `
      }else{ // 로그인 안했거나 찜하기 안한 상태.
         document.querySelector('.offcanvas-body .sideBarBtnBox').innerHTML = `
              <button onclick="plikeWrite( ${ pno } ,  'post' )" type="button"> 찜하기 ♡ </button>
              <button type="button"> 채팅하기 </button> `
      }
}

//plikeWrite( 3 , 'get' );
//plikeWrite( 3 , 'post' );
//plikeWrite( 3 , 'delete' );

/*
    AJAX
        $.ajax({  url : "" , method : "" ,  success : (r)=> {}   })
        $.get( url , (r)=> { } )
*/


