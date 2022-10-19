package study.security.customlogin.core.domain.student;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

@Component
public class StudentManager implements AuthenticationProvider, InitializingBean {

    private HashMap<String, Student> studentDB = new HashMap<>();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        StudentAuthenticationToken token = (StudentAuthenticationToken) authentication;
        if (studentDB.containsKey(token.getCredentials())) {
            Student student = studentDB.get(token.getCredentials());
            return StudentAuthenticationToken.builder()
                    .principal(student)
                    .details(student.getUsername())
                    .authenticated(true)
                    .authorities(student.getRole())
                    .build();
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Set.of(
                new Student("kang", "강백호", Set.of(new SimpleGrantedAuthority("ROLE_STUDENT"))),
                new Student("chae", "채치수", Set.of(new SimpleGrantedAuthority("ROLE_STUDENT"))),
                new Student("seo", "서태웅", Set.of(new SimpleGrantedAuthority("ROLE_STUDENT"))),
                new Student("jeong", "정대만", Set.of(new SimpleGrantedAuthority("ROLE_STUDENT"))),
                new Student("song", "송태섭", Set.of(new SimpleGrantedAuthority("ROLE_STUDENT")))
        ).forEach(student -> {
            studentDB.put(student.getId(), student);
        });
    }
}
