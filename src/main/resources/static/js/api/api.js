
var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
    center : new kakao.maps.LatLng(37.3218778, 126.8308848), // 지도의 중심좌표
    level : 5 // 지도의 확대 레벨
});

var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 6, // 클러스터 할 최소 지도 레벨
        disableClickZoom: true // 클러스터 마커를 클릭했을 때 지도가 확대되지 않도록 설정한다
    });

    		// 마커 이미지의 주소
    		var markerImageUrl = '/img/kmapicon.png',
    		    markerImageSize = new kakao.maps.Size(40, 42), // 마커 이미지의 크기
    		    markerImageOptions = {
    		        offset : new kakao.maps.Point(20, 42)// 마커 좌표에 일치시킬 이미지 안의 좌표
    		    };

    		// 마커 이미지를 생성한다
    		var markerImage = new kakao.maps.MarkerImage(markerImageUrl, markerImageSize, markerImageOptions);



공공데이터포털()
function 공공데이터포털(){

    $.ajax({
        url : "https://api.odcloud.kr/api/15036519/v1/uddi:15033443-816d-45fb-82a6-d3bc678f5a57?page=1&perPage=122&serviceKey=z427Q0DLkQqM0SDOc1Lz8jPzk%2BKj0ng%2Bvz7h3I8CpVs3T90219bWi2o%2BmStIxJW%2B9lwayA%2FsAT6apxsxuvydQg%3D%3D",
        method : "get",
        success : (r)=>{
            console.log(r);

            let html = ``;

            r.data.forEach( (row)=>{

                html += `                <tr>
                                             <th> ${row.관리부서명}</th>
                                             <th> ${row.대표전화}</th>
                                             <th> ${row.소재지도로명주소}</th>
                                             <th> ${row.소재지지번주소}</th>
                                             <th> ${row['수용가능인원(명)'] }</th>
                                             <th> ${row.시설면적 }</th>
                                             <th> ${row.시설명 }</th>
                                         </tr>`


            })


            var markers = r.data.map( (position) => {
                return new kakao.maps.Marker({
                    position : new kakao.maps.LatLng(position.위도, position.경도),
                     image : markerImage
                });
            } );


            // 클러스터러에 마커들을 추가합니다
            clusterer.addMarkers(markers);


            document.querySelector('table > tbody').innerHTML = html;


        }
    })

}
