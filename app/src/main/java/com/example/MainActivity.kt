package com.example

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.example.data.StoreRepository
import com.example.model.AppLanguage
import com.example.model.OrderSubmission
import com.example.model.Product
import com.example.ui.components.BusinessCustomizerDialog
import com.example.ui.components.FaqSection
import com.example.ui.components.FloatingWhatsAppButton
import com.example.ui.components.HeroSection
import com.example.ui.components.OrderAndContactSection
import com.example.ui.components.OrderSuccessDialog
import com.example.ui.components.ProductDetailDialog
import com.example.ui.components.ProductSection
import com.example.ui.components.ReviewsSection
import com.example.ui.components.StoreFooter
import com.example.ui.components.StoreHeader
import com.example.ui.components.TrustBar
import com.example.ui.components.WhyChooseUsSection
import com.example.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                StoreApp()
            }
        }
    }
}

@Composable
fun StoreApp() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()

    // Observe Repository State
    val language by StoreRepository.language.collectAsState()
    val config by StoreRepository.businessConfig.collectAsState()
    val products by StoreRepository.products.collectAsState()
    val testimonials by StoreRepository.testimonials.collectAsState()
    val categories = StoreRepository.categories
    val faqs = StoreRepository.defaultFaqs

    // Interactive Dialog States
    var productForDetail by remember { mutableStateOf<Product?>(null) }
    var selectedProductForOrder by remember { mutableStateOf<Product?>(null) }
    var showCustomizer by remember { mutableStateOf(false) }
    var completedOrder by remember { mutableStateOf<OrderSubmission?>(null) }

    val layoutDirection = if (language == AppLanguage.URDU) LayoutDirection.Rtl else LayoutDirection.Ltr

    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                StoreHeader(
                    config = config,
                    language = language,
                    onLanguageToggle = { StoreRepository.toggleLanguage() },
                    onOpenCustomizer = { showCustomizer = true },
                    onNavigateSection = { section ->
                        coroutineScope.launch {
                            val targetIndex = when (section) {
                                "home" -> 0
                                "products" -> 2
                                "why_us" -> 3
                                "reviews" -> 4
                                "faq" -> 5
                                "order" -> 6
                                "contact" -> 6
                                else -> 0
                            }
                            lazyListState.animateScrollToItem(targetIndex)
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingWhatsAppButton(config = config, language = language)
            }
        ) { innerPadding ->
            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color(0xFFF8F9FA))
            ) {
                // 1. Hero Section
                item(key = "hero") {
                    HeroSection(
                        config = config,
                        language = language,
                        onShopNowClick = {
                            coroutineScope.launch {
                                lazyListState.animateScrollToItem(2)
                            }
                        }
                    )
                }

                // 2. Trust Bar
                item(key = "trust_bar") {
                    TrustBar(language = language)
                }

                // 3. Products Grid Section
                item(key = "products") {
                    ProductSection(
                        products = products,
                        categories = categories,
                        config = config,
                        language = language,
                        onProductClick = { product ->
                            productForDetail = product
                        },
                        onQuickOrderClick = { product ->
                            selectedProductForOrder = product
                            coroutineScope.launch {
                                lazyListState.animateScrollToItem(6)
                            }
                        }
                    )
                }

                // 4. Why Choose Us Section
                item(key = "why_choose_us") {
                    WhyChooseUsSection(language = language)
                }

                // 5. Customer Reviews Section
                item(key = "reviews") {
                    ReviewsSection(testimonials = testimonials, language = language)
                }

                // 6. FAQ Accordion Section
                item(key = "faq") {
                    FaqSection(faqs = faqs, language = language)
                }

                // 7. Instant COD Order & Contact Details Section
                item(key = "order_and_contact") {
                    OrderAndContactSection(
                        config = config,
                        products = products,
                        selectedProduct = selectedProductForOrder,
                        language = language,
                        onOrderPlaced = { newOrder, openWhatsAppDirectly ->
                            StoreRepository.placeOrder(newOrder)
                            completedOrder = newOrder
                            if (openWhatsAppDirectly) {
                                val url = StoreRepository.buildWhatsAppOrderUrl(
                                    phone = config.whatsappNumber,
                                    productName = "${newOrder.productName} (Order #${newOrder.orderId})",
                                    pricePkr = newOrder.totalAmountPkr,
                                    customerName = newOrder.customerName,
                                    city = newOrder.city,
                                    address = newOrder.fullAddress,
                                    notes = newOrder.notes
                                )
                                try {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    context.startActivity(intent)
                                } catch (_: Exception) {
                                }
                            }
                        }
                    )
                }

                // 8. Footer
                item(key = "footer") {
                    StoreFooter(
                        config = config,
                        language = language,
                        onNavigateSection = { section ->
                            coroutineScope.launch {
                                val index = if (section == "home") 0 else 2
                                lazyListState.animateScrollToItem(index)
                            }
                        }
                    )
                }
            }
        }
    }

    // Product Detail Dialog
    productForDetail?.let { prod ->
        ProductDetailDialog(
            product = prod,
            config = config,
            language = language,
            onDismiss = { productForDetail = null },
            onOrderNow = { selectedProd ->
                selectedProductForOrder = selectedProd
                productForDetail = null
                coroutineScope.launch {
                    lazyListState.animateScrollToItem(6)
                }
            }
        )
    }

    // Business Customizer Dialog
    if (showCustomizer) {
        BusinessCustomizerDialog(
            currentConfig = config,
            language = language,
            onDismiss = { showCustomizer = false },
            onSave = { updated ->
                StoreRepository.updateBusinessConfig(updated)
                showCustomizer = false
            }
        )
    }

    // Order Success Dialog
    completedOrder?.let { order ->
        OrderSuccessDialog(
            order = order,
            config = config,
            language = language,
            onDismiss = { completedOrder = null }
        )
    }
}
