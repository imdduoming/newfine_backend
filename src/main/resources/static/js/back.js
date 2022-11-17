let domainURL = 'https://eb.newfine.shop';


function getTokenAndRefresh() {
    const accessToken = localStorage.getItem('accessToken');
    const refreshToken = localStorage.getItem('refreshToken');
    console.log("되고 있냐...");
    console.log("로컬 스토리지 액세스 토큰: ", accessToken, "\n로컬 스토리지 리프레시 토큰: ", refreshToken);

    if (accessToken == null || refreshToken == null) {
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
            console.log("code : " + request.status + "\n" + "message : " + request.message + "\n" + "error : " + error);
            localStorage.clear();
            alert("다시 로그인해주세요.");
        }
    })
}

function login_check() {
    $.ajax({
        headers: {"Authorization": `Bearer ${localStorage.getItem('accessToken')}`},
        type: "POST",
        url: `${domainURL}/admin/checkLogin`,
        data: {},
        success: function (response) {
            console.log(response)
        },
        error: function (request, status, error) {
            console.log("code : " + request.status + "\n" + "message : " + request.message + "\n" + "error : " + error);
            // localStorage.clear();
            if (request.status==401){
                alert("자동 로그인 레쭈고");    // 나중에 지우자
            } else{
                localStorage.clear();
                alert("error!!");
            }
            window.location.href = '/'
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