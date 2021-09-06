package br.com.denisferreira.whitelabelapp.data.di

import br.com.denisferreira.whitelabelapp.data.FirebaseProductDataSource
import br.com.denisferreira.whitelabelapp.data.ProductDataSource
import br.com.denisferreira.whitelabelapp.data.ProductRepositoryImpl
import br.com.denisferreira.whitelabelapp.data.ShoppingCartRepositoryImpl
import br.com.denisferreira.whitelabelapp.domain.model.ProductRepository
import br.com.denisferreira.whitelabelapp.domain.model.ShoppingCartRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Singleton
    @Binds
    fun bindProductDataSource(dataSource: FirebaseProductDataSource): ProductDataSource

    @Singleton
    @Binds
    fun bindProductRepository(productRepository: ProductRepositoryImpl): ProductRepository

    @Singleton
    @Binds
    fun bindShoppingCartRepository(cartRepository: ShoppingCartRepositoryImpl): ShoppingCartRepository
}