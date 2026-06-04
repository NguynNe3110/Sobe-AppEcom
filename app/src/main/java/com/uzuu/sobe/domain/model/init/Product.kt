package com.uzuu.sobe.domain.model.init

import com.uzuu.sobe.R
import com.uzuu.sobe.domain.model.ProductItem

val listProducts: List<ProductItem> = listOf(
    ProductItem(
        name = "Áo phao ấm siêu nhẹ màu kem",
        price = "150.000đ",
        image = R.drawable.img_product, // Đảm bảo file này có trong res/drawable
        rating = 4.5f
    ),
    ProductItem(
        name = "Quần gió đai chun dây rút",
        price = "80.000đ",
        image = R.drawable.img_product,
        rating = 4.8f
    )
)