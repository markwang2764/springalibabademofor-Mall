package cloud.gateway.admin.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * @program: SpringCloudAlibabaDemo
 * @description:
 * @author: Mr.markWang 2764
 * @create: 2023-10-20 13:19
 **/
@JsonComponent
public class GlobalJsonDeserializer {
    public static class StringDeserializer extends JsonDeserializer<String> {
        @Override
        public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return StringEscapeUtils.escapeHtml4(jsonParser.getValueAsString());
        }
    }
}
