package io.sofastack.dynamic.classloader;

import com.alipay.sofa.ark.api.ArkClient;
import com.alipay.sofa.ark.spi.model.Biz;
import com.alipay.sofa.ark.spi.service.classloader.ClassLoaderHook;
import com.alipay.sofa.ark.spi.service.classloader.ClassLoaderService;
import com.alipay.sofa.ark.spi.service.extension.Extension;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * Created by yangjiang on 2020/4/26.
 */

@Extension("biz-classloader-hook")
public class DelegateMasterBizClassloaderHook implements ClassLoaderHook<Biz> {
    @Override
    public Class<?> preFindClass(String name, ClassLoaderService classLoaderService, Biz biz) throws ClassNotFoundException {
        return null;
    }

    @Override
    public Class<?> postFindClass(String name, ClassLoaderService classLoaderService, Biz biz) throws ClassNotFoundException {
        // 按包名组织
        System.out.println("postFindClass=========="+name);
        if (name.startsWith("io.sofastack.dynamic.model")){
            System.out.println("*****************************"+name);
            ClassLoader masterBizClassLoader = ArkClient.getBizManagerService().getBizInOrder().get(0).getBizClassLoader();
            return masterBizClassLoader.loadClass(name);
        }
        return null;
    }

    @Override
    public URL preFindResource(String name, ClassLoaderService classLoaderService, Biz biz) {
        // 资源也要委托
        System.out.println("preFindResource=========="+name);
        if (name.startsWith("io/sofastack/dynamic/model")) {
            System.out.println("*****************************"+name);
            ClassLoader masterBizClassLoader = ArkClient.getBizManagerService().getBizInOrder().get(0).getBizClassLoader();
            try {
                return masterBizClassLoader.getResource(name);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public URL postFindResource(String name, ClassLoaderService classLoaderService, Biz biz) {
        return null;
    }

    @Override
    public Enumeration<URL> preFindResources(String name, ClassLoaderService classLoaderService, Biz biz) throws IOException {
        System.out.println("preFindResources=========="+name);
        if (name.startsWith("io/sofastack/dynamic/model")){
            System.out.println("*****************************"+name);
            ClassLoader masterBizClassLoader = ArkClient.getBizManagerService().getBizInOrder().get(0).getBizClassLoader();
            try {
                return masterBizClassLoader.getResources(name);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public Enumeration<URL> postFindResources(String name, ClassLoaderService classLoaderService, Biz biz) throws IOException {
        return null;
    }
}
