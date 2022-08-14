let domainURL = 'https://eb.newfine.tk';

// function add_attendance() {
//
//     let user_phone = $('#user_phonenumber').val();
//     let data = {"studentPhoneNumber": user_phone};
//
//     $.ajax({
//         type: "POST",
//         url: `${domainURL}/add/attendance`,
//         contentType: "application/json",
//         data: JSON.stringify(data),
//         success: function (response) {
//             console.log(response)
//             alert('출석 되었습니다!');
//             $('input[name=inputNm]').attr('value',변수명);
//         }
//
//     });
// }
// function show_myattendance() {
//
//     let user_phone = $('#user_phonenumber').val();
//     console.log(user_phone)
//     location.href = `./attendance.html?user=`+user_phone;
//
//
// }
//
// function get_myattendance(user_phone) {
//     console.log(user_phone);
//     $.ajax({
//         type: "GET",
//         url: `${domainURL}/get/attendance/${user_phone}`,
//         data: {},
//         success: function (response) {
//             console.log(response);
//             for (let i = 0; i < response.length; i++) {
//                 let time=response[i]['createdDate']
//                 let phone=response[0]['studentPhone']
//
//                 let temp_html = `<tr>
//
//
//                                                 <td>${phone}</td>
//                                                 <td>과학</td>
//                                                 <td>${time}</td>
//
//                                             </tr>`;
//                 $('#attendance-box').append(temp_html);
//
//                 }
//
//         }
//
//     });
// }
//

function getTokenAndRefresh() {
    const accessToken = localStorage.getItem('accessToken');
    const refreshToken = localStorage.getItem('refreshToken');
    console.log("되고 있냐...");
    console.log("로컬 스토리지 액세스 토큰: ", accessToken, "\n로컬 스토리지 리프레시 토큰: ", refreshToken);

    if (accessToken==null || refreshToken==null){
        console.log("로그인쭈고");
        throw new Error();
    }

    let data = {"accessToken": accessToken, "refreshToken": refreshToken}
    $.ajax({
        type: "POST",
        url: `${domainURL}/auth/refreshTokenWeb`,
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (response) {
            // if (response) {
            //     alert("완료");
            // } else {
            //     alert("전송된 값 없음");
            // }
            console.log(response)
            localStorage.setItem('accessToken', response['accessToken']);
            localStorage.setItem('refreshToken', response['refreshToken']);
            // window.location.href = '/main.html'
            window.location.href = '/main'
        },
        // error: function (response) {
        //     alert(response.message);
        //     localStorage.clear();
        //     window.location.href = '/index.html'
        // }
        error: function (request, status, error) {
            console.log("error");
            localStorage.clear();
            alert("code : " + request.status + "\n" + "message : " + request.message + "\n" + "error : " + error);
        }
    })
}

function login_check(){
    $.ajax({
        type: "POST",
        url: `${domainURL}/admin/checkLogin`,
        data: {},
        success: function (response) {
            console.log(response)
        },
        error: function (request, status, error) {
            console.log("error");
            localStorage.clear();
            alert("code : " + request.status + "\n" + "message : " + request.message + "\n" + "error : " + error);
            window.location.href = '/index'
        }
    })
}

// async function getTokenAndRefresh() {
//     const accessToken = await localStorage.getItem('accessToken');
//     const refreshToken = await localStorage.getItem('refreshToken');
//     console.log("되고 있냐...");
//     console.log("로컬 스토리지 액세스 토큰: ", accessToken, "\n로컬 스토리지 리프레시 토큰: ", refreshToken);
//
//     if (accessToken==null || refreshToken==null){
//         console.log("로그인쭈고");
//         throw new Error();
//     }
//
//     let data = {"accessToken": accessToken, "refreshToken": refreshToken}
//     $.ajax({
//         type: "POST",
//         url: `${domainURL}/auth/refreshTokenWeb`,
//         data: JSON.stringify(data),
//         contentType: "application/json",
//         success: async function (response) {
//             if (response) {
//                 alert("완료");
//             } else {
//                 alert("전송된 값 없음");
//             }
//             console.log(response)
//             await localStorage.setItem('accessToken', response['accessToken']);
//             await localStorage.setItem('refreshToken', response['refreshToken']);
//             window.location.href = '/main.html'
//         },
//         // error: function (response) {
//         //     alert(response.message);
//         //     localStorage.clear();
//         //     window.location.href = '/index.html'
//         // }
//         error: function (request, status, error) {
//             console.log("error");
//             localStorage.clear();
//             alert("code : " + request.status + "\n" + "message : " + request.message + "\n" + "error : " + error);
//         }
//     })
// }

