package dbc.vemser.refoundapi.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import dbc.vemser.refoundapi.dataTransfer.item.ItemCreateDTO;
import dbc.vemser.refoundapi.dataTransfer.item.ItemUpdateDTO;
import dbc.vemser.refoundapi.entity.ItemEntity;
import dbc.vemser.refoundapi.entity.RefundEntity;
import dbc.vemser.refoundapi.exception.BusinessRuleException;
import dbc.vemser.refoundapi.repository.ItemRepository;
import dbc.vemser.refoundapi.repository.RefundRepository;
import dbc.vemser.refoundapi.service.ItemService;
import dbc.vemser.refoundapi.service.RefundService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    @InjectMocks
    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private RefundRepository refundRepository;

//TODO - teste de lista

    private static final Integer ID = 2;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void BeforeEach() {
        ReflectionTestUtils.setField(itemService, "objectMapper", objectMapper);
    }


    @Test
    public void deveTestarPegarItemPeloId() throws BusinessRuleException {
        when(itemRepository.findById(anyInt())).thenReturn(Optional.of(getMockItemEntity()));
        Assert.assertNotNull(itemService.getItemById(ID));
    }

    @Test(expected = RuntimeException.class)
    public void deveTestarExceptionNoUpdateSeIdNaoExistir() {
        when(itemRepository.findById(anyInt())).thenThrow(RuntimeException.class);
        itemService.update(ID, getMockItemUpdateDTO());
    }


    private static ItemCreateDTO getMockItemCeateDTO() {
        return ItemCreateDTO.builder()
                .image(new MockMultipartFile("sourceFile.tmp", "exemplo".getBytes()))
                .name("exempleName")
                .dateItem("30/04/2022")
                .value("10.0")
                .build();
    }

    private static ItemUpdateDTO getMockItemUpdateDTO() {
        return ItemUpdateDTO.builder()
                .image(new MockMultipartFile("sourceFile.tmp", "exemplo".getBytes()))
                .name("10.5")
                .dateItem("30/04/2022")
                .value("1")
                .build();
    }

    private static ItemEntity getMockItemEntity() {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setDate(LocalDate.now());
        itemEntity.setImage(new byte['1']);
        itemEntity.setIdItem(1);

        return itemEntity;
    }
}
