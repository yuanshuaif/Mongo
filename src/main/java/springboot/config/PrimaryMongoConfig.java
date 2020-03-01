package springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by Administrator on 2019/3/15.
 */
@Configuration
//@EnableMongoRepositories(basePackages = "springboot.service.primary",
//        mongoTemplateRef = PrimaryMongoConfig.MONGO_TEMPLATE)
public class PrimaryMongoConfig {
    public final static String MONGO_TEMPLATE = "primaryMongoTemplate";
}
