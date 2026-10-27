package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.StoreRepository
import com.example.model.OrderSubmission
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Shaheen Store", appName)
  }

  @Test
  fun `test place order and whatsapp url generation`() {
    val orderId = StoreRepository.generateOrderId()
    assertTrue(orderId.startsWith("PK-"))

    val testOrder = OrderSubmission(
      orderId = orderId,
      customerName = "Ali Raza",
      phoneNumber = "03001234567",
      city = "Lahore",
      fullAddress = "Gulberg III",
      productId = "prod-1",
      productName = "Royal Arabian Oud",
      quantity = 1,
      totalAmountPkr = 3499
    )
    val placedId = StoreRepository.placeOrder(testOrder)
    assertEquals(orderId, placedId)

    val url = StoreRepository.buildWhatsAppOrderUrl(
      phone = "923001234567",
      productName = "Royal Arabian Oud",
      pricePkr = 3499,
      customerName = "Ali Raza",
      city = "Lahore",
      address = "Gulberg III"
    )
    assertTrue(url.contains("wa.me/923001234567"))
    assertTrue(url.contains("Ali+Raza") || url.contains("Ali%20Raza"))
  }
}
