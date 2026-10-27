package com.example.data

import com.example.R
import com.example.model.AppLanguage
import com.example.model.BusinessConfig
import com.example.model.FaqItem
import com.example.model.OrderSubmission
import com.example.model.Product
import com.example.model.ProductCategory
import com.example.model.Testimonial
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

object StoreRepository {

    private val _language = MutableStateFlow(AppLanguage.ENGLISH)
    val language: StateFlow<AppLanguage> = _language.asStateFlow()

    fun toggleLanguage() {
        _language.value = if (_language.value == AppLanguage.ENGLISH) AppLanguage.URDU else AppLanguage.ENGLISH
    }

    fun setLanguage(lang: AppLanguage) {
        _language.value = lang
    }

    private val _businessConfig = MutableStateFlow(BusinessConfig())
    val businessConfig: StateFlow<BusinessConfig> = _businessConfig.asStateFlow()

    fun updateBusinessConfig(updated: BusinessConfig) {
        _businessConfig.value = updated
    }

    val categories = listOf(
        ProductCategory("all", "All Products", "تمام پراڈکٹس"),
        ProductCategory("perfumes", "Luxury Fragrances", "شاہی پرفیومز"),
        ProductCategory("watches", "Premium Watches", "پرتعیش گھڑیاں"),
        ProductCategory("leather", "Pure Leather", "خالص چمڑا"),
        ProductCategory("deals", "Best Deals", "خصوصی آفرز")
    )

