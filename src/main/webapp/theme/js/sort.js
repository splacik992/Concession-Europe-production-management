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

    let newURL = window.location.protocol + "//" + window.location.host + "/" + window.location.pathname;
    console.log(newURL);
    if(newURL === "http://localhost:8081//") {
        let form = document.querySelector("[name='form']");
        console.log(form);

        form.addEventListener('submit', evt => {
            evt.preventDefault();
            if (formValidation() === true) {
                form.submit();
            }

        })


        let searchBar = document.querySelector("#searchBar");
        searchBar.addEventListener('input', evt => {
            let selectedValue = document.querySelector("#searchSelect");
            let input = searchBar.value.toUpperCase();
            let dataTableRows = document.querySelectorAll(".rowOfData");
            dataTableRows.forEach(el => {
                let number = el.children[0].innerText;
                let material = el.children[1].innerText;
                let productName = el.children[2].innerText;
                let client = el.children[3].innerText;
                let principal = el.children[5].innerText;
                let date = el.children[6].innerText;
                let status = el.children[7].innerText;
                switch (selectedValue.value) {
                    case 'number':
                        if (number.toUpperCase().indexOf(input) > -1) {
                            el.style.display = "";
                        } else {
                            el.style.display = "none";
                        }
                        break;
                    case 'material':
                        if (material.toUpperCase().indexOf(input) > -1) {
                            el.style.display = "";
                        } else {
                            el.style.display = "none";
                        }
                        break;
                    case 'name':
                        if (productName.toUpperCase().indexOf(input) > -1) {
                            el.style.display = "";
                        } else {
                            el.style.display = "none";
                        }
                        break;
                    case 'client':
                        if (client.toUpperCase().indexOf(input) > -1) {
                            el.style.display = "";
                        } else {
                            el.style.display = "none";
                        }
                        break;
                    case 'principal':
                        if (principal.toUpperCase().indexOf(input) > -1) {
                            el.style.display = "";
                        } else {
                            el.style.display = "none";
                        }
                        break;
                    case 'date':
                        if (date.toUpperCase().indexOf(input) > -1) {
                            el.style.display = "";
                        } else {
                            el.style.display = "none";
                        }
                        break;
                    case 'status':
                        if (status.toUpperCase().indexOf(input) > -1) {
                            el.style.display = "";
                        } else {
                            el.style.display = "none";
                        }
                        break;
                }

            })
        })
    }

    function formValidation() {
        let material = document.querySelector('#material').value;
        console.log(material);
        let product = document.querySelector('#productName').value;
        let client = document.querySelector('#clientName').value;
        let count = document.querySelector('#count').value;
        let principal = document.querySelector('#principal').value;
        let processName = document.querySelectorAll('input:checked');
        console.log(processName);
        if (processName.length === 0) {
            alert("Ustal kolejność produkcji.");
            window.stop();
            location.reload();
            return false;
        }
        if (material == null || material === '' || product == null || product === '' || client == null || client === '' || count === '0' || count === null || count === '' ||
            principal == null || principal === '') {
            alert("Wypełnij poprawnie wszystie pola formularza !");
            window.stop();
            location.reload();
            return false;
        }
        return true;
    }

    let perDayTable = document.querySelector(".perDayTable");
    let perDayTable2 = document.querySelector(".perDayTable2");

    let addToDayForms = document.querySelectorAll(".formAddToDay");

    addToDayForms.forEach(el=>{
        el.addEventListener('submit',evt => {
            evt.preventDefault();
            if(perDayTable2 == null){
                el.submit();
            }
            if(!(perDayTable.children[0].innerHTML === perDayTable2.children[0].innerHTML)){
                el.submit();
            }else{
                alert("Zamówienie znajuje się na liście!");
                location.reload();
            }
        })

    })




});