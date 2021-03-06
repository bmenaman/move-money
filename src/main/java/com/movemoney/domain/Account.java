package com.movemoney.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.UUID;

public final class Account extends ReflectionEqualsHashCodeToString {

    private final Id id;
    private final String firstName;
    private final Amount ongoingBalance;

    private Account(Id id,
                    String firstName,
                    Amount ongoingBalance) {
        this.id = Objects.requireNonNull(id, "id");
        this.firstName = Objects.requireNonNull(firstName, "firstName");
        this.ongoingBalance = Objects.requireNonNull(ongoingBalance, "ongoingBalance");
    }

    @JsonCreator
    public static Account of(
            @JsonProperty("id") Id id,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("ongoingBalance") Amount ongoingBalance
    ) {
        return new Account(id, firstName, ongoingBalance);
    }


    public Id getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Amount getOngoingBalance() {
        return ongoingBalance;
    }

    public static class Id extends IdentifierUUID<BacsTransaction> {
        public Id() {
        }

        public static Account.Id autoGenerated() {
            return new Account.Id(UUID.randomUUID().toString());
        }

        @JsonCreator
        public Id(String stringEncodedUuid) {
            super(stringEncodedUuid);
        }
    }
}
