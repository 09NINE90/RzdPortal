const token = $("meta[name='_csrf']").attr("content");
const header = $("meta[name='_csrf_header']").attr("content");
tableContainer = document.querySelector('.table-container');
table = document.createElement('table');
thead = document.createElement('thead');
tbody = document.createElement('tbody');
const searchInput = document.querySelector('#search-input');
const searchButton = document.querySelector('#search-button');
function addToCart(product) {
    // Ваша логика добавления продукта в корзину здесь
    console.log('Добавлен в корзину:', product.productNameShort);
}
const theadRow = thead.insertRow();
theadRow.innerHTML ='<th>Код товара</th>\n'+
    '            <th>Короткое название</th>\n'+
    '            <th>Маркировка</th>\n'+
    '            <th>Параметры</th>'+
    '            <th>В корзину</th>'

;
table.appendChild(thead);

getProducts()
searchButton.addEventListener('click', function() {
    const query = searchInput.value;
    searchProducts(query)
});

// searchInput.addEventListener('input', function() {
//     const query = searchInput.value;
//     searchProducts(query)
// });

function getProducts(){
    fetch('/products')
        .then(response => response.json())
        .then(data => {

            // Заполнение tbody
            data.forEach(product => {
                const row = tbody.insertRow();
                row.innerHTML =`<td>${product.productId}</td>
                               <td>${product.productNameShort}</td>
                                <td>${product.productBrand}</td>
                                <td>${product.productSize}</td>
                                <td><button class="add-to-cart-btn">Добавить</td>`
                ;
                const addToCartBtn = row.querySelector('.add-to-cart-btn');
                addToCartBtn.addEventListener('click', () => {
                    // Добавление продукта в корзину
                    const requestData = {
                        productCount: 1,
                        month:1,
                        reason:' ',
                        price: 0,
                        sum: 0,
                        quarter: 1,
                        product: product,
                    }
                    fetch('/addProduct', {
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
                    // Добавление продукта в корзину
                    addToCart(product);
                });
            });

            table.appendChild(tbody);
            tableContainer.appendChild(table);
        })
        .catch(error => console.error('Ошибка получения данных:', error));
}

function searchProducts(query){
    if (query.trim() !== '') {
        tableContainer.innerHTML = `<span style="color: red;">Идет поиск...</span>`;
        fetch(`/searchProducts?query=${query}`)
            .then(response => response.json())
            .then(data => {
                // Очистка таблицы перед добавлением новых данных
                tbody.innerHTML = '';

                data.forEach(product => {
                    const row = tbody.insertRow();
                    row.innerHTML =`<td>${product.productId}</td>
                               <td>${product.productNameShort}</td>
                                <td>${product.productBrand}</td>
                                <td>${product.productSize}</td>
                                <td><button class="add-to-cart-btn">Добавить</td>`
                    ;
                    const addToCartBtn = row.querySelector('.add-to-cart-btn');
                    addToCartBtn.addEventListener('click', () => {
                        const requestData = {
                            productCount: 1,
                            product: product,
                        }
                        fetch('/addProduct', {
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
                        // Добавление продукта в корзину
                        addToCart(product);
                    });
                });

                table.appendChild(tbody);
                tableContainer.innerHTML = ''; // Очистить сообщение о поиске
                tableContainer.appendChild(table);
            });
    }
}
// getProducts()
