const token = $("meta[name='_csrf']").attr("content");
const header = $("meta[name='_csrf_header']").attr("content");
tableContainer = document.querySelector('.table-container');
table = document.createElement('table');
thead = document.createElement('thead');
tbody = document.createElement('tbody');
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

getOrders()
function getOrders(){
    fetch('/getOrders')
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
                                <td class="status">${order.status}</td>
                                
                                
`
                const status = row.querySelector('.status')
                if (`${order.status}` === 'Отказано' && `${order.comment}` !== null){
                    status.innerHTML += `<br>Комментарий:<br>${order.comment}`
                }

                table.appendChild(tbody);
                tableContainer.appendChild(table);
                })
    })
        .catch(error => console.error('Ошибка получения данных:', error));

}