
onGet();
function onGet(){
    $.ajax({
        type: "get",
        url: "/todo/get.do",
        success: function (response) {
            console.log( response );

            let html = '';

            for( let i = 0 ; i < response.length ; i++){
                
                html += `<tr>
                            <td>${ response[i].id}</td>   
                            <th>${ response[i].content}</td>
                            <td>${ response[i].deadline}</td>    
                            <th>${ response[i].state}</td>
                        </tr>`
            }

            document.querySelector('#todolist').innerHTML = html;

        }
    });
}


function onPost(){


    $.ajax({
        type: "post",
        url: "/todo/post.do",
        data: {
            'content' : document.querySelector('#content').value ,
            'deadline' : document.querySelector('#deadline').value 
        },
        success: function (response) {
            console.log( response );
            onGet();
        }
    });


}