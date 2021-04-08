package cn.demo.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import cn.demo.entity.Member;
import cn.demo.utils.Convert;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDTO {
    private String phone;
    private String password;

    public Member convertToMember() {
        return new UserRegisterDTOConverter().doForward(this, Member.class);
    }

    private static class UserRegisterDTOConverter implements Convert<UserDTO, Member> {

        @Override
        public Member doForward(UserDTO userDTO, Class<Member> clazz) {
            var password = new BCryptPasswordEncoder().encode(userDTO.getPassword());
            var member = new Member();
            BeanUtils.copyProperties(userDTO, member);
            member.setPassword(password);
            return member;
        }
    }
}
