package eng.airlines.server.error;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "status", "title" })
public class Response {

	private String title;
	private Integer status;
	private String detail;

	public Response() {
	}

	public Response(String title, Integer status, String detail) {
		super();
		this.title = title;
		this.status = status;
		this.detail = detail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "Response [title=" + title + ", status=" + status + ", detail=" + detail + "]";
	}

}
