package pl.bal.konrad.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("pl.bal.konrad.aop")
@EnableAspectJAutoProxy
public class AppConfiguration {
}
