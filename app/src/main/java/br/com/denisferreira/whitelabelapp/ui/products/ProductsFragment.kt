package br.com.denisferreira.whitelabelapp.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.fragment.findNavController
import br.com.denisferreira.whitelabelapp.R
import br.com.denisferreira.whitelabelapp.databinding.FragmentProductsBinding
import br.com.denisferreira.whitelabelapp.domain.model.Product
import br.com.denisferreira.whitelabelapp.util.PRODUCT_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding
    private val productsAdapter = ProductsAdapter()
    private val viewModel: ProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        observeVMEvents()
        setListener()
        observeNavBackStack()

        getProducts()
    }

    private fun observeNavBackStack() {
        findNavController().run {
            val navBackStackEntry = getBackStackEntry(R.id.productsFragment)
            val savedStateHandle = navBackStackEntry.savedStateHandle
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_RESUME && savedStateHandle.contains(PRODUCT_KEY)) {
                    val product = savedStateHandle.get<Product>(PRODUCT_KEY)
                    val oldList = productsAdapter.currentList
                    val newList = oldList.toMutableList().apply {
                        add(product)
                    }
                    productsAdapter.submitList(newList)
                    binding.recyclerProducts.smoothScrollToPosition(newList.size - 1)
                    savedStateHandle.remove<Product>(PRODUCT_KEY)
                }

            }
            navBackStackEntry.lifecycle.addObserver(observer)

            viewLifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_DESTROY)
                    navBackStackEntry.lifecycle.removeObserver(observer)
            })
        }
    }

    private fun setRecyclerView() {
        binding.recyclerProducts.run {
            setHasFixedSize(true)
            adapter = productsAdapter
        }
    }

    private fun observeVMEvents() {
        with(viewModel) {
            productData.observe(viewLifecycleOwner) { products ->
                productsAdapter.submitList(products)
            }
            showErrorMessage.observe(viewLifecycleOwner) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
            addProductVisibility.observe(viewLifecycleOwner) { visibility ->
                binding.fabAddProduct.visibility = visibility
            }
        }


    }

    private fun setListener() {
        with(binding) {
            fabAddProduct.setOnClickListener {
                findNavController().navigate(ProductsFragmentDirections.actionProductsFragmentToAddProductFragment())
            }

            swipeProducts.setOnRefreshListener {
                getProducts()
            }
        }
    }

    private fun getProducts() {
        binding.swipeProducts.isRefreshing = true
        viewModel.getProducts()
        binding.swipeProducts.isRefreshing = false
    }

}