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
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Verified
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

data class TrustItem(
    val icon: ImageVector,
    val titleEn: String,
    val titleUr: String,
    val descEn: String,
    val descUr: String
)

@Composable
fun TrustBar(
    language: AppLanguage,
    modifier: Modifier = Modifier
) {
    val isUrdu = language == AppLanguage.URDU

    val items = listOf(
        TrustItem(
            icon = Icons.Default.Payments,
            titleEn = "Cash on Delivery",
            titleUr = "کیش آن ڈلیوری",
            descEn = "Pay when parcel arrives",
            descUr = "پارسل ملنے پر رقم دیں"
        ),
        TrustItem(
            icon = Icons.Default.LocalShipping,
            titleEn = "Fast Delivery",
            titleUr = "تیز رفتار ڈلیوری",
            descEn = "2-3 Days via TCS & Leopard",
            descUr = "2 سے 3 دنوں میں ترسیل"
        ),
        TrustItem(
            icon = Icons.Default.Verified,
            titleEn = "100% Original",
            titleUr = "100% اصل پراڈکٹ",
            descEn = "Authenticity Guaranteed",
            descUr = "تسلی بخش خالص معیار"
        ),
        TrustItem(
            icon = Icons.Default.Autorenew,
            titleEn = "7-Day Easy Returns",
            titleUr = "آسان واپسی و تبدیلی",
            descEn = "Hassle-free exchange",
            descUr = "گھر سے پارسل واپسی کی سہولت"
        )
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, CardBorder)
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (isUrdu) "ہمارا اعتماد، آپ کا اطمینان" else "Shop With 100% Peace of Mind",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = EmeraldDark,
                        fontSize = 13.sp
                    )
                )
                Text(
                    text = if (isUrdu) "پاکستان بھر میں" else "Nationwide Service",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = GoldAccent,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                )
            }

            // Grid of 2x2
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TrustCard(item = items[0], isUrdu = isUrdu, modifier = Modifier.weight(1f))
                TrustCard(item = items[1], isUrdu = isUrdu, modifier = Modifier.weight(1f))
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TrustCard(item = items[2], isUrdu = isUrdu, modifier = Modifier.weight(1f))
                TrustCard(item = items[3], isUrdu = isUrdu, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun TrustCard(
    item: TrustItem,
    isUrdu: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF8FAFC))
            .border(1.dp, Color(0xFFF1F5F9), RoundedCornerShape(12.dp))
            .padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE8F5E9)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    tint = EmeraldPrimary,
                    modifier = Modifier.size(18.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = if (isUrdu) item.titleUr else item.titleEn,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F172A),
                        fontSize = 12.sp
                    ),
                    maxLines = 1
                )
                Text(
                    text = if (isUrdu) item.descUr else item.descEn,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = Color(0xFF64748B),
                        fontSize = 10.sp
                    ),
                    maxLines = 1
                )
            }
        }
    }
}
