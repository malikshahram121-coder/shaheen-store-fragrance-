package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.model.AppLanguage
import com.example.model.BusinessConfig
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.GoldAccent

@Composable
fun StoreFooter(
    config: BusinessConfig,
    language: AppLanguage,
    onNavigateSection: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val isUrdu = language == AppLanguage.URDU
    var selectedPolicyTitle by remember { mutableStateOf<String?>(null) }
    var selectedPolicyContent by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(EmeraldDark)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo & Title
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingBag,
                    contentDescription = null,
                    tint = GoldAccent,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = if (isUrdu) config.businessNameUr else config.businessNameEn,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 18.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (isUrdu)
                "پاکستان کا بااعتماد برانڈ • خالص پراڈکٹس اور تیز ترین ترسیل"
            else
                "Pakistan's premier destination for pure leather, luxury fragrances & bespoke watches.",
            style = MaterialTheme.typography.bodySmall.copy(
                color = Color.White.copy(alpha = 0.75f),
                fontSize = 12.sp
            ),
            lineHeight = 17.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Supported Courier Badges
        Text(
            text = if (isUrdu) "ہمارے کوریئر پارٹنرز:" else "Official Delivery Partners:",
            style = MaterialTheme.typography.labelSmall.copy(
                color = GoldAccent,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp
            )
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            listOf("TCS Express", "Leopard Courier", "Trax Logistics", "PostEx").forEach { courier ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color.White.copy(alpha = 0.1f))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = courier,
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Payment Methods
        Text(
            text = if (isUrdu) "ادائیگی کے طریقے:" else "Accepted Payment Methods:",
            style = MaterialTheme.typography.labelSmall.copy(
                color = GoldAccent,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp
            )
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            listOf("Cash on Delivery (COD)", "JazzCash", "EasyPaisa", "Bank Transfer").forEach { payment ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color.White.copy(alpha = 0.1f))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = payment,
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Policy Links Row
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = if (isUrdu) "واپسی پالیسی" else "Return Policy",
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 12.sp,
                modifier = Modifier.clickable {
                    selectedPolicyTitle = if (isUrdu) "7 دن کی واپسی و تبدیلی پالیسی" else "7-Day Return & Exchange Policy"
                    selectedPolicyContent = if (isUrdu)
                        "اگر آپ پراڈکٹ سے مطمئن نہ ہوں تو 7 دنوں کے اندر ہمیں واٹس ایپ پر مطلع کریں۔ ہمارا کوریئر رائیڈر آپ کے گھر سے پارسل واپس حاصل کرے گا اور آپ کو رقم واپس یا نئی چیز مل جائے گی۔"
                    else
                        "We offer a 7-day hassle-free return and exchange guarantee across Pakistan. Contact our WhatsApp support with your Order ID, and our courier will arrange a doorstep pickup."
                }
            )

            Text(text = "•", color = Color.White.copy(alpha = 0.5f))

            Text(
                text = if (isUrdu) "ترسیل کی پالیسی" else "Shipping Terms",
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 12.sp,
                modifier = Modifier.clickable {
                    selectedPolicyTitle = if (isUrdu) "ترسیل اور ڈلیوری کی شرائط" else "Shipping & Delivery Terms"
                    selectedPolicyContent = if (isUrdu)
                        "پورے پاکستان میں آرڈرز 2 سے 3 کاروباری دنوں میں پہنچائے جاتے ہیں۔ 3000 روپے سے زائد کے آرڈرز پر مفت ڈلیوری فراہم کی جاتی ہے۔"
                    else
                        "Orders are dispatched within 24 hours via TCS and Leopard. Estimated delivery is 2-3 business days nationwide. Free delivery on orders over Rs. 3,000."
                }
            )

            Text(text = "•", color = Color.White.copy(alpha = 0.5f))

            Text(
                text = if (isUrdu) "پرائیویسی پالیسی" else "Privacy & Analytics",
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 12.sp,
                modifier = Modifier.clickable {
                    selectedPolicyTitle = if (isUrdu) "پرائیویسی اور اینالیٹکس نوٹس" else "Privacy & Tracking Policy"
                    selectedPolicyContent = if (isUrdu)
                        "آپ کا نام، فون نمبر اور پتہ صرف پارسل کی ترسیل کے لیے استعمال کیا جاتا ہے۔ فیس بک پکسل اور گوگل اینالیٹکس کو محفوظ اشتہارات کے لیے استعمال کیا جاتا ہے۔"
                    else
                        "Customer information is solely used for order fulfillment and courier tracking. Facebook Pixel and Google Analytics tags are pre-configured for campaign attribution."
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Copyright Notice
        Text(
            text = "© 2026 ${config.businessNameEn} Pakistan. All Rights Reserved.",
            color = Color.White.copy(alpha = 0.55f),
            fontSize = 11.sp
        )
        Text(
            text = "Made with pride in Pakistan 🇵🇰",
            color = GoldAccent,
            fontSize = 10.5.sp,
            fontWeight = FontWeight.SemiBold
        )
    }

    if (selectedPolicyTitle != null) {
        Dialog(onDismissRequest = { selectedPolicyTitle = null }) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = selectedPolicyTitle ?: "",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = EmeraldDark
                        )
                    )
                    Text(
                        text = selectedPolicyContent ?: "",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFF334155),
                            lineHeight = 20.sp
                        )
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = { selectedPolicyTitle = null }) {
                            Text(if (isUrdu) "بند کریں" else "Close", color = EmeraldPrimary, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}
