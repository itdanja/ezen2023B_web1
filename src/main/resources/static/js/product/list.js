

const successCallback = (geolocation) => {
  console.log(geolocation);
  kakaoMap(  geolocation );
};

navigator.geolocation.getCurrentPosition( successCallback );

 function kakaoMap( myPosition){
    // 1. 지도 객체
    var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center : new kakao.maps.LatLng( myPosition.coords.latitude , myPosition.coords.longitude ), // 지도의 중심좌표
        level : 3 // 지도의 확대 레벨 [ 0 :확대 ~ 14 : 축소 ]
    });

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        map : map ,
        position: new kakao.maps.LatLng( myPosition.coords.latitude , myPosition.coords.longitude)
    });

    // 마커 이미지의 주소
    var markerImageUrl = '/img/mapicon.png',
        markerImageSize = new kakao.maps.Size(40, 42), // 마커 이미지의 크기
        markerImageOptions = {
            offset : new kakao.maps.Point(20, 42)// 마커 좌표에 일치시킬 이미지 안의 좌표
        };

    // 마커 이미지를 생성한다
    var markerImage = new kakao.maps.MarkerImage(markerImageUrl, markerImageSize, markerImageOptions);


    // 2. 클러스터 객체 ( 클러스터란 마커가 여러일때 효과 )
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 5 // 클러스터 할 최소 지도 레벨
    });
    // 3. 마커 생성후 클러스터 넣을 마커들의 데이터
    // 데이터를 가져오기 위해 jQuery를 사용합니다   // 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
    $.get("/product/list.do", ( response ) => { console.log( response );
        let markers = response.map( (data) => {
            // 1. 마커 생성
            let marker = new kakao.maps.Marker( {
                position  : new kakao.maps.LatLng( data.plat , data.plng ),
                image : markerImage, // 마커의 이미지
            } )

            // - 클러스터에 넣기 전에 마커 커스텀
            // 1. 마커에 클릭이벤트를 등록합니다
            kakao.maps.event.addListener(marker, 'click', function() {

                $.ajax({
                    url : "/product/view.do" ,
                    method : "get",
                    data : { pno : data.pno },
                    async : false ,
                    success : (r)=>{
                        console.log( r );

                        document.querySelector('.offcanvasBtn').click();

                        document.querySelector('.offcanvas-title').innerHTML = `${ r.pname }`
                       document.querySelector('.offcanvas-body').innerHTML = `<div>

                            <div id="carouselExample" class="carousel slide">
                              <div class="carousel-inner">
                                ${ pimgCarousel( r.pimg ) }
                              </div>
                              <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                              </button>
                              <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                              </button>
                            </div>
                            <div> ${ r.pcontent } </div>
                            <div> ${ r.pprice } </div>

                            <div style="margin: 20px 6px;">
                                <div style=" border: 1px solid #e8e8e8;min-height: 50%;">
                                </div>
                                <div style=" border: 1px solid #e8e8e8;   ">
                                    <input type="text" />
                                    <button type="button"> 전송 </button>
                                </div>
                            </div>

                       </div>`
                    }
                });
            });
            return marker; // 2. 클러스터 저장하기 위해 반복문 밖으로 생성된 마커 반환.
        });
        // 3. 클러스터에 마커들(markers)
        clusterer.addMarkers(markers);
    });

 }
/*
    AJAX
        $.ajax({  url : "" , method : "" ,  success : (r)=> {}   })
        $.get( url , (r)=> { } )
*/

function pimgCarousel( pimg ){
    console.log( pimg );
    let html = ``;
    let i = 0;
    pimg.forEach( (img)=>{
        html += `<div class="carousel-item ${ i==0 ? 'active' : ''}">
          <img style="min-height: 300px;object-fit: cover;" src="/img/${ img }" class="d-block w-100" alt="...">
        </div>`
        i++;
    });
    return html;
}