    val defaultProducts = listOf(
        Product(
            id = "prod-1",
            nameEn = "Royal Arabian Oud Eau de Parfum - 100ml",
            nameUr = "رائل عود العطر عربی پرفیوم - 100 ملی لیٹر",
            category = "perfumes",
            pricePkr = 3499,
            originalPricePkr = 4999,
            descriptionEn = "Handcrafted pure Arabic Dehn Al Oud blended with Cambodian amber, taif rose, and rich sandalwood. Long-lasting 24-hour projection designed for royalty.",
            descriptionUr = "خالص عربی دہن العود، کمبوڈین عنبر اور طائف گلاب کا شاہکار امتزاج۔ 24 گھنٹے تک برقرار رہنے والی پرتعیش خوشبو۔",
            imageRes = R.drawable.prod_oud_perfume_1793013322605,
            rating = 4.9f,
            reviewsCount = 284,
            badgeEn = "Bestseller",
            badgeUr = "سب سے مقبول",
            featuresEn = listOf("100% Original Authentic Oud", "Alcohol-free options available", "24+ Hours Projection", "Luxury Gold Gift Box"),
            featuresUr = listOf("100 فیصد خالص اصلی عود", "24 گھنٹے سے زائد دیرپا خوشبو", "پرتعیش گولڈن گفٹ باکس", "ہر موسم کے لیے بہترین")
        ),
        Product(
            id = "prod-2",
            nameEn = "Emerald Royal Chronometer Wristwatch",
            nameUr = "زمردی رائل کرونومیٹر گھڑی مع لیدر سٹریپ",
            category = "watches",
            pricePkr = 4850,
            originalPricePkr = 6500,
            descriptionEn = "Fluted rose-gold bezel with a sunburst emerald green dial, date magnification lens, and genuine textured cowhide leather strap.",
            descriptionUr = "روز گولڈ بیزل اور گہرے زمردی سن برسٹ ڈائل کے ساتھ جدید ترین کرونومیٹر گھڑی۔ واٹر ریزسٹنٹ اور پریمیم چمڑے کا پٹا۔",
            imageRes = R.drawable.prod_emerald_watch_1793013336127,
            rating = 4.95f,
            reviewsCount = 192,
            badgeEn = "Hot Pick",
            badgeUr = "ٹاپ ٹرینڈنگ",
            featuresEn = listOf("Japanese Quartz Chronometer Movement", "Scratch-Resistant Sapphire Glass", "3 ATM Water Resistant", "1-Year Official Warranty"),
            featuresUr = listOf("جاپانی موومنٹ انجن", "اسکریچ پروف سفائر گلاس", "واٹر پروف ڈیزائن", "ایک سال کی مکمل وارنٹی")
        ),
        Product(
            id = "prod-3",
            nameEn = "Handcrafted Pure Grain Leather Bifold Wallet",
            nameUr = "خالص قدرتی چمڑے کا بائی فولڈ پرس",
            category = "leather",
            pricePkr = 1899,
            originalPricePkr = 2800,
            descriptionEn = "100% full-grain Pakistani pull-up cowhide leather, double nylon stitched with 8 card slots, 2 cash compartments, and RFID blocking layer.",
            descriptionUr = "100 فیصد اصلی پاکستانی کاؤ ہائیڈ چمڑا۔ 8 کارڈ سلاٹس، 2 کیش پاکٹس اور آر ایف آئی ڈی تحفظ کے ساتھ نفیس ڈیزائن۔",
            imageRes = R.drawable.prod_leather_wallet_1793013349407,
            rating = 4.88f,
            reviewsCount = 341,
            badgeEn = "Pure Leather",
            badgeUr = "خالص چمڑا",
            featuresEn = listOf("100% Full-Grain Natural Leather", "RFID Anti-Theft Blocking", "8 Card Slots + ID Window", "Luxury Gift Packaging"),
            featuresUr = listOf("خالص چمڑے کی لائف ٹائم گارنٹی", "آر ایف آئی ڈی چوری سے تحفظ", "8 کارڈ سلاٹس مع شناختی کھڑکی", "خوبصورت گولڈ ربن گفٹ پیک")
        ),
        Product(
            id = "prod-4",
            nameEn = "Imperial Amber & Oud Attar Oil - 12ml",
            nameUr = "امپیریل عنبر و عود خالص عطر - 12 ملی لیٹر",
            category = "perfumes",
            pricePkr = 2199,
            originalPricePkr = 3200,
            descriptionEn = "Concentrated non-alcoholic luxury attar oil infused with smoky Agarwood notes and sweet warm amber.",
            descriptionUr = "خالص نان الکوحلک عطر جس میں اودھ اور میٹھے عنبر کی مدہوش کن مہک شامل ہے۔",
            imageRes = R.drawable.prod_oud_perfume_1793013322605,
            rating = 4.85f,
            reviewsCount = 145,
            badgeEn = "Alcohol-Free",
            badgeUr = "الکوحل سے پاک",
            featuresEn = listOf("100% Pure Oil Extract", "Non-Alcoholic Halal Formulation", "Includes Crystal Roll-on Applicator", "Pocket Friendly Luxury"),
            featuresUr = listOf("100 فیصد خالص نچوڑ", "حلال اور الکوحل سے پاک", "کرسٹل رول آن ایپلی کیٹر", "نماز اور خاص تقریبات کے لیے بہترین")
        ),
        Product(
            id = "prod-5",
            nameEn = "Executive Messenger Crossbody Leather Bag",
            nameUr = "ایگزیکٹو اصلی چمڑے کا شولڈر میسنجر بیگ",
            category = "leather",
            pricePkr = 5999,
            originalPricePkr = 8500,
            descriptionEn = "Premium hand-burnished oil-pull leather bag with padded tablet compartment, brass hardware, and adjustable shoulder strap.",
            descriptionUr = "اعلیٰ معیار کے چمڑے سے بنا شولڈر بیگ۔ ٹیبلٹ کی مخصوص پاکٹ، پیتل کے مضبوط بکلز اور آرام دہ پٹے۔",
            imageRes = R.drawable.hero_store_banner_1793013308909,
            rating = 4.92f,
            reviewsCount = 89,
            badgeEn = "Limited Edition",
            badgeUr = "محدود ایڈیشن",
            featuresEn = listOf("Fits 11-inch iPad / Tablet", "Solid Brass Buckles", "Heavy-Duty YKK Zippers", "Lifetime Stitching Warranty"),
            featuresUr = listOf("11 انچ ٹیبلٹ کے لیے مخصوص جگہ", "پیتل کے بکلز اور وائی کے کے زپر", "آرام دہ ایڈجسٹ ایبل بیلٹ", "پیشہ ورانہ و کاروباری استعمال")
        ),
        Product(
            id = "prod-6",
            nameEn = "Heritage Rose Gold Couple Watch Pair",
            nameUr = "ہیریٹیج روز گولڈ کپل واچ پرتعیش جوڑی",
            category = "watches",
            pricePkr = 7499,
            originalPricePkr = 10500,
            descriptionEn = "Matching his-and-hers luxury dress watches with emerald accents, diamond-cut indices, and wooden dual-watch showcase box.",
            descriptionUr = "میاں بیوی یا منگیتر کے لیے جوڑی گھڑیاں۔ زمردی ڈائل، خوبصورت نگینے اور لکڑی کا پریزنٹیشن باکس۔",
            imageRes = R.drawable.prod_emerald_watch_1793013336127,
            rating = 4.97f,
            reviewsCount = 112,
            badgeEn = "Gift Set",
            badgeUr = "تحفہ سیٹ",
            featuresEn = listOf("His & Hers Matching Set", "Dual Wooden Gift Box", "Water Resistant", "Free Greeting Card Included"),
            featuresUr = listOf("جوڑے کے لیے پرفیکٹ گفٹ", "پرتعیش لکڑی کا شوکیس باکس", "مفت گریٹنگ کارڈ سروس", "شادی اور سالگرہ کے لیے بہترین")
        )
    )

