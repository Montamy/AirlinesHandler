package eng.airlines.server.error;

import org.springframework.http.HttpStatus;

public class PlaneServiceException extends Exception {

	private HttpStatus httpStatus;
	private Integer errorCode;
	private String description;

	public PlaneServiceException(PlaneServiceErrorCodes planetServiceErrorCodes) {
		super(planetServiceErrorCodes.getDescription());
		this.httpStatus = planetServiceErrorCodes.getHttpStatus();
		this.errorCode = planetServiceErrorCodes.getCode();
		this.description = planetServiceErrorCodes.getDescription();
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getDescription() {
		return description;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
