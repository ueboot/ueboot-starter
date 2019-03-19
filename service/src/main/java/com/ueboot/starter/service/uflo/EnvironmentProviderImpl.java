package com.ueboot.starter.service.uflo;

import com.bstek.uflo.env.EnvironmentProvider;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;

/**
 * @author yangkui
 * createTime:2019/3/1810:28 AM
 */
@Service
public class EnvironmentProviderImpl implements EnvironmentProvider {
    @Resource
    private SessionFactory sessionFactory;

    @Resource
    private PlatformTransactionManager platformTransactionManager;

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public PlatformTransactionManager getPlatformTransactionManager() {
        return platformTransactionManager;
    }

    @Override
    public String getLoginUser() {
        return null;
    }

    @Override
    public String getCategoryId() {
        return "anonymous";
    }
}
