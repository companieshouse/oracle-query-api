package uk.gov.ch;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import uk.gov.ch.interceptor.LoggingInterceptor;

@SpringBootApplication
public class OracleQueryApplication implements WebMvcConfigurer {

    public static final String APPLICATION_NAME_SPACE = "oracle-query-api";

    private LoggingInterceptor loggingInterceptor;

    @Autowired
    public OracleQueryApplication(LoggingInterceptor loggingInterceptor) {
        this.loggingInterceptor = loggingInterceptor;
    }

    public static void main(String[] args) {
        SpringApplication.run(OracleQueryApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor);
    }
}
