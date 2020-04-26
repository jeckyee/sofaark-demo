package io.sofastack.dynamic.model;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yangjiang on 2020/4/26.
 */
public class CacheContainer {
    private Map<String, ProductInfo> cache = new ConcurrentHashMap<>();

    @PostConstruct
    private void init(){
        for (int i = 10000; i >= 0; i--) {
            ProductInfo productInfo = new ProductInfo();
            productInfo.setName("产品_"+i);
            productInfo.setOrderCount(i);
            productInfo.setSrc("");
            productInfo.setAuthor("作者_"+i);
            cache.put(productInfo.getName(), productInfo);
        }
    }

    public List<ProductInfo> random(int size){
        List<ProductInfo> r = new ArrayList<>();

        for (int i = 0; i < size && i <= cache.size(); i++){
            r.add(cache.get("产品_"+i));
        }
        return r;
    }


}
