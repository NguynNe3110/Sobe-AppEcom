package com.uzuu.sobe.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AppColor
import com.uzuu.sobe.R
import com.uzuu.sobe.domain.model.ProductItem
import com.uzuu.sobe.ui.theme.AppDimens

@Composable
fun ProductCard(
    product: ProductItem,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
    ) {
        // Product Image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(Color(0xFFF5F5F5))
        ) {
            Image(
                modifier = Modifier.fillMaxSize()
                    .clip(RoundedCornerShape(AppDimens.Corner.cornerRadiusSmall)),
                painter = painterResource(id = product.image),
                contentDescription = product.name,
                contentScale = ContentScale.Crop
            )

            // Heart/Wishlist button
            Icon(
                painter = painterResource(id = R.drawable.ic_heart_outline),
                contentDescription = "Wishlist",
                tint = Color.Unspecified,
                modifier = Modifier
                    .padding(8.dp)
                    .size(24.dp)
                    .align(Alignment.TopEnd)
                    .background(Color.White.copy(alpha = 0.7f), CircleShape)
                    .padding(4.dp)
            )
        }

        // Product Info
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = product.name,
                fontSize = 12.sp,
                color = AppColor.neutral300,
                maxLines = 2,
                minLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = product.price,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = AppColor.Secondary
            )

            Spacer(Modifier.height(6.dp))

            // Seller Info
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = product.sellerAvatar),
                    contentDescription = product.sellerName,
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(Modifier.width(4.dp))

                Text(
                    text = product.sellerName,
                    fontSize = 10.sp,
                    color = Color.Gray,
                    maxLines = 1
                )
            }
        }
    }
}