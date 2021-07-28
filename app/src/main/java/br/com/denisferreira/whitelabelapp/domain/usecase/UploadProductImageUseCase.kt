package br.com.denisferreira.whitelabelapp.domain.usecase

import android.net.Uri

interface UploadProductImageUseCase {
    suspend operator fun invoke(imageUri: Uri): String
}