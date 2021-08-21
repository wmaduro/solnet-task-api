package nz.com.solnet.taskapi.core.exception.base;

public class BaseRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -6131480145692860834L;

	public BaseRuntimeException() {
		super();
	}

	public BaseRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseRuntimeException(String message) {
		super(message);
	}

	public BaseRuntimeException(Throwable cause) {
		super(cause);
	}
}
