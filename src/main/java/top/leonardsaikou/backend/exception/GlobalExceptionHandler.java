package top.leonardsaikou.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataAccessException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        return ex.getMessage();
    }

    // 如果需要处理其他类型的异常，你可以在这里添加更多的处理方法
}
