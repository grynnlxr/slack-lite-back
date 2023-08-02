package slack.lite.entity;

import org.springframework.http.HttpStatus;

public class Response {
	private HttpStatus code;
	private Object content;

	public Response() {
	}

	public Response(HttpStatus code, Object content) {
		this.code = code;
		this.content = content;
	}

	public HttpStatus getCode() {
		return code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
}