package eg.gov.iti.jets.exceptions;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;

@XmlRootElement
@Data
@AllArgsConstructor
public class ErrorMessage {

    private String errorMessage;
    private int errorCode;
    private String errorDescription;

    // public ErrorMessage(String message, int i, String string) {
    // }
}