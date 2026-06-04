package com.uzuu.sobe.domain.model.init

import com.uzuu.sobe.R
import com.uzuu.sobe.domain.model.ChatItem

val chatList = listOf(
    ChatItem(
        id = "1",
        userName = "SOBE",
        userAvatar = R.drawable.img_product, // Thay bằng drawable của bạn
        message = "Em chắc chắn sẽ tham gia ạ",
        time = "Hôm nay",
        isReplied = false
    ),
    ChatItem(
        id = "2",
        userName = "hoanggg.ng",
        userAvatar = R.drawable.img_product, // Thay bằng drawable của bạn
        message = "Mình xác nhận đơn cho bạn rồi nha",
        time = "Hôm nay",
        isReplied = true
    ),
    ChatItem(
        id = "3",
        userName = "hamimi",
        userAvatar = R.drawable.img_product, // Thay bằng drawable của bạn
        message = "Áo còn tất mới ạ, mình mới mặc tầm 2 l...",
        time = "Hôm qua",
        isReplied = false
    ),
    ChatItem(
        id = "4",
        userName = "quynhchi",
        userAvatar = R.drawable.img_product, // Thay bằng drawable của bạn
        message = "Hi bạn, món đó mình còn nhé, bạn cần t...",
        time = "Hôm qua",
        isReplied = false
    ),
    ChatItem(
        id = "5",
        userName = "luna.uni",
        userAvatar = R.drawable.img_product, // Thay bằng drawable của bạn
        message = "Mình nhận được đồ rồi nè, xinh lắm ạ, c...",
        time = "Hôm qua",
        isReplied = true
    ),
    ChatItem(
        id = "6",
        userName = "tungtran",
        userAvatar = R.drawable.img_product, // Thay bằng drawable của bạn
        message = "Túi còn ở bạn nữ, có hai trây nhé góc dưới á",
        time = "25/7",
        isReplied = false
    ),
    ChatItem(
        id = "7",
        userName = "phongluu",
        userAvatar = R.drawable.img_product, // Thay bằng drawable của bạn
        message = "Cảm ơn bạn đã ghé qua shop mình, mong c...",
        time = "20/7",
        isReplied = false
    ),
    ChatItem(
        id = "8",
        userName = "leehoang",
        userAvatar = R.drawable.img_product, // Thay bằng drawable của bạn
        message = "Mình nhận được đồ rồi, ny mình thích lắm, cảm...",
        time = "1/7",
        isReplied = false
    ),
    ChatItem(
        id = "9",
        userName = "samily",
        userAvatar = R.drawable.img_product, // Thay bằng drawable của bạn
        message = "Mình vừa xác nhận đơn cho bạn rồi nha, cảm...",
        time = "30/6",
        isReplied = true
    )
)