//async function connect() {
//let id = document.getElementById("floatingUsername").value;
//let pass = document.getElementById("floatingPassword").value;
//
//  let data = {	userId: id,
//                password: pass }
//  fetch('auth/login', {
//    method: 'POST',
//    headers: {
//      'Accept': 'application/json, text/plain, */*',
//      'Content-Type': 'application/json'
//    },
//    body: JSON.stringify(data)
//  }).then(res => res.json())
//    .then(res => {
//        if(res === true)  {
//            location.replace("../")
//            }
//        else alert("Wrong Password")
//    });
//}
//
//async function generatePdfAndCsv() {
//  let data = {	filePath : "C:/Users/Balcem Hassine/Desktop/report" }
//  fetch('/reporting/generateCsv', {
//    method: 'POST',
//    headers: {
//      'Accept': 'application/json, text/plain, */*',
//      'Content-Type': 'application/json'
//    },
//    body: JSON.stringify(data)
//  }).then(res => res.json())
//        .then(res => {
//            if(res === true)  {
//                location.replace("../")
//                alert("File generated at: "+ data.filePath)
//                }
//        })
//}
//
//getAllProducts();
//
//
//async function getAllProducts() {
//
//  fetch('/inventory/', {
//    method: 'GET',
//    headers: {
//      'Accept': 'application/json, text/plain, */*',
//      'Content-Type': 'application/json'
//    }
//  }).then(res => res.json())
//        .then(res => {
//            renderProductTable(res)
//        })
//}
//
//
//async function renderProductTable(productList) {
//let table = ` <table class="table table-striped">
//                         <table class="table">
//                           <thead>
//                           <tr>
//                             <th scope="col">ID</th>
//                             <th scope="col">Name</th>
//                             <th scope="col">Qte</th>
//                             <th scope="col">Price</th>
//                             <th scope="col">Category</th>
//                           </tr>
//                           </thead>
//                           <tbody>`;
//for (let i = 0; i < productList.length; i++) {
//      table += ` <tr>
//          <td scope="row">${ productList[i].productId}</td>
//          <td scope="row">${ productList[i].productName}</td>
//          <td scope="row">${ productList[i].quantityInStock}</td>
//          <td scope="row">${ productList[i].price}</td>
//          <td scope="row">${ productList[i].category}</td>
//          </tr>
//      `
//    }
//
//    table += `           </tbody>
//                          </table>
//                        </table>`
//  document.getElementById("productTableContainer").innerHTML = table
//
//}
//getAllTransactions()

async function getAllEnlisted() {

  fetch('/enlisted/', {
    method: 'GET',
    headers: {
      'Accept': 'application/json, text/plain, */*',
      'Content-Type': 'application/json'
    }
  }).then(res => res.json())
        .then(res => {
        console.warn("Enlisted list", res);
            renderEnlistedTable(res);
        })
}

async function renderEnlistedTable(enlistedList) {
let table = ` <table class="table table-striped">
                         <table class="table">
                           <thead>
                           <tr>
                             <th scope="col">Name</th>
                             <th scope="col">Title</th>
                             <th scope="col">Health</th>
                             <th scope="col">Deployment status</th>
                           </tr>
                           </thead>
                           <tbody>`;
for (let i = 0; i < enlistedList.length; i++) {
      table += ` <tr class="cursor" onClick="onClickEnlisted('${enlistedList[i].id}')">
          <td scope="row">${ enlistedList[i].personnelName}</td>
          <td scope="row">${ enlistedList[i].personnelRole}</td>
          <td scope="row">${ enlistedList[i].health}</td>
          <td scope="row">${ enlistedList[i].deployed ? "Deployed" : "Standby"}</td>
          </tr>
      `
    }

    table += `           </tbody>
                          </table>
                        </table>`
  document.getElementById("enlistedTableContainer").innerHTML = table

}


function onClickEnlisted(id) {
window.location.replace("/enlisted-details/" + id)
}


