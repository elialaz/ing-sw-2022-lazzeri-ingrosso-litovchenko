package it.polimi.ingsw.Server.Network;

/**
 * Enum class of message types
 * @author filibertoingrosso
 */
public enum TypeofMessage {
    LOGINREQUEST,
    LOGINRESPONSE,
    GAMEINFO,
    GAMERESULT,
    SETTOWER,
    NEWGAME,
    LOADGAME,
    MOVEMOTHERNATURE,
    GAMEBOARDUPDATE,
    ERROR,
    GENERIC,
    CHOOSECLOUDTILE,
    CHOOSECARD,
    ALREADYUSED,
}
