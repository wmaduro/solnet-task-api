package nz.com.solnet.taskapi.api.domain.entity;

public enum StatusEnum {
	PENDING("PENDING"), FINISHED("FINISHED");

	private String code;

	StatusEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}