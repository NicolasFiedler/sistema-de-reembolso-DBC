package dbc.vemser.refoundapi.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import dbc.vemser.refoundapi.dataTransfer.refund.RefundCreateDTO;
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
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

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
        Assert.assertNotNull(refundService.create(anyInt(),getMockRefundCreateDTO()));
    }

    @Test
    public void deveTestarSeAdicionaOValorDoItem(){
        when(refundRepository.save(any())).thenReturn(getMockRefundEntity());
        refundService.addItemValue(getMockRefundEntity(), 10.5);
         verify(refundRepository,times(1)).save(ArgumentMatchers.any());
    }

    @Test
    public void deveTestarSeRemoveOValorDoItem(){
        when(refundRepository.getById(anyInt())).thenReturn(getMockRefundEntity());
        when(refundRepository.save(any())).thenReturn(getMockRefundEntity());
        refundService.removeItemValue(anyInt(),10.5);
        verify(refundRepository,times(1)).save(ArgumentMatchers.any());
    }

    private static RefundCreateDTO getMockRefundCreateDTO(){
        return RefundCreateDTO.builder()
                .title("TitleExemple")
                .build();
    }

    private static RefundEntity getMockRefundEntity(){
        RefundEntity refundEntity = new RefundEntity();
        refundEntity.setDate(LocalDateTime.now());
        refundEntity.setValue(10.5);
        refundEntity.setTitle("TitleExemple");
        refundEntity.setStatus(Status.ABERTO);
        refundEntity.setIdRefund(2);
        return refundEntity;
    }
}



