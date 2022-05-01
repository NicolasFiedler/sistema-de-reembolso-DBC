package dbc.vemser.refoundapi.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import dbc.vemser.refoundapi.dataTransfer.refund.RefundCreateDTO;
import dbc.vemser.refoundapi.dataTransfer.refund.RefundUpdateDTO;
import dbc.vemser.refoundapi.entity.RefundEntity;
import dbc.vemser.refoundapi.entity.UserEntity;
import dbc.vemser.refoundapi.enums.Status;
import dbc.vemser.refoundapi.repository.RefundRepository;
import dbc.vemser.refoundapi.repository.UserRepository;
import dbc.vemser.refoundapi.service.EmailService;
import dbc.vemser.refoundapi.service.RefundService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RefundServiceTest {

    @InjectMocks
    private RefundService refundService;

    @Mock
    private RefundRepository refundRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailService emailService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void BeforeEach() {
        ReflectionTestUtils.setField(refundService, "objectMapper", objectMapper);
    }

    @Test
    public void deveTestarRefundCriadoComSucesso(){
        when(userRepository.getById(anyInt())).thenReturn(new UserEntity());
        when(refundRepository.save(any())).thenReturn(getMockRefundEntity());
        when(userRepository.findByRoleEntities_IdRole(3)).thenReturn(getMockUserList());

        Assert.assertNotNull(refundService.create(anyInt(),getMockRefundCreateDTO()));
    }

    @Test
    public void deveTestarSeAdicionaOValorDoItem(){
        when(refundRepository.save(any())).thenReturn(getMockRefundEntity());

         refundService.addItemValue(getMockRefundEntity(), 10.5);


    }

    private static RefundCreateDTO getMockRefundCreateDTO(){
        return RefundCreateDTO.builder()
                .title("TitleExemple")
                .build();
    }

    private static RefundUpdateDTO getMockRefundUpdateDTO(){
        return RefundUpdateDTO.builder()
                .title("TitleExemple")
                .status(0)
                .build();
    }

    private static RefundEntity getMockRefundEntity(){
        RefundEntity refundEntity = new RefundEntity();
        refundEntity.setDate(LocalDateTime.now());
        refundEntity.setValue(10.5);
        refundEntity.setTitle("TitleExemple");
        refundEntity.setStatus(Status.ABERTO);
        refundEntity.setIdRefund(2);
//        UserEntity userEntity = UserEntity.builder()
//                .idUser(3)
//                .build();
//        refundEntity.setUserEntity(userEntity);
//        Set<RefundEntity> setRefund = new HashSet<>();
//        setRefund.add(refundEntity);
//        userEntity.setRefundEntities(setRefund);
        return refundEntity;
    }

    private static List<UserEntity> getMockUserList() {
        ArrayList<UserEntity> userList = new ArrayList<>();
        return userList;
    }
}



