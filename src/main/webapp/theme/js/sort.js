let order = [];
let counter = 1;
let result = document.getElementById('result');
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
        th.setAttribute("disabled","true");
        th.setAttribute("name","processButton");
        th.innerText = order.indexOf(this.value) +1 +" "+ this.value;
        result.appendChild(th);
        sessionStorage.setItem("orderSess",order.toString());
    }

    // <------------------------------------For demonstration



});