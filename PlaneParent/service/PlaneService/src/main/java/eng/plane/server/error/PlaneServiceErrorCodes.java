package eng.plane.server.error;

import org.springframework.http.HttpStatus;

public enum PlaneServiceErrorCodes {

	INTERNAL_SERVER_ERROR(HttpStatus.BAD_REQUEST, 500, " Internal server error", "Kezeletlen hiba, kérjük vegye fel a kapcsolatot a szupportal."),

	;

	private HttpStatus httpStatus;
	private Integer code;
	private String description;
	private String details;

	private PlaneServiceErrorCodes(HttpStatus httpStatus, Integer code, String description, String details) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.description = description;
		this.details = details;
	}


	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
