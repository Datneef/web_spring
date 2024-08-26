window.onload = function () {
    let domContextPath = document.querySelector("input[name='contextPath']").value;
    let url = domContextPath + 'rest/books';

    //1. create XMLHttp
    let xmlHttp = new XMLHttpRequest();

    //2 setting call back function
    xmlHttp.onload = function () {
        //render book
        let textJSON = xmlHttp.responseText;
        let arrayBooks = JSON.parse(textJSON);
        let html = '';

        arrayBooks.forEach(function (book) {
            html = html +
                `<div class="product_item">
                            <img src="${book.image}" alt="${book.title}">
                                <p>Book name</p>
                                <p>Book Price: ${book.price}<sup>đ</sup></p>
                        </div>`;
        });
        document.querySelector('.product_list').innerHTML = html;
    }
    xmlHttp.open('GET', url, true);
    xmlHttp.send();
}