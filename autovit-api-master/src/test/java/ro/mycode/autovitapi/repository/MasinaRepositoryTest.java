package ro.mycode.autovitapi.repository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.mycode.autovitapi.AutovitApiApplication;
import ro.mycode.autovitapi.model.Masina;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AutovitApiApplication.class)
class MasinaRepositoryTest {

        @Autowired
        MasinaRepository masinaRepository;

        @BeforeEach
        public void test(){

            masinaRepository.deleteAll();
        }

        @Test
        public void testGetAllCarsByColor(){
            Faker faker= new Faker();
            List<Masina> masini=new ArrayList<>();
            masinaRepository.saveAll(masini);
            assertEquals(0,masinaRepository.getAllCarsByColor("verde").get().size());
        }

        @Test
        public void testFindByModel(){
            Faker faker= new Faker();
            List<Masina> masini=new ArrayList<>();
            for(int i=0;i<10;i++){
                masini.add(new Masina(faker.lorem().sentence(),faker.lorem().sentence(),faker.number().numberBetween(2015,2022),"verde"));
            }

            masini.add(new Masina("MasinaSpeciala","ModelSpecial",2005,"rosu"));

            masinaRepository.saveAll(masini);

            assertEquals("rosu",masinaRepository.findByModel("ModelSpecial").get().getCuloare());
        }
        @Test
    public void updateCuloareMasinaTest(){
            Faker faker= new Faker();
            List<Masina> masini=new ArrayList<>();
            for(int i=0;i<10;i++){
                masini.add(new Masina(faker.lorem().sentence(),faker.lorem().sentence(),faker.number().numberBetween(2015,2022),"verde"));
            }

            masini.add(new Masina("MasinaSpeciala","ModelSpecial",2005,"rosu"));

            masinaRepository.saveAll(masini);
            masinaRepository.updateColor("galben","ModelSpecial");

            assertEquals("galben",masinaRepository.findByModel("ModelSpecial").get().getCuloare());
        }

    @Test
    public void updateAnMasinaTest(){
        Faker faker= new Faker();
        List<Masina> masini=new ArrayList<>();
        for(int i=0;i<10;i++){
            masini.add(new Masina(faker.lorem().sentence(),faker.lorem().sentence(),faker.number().numberBetween(2015,2022),"verde"));
        }

        masini.add(new Masina("MasinaSpeciala","ModelSpecial",2005,"rosu"));

        masinaRepository.saveAll(masini);
        masinaRepository.updateAn(2001,"ModelSpecial");

        assertEquals(2001,masinaRepository.findByModel("ModelSpecial").get().getAn());
    }

    @Test
    public void updateMarcaMasinaTest(){
        Faker faker= new Faker();
        List<Masina> masini=new ArrayList<>();
        for(int i=0;i<10;i++){
            masini.add(new Masina(faker.lorem().sentence(),faker.lorem().sentence(),faker.number().numberBetween(2015,2022),"verde"));
        }

        masini.add(new Masina("MasinaSpeciala","ModelSpecial",2005,"rosu"));

        masinaRepository.saveAll(masini);
        masinaRepository.updateMarca("Mercedes","ModelSpecial");

        assertEquals("Mercedes",masinaRepository.findByModel("ModelSpecial").get().getMarca());
    }

    @Test
    public void removeByModelMasinaTest(){
        Faker faker= new Faker();
        List<Masina> masini=new ArrayList<>();
        for(int i=0;i<10;i++){
            masini.add(new Masina(faker.lorem().sentence(),faker.lorem().sentence(),faker.number().numberBetween(2015,2022),"verde"));
        }

        masini.add(new Masina("MasinaSpeciala","ModelSpecial",2005,"rosu"));

        masinaRepository.saveAll(masini);
        masinaRepository.removeMasinaByModel("ModelSpecial");

        assertEquals(Optional.empty(),masinaRepository.findByModel("ModelSpecial"));
    }

}