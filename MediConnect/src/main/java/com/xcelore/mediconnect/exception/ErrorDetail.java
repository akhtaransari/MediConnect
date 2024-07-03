package com.xcelore.mediconnect.exception;

import java.time.LocalDateTime;

public record ErrorDetail(String message, String description, LocalDateTime timeStamp) {
}