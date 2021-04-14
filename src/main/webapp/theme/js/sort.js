window.addEventListener('DOMContentLoaded', (event) => {
    let order = [];
    let counter = 1;
    let result = document.getElementById('result');
    let hiddenInput = document.getElementById('a');

// form.addEventListener("submit",ev => {
//     ev.preventDefault();
//     hiddenInput.setAttribute("value",order.toString());
//     $(this).unbind('submit').submit()
// })

    $('.user').submit(function (ev) {
        ev.preventDefault();
        //later you decide you want to submit
        hiddenInput.setAttribute("value", order.toString());
        $(this).unbind('submit').submit();
    });

    $("[type=checkbox]").on('change', function () { // always use change event
        let idx = order.indexOf(this.value);
        if (idx !== -1) {// if already in array
            let array = result.querySelectorAll("button");
            order.splice(idx, 1);
            result.innerHTML = "";
            order.forEach(el => {
                th = document.createElement("button");
                th.classList.add("btn");
                th.classList.add("btn-primary");
                th.classList.add("shadow-sm");
                th.classList.add("btn-sm");
                th.classList.add("ml-2");
                let str = el.split(" ");
                let str2 = str.join("-");
                let str3 = str2 + "1";
                th.setAttribute("id", str3);
                th.setAttribute("disabled", "true");
                th.setAttribute("name", "processButton");
                th.innerText = order.indexOf(el) + 1 + " " + el;
                result.appendChild(th);
            })
            sessionStorage.setItem("orderSess", order.toString());


        }

        if (this.checked) {    // if checked
            order.push(this.value);// add to end of array
            th = document.createElement("button");
            th.classList.add("btn");
            th.classList.add("btn-primary");
            th.classList.add("shadow-sm");
            th.classList.add("btn-sm");
            th.classList.add("ml-2");
            let str = this.value.split(" ");
            let str2 = str.join("-");
            let str3 = str2 + "1";
            th.setAttribute("id", str3);
            th.setAttribute("disabled", "true");
            th.setAttribute("name", "processButton");
            th.innerText = order.indexOf(this.value) + 1 + " " + this.value;
            result.appendChild(th);
            sessionStorage.setItem("orderSess", order.toString());
        }

        // <------------------------------------For demonstration

    });

    let popDivToHide = document.querySelector(".popDivToHide");
    let goToNextProcessButtons = document.querySelectorAll('.goToNextProcessButton');
    let select = document.querySelector('.nextStepSelect');
    let popForms = document.querySelectorAll('.popForm');
    goToNextProcessButtons.forEach(e => {
        e.addEventListener('click', ev => {
            if (e.parentElement.childNodes[3].value === 'Zgłoś uwagi') {
                ev.preventDefault();

                popDivToHide.classList.remove('d-none');
                let closeButton = document.querySelector(".closeButton");
                closeButton.addEventListener('click', e => {
                    popDivToHide.classList.add('d-none');
                })
            }
        })
    })




    popForms.forEach(el => {
        el.addEventListener('submit', e => {
            popDivToHide.classList.add('d-none');
            el.submit();
        })
    })

    let notes = document.querySelectorAll('.notes');

    notes.forEach(el => {
        el.innerText = "BRAK";
    })




// goToNextProcessButton.addEventListener('click', e => {
//     e.preventDefault();
//     if (select.value === 'Zgłoś uwagi') {
//             popDivToHide.classList.remove('d-none');
//     }
// })
    let form = document.querySelector("[name='form']");
    console.log(form);

    form.addEventListener('submit',evt => {
        evt.preventDefault();
        if(formValidation() === true){
            form.submit();
        }
    })

    function formValidation(){
        let material = document.querySelector('#material').value;
        console.log(material);
        let product = document.querySelector('#productName').value;
        let client = document.querySelector('#clientName').value;
        let count = document.querySelector('#count').value;
        let processName = document.querySelectorAll('input:checked');
        console.log(processName);
        if(processName.length === 0){
            alert("Ustal kolejność produkcji.");
            window.stop();
        }
        if(material == null || material === '' || product == null || product === '' || client == null || client === '' || count === '0' || count === null || count === ''){
            alert("Wypełnij poprawnie wszystie pola formularza !");
            window.stop();
            return false;
        }
        return true;
    }
});