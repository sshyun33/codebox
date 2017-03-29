package modelmapper;

import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

public class ModelMappingTest {

  private ModelMapper modelMapper;
  private Order order;

  @Before
  public void setUp() throws Exception {
    // modelMapper는 Thread-Safety 하므로 재사용 가능하다.
    modelMapper = new ModelMapper();

    order = new Order(new Customer(new Name("Seo", "Seung-Hyun")),
        new Address("Street-01", "Seoul"));
  }

  /**
   * 주의:
   *  1) 객체간 상호변환을 위해서
   *     원본 및 대상 객체에 getter와 setter가 존재하여야 한다.
   */
  @Test
  public void testBasics() throws Exception {
    OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);

    assertThat(orderDTO.customerFirstName).isEqualTo("Seo");
    assertThat(orderDTO.customerLastName).isEqualTo("Seung-Hyun");
    assertThat(orderDTO.billingCity).isEqualTo("Seoul");
    assertThat(orderDTO.billingStreet).isEqualTo("Street-01");
  }

  @Test
  public void testModelValidation() throws Exception {
    modelMapper.createTypeMap(Order.class, OrderDTO.class);

    // 누락된 매핑이 있을 경우 ValidationException 예외 발생
    modelMapper.validate();
  }
}