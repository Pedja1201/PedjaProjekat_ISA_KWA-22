package rs.ac.singidunum.app.dto;


import java.io.Serializable;
import java.time.LocalDateTime;

public class StudijskiProgramLogDTO implements Serializable {
    private String id;
    private String signature;
    private String message;
    private LocalDateTime dateTime;
    private String type;

    public StudijskiProgramLogDTO() {super();
    }

    public StudijskiProgramLogDTO(String id, String signature, String message, LocalDateTime dateTime, String type) {
        this.id = id;
        this.signature = signature;
        this.message = message;
        this.dateTime = dateTime;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
