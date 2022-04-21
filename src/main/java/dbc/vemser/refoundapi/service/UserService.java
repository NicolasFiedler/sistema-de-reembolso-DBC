package dbc.vemser.refoundapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dbc.vemser.refoundapi.dataTransfer.UserCreateDTO;
import dbc.vemser.refoundapi.dataTransfer.UserDTO;
import dbc.vemser.refoundapi.entity.UserEntity;
import dbc.vemser.refoundapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;


    public UserDTO save(UserCreateDTO userCreate, MultipartFile file) throws Exception{
        log.info("Chamada de método:: SAVE USER!");
        userCreate.setImage(file.getBytes());
        UserEntity userEntity = objectMapper.convertValue(userCreate, UserEntity.class);
        UserEntity userSaved = userRepository.save(userEntity);
        UserDTO userDTO = objectMapper.convertValue(userSaved, UserDTO.class);
        return userDTO;
    }

    public List<UserDTO> list(){
        log.info("Chamada de método:: LIST USER!");
        return userRepository.findAll().stream()
                .map(userEntity -> objectMapper.convertValue(userEntity, UserDTO.class))
                .collect(Collectors.toList());
    }


    public UserDTO update(Integer id, UserCreateDTO userAtt) throws Exception{
        log.info("Chamada de método:: LIST USER!");
        UserEntity userFound = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User not found!"));
        userFound.setImage(userAtt.getImage());
        userFound.setEmail(userAtt.getEmail());
        userFound.setName(userAtt.getName());
        userFound.setPassword(userAtt.getPassword());
        UserEntity userEntityAtt = userRepository.save(userFound);
        UserDTO userDTO = objectMapper.convertValue(userEntityAtt, UserDTO.class);
                return userDTO;
    }


    public UserDTO delete(Integer id)throws Exception{
        log.info("Chamada de método:: DELETE USER!");
        UserEntity userFound = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User not found!"));
        userRepository.delete(userFound);
        UserDTO userDTO = objectMapper.convertValue(userFound,UserDTO.class);
        return userDTO;
    }

    public List<UserDTO> findByNameContainingIgnoreCase(String name)throws Exception{
        return userRepository.findByNameContainingIgnoreCase(name).stream()
                .map(userEntity -> objectMapper.convertValue(userEntity,UserDTO.class))
                .collect(Collectors.toList());
    }
}
