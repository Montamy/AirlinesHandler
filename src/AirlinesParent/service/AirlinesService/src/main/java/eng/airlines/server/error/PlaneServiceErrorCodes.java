package eng.airlines.server.error;

import org.springframework.http.HttpStatus;

public enum PlaneServiceErrorCodes {

	// general
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, " Internal server error", "Kérjük vegye fel a kapcsolatot a szupportal."),

	// airline service
	AIRLINE_UNHNADLED_DELETE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "Server error under delete airline.", "Kérjük vegye fel a kapcsolatot a szupportal."),

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
