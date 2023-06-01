import { subjectCard } from "./consts.js";

var current = 0;

const showTab = (current) => {
    const tabs = document.getElementsByClassName('tab');
    tabs[current].style.display = "grid";
};

const next = () => {
    if (!validateStep(current)) return false;
    const tabs = document.getElementsByClassName('tab');
    tabs[current].style.display = "none";
    current += 1;
    showTab(current);
}

const prev = () => {
    const tabs = document.getElementsByClassName('tab');
    tabs[current].style.display = "none";
    current -= 1;
    showTab(current);
}

const submit = (func, endpoint) => {
    if(!validateStep(current)) return false;
    const inputs = Array.from(document.getElementsByTagName('input'));
    let thresh = inputs.filter((value) => value.name == "thresh")[0].value;
    const threshCheck = inputs.filter((value) => value.type == "number" && value.value > thresh);
    let marks = inputs.filter((value) => value.type == "number" && value.name != 'k' && value.name != 'thresh');
    marks = marks.map((e) => e.value);
    if(threshCheck.length == 0){
        console.log(marks);
        thresh = marks.reduce((a, b) => Number(a) + Number(b));
        thresh /= marks.length;
    }
    const body = {
        "subjects": [],
        "k": 0,
        "type": 2,
        "electives": [],
    }
    inputs.forEach((element) => {
        if(element.type == "number"){
            if(element.value >= thresh && element.name != 'k' && element.name != 'thresh'){
                body["subjects"].push(element.name);
            }else if(element.name == 'k'){
                body["k"] = Number(element.value);
            } 
        }else if((element.type == "radio" || element.type == "checkbox") && element.checked){
            if(element.type == "radio") body["type"] = Number(element.id);
            else body["electives"].push(Number(element.value));
        }
    })
    func(endpoint, body).then(
        (result) => {
            const body = document.getElementsByClassName("body")[0];
            body.innerHTML = `
            <h2>Your Recommendations Are</h2>
            <p> Less the score subject have, most it recommended by recommendation system.</p>
            <div class="padding"></div>
            <div class="container justify-content-center align-items-center">
                ${(() => {
                    let res = ``
                    Array.from(result).forEach((element, index) => {
                        let key = "elective_" + (index+1);
                        res += `
                        <h4>Elective ${index+1}</h4>
                        <div class="padding"></div>
                        <div class="row p-flex flex-wrap justify-content-between" id="card-holder">
                        ${(() => {
                            let res = ``;
                            Array.from(element[key]).forEach((element) => {
                                res += subjectCard(element['name'], false, element['score']);
                            });
                            return res;
                        })()}
                        </div>
                        <div class="padding-2"></div>`;
                    });
                    return res;
                })()}
            </div>
            `;

        }
    );
}

const validateStep = (current) => {
    const currentTab = document.getElementsByClassName("tab")[current];
    const currentInputs = currentTab.getElementsByTagName("input");
    for(let i=0; i<currentInputs.length; i++){
        let e = currentInputs[i];
        if(e.type == "number"){
            if(e.value.length == 0 || isNaN(e.value) || e.value < 0 || e.value > 100){
                e.classList.add('is-invalid');
                if(e.value.length == 0){
                    e.parentElement.children[2].innerText = "Required Field";
                }
                return false;
            }else{
                e.classList.remove('is-invalid');
            }
        }
    }
    return true;
}


export { showTab, current, next, prev, submit};