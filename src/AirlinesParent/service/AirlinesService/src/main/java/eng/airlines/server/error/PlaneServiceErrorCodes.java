package eng.airlines.server.error;

import org.springframework.http.HttpStatus;

public enum PlaneServiceErrorCodes {

	// general
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, " Internal server error", "Kérjük vegye fel a kapcsolatot a szupportal."),

	// airline service
	AIRLINE_UNKNOWN_DELETE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "Server error under delete airline.", "Kérjük vegye fel a kapcsolatot a szupportal."),
	AIRLINE_UNHNADLED_DELETE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "Server error under delete airline.", "Kérjük vegye fel a kapcsolatot a szupportal."),
	AIRLINE_REQUESTED_DELETE_OBJECT_NOT_EXIST(HttpStatus.BAD_REQUEST, 400, "Requested airline not exist, can not delete.", "A kért törlés nem történt meg, mivel a választott objekt nem létezik"),
	AIRLINE_REQUESTED_GET_OBJECT_NOT_EXIST(HttpStatus.BAD_REQUEST, 400, "Requested airline not exist, can not get it.", "A kért airline objekt nem létezik"),
	
	// city service
	CITY_UNKNOWN_DELETE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "Server error under delete city.", "Kérjük vegye fel a kapcsolatot a szupportal."),
	CITY_UNHNADLED_DELETE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "Server error under delete city.", "Kérjük vegye fel a kapcsolatot a szupportal."),
	CITY_REQUESTED_DELETE_OBJECT_NOT_EXIST(HttpStatus.BAD_REQUEST, 400, "Requested city not exist, can not delete.", "A kért törlés nem történt meg, mivel a választott objekt nem létezik"),
	CITY_REQUESTED_GET_OBJECT_NOT_EXIST(HttpStatus.BAD_REQUEST, 400, "Requested city not exist, can not get it.", "A kért city objekt nem létezik"),
	CITY_UPLOAD_ERROR_UNDER_READ_INPUT_FILE(HttpStatus.INTERNAL_SERVER_ERROR, 400, "Server error under read upload file.", "Kérjük vegye fel a kapcsolatot a szupportal."),
	CITY_UNHNADLED_UPLOAD_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "Server error under upload cities.", "Kérjük vegye fel a kapcsolatot a szupportal."),
	
	// flight service
	FLIGHT_UNKNOWN_DELETE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "Server error under delete flight.", "Kérjük vegye fel a kapcsolatot a szupportal."),
	FLIGHT_UNHNADLED_DELETE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "Server error under delete flight.", "Kérjük vegye fel a kapcsolatot a szupportal."),
	FLIGHT_REQUESTED_DELETE_OBJECT_NOT_EXIST(HttpStatus.BAD_REQUEST, 400, "Requested flight not exist, can not delete.", "A kért törlés nem történt meg, mivel a választott objekt nem létezik"),
	FLIGHT_REQUESTED_GET_OBJECT_NOT_EXIST(HttpStatus.BAD_REQUEST, 400, "Requested flight not exist, can not get it.", "A kért flight objekt nem létezik"),
	FLIGHT_UPLOAD_ERROR_UNDER_READ_INPUT_FILE(HttpStatus.INTERNAL_SERVER_ERROR, 400, "Server error under read upload file.", "Kérjük vegye fel a kapcsolatot a szupportal."),
	FLIGHT_UNHNADLED_UPLOAD_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "Server error under upload flights.", "Kérjük vegye fel a kapcsolatot a szupportal."),


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
