const containerDiv = document.createElement('div');
document.body.appendChild(containerDiv);
let table = document.createElement('table');
containerDiv.appendChild(table);
let tableRow = document.createElement('tr');
let tableHeaderQuestionName = document.createElement('th');
let tableHeaderCreated = document.createElement('th');
let tableHeaderAnswerCount = document.createElement('th');
tableRow.appendChild(tableHeaderQuestionName);
tableRow.appendChild(tableHeaderCreated);
tableRow.appendChild(tableHeaderAnswerCount);
table.appendChild(tableRow);
let newButtonQuestionName = document.createElement('button');
let newButtonCreated = document.createElement('button');
let newButtonAnswerCount = document.createElement('button');
tableHeaderQuestionName.appendChild(newButtonQuestionName);
tableHeaderCreated.appendChild(newButtonCreated);
tableHeaderAnswerCount.appendChild(newButtonAnswerCount);
newButtonQuestionName.innerHTML = 'Question title';
newButtonCreated.innerHTML = 'Question date';
newButtonAnswerCount.innerHTML = 'Number of answers';


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
    for (const question of questions) {
        const row = document.createElement('tr');

        for (const value of Object.values(question)) {
            const col = document.createElement('td');
            col.textContent = value;
            row.appendChild(col);
        }
        table.appendChild(row);
    }
}


fetchQuestions('http://localhost:8080/questions/all').then(r => createTableData(r));
