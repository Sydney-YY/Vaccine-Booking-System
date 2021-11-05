function checkCovidCase() {
    console.log("frontend external api")
    let lga = document.getElementById('locationId').value
    console.log(lga)
    var data = {
        resource_id: '21304414-1ff1-4243-a5d2-f52778048b29', // the resource id
        limit: 5, // get 5 results
        q: lga
    };
    $.ajax({
        url: 'https://data.nsw.gov.au/data/api/3/action/datastore_search',
        data: data,
        dataType: 'json',
        success: function (data) {
            layer.msg('Total cases: ' + data.result.total)
            console.log(data)
        }
    });
}

function getData(){
    console.log("load backend external api data 1")
    $.ajax({
        url: "/api/getData",
        type: "get",
        dataType: "json",
        success: function(result){
            console.log(result)
            let data = result.data[0];
            display = document.getElementById('covid_data')
            let element =
                '<div>New cases acquired locally: ' + data.LocalCases_24hrs + '</div>'+
                '<div>New cases acquired interstate or overseas: ' + data.OverseasCases_24hrs + '</div>'+
                '<div>Cases in hospital:' + data.concurrentHospitalisations + '</div>' +
                '<div>Cases in ICU:' + data.concurrentHospitalisationsIcu + '</div>'
            display.innerHTML = element
        },
    });
}

function loginWithSession(user_id, user_name, option, option1){
    getData()
    sessionStorage.clear();
    if (user_name != null) {
        console.log("user login");
        console.log(user_id);
        console.log(user_name);
        sessionStorage.setItem('user_name', user_name);
        sessionStorage.setItem('user_id', user_id);

        console.log(option);
        let profileURL = 'href=/vacBook/user/profile'
        let profileURL1 = 'href=/vacBook/user/logout'
        console.log(profileURL)
        let element = "<a class=\"nav-link\" style=\"color: black\" " + profileURL + ">Hi, " + user_name + "</a>";
        let element1 = "<a class=\"nav-link\" style=\"color: black\" " + profileURL1 + ">Logout" + "</a>";
        option.innerHTML = element
        option1.innerHTML = element1
    }
}