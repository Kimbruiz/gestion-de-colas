package essalud.gob.pe.gestionatencionservice.common.base;

import essalud.gob.pe.gestionatencionservice.common.constants.ResponseType;
import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public abstract class BaseController {

    private static final Logger logger = LogManager.getLogger(BaseController.class);

    //@Value("${spring.logging.info}")
    private String logginInfo = "show";

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpEntity<?> unKnownException(Exception ex) {
        String errMsg = ex.getMessage();
        this.loggerException(errMsg, ex);
        return new ResponseEntity<>(errMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {HttpClientErrorException.class})
    public HttpEntity<?> clientException(HttpClientErrorException ex) {
        String errMsg = ex.getMessage();
        if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        } else {
            this.loggerException(errMsg, ex);
        }
        return new ResponseEntity<>(errMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /* resilience4j
    @ExceptionHandler(value = {RequestNotPermitted.class})
    public ResponseDto<?> resilience4jException(RequestNotPermitted ex) {
        return _buildResponseDto(ResponseType.ERROR, "Has superado el limite de solicitudes maximas.", ex);
    }*/

    @ExceptionHandler(value = {ServiceException.class})
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<?> serviceException(ServiceException ex) {
        return _buildResponseDto(ResponseType.VALIDATION, ex.getMessage(), ex);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<?> modelValidations(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
        return _buildResponseDto(ResponseType.VALIDATION, message, ex);
    }

    /*@ExceptionHandler(value = {InputValidationException.class})
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<?> handleInputValidationException(InputValidationException ex) {
        ErrorMessage errorMessage = ValidationUtilities.getErrorMessage(ex.getBindingResult(), ex.getCode());

        ResponseDto<?> response = ResponseDto.builder()
                .code(ResponseType.VALIDATION)
                .message(ex.getMessage())
                .data(errorMessage.getMessages())
                .build();

        return response;
    }*/

    private ResponseDto<?> _buildResponseDto(Integer code, String message, Exception ex) {
        ResponseDto<?> response = ResponseDto.builder()
                .codResult(code)
                .message(message)
                .build();

        return response;
    }

    public void loggerException(String title, Exception e) {
        logger.error(title, e);
    }

    public void loggerInfo(String title, String info) {
        if (logginInfo.contains("show"))
            logger.info(title + ":" + info);
    }

}
