package cl.tenpo.challenge.dto.response;

public class ExceptionResponse {
    private String errorMessage;
    private String path;

    public ExceptionResponse(String errorMessage, String requestedURI) {
        this.errorMessage = errorMessage;
        this.path = requestedURI;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
