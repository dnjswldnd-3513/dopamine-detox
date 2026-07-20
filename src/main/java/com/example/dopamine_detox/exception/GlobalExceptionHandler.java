package com.example.dopamine_detox.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
/// 모든 컨트롤러에서 예외가 발생했을때 한곳으로 처리하기 위함임.
// 없으면 각 예외마다 컨트롤러에서 try catch를 해야함
//전역적으로 다 담는거
public class GlobalExceptionHandler {
    // 왜 크게 3가지로 나누는지
    // --> 예외 종류마다 클라이언트에게 다른 상태코드를 줘야하기에
    //하나로 하나의 예외면 같은 상태코드로 나오기 때문임

    //어떻게 보면 다 이름만 바꾼 Runtimeexception인데
    //왜? throw new Runtimeexception("존재하지 않는 유저"):
    //왜? throw new Runtimeexception("이메일 중복"):
    // 이거면 globalhandler에서 구분이 안됨 어떤 코드를 줘야하는지 그래서 클래스로 구분을 하는거
    //404 409 등으로

    //404 --> 없는 데이터 , 없음 처리
    // 409 --> 중복 , 다른 이메일을 입력
    // 400 --> 잘못 보냈음. 입력값을 확인해라

    //왜 Map<String,object> 가 반환인지? --> key/value형태의 json 형태를 만드는거

    // 왜 ResponseEntity.status().body()인 이유
    //status()상태코드 지정
    // body()응답 본문 지정

//200은 성공
//200은 성공이고 201은 생성 성공
//400은 클라이언트 요청 오류 아마 이게 badrequest
//401은 인증 안됨
//403은 권한 X
//404은 없는 데이터야 EntityNotFound
//409은 중복된 데이터 아마 Duplication?
//500은 서버 오류

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleNotFound(EntityNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error",e.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String,Object>> handleBad(BadRequestException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",e.getMessage()));
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<Map<String,Object>> handleDuplication(DuplicateException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error",e.getMessage()));
    }
}
