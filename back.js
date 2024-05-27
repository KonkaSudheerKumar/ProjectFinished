let cartIcon = document.querySelector("#cart-icon");
let cart = document.querySelector(".cart");
let closeCart = document.querySelector("#close-cart");

cartIcon.onclick = () => {
    cart.classList.add("active");
};
closeCart.onclick = () => {
    cart.classList.remove("active");
};

document.addEventListener("DOMContentLoaded", ready);

function ready() {
    var addCartButtons = document.querySelectorAll(".add-cart");

    addCartButtons.forEach(button => {
        button.addEventListener("click", addCartClicked);
    });

    var removeCartButtons = document.querySelectorAll('.cart-remove');
    removeCartButtons.forEach(button => {
        button.addEventListener("click", removeCartItem);
    });

    var quantityInputs = document.querySelectorAll(".cart-quantity");
    quantityInputs.forEach(input => {
        input.addEventListener("change", quantityChanged);
    });
}

function removeCartItem(event) {
    var buttonClicked = event.target;
    var cartItem = buttonClicked.parentElement;
    var cartContent = document.querySelector(".cart-content");
    cartContent.removeChild(cartItem);
    updateTotal(); // Update total after removing item
}

function quantityChanged(event) {
    var input = event.target;
    if (isNaN(input.value) || input.value <= 0) {
        input.value = 1;
    }
    updateTotal();
}

function addCartClicked(event) {
    var button = event.target;
    var shopProduct = button.parentElement;
    var title = shopProduct.querySelector(".product-title").innerText;
    var price = shopProduct.querySelector(".price").innerText;
    var productImg = shopProduct.querySelector(".product-img").src;

    addProductToCart(title, price, productImg);
    updateTotal();
}

function addProductToCart(title, price, productImg) {
    var cartShopBox = document.createElement('div');
    cartShopBox.classList.add('cart-box');

    var cartItems = document.querySelector('.cart-content');
    var cartItemsNames = cartItems.querySelectorAll('.cart-product-title');
    for (var i = 0; i < cartItemsNames.length; i++) {
        if (cartItemsNames[i].innerText === title) {
            alert('You have already added this item to cart');
            return;
        }
    }

    var cartBoxContent = `
        <img src="${productImg}" alt="" class="cart-img"/>
        <div class="details-box">
            <div class="cart-product-title">${title}</div>
            <div class="cart-price">${price}</div>
            <input type="number" name="" value="1" class="cart-quantity"/>
        </div>
        <i class="bx bx-trash-alt cart-remove"></i>
    `;
    cartShopBox.innerHTML = cartBoxContent;
    cartItems.appendChild(cartShopBox);

    cartShopBox.querySelector('.cart-remove').addEventListener('click', removeCartItem);
    cartShopBox.querySelector('.cart-quantity').addEventListener('change', quantityChanged);
}

function updateTotal() {
    var cartContent = document.querySelector(".cart-content");
    var cartBoxes = cartContent.querySelectorAll(".cart-box");
    var total = 0;

    cartBoxes.forEach(cartBox => {
        var priceElement = cartBox.querySelector(".cart-price");
        var quantityElement = cartBox.querySelector(".cart-quantity");

        var price = parseFloat(priceElement.innerText.replace("$", ""));
        var quantity = quantityElement.value;

        total += price * quantity;
    });

    document.querySelector(".total-price").innerText = "$" + total.toFixed(2);
}
