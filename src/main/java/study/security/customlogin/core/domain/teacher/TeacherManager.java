package study.security.customlogin.core.domain.teacher;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import study.security.customlogin.core.domain.student.StudentAuthenticationToken;

import java.util.HashMap;
import java.util.Set;

@Component
public class TeacherManager implements AuthenticationProvider, InitializingBean {

    private HashMap<String, Teacher> teacherDB = new HashMap<>();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TeacherAuthenticationToken token = (TeacherAuthenticationToken) authentication;
        if (teacherDB.containsKey(token.getCredentials())) {
            Teacher teacher = teacherDB.get(token.getCredentials());
            return TeacherAuthenticationToken.builder()
                    .principal(teacher)
                    .credentials(null)
                    .details(teacher.getUsername())
                    .authenticated(true)
                    .authorities(teacher.getRole())
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
                new Teacher("anh", "안감독", Set.of(new SimpleGrantedAuthority("ROLE_TEACHER"))),
                new Teacher("jang", "장한나", Set.of(new SimpleGrantedAuthority("ROLE_TEACHER")))
        ).forEach(teacher -> {
            teacherDB.put(teacher.getId(), teacher);
        });
    }
}
