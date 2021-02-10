package eng.plane.server.error;

public class ErrorResponse {

	private String errorCode;
	private String errorDescription;
	private String errorDetails;

	public ErrorResponse() {
	}

	public ErrorResponse(String errorCode, String errorDescription, String errorDetails) {
		super();
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
		this.errorDetails = errorDetails;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public String getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}

}
