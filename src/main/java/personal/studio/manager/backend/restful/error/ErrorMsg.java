package personal.studio.manager.backend.restful.error;

public class ErrorMsg {

    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }


    public ErrorMsg() {
    }

    public ErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
