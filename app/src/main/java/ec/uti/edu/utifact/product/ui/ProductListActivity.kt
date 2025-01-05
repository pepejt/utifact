package ec.uti.edu.utifact.product.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ec.uti.edu.utifact.databinding.ActivityProductListBinding
import ec.uti.edu.utifact.product.adapter.ProductAdapter
import ec.uti.edu.utifact.product.entity.Product

class ProductListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductListBinding
    private val products = mutableListOf<Product>()
    private val productAdapter = ProductAdapter(
        onDeleteClick = { product ->
            showDeleteConfirmation(product)
        },
        onItemClick = { product ->
            launchEditProduct(product)
        }
    )

    private val addProductLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.let { data ->
                val product = data.getParcelableExtra<Product>("product")
                val isEdit = data.getBooleanExtra("is_edit", false)

                if (isEdit) {
                    product?.let { updateProduct(it) }
                } else {
                    product?.let { addProduct(it) }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupButtons()
        updateProductList()
    }

    private fun setupRecyclerView() {
        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(this@ProductListActivity)
            adapter = productAdapter
        }
    }

    private fun setupButtons() {
        binding.btnAddProduct.setOnClickListener {
            launchAddProduct()
        }

        binding.btnAddMore.setOnClickListener {
            launchAddProduct()
        }

        binding.btnInvoice.setOnClickListener {
            if (products.isNotEmpty()) {
                Toast.makeText(this, "Facturando...", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun launchAddProduct() {
        val intent = Intent(this, AddProductActivity::class.java)
        addProductLauncher.launch(intent)
    }

    private fun launchEditProduct(product: Product) {
        val intent = Intent(this, AddProductActivity::class.java).apply {
            putExtra("edit_product", product)
        }
        addProductLauncher.launch(intent)
    }

    private fun addProduct(product: Product) {
        products.add(product)
        updateProductList()
        Toast.makeText(this, "Producto agregado exitosamente", Toast.LENGTH_SHORT).show()
    }

    private fun updateProduct(updatedProduct: Product) {
        val index = products.indexOfFirst { it.name == updatedProduct.name }
        if (index != -1) {
            products[index] = updatedProduct
            updateProductList()
            Toast.makeText(this, "Producto actualizado exitosamente", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showDeleteConfirmation(product: Product) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar Producto")
            .setMessage("¿Está seguro que desea eliminar este producto?")
            .setPositiveButton("Eliminar") { _, _ ->
                deleteProduct(product)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun deleteProduct(product: Product) {
        products.remove(product)
        updateProductList()
        Toast.makeText(this, "Producto eliminado", Toast.LENGTH_SHORT).show()
    }

    private fun updateProductList() {
        if (products.isEmpty()) {
            binding.cardEmptyState.visibility = View.VISIBLE
            binding.cardProductList.visibility = View.GONE
        } else {
            binding.cardEmptyState.visibility = View.GONE
            binding.cardProductList.visibility = View.VISIBLE
            productAdapter.updateProducts(products)
            updateTotal()
        }
    }

    private fun updateTotal() {
        val total = products.sumOf { it.price * it.quantity }
        binding.tvTotal.text = String.format("$%.2f", total)
    }
}