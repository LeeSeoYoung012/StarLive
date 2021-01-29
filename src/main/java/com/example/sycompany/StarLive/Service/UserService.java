package com.example.sycompany.StarLive.Service;

import com.example.sycompany.StarLive.DTO.UserDTO;
import com.example.sycompany.StarLive.Entity.User;
import com.example.sycompany.StarLive.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    @Transactional
    public Long update(Long id, UserDTO userDTO ){
        User user = userRepository.findById(id).orElseThrow(
                () -> new NullPointerException("회원정보가 존재 하지 않습니다")
        );
        user.update(userDTO);
        return id;
    }
}
