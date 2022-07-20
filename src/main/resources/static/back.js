let domainURL= 'https://eb.newfine.tk';
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
