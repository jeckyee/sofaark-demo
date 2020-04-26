/*
 *  CMB Confidential
 *
 *  Copyright (C) 2020 China Merchants Bank Co., Ltd. All rights reserved.
 *
 *  No part of this file may be reproduced or transmitted in any form or by any
 *  means, electronic, mechanical, photocopying, recording, or otherwise, without
 *  prior written permission of China Merchants Bank Co., Ltd.
 */

package io.sofastack.stockmng.controller;

import com.alipay.sofa.ark.api.ArkClient;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import io.sofastack.dynamic.facade.StrategyService;
import io.sofastack.dynamic.model.CacheContainer;
import io.sofastack.stockmng.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Set;


@RestController
public class JarController {



    @SofaReference(uniqueId = "provider1")
    private StrategyService strategyService1;
    @SofaReference(uniqueId = "provider2")
    private StrategyService strategyService2;

    @Autowired
    private ProductService productService;
    @Autowired
    private CacheContainer cacheContainer;

    @GetMapping(value = "/jar/provider1")
    @ResponseBody
    public Object provider1(){
        return strategyService1.strategy(productService.initProducts(), cacheContainer);
    }

    @GetMapping(value = "/jar/provider2")
    @ResponseBody
    public Object provider2(){
        return strategyService2.strategy(productService.initProducts(), cacheContainer);
    }

    @GetMapping(value = "/jar/load")
    public boolean loadJar(@RequestParam(name = "path") String path){
        try {
            File file = new File(path);
            ArkClient.installBiz(file);
            return true;
        } catch (Throwable e){
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping(value = "/jar/list")
    @ResponseBody
    public Set<String> listBiz(){
        try {
            return ArkClient.getBizManagerService().getAllBizIdentities();
        } catch (Throwable e){
            e.printStackTrace();
            return null;
        }
    }


}
