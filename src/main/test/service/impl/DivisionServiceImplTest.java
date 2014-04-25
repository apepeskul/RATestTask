package service.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dao.DivisionDao;
import domain.Division;
import dto.DivisionDto;

//@RunWith(MockitoJUnitRunner.class)

public class DivisionServiceImplTest {
  @Mock
  private DivisionDao divDao;
  @InjectMocks
  private DivisionServiceImpl service;

  @BeforeClass
  public void init() {
    MockitoAnnotations.initMocks(this);
    when(divDao.getById(anyLong())).thenReturn(new Division());
    when(divDao.getById(null)).thenThrow(NullPointerException.class);
    // when(divDao.delete(any(Division.class)))
  }

  @Test
  public void testAddNew() throws Exception {

    DivisionDto testDto = new DivisionDto(1L, "Blablabla");

    service.getById(1);
    verify(divDao).getById(anyLong());
    service.addNew(testDto);
    verify(divDao).store(any(Division.class));

  }

  @Test
  public void testUpdate() throws Exception {

  }

  @Test
  public void testDeleteById() throws Exception {

  }

  @Test
  public void testGetById() throws Exception {

  }

  @Test
  public void testFindAll() throws Exception {

  }

  @Test
  public void testFindAllAsMap() throws Exception {

  }
}
