package mate.academy.bookshop.service;

import lombok.RequiredArgsConstructor;
import mate.academy.bookshop.dto.UserRegistrationRequestDto;
import mate.academy.bookshop.dto.UserResponseDto;
import mate.academy.bookshop.exception.RegistrationException;
import mate.academy.bookshop.mapper.UserMapper;
import mate.academy.bookshop.model.User;
import mate.academy.bookshop.repository.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("The user with this email is already registered "
                    + requestDto.getEmail(), HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setShippingAddress(requestDto.getShippingAddress());
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
