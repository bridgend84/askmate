const url = window.location.href;

let parts = url.split('/');
let id = parts.pop();

//let id = last.substring(1);

async function fetchQuestion(url) {
    try {
        const response = await fetch(url);
        const data = await response.json();
        return data;
    } catch (error) {
        console.error(error);
    }

}

const valami = document.getElementById("valami");

fetchQuestion(`http://localhost:8080/questions/${id}`).then(question => valami.innerHTML = question.username + " " + question.description + " " + question.name);