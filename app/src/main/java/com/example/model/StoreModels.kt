package com.example.model

enum class AppLanguage {
    ENGLISH,
    URDU
}

data class Product(
    val id: String,
    val nameEn: String,
    val nameUr: String,
    val category: String,
    val pricePkr: Int,
    val originalPricePkr: Int,
    val descriptionEn: String,
    val descriptionUr: String,
    val imageRes: Int,
    val rating: Float = 4.9f,
    val reviewsCount: Int = 120,
    val badgeEn: String? = null,
    val badgeUr: String? = null,
    val inStock: Boolean = true,
    val featuresEn: List<String> = emptyList(),
    val featuresUr: List<String> = emptyList()
)

data class ProductCategory(
    val id: String,
    val nameEn: String,
    val nameUr: String
)

data class Testimonial(
    val id: String,
    val author: String,
    val city: String,
    val rating: Int,
    val date: String,
    val reviewEn: String,
    val reviewUr: String,
    val verified: Boolean = true
)

data class FaqItem(
    val id: String,
    val questionEn: String,
    val questionUr: String,
    val answerEn: String,
    val answerUr: String
)

data class BusinessConfig(
    val businessNameEn: String = "Shaheen Lifestyle",
    val businessNameUr: String = "شاہین لائف اسٹائل",
    val taglineEn: String = "Pakistan's Most Trusted Premium Store",
    val taglineUr: String = "پاکستان کا سب سے قابلِ اعتماد پریمیم اسٹور",
    val whatsappNumber: String = "923001234567",
    val whatsappDisplay: String = "0300-1234567",
    val phoneDisplay: String = "+92 300 1234567",
    val email: String = "support@shaheenstore.pk",
    val addressEn: String = "Shop #14, Main Boulevard, Gulberg III, Lahore, Pakistan",
    val addressUr: String = "دکان نمبر 14، مین بلیوارڈ، گلبرگ 3، لاہور، پاکستان",
    val city: String = "Lahore",
    val deliveryFeePkr: Int = 200,
    val freeDeliveryAbovePkr: Int = 3000,
    val deliveryTimeEn: String = "2-3 Working Days (Nationwide COD)",
    val deliveryTimeUr: String = "2 سے 3 کاروباری دن (پورے پاکستان میں کیش آن ڈلیوری)",
    val facebookUrl: String = "https://facebook.com/shaheenstorepk",
    val instagramUrl: String = "https://instagram.com/shaheenstorepk"
)

data class OrderSubmission(
    val orderId: String,
    val customerName: String,
    val phoneNumber: String,
    val city: String,
    val fullAddress: String,
    val productId: String,
    val productName: String,
    val quantity: Int,
    val totalAmountPkr: Int,
    val paymentMethod: String = "Cash on Delivery (COD)",
    val notes: String = "",
    val orderTime: Long = System.currentTimeMillis()
)
