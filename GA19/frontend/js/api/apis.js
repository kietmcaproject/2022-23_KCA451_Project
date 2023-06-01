const getOptions = {
    method: 'GET',
    redirect: 'follow'
};

const myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

var postOptions = {
    method: 'POST',
    headers: myHeaders,
    body: "",
    redirect: 'follow'
};

const api = {  
    subjectListEndpoint: "https://aniketars.pythonanywhere.com/api/v1/subject-list",
    similarSubjectsEndpoint: "https://aniketars.pythonanywhere.com/api/v1/similar-subject",
    subjectList: async (endpoint) => {
        try{
            const response = await fetch(endpoint, getOptions);
            const data = response.json();
            return data;
        }catch (error) {
            return error;
        }
    },
    similarSubjects: async (endpoint, body) => {
        postOptions["body"] = JSON.stringify(body);
        try{
            const response = await fetch(endpoint, postOptions);
            const data = response.json();
            return data;
        }catch (error) {
            return error;
        }
    }
}

export { api };