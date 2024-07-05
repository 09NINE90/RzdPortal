const token = $("meta[name='_csrf']").attr("content");
const header = $("meta[name='_csrf_header']").attr("content");
tableContainer = document.querySelector('.table-container');
table = document.createElement('table');
thead = document.createElement('thead');
tbody = document.createElement('tbody');
const searchInput = document.querySelector('#search-input');
const searchButton = document.querySelector('#search-button');

const theadRow = thead.insertRow();
theadRow.innerHTML ='<th>Количество</th>\n'+
    '            <th>Короткое название</th>\n'+
    '            <th>Маркировка</th>\n'+
    '            <th>Параметры</th>'+
    '            <th>Удалить</th>'

;
table.appendChild(thead);

getProductsCart()
function getProductsCart(){
    fetch('/productsCart')
        .then(response => response.json())
        .then(data => {

            // Заполнение tbody
            data.forEach(cart => {
                const row = tbody.insertRow();
                row.innerHTML =`<td>${cart.productCount}</td>
                               <td>${cart.product.productNameShort}</td>
                                <td>${cart.product.productBrand}</td>
                                <td>${cart.product.productSize}</td>
                                <td><button class="remove-from-cart-btn">Удалить</td>`
                ;
                const deleteFromCartBtn = row.querySelector('.remove-from-cart-btn');
                // deleteFromCartBtn.addEventListener('click', () => {
                //     // Добавление продукта в корзину
                //     const requestData = {
                //         productCount: 1,
                //         productId: `${product.id}`,
                //     }
                //     fetch('/addProduct', {
                //         method: 'POST',
                //         headers: {
                //             'Content-Type': 'application/json',
                //             'X-CSRF-TOKEN': token
                //         },
                //         body: JSON.stringify(requestData)
                //     })
                //         .then(response => {
                //             console.log(response)
                //             // window.location.href = '/api/v1/getWorkoutPage/' + userId
                //         })
                //         .catch(error => {
                //             console.error('Ошибка при отправке формы:', error);
                //         });
                //     // Добавление продукта в корзину
                //     addToCart(product);
                // });
            });

            table.appendChild(tbody);
            tableContainer.appendChild(table);
        })
        .catch(error => console.error('Ошибка получения данных:', error));
}
