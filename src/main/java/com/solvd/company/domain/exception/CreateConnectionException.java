package com.solvd.company.domain.exception;

import java.sql.SQLException;

public class CreateConnectionException extends RuntimeException {

    public CreateConnectionException(String message, SQLException e) {
        super(message, e);
    }
}
