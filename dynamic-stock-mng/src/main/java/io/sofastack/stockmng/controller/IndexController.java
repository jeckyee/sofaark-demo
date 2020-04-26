package io.sofastack.stockmng.controller;

import com.alipay.sofa.ark.api.ArkClient;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import io.sofastack.dynamic.facade.StrategyService;
import io.sofastack.dynamic.model.CacheContainer;
import io.sofastack.dynamic.model.ProductInfo;
import io.sofastack.stockmng.data.DatabaseSeed;
import io.sofastack.stockmng.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;

import java.util.ArrayList;
import java.util.List;

/**
 * index controller
 *
 * @author caojie.cj@antfin.com
 * @since 2020/2/11
 */
@Controller
public class IndexController {
    @SofaReference(uniqueId = "provider1")
    private StrategyService strategyService;
    @Autowired
    ProductService productService;

    @Autowired
    CacheContainer cacheContainer;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("productList", strategyService.strategy(productService.initProducts(), cacheContainer));
        return "index";
    }

}
