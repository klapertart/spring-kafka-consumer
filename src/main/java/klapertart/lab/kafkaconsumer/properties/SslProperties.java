package klapertart.lab.kafkaconsumer.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author kurakuraninja
 * @since 21/02/23
 */

@Component
@ConfigurationProperties("ssl")
@Data
public class SslProperties {
    private boolean enabled;
    private JaasProperties jaas;
    private KeyProperties key;
    private TruststoreProperties truststore;
    private KeystoreProperties keystore;

    @Data
    public static class JaasProperties{
        private String username;
        private String password;
    }

    @Data
    public static class KeyProperties{
        private String password;
    }

    @Data
    public static class TruststoreProperties{
        private String location;
        private String password;
    }

    @Data
    public static class KeystoreProperties{
        private String location;
        private String password;
    }
}
