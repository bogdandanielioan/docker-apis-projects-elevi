//package ro.mycode.autovitapi.view;
//
//import lombok.extern.log4j.Log4j2;
//import org.springframework.stereotype.Component;
//import ro.mycode.autovitapi.exceptii.EmptyDatabaseMasiniException;
//import ro.mycode.autovitapi.exceptii.MasinaAlreadyExistsException;
//import ro.mycode.autovitapi.exceptii.MasinaDoesntExistException;
//import ro.mycode.autovitapi.model.Masina;
//import ro.mycode.autovitapi.service.MasinaService;
//
//import java.util.List;
//import java.util.Scanner;
//
//import static java.lang.System.exit;
//
//@Component
//public class View {
//
//    private MasinaService masinaService;
//
//    private Scanner scanner;
//
//
//    public View(MasinaService masinaService) {
//        this.masinaService = masinaService;
//        this.scanner = new Scanner(System.in);
//    }
//
//
//    //meniu
//
//    public void meniu() {
//        System.out.println("------------------------MENU--------------");
//        System.out.println("Apasa tasta 1 pentru a viziona toate masinile");
//        System.out.println("Apasa tasta 2 pentru a viziona toate masinile de culoare dorita");
//        System.out.println("Apasa tasta 3 pentru adauga o masina");
//        System.out.println("Apasa tasta 4 pentru a sterge o masina");
//        System.out.println("Apasa tasta 5 pentru a updata culoarea masini");
//        System.out.println("Apasa tasta 6 pentru a inchide meniu");
//        System.out.println("-------------------------------------------");
//
//    }
//
//    //play
//
//    public void play() {
//        boolean run = true;
//        int choose;
//        while (run) {
//            meniu();
//            choose = Integer.parseInt(scanner.nextLine());
//            switch (choose) {
//                case 1:
//                    showAllCars();
//                    break;
//                case 2:
//                    carsByColor();
//                    break;
//                case 3:
//                    addCar();
//                    break;
//                case 4:
//                    removeCar();
//                    break;
//                case 5:
//                    updateCar();
//                case 6:
//                    run=false;
//                    break;
//            }
//        }
//    }
//
//    public void showAllCars() {
//
//        try {
//            this.masinaService.getAllCars().forEach(System.out::println);
//        } catch (EmptyDatabaseMasiniException e) {
//
//
//            System.out.println("Nu avem  masini");
//        }
//
//    }
//
//    public void carsByColor() {
//        System.out.println("Culoarea dorita ? : ");
//        String culoare = scanner.nextLine();
//        try {
//            List<Masina> listOfMasini = masinaService.getAllCarsByColor(culoare);
//            for (Masina x : listOfMasini) {
//                System.out.println(x);
//            }
//        } catch (EmptyDatabaseMasiniException e) {
//            System.out.println("Nu exista masini de culoarea respectiva!");
//        }
//    }
//
//    public void addCar() {
//        System.out.println("Introduceti marca : ");
//        String marca = scanner.nextLine();
//        System.out.println("Introduceti modelul : ");
//        String model = scanner.nextLine();
//        System.out.println("Introduceti anul : ");
//        int anul = Integer.parseInt(scanner.nextLine());
//        System.out.println("Introduceti culoarea : ");
//        String culoare = scanner.nextLine();
//
//        try {
//            Masina m = Masina.builder().an(anul).culoare(culoare).model(model).marca(marca).build();
//            masinaService.add(m);
//        } catch (MasinaAlreadyExistsException e) {
//            System.out.println("Masina exista deja!!");
//        }
//    }
//
//    public void removeCar() {
//        System.out.println("Introduceti marca : ");
//        String marca = scanner.nextLine();
//
//        try {
//            masinaService.remove(marca);
//        } catch (MasinaDoesntExistException e) {
//            System.out.println("Nu exista aceasta masina !");
//        }
//    }
//
//    public void updateCar(){
//        System.out.println("Introduceti modelul masini :");
//        String model= scanner.nextLine();
//        System.out.println("Introduceti noua culoare : ");
//        String color= scanner.nextLine();
//
//        try{
//            masinaService.update(color,model);
//        }catch (MasinaDoesntExistException e){
//            System.out.println("Nu exista masina !");
//        }
//    }
//}
