package it.polimi.ingsw.Server.Network;

/**
 * Network message class to receive a generic response
 * @author filibertoingrosso, elia_laz
 */
public class GenericResponse extends Message{
    private String message;

    public GenericResponse( String message) {
        super(TypeofMessage.GENERIC);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "GenericResponse {" +
                "message: " + message + '\'' +
                '}';
    }
}
