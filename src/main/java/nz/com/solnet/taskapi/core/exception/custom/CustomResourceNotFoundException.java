package nz.com.solnet.taskapi.core.exception.custom;

import nz.com.solnet.taskapi.core.exception.base.BaseRuntimeException;

public class CustomResourceNotFoundException extends BaseRuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomResourceNotFoundException(String message) {
		super(message);
	}
	
	
}	