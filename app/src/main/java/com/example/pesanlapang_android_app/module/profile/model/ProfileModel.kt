package com.example.pesanlapang_android_app.module.profile.model
data class ProfileModel(
    val name: String,
    val email: String,
    val profileImageUrl: String? = null
) {
    companion object {
        fun dummyProfile() = ProfileModel(
            name = "Khabib Nurmagomedov",
            email = "khabib@example.com",
            profileImageUrl = null // nanti bisa diisi url kalau ada sistem gambar
        )
    }
}
