package com.digitalidentityapi.operators.constants;

public class Constants {
    public static String URL = "https://govcarpeta-21868b7e9dd3.herokuapp.com/apis";
    public static String EXISTCLIENT = "Ciudadano ya registrado en Operador";
    public static String NOEXISTCLIENT = "Ciudadano no registrado en Operador";

    public static String IDOPERATOR = "65fbaa97404d11001555ac4c";
    public static String OPERATORNAME = "Identidad Digital";
    public static String PATHVALIDATECITIZEN = "/validateCitizen/";
    public static String UNREGISTEREDCITIZEN = "/unregisterCitizen";

    public static String GETOPERATORS = "/getOperators";
    public static String REGISTERCITIZEN = "/registerCitizen";
    public static String NOTIFICATIONSQUEU = "notificationQueue";
    public static String REGISTERCITIZENQUEUE = "registerCitizen";
    public static String CITIZEN = "citizen";

    public static String UNREGISTERCITIZENQUEUE = "unregisterCitizen";
    public static String SIGNDOCUMENTSQUEUE = "signDocuments";
    public static String SIGNDOCUMENTSEXCHANGE = "signDocumentsExchange";
    public static final String ROUTING_KEY_PATTERN = "#";

    public static final String ERRORTRANSFERCITIZENOPERATOR = "Error de transferencia al usuario - Operador no disponible, se realiza registro de nuevo ante MinTic";
    public static final String SUCCESFULLTRANSFERCITIZENOPERATORS = "Envio de solicitud exitosa al operador ";

}
