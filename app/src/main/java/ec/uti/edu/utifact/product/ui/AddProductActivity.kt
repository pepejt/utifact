package ec.uti.edu.utifact.product.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import ec.uti.edu.utifact.databinding.ActivityAddProductBinding
import ec.uti.edu.utifact.product.entity.Product

class AddProductActivity : AppCompatActivity() {

    companion object {
        private const val MAX_QUANTITY = 99
        private const val MAX_PRICE = 999.99
    }

    private lateinit var binding: ActivityAddProductBinding
    private var selectedProduct: String? = null
    private var editingProduct: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editingProduct = intent.getParcelableExtra("edit_product")

        setupProductDropdown()
        setupButtons()
        setupPriceListener()

        editingProduct?.let { loadProductData(it) }
    }

    private fun setupProductDropdown() {
        val products = listOf(
            Product(1, "Monitor Dell", 1, 150.0, "Monitor Full HD"),
            Product(2, "Laptop", 1, 850.0, "Laptop con 16GB RAM"),
            Product(3, "Monitor", 1, 120.0, "Monitor HD"),
            Product(4, "Teclado", 1, 25.0, "Teclado mecánico"),
            Product(5, "Mouse", 2, 15.0, "Mouse inalámbrico")
        )

        val productNames = products.map { it.name }

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, productNames)
        binding.autoCompleteProduct.setAdapter(adapter)

        binding.autoCompleteProduct.setOnItemClickListener { _, _, position, _ ->
            selectedProduct = productNames[position]

            val selected = products.find { it.name == selectedProduct }
            selected?.let {
                binding.etQuantity.setText(it.quantity.toString())
                binding.etPrice.setText(String.format("%.2f", it.price))
                binding.etAdditionalInfo.setText(it.additionalInfo)
            }
        }
    }



    private fun loadProductData(product: Product) {
        binding.autoCompleteProduct.setText(product.name)
        binding.autoCompleteProduct.isEnabled = false
        selectedProduct = product.name
        binding.etQuantity.setText(product.quantity.toString())
        binding.etPrice.setText(String.format("%.2f", product.price))
        binding.etAdditionalInfo.setText(product.additionalInfo)
        updateTotal()
    }

    private fun setupButtons() {
        binding.btnCancel.setOnClickListener {
            finish()
        }

        binding.btnAccept.setOnClickListener {
            if (validateFields()) {
                createProduct()
            }
        }
    }

    private fun setupPriceListener() {
        binding.etPrice.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                updateTotal()
            }
        })

        binding.etQuantity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                updateTotal()
            }
        })
    }

    private fun validateFields(): Boolean {
        if (selectedProduct == null && editingProduct == null) {
            binding.autoCompleteProduct.error = "Seleccione un producto"
            return false
        }

        val quantity = binding.etQuantity.text.toString().toIntOrNull()
        if (binding.etQuantity.text.isNullOrEmpty() || quantity == null) {
            binding.etQuantity.error = "Ingrese una cantidad"
            return false
        }
        if (quantity <= 0) {
            binding.etQuantity.error = "La cantidad debe ser mayor a 0"
            return false
        }
        if (quantity > MAX_QUANTITY) {
            binding.etQuantity.error = "La cantidad no puede ser mayor a $MAX_QUANTITY"
            return false
        }

        val price = binding.etPrice.text.toString().toDoubleOrNull()
        if (binding.etPrice.text.isNullOrEmpty() || price == null) {
            binding.etPrice.error = "Ingrese un precio"
            return false
        }
        if (price <= 0) {
            binding.etPrice.error = "El precio debe ser mayor a 0"
            return false
        }
        if (price > MAX_PRICE) {
            binding.etPrice.error = "El precio no puede ser mayor a ${formatCurrency(MAX_PRICE)}"
            return false
        }

        return true
    }

    private fun createProduct() {
        val product = Product(
            id = editingProduct?.id ?: 0,
            name = editingProduct?.name ?: selectedProduct ?: "",
            quantity = binding.etQuantity.text.toString().toIntOrNull() ?: 0,
            price = binding.etPrice.text.toString().toDoubleOrNull() ?: 0.0,
            additionalInfo = binding.etAdditionalInfo.text.toString()
        )

        setResult(RESULT_OK, intent.apply {
            putExtra("product", product)
            putExtra("is_edit", editingProduct != null)
        })
        finish()
    }

    private fun updateTotal() {
        val quantity = binding.etQuantity.text.toString().toIntOrNull() ?: 0
        val price = binding.etPrice.text.toString().toDoubleOrNull() ?: 0.0
        val total = quantity * price
        binding.tvTotal.text = formatCurrency(total)
    }

    private fun formatCurrency(amount: Double): String {
        return String.format("$%,.2f", amount)
    }
}