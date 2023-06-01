export const subjectCard = (element, margin, score=-1.0) => `
<div class="card" style="width: 27%; margin-top: ${margin ? "20px" : "0"};">
    <div class="card-body">
        <h5 class="card-title">${element}</h5>
        ${score > -1.0 ? `<p class="card-text">Score: <span class="badge rounded-pill text-bg-primary" style="margin-right: 10px; padding: 8px 12px;">${score}</span></p>` : ``}
    </div>
</div>
`;

export const subjectFormInput = (element, hint) => `
<div class="form-group">
    <div class='padding'></div>
    <label for="${nameToId(element)}" class="form-label">${element}</label>
    <input type="number" class="form-control" name="${element}" value="" id="${nameToId(element)}" placeholder="${hint}">
    <div class="invalid-feedback">You cannot get marks less than 0 or greater than 100.</div>
</div>
`;

export const recommendationParams = (values, index) => `
<div class="form-check">
    <div class="padding"></div>
    <input class="form-check-input" type="checkbox" value="${index}" id="elective${index}" checked>
    <label class="form-check-label" for="elective${index}">
        Elective ${index} <span style="margin-right: 20px"></span> 
        ${(() => {
            let res = ``;
            for(let i=0; i<values.length; i++)
                res += `<span class="badge rounded-pill text-bg-primary" style="margin-right: 10px; padding: 8px 12px;">${values[i]}</span>`
            return res;
        })()}
    </label>
</div>
`;

export const prevNextBtn = (current) => `
    <div class="padding"></div>
    <div class="btn-group" style="justify-self: end" role="group" aria-label="Steps Buttons">
        ${current > 0 ? `<button type="button" class="btn btn-outline-primary">Prev</button>` : ``}
        <button type="button" class="btn btn-primary">${current == 2 ? 'Recommend Me' : "Next"}</button>
    </div>
    `;

export const nameToId = (value) => {
    value = value.replace("&", "and");
    return value.toLowerCase().split(' ').join('-');
}