const param = window.location.href;

let parts = url.split('/');
let last = parts.pop() || parts.pop();

let id = last.substring(1);

async function fetchQuestion(url) {
    try {
        const response = await fetch(url);
        const data = await response.json();
        return data;
    } catch (error) {
        console.error(error);
    }

}

fetchQuestion(`http://localhost:8080/question/${id}`)