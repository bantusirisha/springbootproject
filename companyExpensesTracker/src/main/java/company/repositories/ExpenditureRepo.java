package company.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import company.entities.Expenditure;

public interface ExpenditureRepo extends JpaRepository<Expenditure, Integer> {
	// 5
	@Query(value = "SELECT e FROM Expenditure e WHERE e.catcode = :catcode")
	List<Expenditure> findExpensesByCategory(@Param("catcode") String catcode, Pageable pageable);

	// 6
	@Query(value = "SELECT e FROM Expenditure e WHERE e.paymentmodecode = :paymentmodecode")
	List<Expenditure> findExpensesByPaymentMode(@Param("paymentmodecode") String paymentmodecode, Pageable pageable);

	// 7
	@Query("SELECT e FROM Expenditure e WHERE e.expdate BETWEEN :startDate AND :endDate")
	List<Expenditure> findExpensesBetweenDatesSortedByDate(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate, Pageable pageable);

	// 8
	@Query("SELECT e.catcode,SUM(e.amount) FROM Expenditure e WHERE MONTH(e.expdate)=:selectedMonth GROUP BY e.catcode")
	List<Object[]> getTotalExpensesByCategoryInMonth(@Param("selectedMonth") int SelectedMonth);

	// 9
	List<Expenditure> findByDeptcodeAndExpdateBetween(String deptcode, LocalDate startDate, LocalDate endDate);

	// 10
	List<Expenditure> findByAuthorizedby(String authorizedby);

	// 11
	List<Expenditure> findByDescriptionContaining(String searchString);

	// 12
	List<Expenditure> findByAmountBetween(Double min, Double max);

	// 14
	@Query("SELECT deptcode, SUM(amount) as total_amount FROM Expenditure GROUP BY deptcode")
	List<Object[]> getTotalAmountOfEachDepartment();

	// 15
	@Query("SELECT catcode, SUM(amount) as total_amount FROM Expenditure GROUP BY catcode")
	List<Object[]> getTotalAmountOfEachCategory();

	
	
	@Query("from Expenditure where catcode=:catCode")
	List<Expenditure> findByCategory(String catCode);

}