package com.empik.usersservice.http.interceptor;

import com.empik.usersservice.http.BufferingClientHttpResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Http interceptor for logging API communication
 *
 * @author Marcin Kowalczyk (marcinkowalczyk1992@gmail.com)
 */
@Component
public class LoggingRequestResponseInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingRequestResponseInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        interceptRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        return interceptResponse(response);
    }

    private void interceptRequest(HttpRequest request, byte[] body) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("############################~REQUEST~#################################");
            LOGGER.debug("Request URI: {}", request.getURI());
            LOGGER.debug("Request Method: {}", request.getMethod());
            LOGGER.debug("Request headers: {}", request.getHeaders());
            LOGGER.debug("Request body: {}", body);
            LOGGER.debug("######################################################################");
        }
    }

    private ClientHttpResponse interceptResponse(ClientHttpResponse response) {
        BufferingClientHttpResponseWrapper responseWrapper = new BufferingClientHttpResponseWrapper(response);
        if (LOGGER.isDebugEnabled()) {
            try {
                LOGGER.debug("############################~RESPONSE~#################################");
                LOGGER.debug("Response Status: {}", responseWrapper.getStatusText());
                LOGGER.debug("Response headers: {}", responseWrapper.getHeaders());
                LOGGER.debug("Response body: {}", getResponseBody(StreamUtils.copyToByteArray(responseWrapper.getBody())));
                LOGGER.debug("######################################################################");
            } catch (IOException e) {
                LOGGER.error("Exception occurred while intercepting http response, exception message:{}", e.getMessage());
            }
        }
        return responseWrapper;
    }

    private String getResponseBody(byte[] body) {
        return new String(body, StandardCharsets.UTF_8);
    }
}
