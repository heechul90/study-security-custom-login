package study.spring.security.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestInfo {

    private String remoteIp;
    private String sessionId;
    private LocalDateTime loginTime;
}
