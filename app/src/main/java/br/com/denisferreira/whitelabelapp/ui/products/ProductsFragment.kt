package br.com.denisferreira.whitelabelapp.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.denisferreira.whitelabelapp.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {


    private lateinit var binding : FragmentProductsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        val adapter = ProductsAdapter()
        binding.recyclerProducts.adapter = adapter
        return binding.root
    }


}