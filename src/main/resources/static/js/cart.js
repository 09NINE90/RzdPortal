const token = $("meta[name='_csrf']").attr("content");
const header = $("meta[name='_csrf_header']").attr("content");
tableContainer = document.querySelector('.table-container');
table = document.createElement('table');
thead = document.createElement('thead');
tbody = document.createElement('tbody');
const submitButton = document.querySelector('#submit-button');
const searchInput = document.querySelector('#search-input');
const searchButton = document.querySelector('#search-button');

const theadRow = thead.insertRow();
theadRow.innerHTML =
    '            <th>Короткое название</th>\n'+
    '            <th>Маркировка</th>\n'+
    '            <th>Параметры</th>'+
    '            <th>Обоснование</th>'+
    '            <th>Количество</th>\n'+
    '            <th>Цена (руб.)</th>'+
    '            <th>Сумма (руб.)</th>'+
    '            <th>Месяц</th>'+
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
                row.setAttribute('data-cart', JSON.stringify(cart)); // Добавляем объект cart как атрибут строки
                row.innerHTML =`
                               <td>${cart.product.productNameShort}</td>
                                <td>${cart.product.productBrand}</td>
                                <td>${cart.product.productSize}</td>
                                <td><textarea class="reason" type="text">${cart.reason}</textarea></td>
                                <td>    <button class="decrement" style="padding: 3px; padding-inline: 5px">-</button>
                                        <span class="product-count">${cart.productCount}</span>
                                        <button class="increment" style="padding: 3px; padding-inline: 5px">+</button></td>
                                <td><input class="product-price" type="text" style="width: 50px" value="${cart.price}"></td>
                                <td class="product-multiplication">${cart.sum}</td>
                                <td><select class="month" name="numbers" class="selectNumbers">
                                    <option value="${cart.month}">${cart.month}</option> 
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option>
                                    <option value="11">11</option>
                                    <option value="12">12</option>
                                </select></td>
                                <td><button class="remove-from-cart-btn">Удалить</td>`
                ;
                const decrementButton = row.querySelector('.decrement');
                const incrementButton = row.querySelector('.increment');
                const selectElement = row.querySelector('.selectNumbers');
                const textArea = row.querySelector('.reason')
                textArea.addEventListener('blur', function() {
                    const month = row.querySelector('.month').value
                    const reason = row.querySelector('.reason').value
                    let count = parseInt(productCountCell.textContent);
                    const inputValue = parseFloat(inputPrice.value) || 0;
                    const productCountValue = parseInt(productCountCell.textContent) || 0;
                    multiplicationCell.textContent = inputValue * productCountValue;
                    postEditProduct(count, month, reason, inputValue, multiplicationCell.textContent, cart.product)

                });

                decrementButton.addEventListener('click', function() {
                    const month = row.querySelector('.month').value
                    const reason = row.querySelector('.reason').value
                    let count = parseInt(productCountCell.textContent);
                    if (count > 1) {
                        count--;
                        productCountCell.textContent = count;
                        const inputValue = parseFloat(inputPrice.value) || 0;
                        const productCountValue = parseInt(productCountCell.textContent) || 0;
                        multiplicationCell.textContent = inputValue * productCountValue;
                        postEditProduct(count, month, reason, inputValue, multiplicationCell.textContent, cart.product)
                    }
                });

                incrementButton.addEventListener('click', function() {
                    const month = row.querySelector('.month').value
                    const reason = row.querySelector('.reason').value
                    let count = parseInt(productCountCell.textContent);
                    count++;
                    productCountCell.textContent = count;
                    const inputValue = parseFloat(inputPrice.value) || 0;
                    const productCountValue = parseInt(productCountCell.textContent) || 0;
                    multiplicationCell.textContent = inputValue * productCountValue;
                    postEditProduct(count, month, reason, inputValue, multiplicationCell.textContent, cart.product)

                });
                submitButton.addEventListener('click', () => {
                    const cartData = JSON.parse(row.getAttribute('data-cart'));

                    fetch('/createOder', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                            'X-CSRF-TOKEN': token
                        },
                        body: JSON.stringify(cartData)
                    })
                        .then(response => {
                            console.log(response)
                            // window.location.href = '/api/v1/getWorkoutPage/' + userId
                        })
                        .catch(error => {
                            console.error('Ошибка при отправке формы:', error);
                        });
                })
                const multiplicationCell = row.querySelector('.product-multiplication')
                const productCountCell = row.querySelector('.product-count')
                const inputPrice = row.querySelector('.product-price')
                inputPrice.addEventListener('blur', function() {
                    const month = row.querySelector('.month').value
                    const reason = row.querySelector('.reason').value
                    let count = parseInt(productCountCell.textContent);
                    const inputValue = parseFloat(inputPrice.value) || 0;
                    const productCountValue = parseInt(productCountCell.textContent) || 0;
                    multiplicationCell.textContent = inputValue * productCountValue;
                    postEditProduct(count, month, reason, inputValue, multiplicationCell.textContent, cart.product)

                });

                const deleteFromCartBtn = row.querySelector('.remove-from-cart-btn');
                deleteFromCartBtn.addEventListener('click', () => {
                    // Добавление продукта в корзину
                    const requestData = {
                        cart: `${cart}`,
                    }
                    fetch('/deleteProductCart', {
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

function postEditProduct(pCount, pMonth, pReason, pPrice, pSum, product){
    let quarter
    if (pMonth < 4){
        quarter = 1
    }else if (pMonth < 7){
        quarter = 2
    }else if (pMonth < 10){
        quarter = 3
    }else {
        quarter = 4
    }
    const requestData = {
        productCount: pCount,
        month: pMonth,
        reason: pReason,
        price: pPrice,
        sum: pSum,
        quarter: quarter,
        product: product,
    }
    fetch('/editProductCart', {
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

}
