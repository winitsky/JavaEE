package exceptions;

import java.sql.SQLException;

public class CustomException extends SQLException {
	private static final long serialVersionUID = 1L;

	public CustomException() {
		super();

	}

	public CustomException(String message, Throwable cause) {
		super(message, cause);

	}

	public CustomException(String message) {
		super(message);

	}

	public CustomException(Throwable cause) {
		super(cause);

	}
}