    private val _products = MutableStateFlow(defaultProducts)
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    fun addOrUpdateProduct(product: Product) {
        val current = _products.value.toMutableList()
        val index = current.indexOfFirst { it.id == product.id }
        if (index >= 0) {
            current[index] = product
        } else {
            current.add(0, product)
        }
        _products.value = current
    }

    val defaultTestimonials = listOf(
        Testimonial(
            id = "t1",
            author = "Usman Khan",
            city = "Lahore (DHA Phase 5)",
            rating = 5,
            date = "Yesterday",
            reviewEn = "Parcel received in just 2 days through TCS. Fragrance quality is literally 10/10, long lasting projection and original seal. Cash on delivery was seamless. Highly recommended store!",
            reviewUr = "لاہور میں صرف دو دن میں ٹی سی ایس کے ذریعے پارسل پہنچ گیا۔ پرفیوم کی خوشبو 10/10 ہے، شاندار پروڈکٹ اور کیش آن ڈلیوری کی بہترین سہولت۔"
        ),
        Testimonial(
            id = "t2",
            author = "Dr. Ayesha Siddiqui",
            city = "Karachi (Clifton)",
            rating = 5,
            date = "3 days ago",
            reviewEn = "The pure leather wallet quality is genuinely top-tier. Authentic leather fragrance, clean stitching, and comes inside a luxury gift box with ribbon. Perfect for gifting my husband.",
            reviewUr = "خالص چمڑے کا پرس بہت شاندار کوالٹی کا ہے۔ سلائی بہت نفیس ہے اور گفٹ پیکنگ تو کمال ہے۔ کراچی میں تیز ترین ڈلیوری ملی۔"
        ),
        Testimonial(
            id = "t3",
            author = "Muhammad Bilal",
            city = "Islamabad (F-7)",
            rating = 5,
            date = "5 days ago",
            reviewEn = "I ordered via WhatsApp. Customer support was incredibly polite and shared pictures before dispatching. Delivered safely with COD receipt. Will shop again for sure!",
            reviewUr = "میں نے واٹس ایپ پر آرڈر کیا، سپورٹ ٹیم نے فوری تفصیلات دیں۔ پارسل کھول کر چیک کرنے کی بھی اجازت تھی۔ بہت قابلِ بھروسہ سروس!"
        ),
        Testimonial(
            id = "t4",
            author = "Zainab Tariq",
            city = "Faisalabad",
            rating = 5,
            date = "1 week ago",
            reviewEn = "The emerald chronometer watch is even more breathtaking in person than in the pictures. Rose gold finishing looks ultra rich. 100% satisfied!",
            reviewUr = "زمردی گھڑی تصاویر سے کہیں زیادہ خوبصورت اور وزنی ہے۔ روز گولڈ کا کام بہت نفاست سے ہوا ہے۔ فیصل آباد میں وقت پر پارسل ملا۔"
        )
    )

