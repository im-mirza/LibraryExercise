import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Author a1 = new Author();
        a1.setName("Mark Twain");
        a1.setCountry("Amerika");
        a1.setBirthDate(LocalDate.parse("30/11/1835", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        entityManager.persist(a1);

        Category c1 = new Category();
        c1.setName("Roman");
        c1.setDescription("Genel Kurgu");
        entityManager.persist(c1);

        Publisher p1 = new Publisher();
        p1.setName("ABC Yayınları");
        p1.setAddress("Ankara");
        p1.setEstablishmentYear(2005);
        entityManager.persist(p1);

        Book b1 = new Book();
        b1.setName("Tom Sawyer");
        b1.setBookStock(15);
        b1.setPublicationYear(1884);
        b1.setPublisher(p1);
        b1.setAuthor(a1);
        entityManager.persist(b1);
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(c1);
        b1.setCategoryList(categoryList);

        BookBorrowing bb1 = new BookBorrowing();
        bb1.setName("Mehmet Yılmaz");
        bb1.setBorrowingDate(LocalDate.now());
        bb1.setReturnDate(LocalDate.parse("15/03/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        bb1.setBook(b1);
        entityManager.persist(bb1);

        Book b2 = new Book();
        b2.setName("Huckleberry Finn");
        b2.setBookStock(25);
        b2.setPublicationYear(1885);
        b2.setPublisher(p1);
        b2.setAuthor(a1);
        entityManager.persist(b2);
        b2.setCategoryList(categoryList);

        Book book = entityManager.find(Book.class, 1);
        System.out.println(book.toString());
        transaction.commit();
    }
}
