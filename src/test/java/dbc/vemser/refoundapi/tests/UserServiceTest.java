package dbc.vemser.refoundapi.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import dbc.vemser.refoundapi.dataTransfer.user.UserCreateDTO;
import dbc.vemser.refoundapi.dataTransfer.user.UserUpdateDTO;
import dbc.vemser.refoundapi.entity.RoleEntity;
import dbc.vemser.refoundapi.entity.UserEntity;
import dbc.vemser.refoundapi.exception.BusinessRuleException;
import dbc.vemser.refoundapi.repository.RoleRepository;
import dbc.vemser.refoundapi.repository.UserRepository;
import dbc.vemser.refoundapi.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String ROLE = "4";

    @Before
    public void BeforeEach() {
        ReflectionTestUtils.setField(userService, "objectMapper", objectMapper);
    }

    @Test
    public void deveTestarSeUsuarioNaoSeraCadastradoComEmailDuplicado() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new UserEntity()));

        BusinessRuleException exc = Assertions.assertThrows(BusinessRuleException.class,
                () -> userService.save(getMockUserCreateDTO(), ROLE));
        Assertions.assertEquals("Email already exists!", exc.getMessage());
    }

    @Test
    public void deveTestarSeNaoExistirEmailNoBancoUsuarioPodeSerCadastrado() throws Exception {
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(roleRepository.findById(anyInt())).thenReturn(Optional.of(new RoleEntity()));
        when(userRepository.save(any())).thenReturn(new UserEntity());

        Assert.assertNotNull(userService.save(getMockUserCreateDTO(), ROLE));
    }

    @Test
    public void deveTestarUpdateAdminComSucesso() throws BusinessRuleException {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(new UserEntity()));
        when(roleRepository.findById(anyInt())).thenReturn(Optional.of(new RoleEntity()));
        when(userRepository.save(any())).thenReturn(new UserEntity());

        Assert.assertNotNull(userService.updateAdmin("1", getMockUserCreateDTO(), ROLE));
    }

    @Test
    public void deveTestarUpdateComSucesso() throws Exception {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(new UserEntity()));
        when(userRepository.save(any())).thenReturn(new UserEntity());

        var userEntity = userService.update(2, getMockUserUpdateDTO());

        Assert.assertNotNull(userEntity);
    }


    private static UserCreateDTO getMockUserCreateDTO() {
        return UserCreateDTO.builder()
                .image(new MockMultipartFile("sourceFile.tmp", "Hello World".getBytes()))
                .email("exemplo@dbccompany.com.br")
                .password("exemploSenha")
                .name("exemploName")
                .build();
    }

    private static UserUpdateDTO getMockUserUpdateDTO() {
        return UserUpdateDTO.builder()
                .image(new MockMultipartFile("sourceFile.tmp", "Hello World".getBytes()))
                .password("exemploSenha")
                .build();
    }


}
