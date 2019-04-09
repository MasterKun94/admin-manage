package com.example.adminmanage.global.response;

import com.example.adminmanage.global.config.StatusCode;
import lombok.Getter;

@Getter
public class CommitResponse implements ResponseEntity {
    private String code;
    private String message;

    CommitResponse(String message) {
        if (message.equals(StatusCode.COMMIT_SUCCESS)) {
            this.code = StatusCode.COMMIT_SUCCESS;
        } else {
            this.code = StatusCode.COMMIT_FAIL;
            this.message = message;
        }
    }
}
