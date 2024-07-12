const token = $("meta[name='_csrf']").attr("content");
const header = $("meta[name='_csrf_header']").attr("content");
tableContainer = document.querySelector('.table-container');
table = document.createElement('table');
thead = document.createElement('thead');
tbody = document.createElement('tbody');
select_quarter = document.querySelector('.quarter')
select_status = document.querySelector('.status')

const theadRow = thead.insertRow();
theadRow.innerHTML =
    '            <th>Отдел</th>\n'+
    '            <th>Короткое название</th>\n'+
    '            <th>Маркировка</th>\n'+
    '            <th>Параметры</th>'+
    '            <th>Обоснование</th>'+
    '            <th>Количество</th>\n'+
    '            <th>Цена (руб.)</th>'+
    '            <th>Сумма (руб.)</th>'+
    '            <th>Месяц</th>'+
    '            <th>Квартал</th>'+
    '            <th>Статус</th>'
;
table.appendChild(thead);

getAllOrders()
select_status.addEventListener('change', () =>{
    getOrdersByStatus(select_status.value)
})

select_quarter.addEventListener('change', () =>{
    getOrdersByQuarter(select_quarter.value)
})

function getAllOrders(){
    fetch('/getAllOrders')
        .then(response => response.json())
        .then(data => {
            // Заполнение tbody
            data.forEach(order => {
                const row = tbody.insertRow();
                row.setAttribute('data-cart', JSON.stringify(order)); // Добавляем объект cart как атрибут строки
                row.innerHTML =`
                                <td>${order.user.post}</td>
                                <td>${order.product.productNameShort}</td>
                                <td>${order.product.productBrand}</td>
                                <td>${order.product.productSize}</td>
                                <td>${order.reason}</td>
                                <td>${order.productCount}</td>
                                <td>${order.price}</td>
                                <td>${order.sum}</td>
                                <td>${order.month}</td>
                                <td>${order.quarter}</td>
                                <td><select class="status" name="status">
                                    <option value="${order.status}">${order.status}</option> 
                                    <option value="Отправлено">Отправлено</option>
                                    <option value="Принято">Принято</option>
                                    <option value="Отказано">Отказано</option>
                                    <option value="Выполнено">Выполнено</option></select></td>`;
                const selectElement = row.querySelector('.status');

                selectElement.addEventListener('change', () =>{
                    if (selectElement.value === 'Отказано'){
                        console.log("Почему?")
                    }
                    const requestData = {
                        id: `${order.id}`,
                        status : selectElement.value
                    }
                    fetch('/editOrderStatus', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                            'X-CSRF-TOKEN': token
                        },
                        body: JSON.stringify(requestData)

                    })
                        .then(response => {
                            console.log(response)
                            // window.location.href = '/api/v1/getWorkoutPage/' + userId
                        })
                        .catch(error => {
                            console.error('Ошибка при отправке формы:', error);
                        });


                })

                table.appendChild(tbody);
                tableContainer.appendChild(table);
            })
        })
        .catch(error => console.error('Ошибка получения данных:', error));

}

function getOrdersByStatus(status){
    fetch(`/getOrdersByStatus?query=${status}`)
        .then(response => response.json())
        .then(data => {
            tbody.innerHTML = '';

            // Заполнение tbody
            data.forEach(order => {
                const row = tbody.insertRow();
                row.setAttribute('data-cart', JSON.stringify(order)); // Добавляем объект cart как атрибут строки
                row.innerHTML =`
                                <td>${order.user.post}</td>
                                <td>${order.product.productNameShort}</td>
                                <td>${order.product.productBrand}</td>
                                <td>${order.product.productSize}</td>
                                <td>${order.reason}</td>
                                <td>${order.productCount}</td>
                                <td>${order.price}</td>
                                <td>${order.sum}</td>
                                <td>${order.month}</td>
                                <td>${order.quarter}</td>
                                <td><select class="status" name="status">
                                    <option value="${order.status}">${order.status}</option> 
                                    <option value="Отправлено">Отправлено</option>
                                    <option value="Принято">Принято</option>
                                    <option value="Отказано">Отказано</option>
                                    <option value="Выполнено">Выполнено</option></select></td>`;
                table.appendChild(tbody);
                tableContainer.appendChild(table);
            })
        })
        .catch(error => console.error('Ошибка получения данных:', error));

}

function getOrdersByQuarter(quarter){
    fetch(`/getOrdersByQuarter?query=${quarter}`)
        .then(response => response.json())
        .then(data => {
            tbody.innerHTML = '';

            // Заполнение tbody
            data.forEach(order => {
                const row = tbody.insertRow();
                row.setAttribute('data-cart', JSON.stringify(order)); // Добавляем объект cart как атрибут строки
                row.innerHTML =`
                                <td>${order.user.post}</td>
                                <td>${order.product.productNameShort}</td>
                                <td>${order.product.productBrand}</td>
                                <td>${order.product.productSize}</td>
                                <td>${order.reason}</td>
                                <td>${order.productCount}</td>
                                <td>${order.price}</td>
                                <td>${order.sum}</td>
                                <td>${order.month}</td>
                                <td>${order.quarter}</td>
                                <td><select class="status" name="status">
                                    <option value="${order.status}">${order.status}</option> 
                                    <option value="Отправлено">Отправлено</option>
                                    <option value="Принято">Принято</option>
                                    <option value="Отказано">Отказано</option>
                                    <option value="Выполнено">Выполнено</option></select></td>`;
                table.appendChild(tbody);
                tableContainer.appendChild(table);
            })
        })
        .catch(error => console.error('Ошибка получения данных:', error));

}