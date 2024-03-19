package com.digitalidentityapi.brokerintermediary.constants;

public class Constants {
    // Queue names
    public static final String CITIZEN_QUEUE = "citizen";
    public static final String CITIZEN_SUBSCRIPTION_QUEUE = "citizenSubscription";
    public static final String DIGITAL_IDENTITY_SERVICES_QUEUE = "digitalIdentityServices";
    public static final String DOCUMENT_QUEUE = "document";
    public static final String EXTERNAL_COMPANY_QUEUE = "externalCompany";
    public static final String OFFERED_SOLUTION_QUEUE = "offeredSolution";
    public static final String TRANSFERS_QUEUE = "transfers";
    public static final String NOTIFICATION_QUEUE = "notificationQueue";
    public static final String REGISTER_CITIZEN_QUEUE = "registerCitizen";

    // Exchange names
    public static final String CITIZEN_EXCHANGE = "citizenExchange";
    public static final String CITIZEN_SUBSCRIPTION_EXCHANGE = "citizenSubscriptionExchange";
    public static final String DIGITAL_IDENTITY_SERVICES_EXCHANGE = "digitalIdentityServicesExchange";
    public static final String DOCUMENT_EXCHANGE = "documentExchange";
    public static final String EXTERNAL_COMPANY_EXCHANGE = "externalCompanyExchange";
    public static final String OFFERED_SOLUTION_EXCHANGE = "offeredSolutionExchange";
    public static final String TRANSFERS_EXCHANGE = "transfersExchange";

    // Routing key patterns
    public static final String ROUTING_KEY_PATTERN = "#";
}
