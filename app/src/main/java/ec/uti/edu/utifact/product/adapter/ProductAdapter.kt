package ec.uti.edu.utifact.product.adapter

import android.view.LayoutInflater
import android.view.View
import android.widget.PopupMenu
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ec.uti.edu.utifact.R
import ec.uti.edu.utifact.databinding.ItemProductBinding
import ec.uti.edu.utifact.product.entity.Product


class ProductAdapter(
    private val onDeleteClick: (Product) -> Unit,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var products: List<Product> = emptyList()

    fun updateProducts(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding, onDeleteClick, onItemClick)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size

    class ProductViewHolder(
        private val binding: ItemProductBinding,
        private val onDeleteClick: (Product) -> Unit,
        private val onItemClick: (Product) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.apply {
                tvQuantity.text = product.quantity.toString()
                tvProductName.text = product.name
                tvUnitPrice.text = formatCurrency(product.price)
                tvPrice.text = formatCurrency(product.price * product.quantity)

                btnDelete.setOnClickListener { view ->
                    showPopupMenu(view, product)
                }
            }
        }

        private fun formatCurrency(amount: Double): String {
            return String.format("$%,.2f", amount)
        }

        private fun showPopupMenu(view: View, product: Product) {
            PopupMenu(view.context, view).apply {
                menuInflater.inflate(R.menu.menu_item_options, menu)
                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.action_edit -> {
                            onItemClick(product)
                            true
                        }
                        R.id.action_delete -> {
                            onDeleteClick(product)
                            true
                        }
                        else -> false
                    }
                }
                show()
            }
        }
    }
}