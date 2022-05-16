package it.polimi.ingsw.Network;

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
