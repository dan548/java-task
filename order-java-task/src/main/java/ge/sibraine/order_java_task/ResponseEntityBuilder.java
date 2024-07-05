package ge.sibraine.order_java_task;

import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {
    public static ResponseEntity<Object> build(APIError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
