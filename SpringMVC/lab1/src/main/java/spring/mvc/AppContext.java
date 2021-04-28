package spring.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import spring.mvc.controllers.PdfViewResolver;

import java.util.ArrayList;
import java.util.List;


@Configuration
@ComponentScan("spring.mvc")
// @ImportResource("classpath:/beans.xml")
@PropertySource("classpath:application.properties")
public class AppContext
//        extends WebMvcConfigurerAdapter
{
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer
//                .defaultContentType(MediaType.APPLICATION_JSON)
//                .favorPathExtension(true);
//    }
//
//    /*
//     * Configure ContentNegotiatingViewResolver
//     */
//    @Bean
//    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
//        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
//        resolver.setContentNegotiationManager(manager);
//
//        // Define all possible view resolvers
//        List<ViewResolver> resolvers = new ArrayList<>();
//
//        resolvers.add(pdfViewResolver());
//
//        resolver.setViewResolvers(resolvers);
//        return resolver;
//    }
//
//    /*
//     * Configure View resolver to provide Pdf output using iText library to
//     * generate pdf output for an object content
//     */
//    @Bean
//    public ViewResolver pdfViewResolver() {
//        return new PdfViewResolver();
//    }


}