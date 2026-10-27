package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AppLanguage
import com.example.ui.theme.CardBorder
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.GoldAccent

data class BenefitItem(
    val icon: ImageVector,
    val titleEn: String,
    val titleUr: String,
    val descEn: String,
    val descUr: String
)

@Composable
fun WhyChooseUsSection(
    language: AppLanguage,
    modifier: Modifier = Modifier
) {
    val isUrdu = language == AppLanguage.URDU

    val benefits = listOf(
        BenefitItem(
            icon = Icons.Default.Paid,
            titleEn = "Nationwide COD in 250+ Cities",
            titleUr = "250+ شہروں میں کیش آن ڈلیوری",
            descEn = "Pay in cash when you receive your parcel at your doorstep. Zero online payment risk.",
            descUr = "اپنے گھر کی دہلیز پر پارسل موصول ہونے کے بعد نقد رقم ادا کریں۔ کوئی پیشگی ادائیگی نہیں۔"
        ),
        BenefitItem(
            icon = Icons.Default.AssignmentTurnedIn,
            titleEn = "Inspect Before Paying (Open Parcel)",
            titleUr = "اوپن پارسل چیک کرنے کی اجازت",
            descEn = "Verify and inspect your parcel with the courier rider before handing over cash.",
            descUr = "کورئیر رائیڈر کے سامنے پارسل کھول کر پراڈکٹ کی تصدیق اور تسلی کریں۔"
        ),
        BenefitItem(
            icon = Icons.Default.CheckCircle,
            titleEn = "100% Original Sourced Articles",
            titleUr = "100 فیصد اصل پراڈکٹ کی گارنٹی",
            descEn = "Strict quality inspection on every perfume batch and genuine full-grain leather piece.",
            descUr = "ہر عطر اور لیدر آرٹیکل کا سخت کوالٹی ٹیسٹ۔ غیر معیاری ہونے پر 100 فیصد رقم کی واپسی۔"
        ),
        BenefitItem(
            icon = Icons.Default.HeadsetMic,
            titleEn = "24/7 Dedicated WhatsApp Support",
            titleUr = "24 گھنٹے واٹس ایپ کسٹمر سروس",
            descEn = "Chat directly with our support team in English or Urdu for live pictures, sizing and tracking.",
            descUr = "لائیو تصاویر، آرڈر ٹریکنگ اور سائز کے لیے اردو یا انگریزی میں براہ راست رہنمائی پائیں۔"
        ),
        BenefitItem(
            icon = Icons.Default.Security,
            titleEn = "Tamper-Proof Luxury Packaging",
            titleUr = "محفوظ اور خوبصورت لگژری پیکنگ",
            descEn = "Double bubble-wrapped and packed in branded gift presentation boxes, ready for gifting.",
            descUr = "مضبوط ببل ریپ اور خوبصورت برانڈڈ گفٹ باکس میں پیک، تحفے کے لیے انتہائی موزوں۔"
        ),
        BenefitItem(
            icon = Icons.Default.LocalShipping,
            titleEn = "7-Day Doorstep Courier Pickup",
            titleUr = "7 دن میں گھر سے واپسی کی سہولت",
            descEn = "Unhappy with your purchase? Our courier partner will pick it up directly from your home.",
            descUr = "اگر آپ کو پراڈکٹ پسند نہ آئے تو ہمارے کوریئر نمائندے آپ کے گھر سے پارسل واپس لے لیں گے۔"
        )
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(20.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, CardBorder)
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = if (isUrdu) "ہمیں کیوں منتخب کریں؟" else "Why Choose Us?",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = EmeraldDark,
                            fontSize = 20.sp
                        )
                    )
                    Text(
                        text = if (isUrdu) "ہمارے ساتھ آن لائن خریداری 100 فیصد محفوظ اور قابلِ اعتبار ہے" else "The most trusted e-commerce shopping experience in Pakistan",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color(0xFF64748B),
                            fontSize = 12.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Benefit Items
            Column(
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                benefits.forEach { benefit ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFF8FAFC))
                            .border(1.dp, Color(0xFFF1F5F9), RoundedCornerShape(12.dp))
                            .padding(12.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color(0xFFE8F5E9)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = benefit.icon,
                                contentDescription = null,
                                tint = EmeraldPrimary,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (isUrdu) benefit.titleUr else benefit.titleEn,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF0F172A),
                                    fontSize = 13.sp
                                )
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = if (isUrdu) benefit.descUr else benefit.descEn,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = Color(0xFF64748B),
                                    fontSize = 11.5.sp,
                                    lineHeight = 17.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
