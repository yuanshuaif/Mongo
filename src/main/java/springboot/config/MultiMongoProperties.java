package springboot.config;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/3/15.
 * 3.封装读取以 spring.mongodb 开头的两个配置文件
 */
@Component
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class MultiMongoProperties {
    private MongoProperties primary = new MongoProperties();
    private MongoProperties secondary = new MongoProperties();

    public MongoProperties getPrimary() {
        return primary;
    }

    public void setPrimary(MongoProperties primary) {
        this.primary = primary;
    }

    public MongoProperties getSecondary() {
        return secondary;
    }

    public void setSecondary(MongoProperties secondary) {
        this.secondary = secondary;
    }

    @Override
    public String toString() {
        return "MultiMongoProperties{" +
                "primary=" + primary +
                ", secondary=" + secondary +
                '}';
    }
}
