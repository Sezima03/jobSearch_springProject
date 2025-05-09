package kg.attractor.job_search_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class LocaleConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver clr = new CookieLocaleResolver();
        clr.setDefaultLocale(new Locale("ru"));
        clr.setDefaultLocale(new Locale("lang"));
        clr.setCookieMaxAge(30*24*60*60);
        return clr;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localChangeInterceptor());
    }

    private LocaleChangeInterceptor localChangeInterceptor(){
        var loc = new LocaleChangeInterceptor();
        loc.setParamName("lang");
        return loc;
    }
}
