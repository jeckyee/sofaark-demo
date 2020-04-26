package io.sofastack.dynamic.provider.impl;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import io.sofastack.dynamic.facade.StrategyService;
import io.sofastack.dynamic.model.CacheContainer;
import io.sofastack.dynamic.model.ProductInfo;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * 对传入的商品列表进行排序实现类
 *
 * @author caojie.cj@antfin.com
 * @since 2020/2/11
 */
@Service
@SofaService(uniqueId = "provider2", bindings = {@SofaServiceBinding(serialize = false)})
public class StrategyService2Impl implements StrategyService {
    @Override
    public List<ProductInfo> strategy(List<ProductInfo> products, CacheContainer cacheContainer) {
        return cacheContainer.random(22);
//        products.sort(((o1, o2) -> o1.getOrderCount()-o2.getOrderCount()));
//        products.stream().forEach(p -> p.setName(p.getName()+"[=="+p.getOrderCount()+"==]"));
//        return products;
    }
}
