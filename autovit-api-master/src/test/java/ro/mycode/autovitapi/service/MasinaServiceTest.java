package ro.mycode.autovitapi.service;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ro.mycode.autovitapi.AutovitApiApplication;
import ro.mycode.autovitapi.dto.MasinaDTO;
import ro.mycode.autovitapi.exceptii.EmptyDatabaseMasiniException;
import ro.mycode.autovitapi.exceptii.MasinaAlreadyExistsException;
import ro.mycode.autovitapi.exceptii.MasinaDoesntExistException;
import ro.mycode.autovitapi.model.Masina;
import ro.mycode.autovitapi.repository.MasinaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = AutovitApiApplication.class)
class MasinaServiceTest {
    @BeforeEach
    public void test() {

        masinaRepository.deleteAll();
    }

    @Mock
    private MasinaRepository masinaRepository;

    @InjectMocks
    private MasinaService masinaService;


    @Captor
    ArgumentCaptor<String> carFieldsArgumentCapture;


    @Test
    public void getAllCars() {
        Faker faker = new Faker();
        List<Masina> masini = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            masini.add(new Masina(faker.lorem().sentence(), faker.lorem().sentence(), faker.number().numberBetween(2015, 2022), "verde"));
        }

        masini.add(new Masina("MasinaSpeciala", "ModelSpecial", 2005, "rosu"));

        doReturn(masini).when(masinaRepository).findAll();


        assertEquals(masini, masinaService.getAllCars());

    }

    @Test
    public void getAllCarsException() {
        doReturn(new ArrayList<Masina>()).when(masinaRepository).findAll();

        assertThrows(EmptyDatabaseMasiniException.class, () -> {
            masinaService.getAllCars();
        });
    }


    @Test
    public void getAllCarsByColor() {
        Faker faker = new Faker();
        List<Masina> masini = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            masini.add(new Masina(faker.lorem().sentence(), faker.lorem().sentence(), faker.number().numberBetween(2015, 2022), "verde"));
        }
        Optional<List<Masina>> masinas = Optional.of(masini);
        doReturn(masinas).when(masinaRepository).getAllCarsByColor("verde");
        assertEquals(masinas.get().size(), masinaService.getAllCarsByColor("verde").get().size());
    }

    @Test
    public void getAllCarsByColorException() {
        doReturn(Optional.empty()).when(masinaRepository).getAllCarsByColor("verde");
        assertThrows(EmptyDatabaseMasiniException.class, () -> {
            masinaService.getAllCarsByColor("verde");
        });
    }

    @Test
    public void addTest() {
        Masina masina = new Masina("Mercedes", "B", 2005, "verde");
        Optional<Masina> masinaOptional = Optional.of(masina);
        masinaService.add(masina);
        doReturn(masinaOptional).when(masinaRepository).findByModel(masina.getModel());
        assertEquals("verde", masinaRepository.findByModel("B").get().getCuloare());
    }

    @Test
    public void addTestException() {
        Masina masina = new Masina("Mercedes", "B", 2005, "verde");
        Optional<Masina> masinaOptional = Optional.of(masina);
        doReturn(masinaOptional).when(masinaRepository).findByModel(masina.getModel());
        assertThrows(MasinaAlreadyExistsException.class, () -> {
            masinaService.add(masinaOptional.get());
        });
    }

    @Test
    public void removeTest() {
        Masina masina = new Masina("Mercedes", "B", 2005, "verde");
        Optional<Masina> masinaOptional = Optional.of(masina);
        doReturn(masinaOptional).when(masinaRepository).findByModel(masina.getModel());
        masinaService.removeByModel("B");
        verify(masinaRepository, times(1)).removeMasinaByModel(carFieldsArgumentCapture.capture());
        assertEquals("B", carFieldsArgumentCapture.getValue());
    }


    @Test
    public void removeTestException() {

        doReturn(Optional.empty()).when(masinaRepository).findByModel("de");

        assertThrows(MasinaDoesntExistException.class, () -> {
            masinaService.removeByModel("de");
        });


    }

    @Test
    public void updateTest() {
        MasinaDTO masinaDTO = new MasinaDTO("Mercedes", "S", 2005, "verde");
        Masina masina = new Masina("Mercedes", "S", 2005, "alb");
        Optional<Masina> masinas = Optional.of(masina);
        doReturn(masinas).when(masinaRepository).findByModel(masinaDTO.getModel());
        masinaService.update(masinaDTO);
        assertEquals("verde", masinaRepository.findByModel("S").get().getCuloare());
    }

    @Test
    public void updateTestExcepiton() {
        MasinaDTO masinaDTO = new MasinaDTO("Mercedes", "S", 2005, "verde");
        Masina masina = new Masina("Mercedes", "S", 2005, "alb");
        Optional<Masina> masinas = Optional.of(masina);
        doReturn(Optional.empty()).when(masinaRepository).findByModel(masinaDTO.getModel());
        assertThrows(MasinaDoesntExistException.class, () -> {
            masinaService.update(masinaDTO);
        });
    }

    @Test
    public void filterTest() {
        Faker faker = new Faker();
        List<Masina> masini = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            masini.add(new Masina(faker.lorem().sentence(), faker.lorem().sentence(), faker.number().numberBetween(2015, 2022), "verde"));
        }
        masini.add(new Masina("MasinaSpeciala", "ModelSpecial", 2005, "rosu"));
        MasinaDTO masinaDTO = new MasinaDTO("MasinaSpeciala", "ModelSpecial", 2005, "rosu");
        doReturn(masini).when(masinaRepository).findAll();
        assertEquals(1, masinaService.filter(masinaDTO).get().size());
    }


    @Test
    public void filterTestException() {
        MasinaDTO masinaDTO = new MasinaDTO("MasinaSpeciala", "ModelSpecial", 2005, "rosu");
        doReturn(new ArrayList<>()).when(masinaRepository).findAll();
        assertThrows(EmptyDatabaseMasiniException.class, () -> {
            masinaService.filter(masinaDTO);
        });
    }

    @Test
    public void filterTestException2() {
        Faker faker = new Faker();
        List<Masina> masini = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            masini.add(new Masina(faker.lorem().sentence(), faker.lorem().sentence(), faker.number().numberBetween(2015, 2022), "verde"));
        }
        masini.add(new Masina("MasinaSpeciala", "ModelSpecial", 2005, "rosu"));
        MasinaDTO masinaDTO = new MasinaDTO("Masina", "Model", 2005, "rosu");
        doReturn(masini).when(masinaRepository).findAll();
        assertThrows(MasinaDoesntExistException.class,()->masinaService.filter(masinaDTO));
    }

}