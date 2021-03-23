let order = [];
let counter = 1;
let result = document.getElementById('result');
let form = document.getElementById('form');
let hiddenInput = document.getElementById('a');

// form.addEventListener("submit",ev => {
//     ev.preventDefault();
//     hiddenInput.setAttribute("value",order.toString());
//     $(this).unbind('submit').submit()
// })

$('.user').submit( function(ev) {
    ev.preventDefault();
    //later you decide you want to submit
    hiddenInput.setAttribute("value",order.toString());
    $(this).unbind('submit').submit();
});

$("[type=checkbox]").on('change', function() { // always use change event
    let idx = order.indexOf(this.value);
    if (idx !== -1) {// if already in array
        console.log(idx)
        let array = result.querySelectorAll("button");
        order.splice(idx,1);
        console.log(order);
        result.innerHTML = "";
        order.forEach(el=>{
            th = document.createElement("button");
            th.classList.add("btn");
            th.classList.add("btn-primary");
            th.classList.add("shadow-sm");
            th.classList.add("btn-sm");
            th.classList.add("ml-2");
            let str = el.split(" ");
            let str2 = str.join("-");
            let str3 = str2 + "1";
            th.setAttribute("id",str3);
            th.setAttribute("disabled","true");
            th.setAttribute("name", "processButton");
            th.innerText = order.indexOf(el) + 1 +" "+ el;
            result.appendChild(th);
        })
        sessionStorage.setItem("orderSess",order.toString());


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
        th.setAttribute("id",str3);
        th.setAttribute("disabled","true");
        th.setAttribute("name","processButton");
        th.innerText = order.indexOf(this.value) +1 +" "+ this.value;
        result.appendChild(th);
        sessionStorage.setItem("orderSess",order.toString());
    }

    // <------------------------------------For demonstration

});

let closeButton = document.querySelector(".closeButton");
let uwagiBtn = document.querySelector(".uwagiBtn");
let popDivToHide = document.querySelector(".popDivToHide");
let goToNextProcessButton = document.querySelector('.goToNextProcessButton');
let select = document.querySelector('.nextStepSelect');
closeButton.addEventListener('click',e=>{
    popDivToHide.classList.add('d-none');
})

uwagiBtn.addEventListener('click',e=>{
    popDivToHide.classList.add('d-none');
})

goToNextProcessButton.addEventListener('click',e=>{
    e.preventDefault();
    if(select.value === 'Zgłoś uwagi'){
       popDivToHide.classList.remove('d-none');
    }
})