package com.example.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.model.AppLanguage
import com.example.model.BusinessConfig
import com.example.ui.theme.CardBorder
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldPrimary

@Composable
fun BusinessCustomizerDialog(
    currentConfig: BusinessConfig,
    language: AppLanguage,
    onDismiss: () -> Unit,
    onSave: (BusinessConfig) -> Unit
) {
    val isUrdu = language == AppLanguage.URDU

    var businessNameEn by remember { mutableStateOf(currentConfig.businessNameEn) }
    var businessNameUr by remember { mutableStateOf(currentConfig.businessNameUr) }
    var taglineEn by remember { mutableStateOf(currentConfig.taglineEn) }
    var taglineUr by remember { mutableStateOf(currentConfig.taglineUr) }
    var whatsappNumber by remember { mutableStateOf(currentConfig.whatsappNumber) }
    var whatsappDisplay by remember { mutableStateOf(currentConfig.whatsappDisplay) }
    var city by remember { mutableStateOf(currentConfig.city) }
    var addressEn by remember { mutableStateOf(currentConfig.addressEn) }
    var addressUr by remember { mutableStateOf(currentConfig.addressUr) }
    var deliveryFee by remember { mutableStateOf(currentConfig.deliveryFeePkr.toString()) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Header
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Business,
                        contentDescription = null,
                        tint = EmeraldPrimary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isUrdu) "کاروباری معلومات میں تبدیلی کریں" else "Customize Business & Store",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = EmeraldDark,
                            fontSize = 17.sp
                        )
                    )
                }

                Text(
                    text = if (isUrdu)
                        "اپنے بزنس کا نام، واٹس ایپ نمبر، شہر اور پتہ تبدیل کریں تاکہ ایپ آپ کے برانڈ کے مطابق ہو جائے۔"
                    else
                        "Edit your Business Name, WhatsApp number, and address to personalize this app for your company.",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFF64748B), fontSize = 11.5.sp)
                )

                // Business Name (English)
                OutlinedTextField(
                    value = businessNameEn,
                    onValueChange = { businessNameEn = it },
                    label = { Text("Business Name (English)") },
                    modifier = Modifier.fillMaxWidth().testTag("edit_biz_name_en"),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = EmeraldPrimary, unfocusedBorderColor = CardBorder)
                )

                // Business Name (Urdu)
                OutlinedTextField(
                    value = businessNameUr,
                    onValueChange = { businessNameUr = it },
                    label = { Text("کاروبار کا نام (Urdu)") },
                    modifier = Modifier.fillMaxWidth().testTag("edit_biz_name_ur"),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = EmeraldPrimary, unfocusedBorderColor = CardBorder)
                )

                // Tagline
                OutlinedTextField(
                    value = taglineEn,
                    onValueChange = { taglineEn = it },
                    label = { Text("Tagline / Headline (English)") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = EmeraldPrimary, unfocusedBorderColor = CardBorder)
                )

                // WhatsApp Phone
                OutlinedTextField(
                    value = whatsappNumber,
                    onValueChange = { whatsappNumber = it },
                    label = { Text("WhatsApp Number (Intl format, e.g. 923001234567)") },
                    modifier = Modifier.fillMaxWidth().testTag("edit_biz_whatsapp"),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = EmeraldPrimary, unfocusedBorderColor = CardBorder)
                )

                // WhatsApp Display
                OutlinedTextField(
                    value = whatsappDisplay,
                    onValueChange = { whatsappDisplay = it },
                    label = { Text("Display Phone (e.g. 0300-1234567)") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = EmeraldPrimary, unfocusedBorderColor = CardBorder)
                )

                // City
                OutlinedTextField(
                    value = city,
                    onValueChange = { city = it },
                    label = { Text("Primary City (e.g. Lahore, Karachi)") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = EmeraldPrimary, unfocusedBorderColor = CardBorder)
                )

                // Delivery Fee
                OutlinedTextField(
                    value = deliveryFee,
                    onValueChange = { deliveryFee = it },
                    label = { Text("Nationwide Delivery Fee (PKR)") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = EmeraldPrimary, unfocusedBorderColor = CardBorder)
                )

                // Address
                OutlinedTextField(
                    value = addressEn,
                    onValueChange = { addressEn = it },
                    label = { Text("Address (English)") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    minLines = 2,
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = EmeraldPrimary, unfocusedBorderColor = CardBorder)
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Actions
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text(if (isUrdu) "منسوخ" else "Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            val feeInt = deliveryFee.toIntOrNull() ?: currentConfig.deliveryFeePkr
                            val updated = currentConfig.copy(
                                businessNameEn = businessNameEn.ifBlank { currentConfig.businessNameEn },
                                businessNameUr = businessNameUr.ifBlank { currentConfig.businessNameUr },
                                taglineEn = taglineEn.ifBlank { currentConfig.taglineEn },
                                whatsappNumber = whatsappNumber.ifBlank { currentConfig.whatsappNumber },
                                whatsappDisplay = whatsappDisplay.ifBlank { currentConfig.whatsappDisplay },
                                city = city.ifBlank { currentConfig.city },
                                addressEn = addressEn.ifBlank { currentConfig.addressEn },
                                deliveryFeePkr = feeInt
                            )
                            onSave(updated)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(if (isUrdu) "محفوظ کریں" else "Save Changes")
                    }
                }
            }
        }
    }
}
