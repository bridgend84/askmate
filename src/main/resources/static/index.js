const containerDiv = document.createElement('div');
document.body.appendChild(containerDiv);
containerDiv.className = 'table-container';
const table = document.createElement('table');
table.className = 'page-table';
const tableRow = document.createElement('tr');
tableRow.className = 'page-table-header-row';
const tableHeaderQuestionName = document.createElement('th');
const tableHeaderCreated = document.createElement('th');
const tableHeaderAnswerCount = document.createElement('th');
const newButtonQuestionName = document.createElement('button');
const newButtonCreated = document.createElement('button');
const newButtonAnswerCount = document.createElement('button');
const newQuestionFormDiv = document.createElement("div");
newQuestionFormDiv.className = 'page-form-container';

async function fetchQuestions(url) {
    try {
        const response = await fetch(url);
        const data = await response.json();
        return data;
    } catch (error) {
        console.error(error);
    }
}

function createTableData(questions) {
    table.innerHTML = "";

    containerDiv.appendChild(table);
    tableRow.appendChild(tableHeaderQuestionName);
    tableRow.appendChild(tableHeaderCreated);
    tableRow.appendChild(tableHeaderAnswerCount);
    table.appendChild(tableRow);
    tableHeaderQuestionName.appendChild(newButtonQuestionName);
    tableHeaderCreated.appendChild(newButtonCreated);
    tableHeaderAnswerCount.appendChild(newButtonAnswerCount);
    newButtonQuestionName.innerHTML = 'Question title';
    newButtonCreated.innerHTML = 'Question date';
    newButtonAnswerCount.innerHTML = 'Number of answers';

    for (const question of questions) {
        const row = document.createElement('tr');
        for (const value of Object.values(question)) {
            const col = document.createElement('td');
            col.className = 'page-table-cell';
            col.textContent = value;
            row.appendChild(col);
        }
        table.appendChild(row);
    }
}

newButtonQuestionName.addEventListener("click", (e) => {
    handleClick(e.target)
});
newButtonCreated.addEventListener("click", (e) => {
    handleClick(e.target)
});
newButtonAnswerCount.addEventListener("click", (e) => {
    handleClick(e.target)
});

const handleClick = (target) => {
    let buttonClass;
    if (target.innerHTML === "Question title") {
        buttonClass = newButtonQuestionName.classList;
        if (buttonClass.contains("asc")) {
            buttonClass.remove("asc");
            buttonClass.add("disc");
            fetchQuestions('http://localhost:8080/questions/sorted/name/DESC')
                .then(res => createTableData(res));
        } else {
            buttonClass.remove("disc");
            buttonClass.add("asc");
            fetchQuestions('http://localhost:8080/questions/sorted/name/ASC')
                .then(res => createTableData(res));
        }
    } else if (target.innerHTML === "Question date") {
        buttonClass = newButtonCreated.classList;
        if (buttonClass.contains("asc")) {
            buttonClass.remove("asc");
            buttonClass.add("disc");
            fetchQuestions('http://localhost:8080/questions/sorted/date/DESC')
                .then(res => createTableData(res));
        } else {
            buttonClass.remove("disc");
            buttonClass.add("asc");
            fetchQuestions('http://localhost:8080/questions/sorted/date/ASC')
                .then(res => createTableData(res));
        }
    } else if (target.innerHTML === "Number of answers") {
        buttonClass = newButtonAnswerCount.classList;
        if (buttonClass.contains("asc")) {
            buttonClass.remove("asc");
            buttonClass.add("disc");
            fetchQuestions('http://localhost:8080/questions/sorted/answers-count/DESC')
                .then(res => createTableData(res));
        } else {
            buttonClass.remove("disc");
            buttonClass.add("asc");
            fetchQuestions('http://localhost:8080/questions/sorted/answers-count/ASC')
                .then(res => createTableData(res));
        }
    }
}

const createFrom = () => {
    document.body.appendChild(newQuestionFormDiv);
    const newQuestion =
        "<button id='newQuestionToggle'>New question</button>" +
        "<form id=\"submit\">" +
        "<label for=\"question\">Question:</label>" +
        "<input type=\"text\" id=\"question\" placeholder=\"Question title\">" +
        "<label for=\"description\">Description:</label>" +
        "<input type=\"text\" id=\"description\" placeholder=\"Type in your question\">" +
        "<input id=\"submit-button\" type=\"submit\" value=\"Submit\">"
        "</form>";
    newQuestionFormDiv.innerHTML = newQuestion;
    const submitBtn = document.getElementById("submit");
    const newQuestionBtn = document.getElementById("newQuestionToggle");
    newQuestionBtn.classList.add("hidden");
    document.getElementById("submit").style.display = "none";

    newQuestionBtn.addEventListener("click", () => {
        if (newQuestionBtn.classList.contains("shown")) {
            newQuestionBtn.classList.remove("shown");
            newQuestionBtn.classList.add("hidden");
            document.getElementById("submit").style.display = "none";
        } else {
            newQuestionBtn.classList.remove("hidden");
            newQuestionBtn.classList.add("shown");
            document.getElementById("submit").style.display = "flex";
        }
    })

    submitBtn.addEventListener("submit", () => {
        let questionTitle = document.getElementById("question").value;
        let questionDescription = document.getElementById("description").value;
        postFetch("http://localhost:8080/questions/", questionTitle, questionDescription)
            .then(fetchQuestions())

    });
}

const postFetch = async (url, name, description) => {
    console.log(name);
    console.log(description);
    const rawResponse = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({name, description})
    });
    const content = await rawResponse.json();
    console.log(content);
};

fetchQuestions('http://localhost:8080/questions/all').then(r => createTableData(r));
createFrom();
