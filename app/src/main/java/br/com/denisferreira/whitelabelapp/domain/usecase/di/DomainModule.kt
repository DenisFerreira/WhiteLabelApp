package br.com.denisferreira.whitelabelapp.domain.usecase.di

import br.com.denisferreira.whitelabelapp.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DomainModule {
    @Binds
    fun bindCreateProductUseCase(useCaseImpl: CreateProductUseCaseImpl): CreateProductUseCase
    @Binds
    fun bindGetProductsUseCase(useCaseImpl: GetProductsUseCaseImpl): GetProductsUseCase
    @Binds
    fun bindUploadProductImageUseCase(useCaseImpl: UploadProductImageUseCaseImpl): UploadProductImageUseCase
}