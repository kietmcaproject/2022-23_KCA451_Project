import { api } from './api/apis.js';
import { subjectCard, subjectFormInput, prevNextBtn, recommendationParams } from './consts.js';
import { showTab, current, next, prev, submit } from './multiStepForm.js';

// Initial function to render elements from API in HTML
api.subjectList(api.subjectListEndpoint).then(
    (result) => {
        const container = document.getElementById('card-holder');
        const intermediate = document.getElementById('intermediate');
        const firstSem = document.getElementById('firstSem');
        const eC = document.getElementById('electiveChoice');
        const rP = document.getElementById('recommendationParameters');
        const hint = "If you had not this subject please enter 0.";
        let electives = [];
        result['subjects'].forEach((element, index) => {
            if (intermediate != null && index < 6) {
                intermediate.innerHTML += subjectFormInput(element, hint);
                if (index == 5) {
                    intermediate.innerHTML += prevNextBtn(current);
                    intermediate.querySelector(".btn-primary").addEventListener('click', next);
                }
            }
            if (firstSem != null && index > 5 && index < 11) {
                firstSem.innerHTML += subjectFormInput(element, "Enter Marks");
                if (index == 10) {
                    firstSem.innerHTML += prevNextBtn(current + 1);
                    firstSem.querySelector(".btn-primary").addEventListener('click', next);
                    firstSem.querySelector(".btn-outline-primary").addEventListener('click', prev);
                }
            }

            if (index > 10 && eC != null) {
                electives.push(element);
                if (index % 5 == 0) {
                    eC.innerHTML += recommendationParams(electives, index / 5 - 2);
                    electives = [];
                }
            }

            if (container != null) {
                container.innerHTML += subjectCard(element, true);
            }
        });
        if (rP != null) {
            rP.innerHTML += prevNextBtn(current + 2);
            rP.querySelector(".btn-primary").addEventListener('click', () => submit(api.similarSubjects, api.similarSubjectsEndpoint));
            rP.querySelector(".btn-outline-primary").addEventListener('click', prev);
        }
    });

// Form Controlling
showTab(current);