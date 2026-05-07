// ================================
// FETCH API CONFIGURATION
// ================================

const API_URL = "http://localhost:8080/api/products";


// ================================
// FETCH PRODUCTS FROM BACKEND
// ================================

async function fetchProducts() {

    try {

        const response = await fetch(API_URL);

        // Manual Status Checking
        if (!response.ok) {

            if (response.status === 404) {
                throw new Error("404 Error: API endpoint not found.");
            }

            if (response.status === 500) {
                throw new Error("500 Error: Internal server error.");
            }

            throw new Error(`HTTP Error: ${response.status}`);
        }

        const products = await response.json();

        renderProducts(products);

    } catch (error) {

        console.error("Fetch Error:", error.message);

        const grid = document.getElementById("productGrid");

        if (grid) {
            grid.innerHTML = `
                <div class="error">
                    <h3>Failed to load products.</h3>
                    <p>${error.message}</p>
                </div>
            `;
        }
    }
}


// ================================
// RENDER PRODUCTS DYNAMICALLY
// ================================

function renderProducts(products) {

    const grid = document.getElementById("productGrid");

    // Prevent errors if page has no product grid
    if (!grid) return;

    // Empty State
    if (products.length === 0) {

        grid.innerHTML = `
            <div class="empty-state">
                <h3>No Products Available</h3>
                <p>Please add products in the backend database.</p>
            </div>
        `;

        return;
    }

    // Dynamic Rendering
    grid.innerHTML = products.map(product => `

        <div class="product-card">

            <img 
                src="/frontend/assets/images/logo/dress.jpg" 
                alt="${product.name}"
            >

            <h3>${product.name}</h3>

            <p class="price">₱${product.price}</p>

            <a href="/frontend/pages/detail.html" class="btn">
                View Details
            </a>

        </div>

    `).join("");
}


// ================================
// CART FUNCTIONALITY
// ================================

document.addEventListener('DOMContentLoaded', function() {

    // Load products immediately
    fetchProducts();

    // ----------------------------
    // Update Cart Subtotal
    // ----------------------------

    function updateSubtotal() {

        let subtotal = 0;

        const items = document.querySelectorAll('.cart-items li');

        if(items.length === 0) {

            const subtotalElement = document.querySelector('.subtotal');

            if(subtotalElement) {
                subtotalElement.innerHTML = "Total: ₱0.00";
            }

            const emptyMsg = document.getElementById('empty-cart-msg');

            if(emptyMsg) {
                emptyMsg.style.display = "block";
            }

        } else {

            const emptyMsg = document.getElementById('empty-cart-msg');

            if(emptyMsg) {
                emptyMsg.style.display = "none";
            }

            items.forEach(item => {

                const price = parseFloat(item.dataset.price);

                const qtyInput = item.querySelector('.qty-input');

                const qty = parseInt(qtyInput.value);

                subtotal += price * qty;
            });

            const subtotalElement = document.querySelector('.subtotal');

            if(subtotalElement) {
                subtotalElement.innerHTML =
                    `Subtotal: <strong>₱${subtotal.toFixed(2)}</strong>`;
            }
        }
    }

    // ----------------------------
    // Quantity Input Listeners
    // ----------------------------

    const qtyInputs = document.querySelectorAll('.qty-input');

    qtyInputs.forEach(input => {

        input.addEventListener('change', updateSubtotal);

    });

    // Initial subtotal calculation
    if(document.querySelector('.subtotal')) {

        updateSubtotal();

    }

    // ----------------------------
    // FORM VALIDATION
    // ----------------------------

    const forms = document.querySelectorAll('form');

    forms.forEach(form => {

        form.addEventListener('submit', function(e) {

            const requiredFields =
                form.querySelectorAll('[required]');

            let valid = true;

            requiredFields.forEach(field => {

                if(!field.value) {
                    valid = false;
                }

            });

            if(!valid) {

                e.preventDefault();

                alert('Please fill in all required fields!');
            }
        });
    });
});