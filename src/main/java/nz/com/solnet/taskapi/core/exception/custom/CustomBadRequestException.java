package nz.com.solnet.taskapi.core.exception.custom;

import nz.com.solnet.taskapi.core.exception.base.BaseRuntimeException;


public class CustomBadRequestException extends BaseRuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomBadRequestException(String message) {
		super(message);
	}
	
}	