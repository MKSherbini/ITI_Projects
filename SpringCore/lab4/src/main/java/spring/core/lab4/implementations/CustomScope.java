package spring.core.lab4.implementations;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.context.annotation.ScopeMetadataResolver;
import org.springframework.context.annotation.ScopedProxyMode;

public class CustomScope implements ScopeMetadataResolver {

    @Override
    public ScopeMetadata resolveScopeMetadata(BeanDefinition definition) {
        var scope = new ScopeMetadata();
        System.out.println("customscope: " + definition.getBeanClassName());
        if (definition.getBeanClassName().equalsIgnoreCase("spring.core.lab4.implementations.Service1Impl"))
            scope.setScopeName("prototype");
        else
            scope.setScopeName("singleton");
        return scope;
    }

}
