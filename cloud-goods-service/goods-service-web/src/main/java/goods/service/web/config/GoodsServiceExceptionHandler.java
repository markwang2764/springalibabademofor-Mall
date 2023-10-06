package goods.service.web.config;

import com.example.springcloudalibabacommon.dto.Result;
import com.example.springcloudalibabacommon.exception.CloudException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


/**
 * @program: SpringCloudAlibabaDemo
 * @description: Exception Handler
 * @author: Mr.markWang 2764
 * @create: 2023-09-10 00:58
 **/
@RestControllerAdvice
public class GoodsServiceExceptionHandler {
    @ExceptionHandler(BindException.class)
    public Object bindException(BindException e) {
        Result<String> result = new Result<>();
        result.setResultCode(510);
        BindingResult bindingResult = e.getBindingResult();
        result.setMessage(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        return result;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object bindException(MethodArgumentNotValidException e) {
        Result<?> r = new Result<>();
        r.setResultCode(510);
        BindingResult bindingResult = e.getBindingResult();
        r.setMessage(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        return r;
    }

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e, HttpServletRequest request) {
        Result<?> r = new Result<>();
        r.setResultCode(500);
        if (e instanceof CloudException) {
            r.setMessage(e.getMessage());
            if (e.getMessage().equals("ADMIN_NOT_LOGIN_ERROR")
                    || e.getMessage().equals("ADMIN_TOKEN_EXPIRE_ERROR")
            ) {
                r.setResultCode(419);
            }
        } else {
            e.printStackTrace();
            r.setMessage("未知异常，请查看控制台日志并检查配置文件");
        }
        return r;
    }
}
