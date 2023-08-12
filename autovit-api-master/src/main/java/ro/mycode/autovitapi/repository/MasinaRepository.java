package ro.mycode.autovitapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.mycode.autovitapi.model.Masina;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
public interface MasinaRepository extends JpaRepository<Masina,Long> {


    @Query("select m from Masina m where m.culoare = ?1")
    Optional<List<Masina>> getAllCarsByColor(String color);

    @Query("select m from Masina m where m.model = ?1")
    Optional<Masina> findByModel(String model);


    @Transactional
    @Modifying
    @Query("update Masina m set m.culoare=?1 where m.model = ?2")
     void updateColor(String color,String model);
    @Transactional
    @Modifying
    @Query("update Masina m set m.marca=?1 where m.model = ?2")
     void updateMarca(String marca,String model);
    @Transactional
    @Modifying
    @Query("update Masina m set m.an=?1 where m.model = ?2")
     void updateAn(int an,String model);


    @Transactional
    @Modifying
    void removeMasinaByModel(String model);

    @Transactional
    @Modifying
    void removeById(Long id);




}
