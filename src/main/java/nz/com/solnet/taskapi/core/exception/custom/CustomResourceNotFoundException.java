package nz.com.solnet.taskapi.core.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import nz.com.solnet.taskapi.core.exception.base.BaseRuntimeException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomResourceNotFoundException extends BaseRuntimeException {

	public CustomResourceNotFoundException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 7448029918650113144L;
	
}	