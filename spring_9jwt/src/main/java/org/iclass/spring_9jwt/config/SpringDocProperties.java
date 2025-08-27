package org.iclass.spring_9jwt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "springdoc")
public class SpringDocProperties {

    private SwaggerUi swaggerUi = new SwaggerUi();
    private ApiDocs apiDocs = new ApiDocs();

    public SwaggerUi getSwaggerUi() {
        return swaggerUi;
    }

    public void setSwaggerUi(SwaggerUi swaggerUi) {
        this.swaggerUi = swaggerUi;
    }

    public ApiDocs getApiDocs() {
        return apiDocs;
    }

    public void setApiDocs(ApiDocs apiDocs) {
        this.apiDocs = apiDocs;
    }

    public static class SwaggerUi {
        private boolean enabled;
        private boolean tryItOutEnabled;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public boolean isTryItOutEnabled() {
            return tryItOutEnabled;
        }

        public void setTryItOutEnabled(boolean tryItOutEnabled) {
            this.tryItOutEnabled = tryItOutEnabled;
        }
    }

    public static class ApiDocs {
        private boolean enabled;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}

