package br.com.denisferreira.whitelabelapp.ui.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.denisferreira.whitelabelapp.databinding.ItemProductBinding
import br.com.denisferreira.whitelabelapp.domain.model.Product
import br.com.denisferreira.whitelabelapp.util.toCurrency
import com.bumptech.glide.Glide

class ProductsAdapter(private val listener: ManageCartListener) :
    ListAdapter<Product, ProductsAdapter.ProductViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.create(parent, listener)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position) as Product
        holder.bind(product)
    }

    class ProductViewHolder(
        private val itemBinding: ItemProductBinding,
        private val listener: ManageCartListener
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(product: Product) {
            itemBinding.run {
                Glide.with(itemView)
                    .load(product.imageUrl)
                    .fitCenter()
                    .into(imageProduct)
                imageProduct.contentDescription = product.description
                textDescription.text = product.description
                textPrice.text = product.price.toCurrency()

                buyProductButton.setOnClickListener {
                    listener.addProductToCart(product)
                    buyProductButton.visibility = View.GONE
                    removeProductButton.visibility = View.VISIBLE
                }
                removeProductButton.setOnClickListener {
                    listener.removeProductFromCart(product)
                    buyProductButton.visibility = View.VISIBLE
                    removeProductButton.visibility = View.GONE
                }
            }
        }

        companion object {
            fun create(parent: ViewGroup, listener: ManageCartListener): ProductViewHolder {
                val itemBinding =
                    ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ProductViewHolder(itemBinding, listener)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }
        }
    }


    interface ManageCartListener {
        fun addProductToCart(product: Product)
        fun removeProductFromCart(product: Product)
    }
}