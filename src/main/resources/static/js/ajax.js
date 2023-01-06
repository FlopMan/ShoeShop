function generateProduct(product) {
    return `<div class="col-lg-4 col-sm-6" >
                                    <!-- product grid item start -->
                                    <div class="product-item mb-53" >
                                        <div class="product-thumb">
                                            <a href="/detail/` + product.id + `">
                                                <img src="/images/` + product.imageSp + `" alt="">
                                            </a>
                                        </div>
                                        <div class="product-content">
                                            <h5 class="product-name">
                                                 <a href="/detail/` + product.id + `">` + product.name + `</a>
                                            </h5>
                                            <div class="price-box">
                                                <span class="price-regular">` + product.price + `</span>
                                                <span class="price-old"><del>$70.00</del></span>
                                            </div>
                                            <div class="product-action-link">
                                                <a href="#" data-toggle="tooltip" title="Wishlist"><i class="ion-android-favorite-outline"></i></a>
                                                <a href="#" data-toggle="tooltip" title="Add To Cart"><i class="ion-bag"></i></a>
                                                <a href="#" data-toggle="modal" data-target="#quick_view"> <span data-toggle="tooltip"
                                                    title="Quick View"><i class="ion-ios-eye-outline"></i></span> </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                `
}

function ShowdetailCategory(id) {
    console.log(id)
    $.ajax({
        type: "GET",
        url: "/detailCategory/" + id,

        success: function (result) {
            console.log("aaaaa")
            var str = "";
            console.log(result)
            $.each(result, function (key, value) {
                str += generateProduct(value)
            });
            $('#productByIdCate').html(str);
        }

    });
}


function loadPagination(pageNo) {
    console.log(pageNo)
    $.ajax({
        type: "GET",
        // dataType: "json",
        url: "/page/" + pageNo,

        success: function (data) {
            // console.log("đánh dấu")
            console.log(data)
            var str = "";
            $.each(data, function (key, value) {
                str += generateProduct(value)
            });
            $('#productByIdCate').html(str);
        }
    })
}


$(document).ready(function () {
getValue();
})

function getValue() {
    var cart_item = document.getElementById("cart-item");
    console.log(cart_item);
    var cart_rows = cart_item.getElementsByClassName("cart-row");
    console.log(cart_rows);
    var total = 0;
    for (i = 0; i < cart_rows.length; i++) {
        var cart_row = cart_rows[i];
        // console.log("cart_row:")
        // console.log(cart_row)
        var price_item = cart_row.getElementsByClassName("cart_price")[0];
        // console.log("price-item:")
        // console.log(price_item)
        var quantity_item = cart_row.getElementsByClassName("cart-quantity-input")[0];
        var price = parseFloat(price_item.innerText);
        var quantity = quantity_item.value
        total = total + (price * quantity);
    }
    console.log(total)
    document.getElementById("cart-total-price").innerHTML = total + "$";
}

function removeProductInCart(id) {
    $.ajax({
        type: "GET",
        url: "/remove/" + id,
        success: function (result) {
            location.reload();
        }
    })
    getValue();
}

function changeQuantity(itemNumber,id){
    console.log({
        itemNumber,
        id,
    });
    if (itemNumber == 0) {
        var option = confirm("Bạn có muốn xóa sản phẩm này không ?")
        if (option == true){
            removeProductInCart(id);
        }else {
            location.reload();
        }
    }
    document.getElementById("cart-quantity-input-"+id).textContent = parseInt(itemNumber);
    var price_item = document.getElementById("cart-price-" + id).innerText;
    price_item = parseFloat(price_item);
    document.getElementById("pro-subtotal-" + id).textContent = parseInt(itemNumber) * price_item;
    getValue();
}



