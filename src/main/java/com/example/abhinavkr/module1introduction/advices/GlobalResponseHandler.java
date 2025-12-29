package com.example.abhinavkr.module1introduction.advices;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ApiResponse<?>) {
            return body;
        }

        return new ApiResponse<>(body);
    }
}


//http://localhost:8080/employees
// post
//{
//        "name": "abhui",
//        "email": "abhi",
//        "age": 2,
//        "dateOfJoining": "2025-08-18",
//        "isActive": true,
//        "role": "USER",
//        "salary": 5000.00
//        }

//output -
//{
//        "data": null,
//        "error": {
//        "message": "input validation failed",
//        "status": "400 BAD_REQUEST",
//        "subErrors": [
//        "Email should be valid",
//        "Age should not be less than 18"
//        ]
//        },
//        "timeStamp": "2025-12-30T00:23:53.6793876"
//        }

//-----
//
//{
//        "name": "abhui",
//        "email": "abhi@yugy.uih",
//        "age": 22,
//        "dateOfJoining": "2025-08-18",
//        "isActive": true,
//        "role": "USER",
//        "salary": 5000.00
//        }

//output

//{
//        "data": {
//        "id": 1,
//        "name": "abhui",
//        "email": "abhi@yugy.uih",
//        "age": 22,
//        "dateOfJoining": "2025-08-18",
//        "active": true,
//        "role": "USER",
//        "salary": 5000.0
//        },
//        "error": null,
//        "timeStamp": "2025-12-30T00:26:01.4936808"
//        }