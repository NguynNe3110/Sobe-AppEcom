package com.uzuu.sobe.ui.component


import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sobe.ui.theme.AppTextStyles
import com.example.ui.theme.AppBrush
import com.example.ui.theme.AppColor
import com.uzuu.sobe.R
import com.uzuu.sobe.domain.model.CategoryItem
import com.uzuu.sobe.domain.model.ChatItem
import com.uzuu.sobe.domain.model.ProductItem
import com.uzuu.sobe.domain.model.init.listBanners
import com.uzuu.sobe.domain.model.init.listCategories
import com.uzuu.sobe.domain.model.init.listProducts
import com.uzuu.sobe.ui.component.OutlinedBrushText
import com.uzuu.sobe.ui.component.ProductCard
import com.uzuu.sobe.ui.theme.AppDimens

@Composable
fun ChatListItem(
    chatItem: ChatItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar
        Image(
            painter = painterResource(id = chatItem.userAvatar),
            contentDescription = chatItem.userName,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Content
        Column(
            modifier = Modifier.weight(1f)
        ) {
            // Name and Time
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = chatItem.userName,
                    style = if (chatItem.isReplied) AppTextStyles.Heading3 else AppTextStyles.InputText,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = chatItem.time,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Message with red dot indicator
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Red dot for unread/not replied
                if (!chatItem.isReplied) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(AppColor.Secondary, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }

                Text(
                    text = chatItem.message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (chatItem.isReplied) Color.Gray else Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

    // Divider
    Divider(
        modifier = Modifier.padding(start = 78.dp),
        color = Color.LightGray.copy(alpha = 0.3f)
    )
}

@Preview
@Composable
fun PreChatListItem() {
    ChatListItem(
        chatItem = ChatItem(
            id = "1",
            userName = "SOBE",
            userAvatar = R.drawable.img_product, // Thay bằng drawable của bạn
            message = "Em chắc chắn sẽ tham gia ạ",
            time = "Hôm nay",
            isReplied = false
        )
    )
}
