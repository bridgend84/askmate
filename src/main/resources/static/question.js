const url = window.location.href;
let parts = url.split('/');
let id = parts.pop();

async function fetchQuestion(url) {
    try {
        const response = await fetch(url);
        const data = await response.json();
        return data;
    } catch (error) {
        console.error(error);
    }

}
async function fetchAnswers(url) {
    try {
        const response = await fetch(url);
        const data = await response.json();
        return data;
    } catch (error) {
        console.error(error);
    }
}

const valami = document.getElementById("valami");

fetchQuestion(`http://localhost:8080/questions/${id}`).then(question => {
    valami.innerHTML = question.username + " " + question.description + " " + question.name;
} );

fetchAnswers(`http://localhost:8080/questions/answers/${id}`).then(answers => {
    answers.map(a=>{
        valami.innerHTML += `<div>
<p>${a.description} ${a.created}</p>
</div>`
    })
} );