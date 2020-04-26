package io.sofastack.stockmng.service;

import io.sofastack.dynamic.model.ProductInfo;
import io.sofastack.stockmng.data.DatabaseSeed;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangjiang on 2020/4/26.
 */
@Service
public class ProductService {

    /**
     * 初始化默认展示列表,为了实验效果，此处初始化的列表与实际列表是相反的，但是实际排序结果与现场购买订单直接挂钩
     *
     * @return
     */
    public List<ProductInfo> initProducts() {
        List<ProductInfo> products = new ArrayList<>(5);
        for (int i = 4; i >= 0; i--) {
            ProductInfo productInfo = new ProductInfo();
            productInfo.setName(DatabaseSeed.name[i]);
            productInfo.setOrderCount(DatabaseSeed.orderCount[i]);
            productInfo.setSrc(DatabaseSeed.imageUrls[i]);
            productInfo.setAuthor(DatabaseSeed.authors[i]);
            products.add(productInfo);
        }
        return products;
    }
}