    private val _testimonials = MutableStateFlow(defaultTestimonials)
    val testimonials: StateFlow<List<Testimonial>> = _testimonials.asStateFlow()

    fun addTestimonial(testimonial: Testimonial) {
        _testimonials.value = listOf(testimonial) + _testimonials.value
    }

    val defaultFaqs = listOf(
        FaqItem(
            id = "faq-1",
            questionEn = "How do I place an order via WhatsApp?",
            questionUr = "واٹس ایپ کے ذریعے آرڈر کیسے دیا جا سکتا ہے؟",
            answerEn = "Simply click the 'Order on WhatsApp' button on any product or tap the floating WhatsApp button. It will open WhatsApp with your chosen item details pre-filled. Just send your name, city, and delivery address to confirm!",
            answerUr = "کسی بھی پراڈکٹ پر موجود 'آرڈر آن واٹس ایپ' بٹن دبائیں۔ واٹس ایپ پر پراڈکٹ کی تفصیل خود بخود لکھی جائے گی۔ صرف اپنا نام، شہر اور پتہ بھیج کر آرڈر کنفرم کریں۔"
        ),
        FaqItem(
            id = "faq-2",
            questionEn = "Is Cash on Delivery (COD) available nationwide?",
            questionUr = "کیا پورے پاکستان میں کیش آن ڈلیوری (COD) دستیاب ہے؟",
            answerEn = "Yes! Cash on Delivery is available across 250+ cities and towns all over Pakistan including Lahore, Karachi, Islamabad, Rawalpindi, Peshawar, Quetta, Multan, Faisalabad, and rural areas.",
            answerUr = "جی ہاں! پورے پاکستان کے 250 سے زائد شہروں اور قصبات میں کیش آن ڈلیوری کی سہولت موجود ہے۔ آپ پارسل موصول ہونے پر کورئیر والے کو رقم ادا کرتے ہیں۔"
        ),
        FaqItem(
            id = "faq-3",
            questionEn = "How long does delivery take in Pakistan?",
            questionUr = "ڈلیوری کتنے دنوں میں پہنچتی ہے؟",
            answerEn = "Major cities (Lahore, Karachi, Islamabad, Rawalpindi) typically receive parcels within 24 to 48 hours. Other cities take 2 to 3 working days via TCS, Leopard, and Trax couriers with tracking SMS.",
            answerUr = "بڑے شہروں (لاہور، کراچی، اسلام آباد، راولپنڈی) میں 24 سے 48 گھنٹے، جبکہ دیگر تمام شہروں میں 2 سے 3 کاروباری دنوں میں پارسل پہنچ جاتا ہے۔ کوریئر ٹریکنگ ایس ایم ایس بھی دیا جاتا ہے۔"
        ),
        FaqItem(
            id = "faq-4",
            questionEn = "Can I open and inspect the parcel before paying?",
            questionUr = "کیا میں پیسے دینے سے پہلے پارسل کھول کر چیک کر سکتا ہوں؟",
            answerEn = "Yes, we ship with 'Open Parcel Allowed' packaging via our courier partners so you can verify your product with the courier rider before paying Cash on Delivery.",
            answerUr = "جی ہاں، ہمارے پارسلز پر اوپن پارسل الاؤڈ کی سہولت ہوتی ہے تاکہ آپ کورئیر رائیڈر کے سامنے پراڈکٹ کی تسلی کر کے رقم ادا کر سکیں۔"
        ),
        FaqItem(
            id = "faq-5",
            questionEn = "What is your return and exchange policy?",
            questionUr = "واپسی یا تبدیلی کی کیا پالیسی ہے؟",
            answerEn = "We offer a 7-Day No-Questions-Asked Return & Exchange Guarantee. If you are not 100% happy with your purchase or if there is any issue, WhatsApp us and our courier will pick it up from your doorstep.",
            answerUr = "ہمارے پاس 7 دن کی آسان اور بے جھجھک واپسی و تبدیلی کی پالیسی ہے۔ اگر آپ مطمئن نہ ہوں تو واٹس ایپ پر رابطہ کریں، ہم آپ کے گھر سے پارسل واپس لے لیں گے۔"
        ),
        FaqItem(
            id = "faq-6",
            questionEn = "Are your products 100% authentic and original?",
            questionUr = "کیا آپ کی تمام پراڈکٹس 100 فیصد اصل ہیں؟",
            answerEn = "Guaranteed 100% original. Every batch of fragrances and pure leather articles undergoes strict quality inspection. We provide money-back guarantee on authenticity.",
            answerUr = "100 فیصد اصل پراڈکٹ کی گارنٹی ہے۔ ہماری تمام اشیاء کوالٹی ٹیسٹ کے بعد پیک کی جاتی ہیں۔ اصل نہ ہونے کی صورت میں پوری رقم کی واپسی کی گارنٹی ہے۔"
        ),
        FaqItem(
            id = "faq-7",
            questionEn = "What are the delivery charges?",
            questionUr = "ڈلیوری چارجز کتنے ہیں؟",
            answerEn = "Standard delivery charges are Rs. 200 nationwide. Orders above Rs. 3,000 qualify for 100% FREE Delivery anywhere in Pakistan!",
            answerUr = "پورے پاکستان میں معیاری ڈلیوری چارجز 200 روپے ہیں۔ جبکہ 3,000 روپے سے زائد کے تمام آرڈرز پر ڈلیوری بالکل مفت ہے!"
        )
    )

