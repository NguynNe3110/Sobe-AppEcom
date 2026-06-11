package com.uzuu.sobe.domain.model.init

import com.uzuu.sobe.feature.main.message.Message

val textChatList = listOf(
    Message(
        id = "1",
        content = "góp những món đồ còn sử dụng tốt",
        isSender = false, // Người khác gửi (bên trái)
        timestamp = 1718164200000L
    ),
    Message(
        id = "2",
        content = "Đặc biệt là đồ mùa đông như áo khoác, khăn, mũ, găng tay,...",
        isSender = false,
        timestamp = 1718164260000L
    ),
    Message(
        id = "3",
        content = "Tất cả sẽ được gửi tới các em nhỏ vùng cao có hoàn cảnh khó khăn.",
        isSender = false,
        timestamp = 1718164320000L
    ),
    Message(
        id = "4",
        content = "Nghe hay quá ạ, mình có thể góp đồ ở đâu nhỉ?",
        isSender = true, // Mình gửi (bên phải)
        timestamp = 1718164500000L
    ),
    Message(
        id = "5",
        content = "Với cá nhân nếu muốn góp sức bằng cách tình nguyện hỗ trợ chương trình thì có được không ad?",
        isSender = true,
        timestamp = 1718164620000L
    ),
    Message(
        id = "6",
        content = "Mình muốn tham gia ạ",
        isSender = true,
        timestamp = 1718164680000L
    ),
    Message(
        id = "7",
        content = "Tất nhiên rồi bạn ơi\nBạn mình có nhận cả đồ quyên góp lẫn đăng ký tình nguyện viên đó.",
        isSender = false,
        timestamp = 1718164800000L
    ),
    Message(
        id = "8",
        content = "Dong am cho en 2025.pdf",
        isSender = false,
        timestamp = 1718164860000L
    ),
    Message(
        id = "9",
        content = "Sobe gửi bạn thông tin chi tiết Chương trình và cách thức tham gia nha",
        isSender = false,
        timestamp = 1718164920000L
    )
)