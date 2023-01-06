const url = window.location.href;
let parts = url.split('/');
let id = parts.pop();
const questionDiv = document.getElementById("question_div");
const newQuestionFormDiv = document.createElement("div");
newQuestionFormDiv.className = 'page-form-container';

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

fetchQuestion(`http://localhost:8080/questions/${id}`)
    .then(question => {
        questionDiv.innerHTML =
            "Question by: " + question.username + " </br>"
            + "Question about: " + question.name + "</br>"
            + "Description: " + question.description + "</br>";
    })
    .then(
    fetchAnswers(`http://localhost:8080/questions/answers/${id}`)
        .then(answers => {
        answers.map(a => {
            console.log(a)
            const createDiv = document.createElement("div");
            createDiv.innerHTML = `<div id="answer-description">
        <h3>Answer</h3>
        <p>${a.description} </p>
        <p>${a.created} </p>
        </div>`
            questionDiv.appendChild(createDiv);
        })
    })
)

const createFrom = () => {
    document.body.appendChild(newQuestionFormDiv);
    const newAnswerForm =
        "<button id='newAnswerToggle' class='a'>New answer</button>" +
        "<form id=\"submit\">" +
        "<label for=\"answer\">Answer:</label>" +
        "<input type=\"text\" id=\"answer\" placeholder=\"Answer\">" +
        "<label for=\"username\">Username:</label>" +
        "<input type=\"text\" id=\"username\" placeholder=\"Username1\" required>" +
        "<input id=\"submit-button\" type=\"submit\" value=\"Submit\">" +
        "</form>";
    newQuestionFormDiv.innerHTML = newAnswerForm;
    const submitBtn = document.getElementById("submit");
    const newAnswerBtn = document.getElementById("newAnswerToggle");
    newAnswerBtn.classList.add("hidden");
    document.getElementById("submit").style.display = "none";

    newAnswerBtn.addEventListener("click", () => {
        if (newAnswerBtn.classList.contains("shown")) {
            newAnswerBtn.classList.remove("shown");
            newAnswerBtn.classList.add("hidden");
            document.getElementById("submit").style.display = "none";
        } else {
            newAnswerBtn.classList.remove("hidden");
            newAnswerBtn.classList.add("shown");
            document.getElementById("submit").style.display = "flex";
        }
    })

    submitBtn.addEventListener("submit", async () => {
        let answer = document.getElementById("answer").value;

        postFetch(`http://localhost:8080/questions/newanswer/${id}`, answer)
        //  .then(await fetchAnswers(`http://localhost:8080/questions/answers/${id}`))

    })
}

const postFetch = async (url, description) => {
    const rawResponse = await fetch(url, {
        method: 'POST', headers: {
            'Content-Type': 'application/json'
        }, body: JSON.stringify({description})
    });
    const content = await rawResponse.json();
    console.log(content);
};

createFrom();
