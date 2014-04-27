package service.impl;

import dao.DivisionDao;
import domain.Division;
import dto.DivisionDto;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;


public class DivisionServiceImplTest extends Assert {
    @Mock
    private DivisionDao divDao;
    @InjectMocks
    private DivisionServiceImpl service;

    @BeforeClass
    public void init() {
        MockitoAnnotations.initMocks(this);
        when(divDao.getById(anyLong())).thenReturn(new Division());
        when(divDao.getById(null)).thenThrow(NullPointerException.class);
        when(divDao.findAll()).thenReturn(anyListOf(Division.class));

    }

    @Test
    public void testAddNew() throws Exception {

        DivisionDto testDto = new DivisionDto(1L, "Blablabla");


        service.addNew(testDto);
        verify(divDao).store(any(Division.class));

    }

    @Test
    public void testUpdate() throws Exception {
        service.update(new DivisionDto(2L, "randomString"));

        verify(divDao).store(any(Division.class));


    }

    @Test
    public void testDeleteById() throws Exception {

        service.deleteById(1L);
        verify(divDao, times(1)).deleteById(anyLong());

    }

    @Test
    public void testGetById() throws Exception {
        service.getById(1);
        verify(divDao).getById(anyLong());

    }


    @Test
    public void testFindAllAsMap() throws Exception {
        service.findAllAsMap();
        verify(divDao, times(1)).findAll();


    }
}
