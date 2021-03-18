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
            th.innerText = order.indexOf(el) + 1 +" "+ el;
            result.appendChild(th);
        })
        // array.forEach(el=>{
        //     let curr = Array.from(array).indexOf(el);
        //     console.log(curr)
        //     let x;
        //     if(curr === 0){
        //         x = 1 + " " + value;
        //     }else {
        //         x = curr + " " + value;
        //     }
        //     if(el.innerText === x){
        //        el.remove();// make sure we remove it
        //         console.log("done")
        //     }
        //     let arrayNew = result.querySelectorAll("button");
        //     if(curr === 0){
        //         console.log("first")
        //         arrayNew.forEach(el => {
        //             let str = el.innerText;
        //             let nameOfProcess = str.slice(2);
        //             el.innerText = Array.from(array).indexOf(el) + " " + nameOfProcess;
        //         })
        //     }else if(curr === array.length-1){
        //
        //     }else {
        //
        //         let newArray = Array.from(arrayNew).slice(curr);
        //         newArray.forEach(el => {
        //             let str = el.innerText;
        //             let nameOfProcess = str.slice(2);
        //             el.innerText = Array.from(array).indexOf(el) + " " + nameOfProcess;
        //         })
        //     }
        //
        // })
        // counter--;

    }

    if (this.checked) {    // if checked
        order.push(this.value);// add to end of array
        th = document.createElement("button");
        th.classList.add("btn");
        th.classList.add("btn-primary");
        th.classList.add("shadow-sm");
        th.classList.add("btn-sm");
        th.classList.add("ml-2");
        th.innerText = order.indexOf(this.value) +1 +" "+ this.value;
        result.appendChild(th);
    }

    // <------------------------------------For demonstration



});