    val pakistaniCities = listOf(
        "Lahore", "Karachi", "Islamabad", "Rawalpindi", "Faisalabad",
        "Multan", "Peshawar", "Quetta", "Gujranwala", "Sialkot",
        "Hyderabad", "Bahawalpur", "Sargodha", "Sukkur", "Abbottabad",
        "Sheikhupura", "Jhelum", "Gujrat", "Mardan", "Kasur",
        "Rahim Yar Khan", "Sahiwal", "Okara", "Wah Cantt", "Muzaffarabad"
    )

    private val _orders = MutableStateFlow<List<OrderSubmission>>(emptyList())
    val orders: StateFlow<List<OrderSubmission>> = _orders.asStateFlow()

    fun placeOrder(order: OrderSubmission): String {
        _orders.value = listOf(order) + _orders.value
        return order.orderId
    }

    fun generateOrderId(): String {
        val randomNum = (10000..99999).random()
        return "PK-$randomNum"
    }

    fun buildWhatsAppOrderUrl(
        phone: String,
        productName: String,
        pricePkr: Int,
        customerName: String = "",
        city: String = "",
        address: String = "",
        notes: String = ""
    ): String {
        val cleanPhone = phone.replace("+", "").replace("-", "").replace(" ", "").trim()
        val textBuilder = StringBuilder()
        textBuilder.append("Assalam-o-Alaikum! 🛍️\n")
        textBuilder.append("I would like to order from *${_businessConfig.value.businessNameEn}*:\n\n")
        textBuilder.append("📦 *Product:* $productName\n")
        textBuilder.append("💰 *Price:* Rs. $pricePkr (Cash on Delivery)\n")
        if (customerName.isNotBlank()) {
            textBuilder.append("👤 *Name:* $customerName\n")
        }
        if (city.isNotBlank()) {
            textBuilder.append("🏙️ *City:* $city\n")
        }
        if (address.isNotBlank()) {
            textBuilder.append("📍 *Address:* $address\n")
        }
        if (notes.isNotBlank()) {
            textBuilder.append("📝 *Notes:* $notes\n")
        }
        textBuilder.append("\nPlease confirm my order and share courier tracking details. JazakAllah!")

        val encoded = java.net.URLEncoder.encode(textBuilder.toString(), "UTF-8")
        return "https://wa.me/$cleanPhone?text=$encoded"
    }
}
