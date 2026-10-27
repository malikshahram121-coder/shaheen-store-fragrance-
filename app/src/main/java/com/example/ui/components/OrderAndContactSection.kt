package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocalMall
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.StoreRepository
import com.example.model.AppLanguage
import com.example.model.BusinessConfig
import com.example.model.OrderSubmission
import com.example.model.Product
import com.example.ui.theme.CardBorder
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.WhatsAppGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderAndContactSection(
    config: BusinessConfig,
    products: List<Product>,
    selectedProduct: Product?,
    language: AppLanguage,
    onOrderPlaced: (OrderSubmission, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val isUrdu = language == AppLanguage.URDU

    // Order Form States
    var customerName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("Lahore") }
    var address by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var quantity by remember { mutableIntStateOf(1) }
    var currentProduct by remember(selectedProduct, products) {
        mutableStateOf(selectedProduct ?: products.firstOrNull())
    }
    var cityDropdownExpanded by remember { mutableStateOf(false) }
    var productDropdownExpanded by remember { mutableStateOf(false) }
    var formError by remember { mutableStateOf<String?>(null) }

    // Pricing calculation
    val unitPrice = currentProduct?.pricePkr ?: 0
    val itemsSubtotal = unitPrice * quantity
    val deliveryFee = if (itemsSubtotal >= config.freeDeliveryAbovePkr || itemsSubtotal == 0) 0 else config.deliveryFeePkr
    val totalAmount = itemsSubtotal + deliveryFee

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. CASH ON DELIVERY ORDER FORM CARD
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("cod_order_form_card"),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, CardBorder)
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Form Header
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = if (isUrdu) "فوری کیش آن ڈلیوری آرڈر فارم" else "Instant Cash on Delivery Order",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = EmeraldDark,
                                fontSize = 19.sp
                            )
                        )
                    }
                    Text(
                        text = if (isUrdu)
                            "نام اور پتہ درج کریں، پارسل موصول ہونے پر ادائیگی کریں۔"
                        else
                            "Fill details below. Pay in cash when rider delivers to your doorstep.",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color(0xFF64748B),
                            fontSize = 12.sp
                        )
                    )
                }

                // Product Selection Dropdown
                ExposedDropdownMenuBox(
                    expanded = productDropdownExpanded,
                    onExpandedChange = { productDropdownExpanded = it },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = if (isUrdu) currentProduct?.nameUr ?: "" else currentProduct?.nameEn ?: "Select Product",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(if (isUrdu) "منتخب پراڈکٹ" else "Selected Item") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = productDropdownExpanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = EmeraldPrimary,
                            unfocusedBorderColor = CardBorder
                        )
                    )
                    ExposedDropdownMenu(
                        expanded = productDropdownExpanded,
                        onDismissRequest = { productDropdownExpanded = false }
                    ) {
                        products.forEach { prod ->
                            DropdownMenuItem(
                                text = {
                                    Column {
                                        Text(
                                            text = if (isUrdu) prod.nameUr else prod.nameEn,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 13.sp
                                        )
                                        Text(
                                            text = "Rs. ${prod.pricePkr}",
                                            color = EmeraldPrimary,
                                            fontSize = 12.sp
                                        )
                                    }
                                },
                                onClick = {
                                    currentProduct = prod
                                    productDropdownExpanded = false
                                }
                            )
                        }
                    }
                }

                // Quantity Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (isUrdu) "تعداد (Quantity):" else "Quantity:",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFF1F5F9))
                            .padding(4.dp)
                    ) {
                        IconButton(
                            onClick = { if (quantity > 1) quantity-- },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(Icons.Default.Remove, contentDescription = "Decrease", modifier = Modifier.size(16.dp))
                        }
                        Text(
                            text = "$quantity",
                            modifier = Modifier.padding(horizontal = 14.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        IconButton(
                            onClick = { if (quantity < 10) quantity++ },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(Icons.Default.Add, contentDescription = "Increase", modifier = Modifier.size(16.dp))
                        }
                    }
                }

                // Full Name
                OutlinedTextField(
                    value = customerName,
                    onValueChange = { customerName = it },
                    label = { Text(if (isUrdu) "آپ کا مکمل نام" else "Full Name") },
                    placeholder = { Text(if (isUrdu) "مثلاً محمد احمد" else "e.g. Muhammad Ahmed") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("order_name_input"),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = EmeraldPrimary,
                        unfocusedBorderColor = CardBorder
                    )
                )

                // Phone Number with Pakistani format
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text(if (isUrdu) "موبائل فون نمبر (03XX-XXXXXXX)" else "Mobile Phone (03XX-XXXXXXX)") },
                    placeholder = { Text("0300-1234567") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("order_phone_input"),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = EmeraldPrimary,
                        unfocusedBorderColor = CardBorder
                    )
                )

                // City Selector
                ExposedDropdownMenuBox(
                    expanded = cityDropdownExpanded,
                    onExpandedChange = { cityDropdownExpanded = it },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = city,
                        onValueChange = { city = it },
                        label = { Text(if (isUrdu) "شہر (City in Pakistan)" else "City in Pakistan") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = cityDropdownExpanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                            .testTag("order_city_input"),
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = EmeraldPrimary,
                            unfocusedBorderColor = CardBorder
                        )
                    )
                    ExposedDropdownMenu(
                        expanded = cityDropdownExpanded,
                        onDismissRequest = { cityDropdownExpanded = false }
                    ) {
                        StoreRepository.pakistaniCities.forEach { cityName ->
                            DropdownMenuItem(
                                text = { Text(cityName) },
                                onClick = {
                                    city = cityName
                                    cityDropdownExpanded = false
                                }
                            )
                        }
                    }
                }

                // Delivery Address
                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text(if (isUrdu) "مکمل پتہ (مکان نمبر، گلی، علاقہ)" else "Delivery Address (House #, Street, Area)") },
                    placeholder = { Text(if (isUrdu) "مکان 12، گلی 4، ماڈل ٹاؤن، لاہور" else "House 12, Street 4, Gulberg, Lahore") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("order_address_input"),
                    shape = RoundedCornerShape(10.dp),
                    minLines = 2,
                    maxLines = 3,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = EmeraldPrimary,
                        unfocusedBorderColor = CardBorder
                    )
                )

                // Order Notes / Delivery Instructions
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text(if (isUrdu) "خصوصی ہدایات (اختیاری)" else "Delivery Instructions (Optional)") },
                    placeholder = { Text(if (isUrdu) "پارسل شام 5 بجے کے بعد دیں" else "Deliver after 5 PM") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = EmeraldPrimary,
                        unfocusedBorderColor = CardBorder
                    )
                )

                // Price Summary Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFF8FAFC))
                        .border(1.dp, CardBorder, RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = if (isUrdu) "قیمت سامان ($quantity آئٹم):" else "Items Subtotal ($quantity item):",
                                style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFF64748B))
                            )
                            Text(
                                text = "Rs. ${formatPrice(itemsSubtotal)}",
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = if (isUrdu) "ڈلیوری چارجز (پورے پاکستان میں):" else "Delivery Charges (Nationwide):",
                                style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFF64748B))
                            )
                            Text(
                                text = if (deliveryFee == 0) (if (isUrdu) "مفت (FREE)" else "FREE") else "Rs. $deliveryFee",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (deliveryFee == 0) EmeraldPrimary else Color(0xFF0F172A)
                                )
                            )
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(CardBorder)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (isUrdu) "کل رقم (کیش آن ڈلیوری):" else "Total Payable (COD):",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = EmeraldDark
                                )
                            )
                            Text(
                                text = "Rs. ${formatPrice(totalAmount)}",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = EmeraldPrimary,
                                    fontSize = 20.sp
                                )
                            )
                        }
                    }
                }

                if (formError != null) {
                    Text(
                        text = formError ?: "",
                        color = Color(0xFFDC2626),
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                    )
                }

                // Dual Submit Buttons
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Button 1: Place Direct COD Order
                    Button(
                        onClick = {
                            if (customerName.isBlank() || phoneNumber.isBlank() || address.isBlank() || currentProduct == null) {
                                formError = if (isUrdu) "براہ کرم نام، موبائل نمبر اور پتہ درج کریں" else "Please enter your name, phone number, and address"
                            } else {
                                formError = null
                                val order = OrderSubmission(
                                    orderId = StoreRepository.generateOrderId(),
                                    customerName = customerName.trim(),
                                    phoneNumber = phoneNumber.trim(),
                                    city = city.trim(),
                                    fullAddress = address.trim(),
                                    productId = currentProduct?.id ?: "",
                                    productName = if (isUrdu) currentProduct?.nameUr ?: "" else currentProduct?.nameEn ?: "",
                                    quantity = quantity,
                                    totalAmountPkr = totalAmount,
                                    notes = notes.trim()
                                )
                                onOrderPlaced(order, false)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("submit_cod_order_button"),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = EmeraldPrimary,
                            contentColor = Color.White
                        )
                    ) {
                        Icon(Icons.Default.LocalMall, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isUrdu) "کیش آن ڈلیوری آرڈر کنفرم کریں" else "Place Cash on Delivery Order",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }

                    // Button 2: Confirm Order via WhatsApp
                    Button(
                        onClick = {
                            if (customerName.isBlank() || phoneNumber.isBlank() || address.isBlank() || currentProduct == null) {
                                formError = if (isUrdu) "براہ کرم نام، موبائل نمبر اور پتہ درج کریں" else "Please enter your name, phone number, and address"
                            } else {
                                formError = null
                                val order = OrderSubmission(
                                    orderId = StoreRepository.generateOrderId(),
                                    customerName = customerName.trim(),
                                    phoneNumber = phoneNumber.trim(),
                                    city = city.trim(),
                                    fullAddress = address.trim(),
                                    productId = currentProduct?.id ?: "",
                                    productName = if (isUrdu) currentProduct?.nameUr ?: "" else currentProduct?.nameEn ?: "",
                                    quantity = quantity,
                                    totalAmountPkr = totalAmount,
                                    notes = notes.trim()
                                )
                                onOrderPlaced(order, true)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("submit_whatsapp_order_button"),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = WhatsAppGreen,
                            contentColor = Color.White
                        )
                    ) {
                        Icon(Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isUrdu) "واٹس ایپ پر آرڈر بھیجیں" else "Confirm & Send via WhatsApp",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }

        // 2. CONTACT US & STORE LOCATION DETAILS CARD
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, CardBorder)
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = if (isUrdu) "رابطہ اور دکان کا پتہ" else "Contact & Store Location",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = EmeraldDark,
                        fontSize = 18.sp
                    )
                )

                // Phone & WhatsApp action tiles
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Call Tile
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFF1F5F9))
                            .clickable {
                                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${config.whatsappNumber}"))
                                context.startActivity(intent)
                            }
                            .padding(12.dp)
                    ) {
                        Column {
                            Icon(Icons.Default.Call, contentDescription = "Call", tint = EmeraldPrimary, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = if (isUrdu) "کال کریں" else "Call Us",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = Color(0xFF0F172A)
                            )
                            Text(
                                text = config.phoneDisplay,
                                fontSize = 11.sp,
                                color = Color(0xFF64748B)
                            )
                        }
                    }

                    // WhatsApp Tile
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFDCFCE7))
                            .clickable {
                                val url = "https://wa.me/${config.whatsappNumber}?text=Assalam-o-Alaikum!%20I%20have%20an%20inquiry."
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                context.startActivity(intent)
                            }
                            .padding(12.dp)
                    ) {
                        Column {
                            Icon(Icons.Default.Chat, contentDescription = "WhatsApp", tint = WhatsAppGreen, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = if (isUrdu) "واٹس ایپ چیٹ" else "WhatsApp Chat",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = Color(0xFF0F172A)
                            )
                            Text(
                                text = config.whatsappDisplay,
                                fontSize = 11.sp,
                                color = Color(0xFF15803D)
                            )
                        }
                    }
                }

                // Physical Address
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFF8FAFC))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFE8F5E9)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.LocationOn, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(18.dp))
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (isUrdu) "دکان کا پتہ:" else "Physical Address:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = Color(0xFF0F172A)
                        )
                        Text(
                            text = if (isUrdu) config.addressUr else config.addressEn,
                            fontSize = 11.sp,
                            color = Color(0xFF64748B),
                            lineHeight = 16.sp
                        )
                    }
                }

                // Google Map Action Button
                Button(
                    onClick = {
                        val geoUri = "geo:31.5204,74.3587?q=Gulberg+III+Lahore+Pakistan"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
                        context.startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0284C7))
                ) {
                    Icon(Icons.Default.Map, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isUrdu) "گوگل میپ پر لوکیشن دیکھیں" else "Open in Google Maps",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

private fun formatPrice(amount: Int): String {
    return java.text.NumberFormat.getIntegerInstance().format(amount)
}
