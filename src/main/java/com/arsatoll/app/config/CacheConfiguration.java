package com.arsatoll.app.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {

            cm.createCache(com.arsatoll.app.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.arsatoll.app.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.User.class.getName() + ".authorities", jcacheConfiguration);

            cm.createCache(com.arsatoll.app.domain.InsecteUtile.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.InsecteUtile.class.getName() + ".cultures", jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.InsecteRavageur.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.InsecteRavageur.class.getName() + ".ravageurs", jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.InsecteRavageur.class.getName() + ".cultures", jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.Maladie.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.Maladie.class.getName() + ".cultures", jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.Herbe.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.Herbe.class.getName() + ".cultures", jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.Culture.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.Culture.class.getName() + ".attaques", jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.Culture.class.getName() + ".cultureMaladies", jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.Culture.class.getName() + ".cultureInsecteUtiles", jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.Culture.class.getName() + ".culturesRavageurs", jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.Culture.class.getName() + ".cultureHerbes", jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.Attaque.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.MethodeLutte.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.ImageEnvoye.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.Agriculteur.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.Agriculteur.class.getName() + ".agriculteurs", jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.Chercheur.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.Chercheur.class.getName() + ".ajoutRavageurs", jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.Chercheur.class.getName() + ".ajoutInsUtiles", jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.Chercheur.class.getName() + ".ajoutAttaques", jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.Famille.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.Famille.class.getName() + ".familleRavageurs", jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.Famille.class.getName() + ".familleInseUtiles", jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.Ordre.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.Ordre.class.getName() + ".ordres", jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.TypeDegat.class.getName(), jcacheConfiguration);
            cm.createCache(com.arsatoll.app.domain.TypeDegat.class.getName() + ".degats", jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
