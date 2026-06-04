package com.uzuu.sobe.domain.model.init

import com.uzuu.sobe.R
import com.uzuu.sobe.domain.model.PostItem


val listPosts = listOf(
    PostItem(
        id = "1",
        title = "Lorem ipsum dolor sit amet consectetur. Vestibulum...",
        image = R.drawable.img_post,
        authorName = "thuytien21",
        authorAvatar = R.drawable.img_product,
        likesCount = 99,
        isLiked = false,
        imageCount = 1
    ),
    PostItem(
        id = "2",
        title = "Lorem ipsum dolor sit amet consectetur. Vestibulum...",
        image = R.drawable.img_post,
        authorName = "quynhanh11",
        authorAvatar = R.drawable.img_product,
        likesCount = 99,
        isLiked = true,
        imageCount = 9
    ),
    PostItem(
        id = "3",
        title = "• KIOSK GIVEAWAY •",
        image = R.drawable.img_post,
        authorName = "kiosk.official",
        authorAvatar = R.drawable.img_product,
        likesCount = 99,
        isLiked = false,
        imageCount = 4
    ),
    PostItem(
        id = "4",
        title = "4 tips giúp bạn Ăn mặc trông có \"gu\" hơn",
        image = R.drawable.img_post,
        authorName = "jodie",
        authorAvatar = R.drawable.img_product,
        likesCount = 99,
        isLiked = false,
        imageCount = 1
    ),
    PostItem(
        id = "5",
        title = "Outfit đi chơi cuối tuần cực xinh",
        image = R.drawable.img_post,
        authorName = "fashion_lover",
        authorAvatar = R.drawable.img_product,
        likesCount = 128,
        isLiked = true,
        imageCount = 3
    ),
    PostItem(
        id = "6",
        title = "Cách phối đồ đơn giản mà vẫn đẹp",
        image = R.drawable.img_post,
        authorName = "style_daily",
        authorAvatar = R.drawable.img_product,
        likesCount = 256,
        isLiked = false,
        imageCount = 1
    )
)
