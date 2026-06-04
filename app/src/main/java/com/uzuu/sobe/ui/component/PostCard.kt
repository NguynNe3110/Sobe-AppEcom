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
import com.example.sobe.ui.theme.AppTextStyles
import com.example.ui.theme.AppBrush
import com.example.ui.theme.AppColor
import com.uzuu.sobe.R
import com.uzuu.sobe.domain.model.CategoryItem
import com.uzuu.sobe.domain.model.PostItem
import com.uzuu.sobe.domain.model.ProductItem
import com.uzuu.sobe.domain.model.init.listBanners
import com.uzuu.sobe.domain.model.init.listCategories
import com.uzuu.sobe.domain.model.init.listProducts
import com.uzuu.sobe.ui.component.OutlinedBrushText
import com.uzuu.sobe.ui.component.ProductCard
import com.uzuu.sobe.ui.theme.AppDimens

@Composable
fun PostCard(
    post: PostItem,
    onLikeClick: () -> Unit,
    onPostClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = onPostClick
    ) {
        Column {
            // Image Section
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = post.image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )

                // Image count indicator
                if (post.imageCount > 1) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                            .background(
                                Color.Black.copy(alpha = 0.6f),
                                RoundedCornerShape(4.dp)
                            )
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "1/${post.imageCount}",
                            color = Color.White,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }

            // Content Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                // Title
                Text(
                    text = post.title,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Author and Like Section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Author Info
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = post.authorAvatar),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = post.authorName,
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.Gray
                        )
                    }

                    // Like Button
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable(onClick = onLikeClick)
                    ) {
                        Icon(
                            painter = painterResource(
                                if (post.isLiked) {
                                    R.drawable.ic_heart_filled
                                } else {
                                    R.drawable.ic_heart_outline
                                }
                            ),
                            contentDescription = "Like",
                            tint = if (post.isLiked) Color.Red else Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = post.likesCount.toString(),
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}